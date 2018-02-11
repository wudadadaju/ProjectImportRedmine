package com.sinosoft.redMine.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.sinosoft.redMine.db.BlRedMineImport;
import com.sinosoft.redMine.db.BlRedMineProject;
import com.sinosoft.redMine.db.BlRedMineUser;
import com.sinosoft.redMine.dto.RedMineExcelDto;
import com.sinosoft.redMine.entity.RedMineIssue;
import com.sinosoft.redMine.entity.RedMineProject;
import com.sinosoft.redMine.entity.RedMineUser;
import com.sinosoft.redMine.util.ImportExcel;

/**
 * 导入RedMine业务逻辑
 * @author pengju
 *
 */
public class RedMineImportImpl {

	private int lftNodeId = 0;
	private int rgtNodeId = 0;
	
	private BlRedMineImport blRedMineImport;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
	
	
	/**
	 * 获取数据操作对象
	 * @return
	 */
	private BlRedMineImport getDBObj(){
		if(blRedMineImport==null){
			blRedMineImport = new BlRedMineImport();
		}
		return blRedMineImport;
	}
	
	/**
	 * 执行导入的方法
	 * 
	 * Logic:
	 * 		1、先编号、存表，将每一条数据的上级关系理清楚。
	 * 		2、根据层级关系，组织（更新）其树形结构必要数据。--->lft  rgt 
	 * 
	 */
	public void doImportIssue(String filePath){
		/**
		 * 建立10级大纲所对应的序号，若下一个循环中与其中某个相重的，将更新其序号
		 */
		HashMap<Integer, Integer> outLineMap = new HashMap<Integer, Integer>();
		
		try {
			List<RedMineExcelDto> redMineExcelDtos = ImportExcel.importParamsToList(filePath);
			//记录本地首条任务号，供下面使用
			int queryMaxId = 0;
			
			//获取到issue表查询对象
			BlRedMineImport redMineImport = getDBObj();
			//获取到解析excel的集合
			if(redMineExcelDtos!=null && redMineExcelDtos.size()>0){
				//查询到此表的最大可用序号
				queryMaxId = redMineImport.queryMaxId();
//				建立上一个等级变量，用于判断当前等级与上一等级是否一样
//				int lastLevel = 0;
				
				for (int i=0;i<redMineExcelDtos.size();i++) {
					RedMineExcelDto redMineExcelDto = redMineExcelDtos.get(i);
					redMineExcelDto.setId((queryMaxId+i)+"");
					//对入参进行处理，如父节点id、工期、日期转换、备注等
					//处理redMineExcelDto对象，转换为RedMineIssue对象
					RedMineIssue redMineIssue = switchLevelRelation(redMineExcelDto,outLineMap);
					redMineImport.insert(redMineIssue);
				}
			}
			
			String queryCondition = "id >= '"+ queryMaxId +"' and parent_id is null ";
			/**
			 * 保存完成之后，做更新操作
			 * 根据层级关系，为每一个任务号的lft  rgt赋值
			 */
			//树形序号
			int treeNodeId = queryMaxId;
			
			//查找到本次首条任务，查询到其所有的子任务
			List<RedMineIssue> RedMineIssues = redMineImport.queryByCondition(queryCondition);
			if(RedMineIssues!=null && RedMineIssues.size()>0){
				treeNodeId = querySon(redMineImport, treeNodeId, RedMineIssues);
			}
			
		} catch (Exception e) {
 			e.printStackTrace();
		}
		
	}

	private int querySon(BlRedMineImport redMineImport, int treeNodeId, List<RedMineIssue> RedMineIssues) {
		String queryCondition;
		Integer id = null;
		
		for (int i = 0; i < RedMineIssues.size(); i++) {
			id = RedMineIssues.get(i).getId();
//					RedMineIssues.get(i).setLft(treeNodeId);
			treeNodeId = treeNodeId + 1;
			//左节点大于0的时候说明，它不是第一次进入此方法，是在遍历其他的具有相同父类的节点
			if(lftNodeId>0){
				treeNodeId = lftNodeId + 1;
			}
			queryCondition = "parent_id = '"+id+"' ";
			List<RedMineIssue> RedMineIssueSon = redMineImport.queryByCondition(queryCondition);
			if(i==0){
				lftNodeId = treeNodeId;
				rgtNodeId = treeNodeId + 1;
			}else{
				rgtNodeId = rgtNodeId + 1;
				lftNodeId = rgtNodeId;
			}
			if(RedMineIssueSon!=null && RedMineIssueSon.size()>0){
				//这里做更新，递归更新子节点
				int diGuiNo=0;
				rgtNodeId = rgtNodeId + 1;
				RedMineIssues.get(i).setLft(lftNodeId);
				redMineImport.update(RedMineIssues.get(i), " and id = "+id );
				//递归处理子节点
				int sonId = querySon(redMineImport, treeNodeId, RedMineIssueSon);
				id = sonId;
				
				updateTreeNode(redMineImport, id,diGuiNo);
				
			}else{//如果没有子节点，可以设置此lft  以及rgt节点
				id = RedMineIssues.get(i).getId();
				RedMineIssues.get(i).setLft(lftNodeId);
				rgtNodeId = lftNodeId + 1;
				RedMineIssues.get(i).setRgt(rgtNodeId);
				//更新此节点
				redMineImport.update(RedMineIssues.get(i), " and id = "+id );
				//如果是此循环最后一个值时。将此循环共同的父任务记录下来，用于获取父任务的lft值
				if(i==RedMineIssues.size()-1){
					Integer parentId = RedMineIssues.get(i).getParentId();
					queryCondition = "parent_id = '"+parentId+"' ";
					List<RedMineIssue> endRedMainIssues = redMineImport.queryByCondition(queryCondition);
					if(endRedMainIssues!=null && endRedMainIssues.size()>0){
						rgtNodeId = endRedMainIssues.get(endRedMainIssues.size()-1).getRgt();
						
					}
					id = parentId;
				}
			}
		}
		return id;
	}

	private void updateTreeNode(BlRedMineImport redMineImport, Integer id,int diGuiNo) {
		RedMineIssue updateMineIssue = redMineImport.queryByPrimary(id);
		if(updateMineIssue!=null){
			diGuiNo++;
			rgtNodeId = rgtNodeId + diGuiNo;
			lftNodeId = rgtNodeId + diGuiNo;
			updateMineIssue.setRgt(rgtNodeId);
			//更新此节点
			redMineImport.update(updateMineIssue, " and id = "+id );
			
			//什么时候允许它继续级联往上更新右节点
			//当此节点的其他平级节点的rgt都有值（说明此级处理完成）、或者此级只有此一个节点时，允许往上走
			String queryCondition = "parent_id = '"+updateMineIssue.getParentId()+"' and rgt = '0'";
			List<RedMineIssue> endRedMainIssues = redMineImport.queryByCondition(queryCondition);
			if(endRedMainIssues==null || endRedMainIssues.size()==0){
				updateTreeNode(redMineImport,  updateMineIssue.getParentId(), diGuiNo);
			}
			
		}else{
			
		}
	}

	/**
	 * 封装返回对象，对入参进行处理，如父节点id、工期、日期转换、备注等
	 * @param redMineExcelDto
	 * @param outLineMap
	 * @param lastLevel
	 * @return
	 * @throws ParseException 
	 */
	private RedMineIssue switchLevelRelation(RedMineExcelDto redMineExcelDto,HashMap<Integer, Integer> outLineMap) throws ParseException {
		//返回对象
		RedMineIssue redMineIssue = new RedMineIssue();
		if(redMineExcelDto!=null){
			String outLineLevel = redMineExcelDto.getOutLineLevel();
			if(outLineLevel!=null && !"".equals(outLineLevel)){
				Integer nowLevel = Integer.valueOf(outLineLevel);
				//按照list集合遍历的，一定是按照1~N级标准来的，所以此处，以及后面的取上级都不会有问题
				outLineMap.put(nowLevel, Integer.valueOf(redMineExcelDto.getId()));
				if(nowLevel!=1){
					redMineIssue.setParentId(outLineMap.get(nowLevel-1));
				}
				redMineIssue.setId(Integer.valueOf(redMineExcelDto.getId()));
				redMineIssue.setDescription(redMineExcelDto.getReMark());
				//此处的workTime为 “15 Days” 格式的 
				String workTime = redMineExcelDto.getWorkTime();
				if(workTime!=null && !"".equals(workTime)){
					String[] strings = workTime.split(" ");
					if(strings.length>0){
						String days = strings[0];
						BigDecimal bd = new BigDecimal(days);
						BigDecimal huors = bd.multiply(new BigDecimal(24));
						redMineIssue.setEstimatedHours(huors.doubleValue());
					}
				}
				redMineIssue.setSubject(redMineExcelDto.getTaskName());
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
				java.util.Date parse = sdf.parse(redMineExcelDto.getBeginDate());
				Timestamp timestamp = Timestamp.valueOf(sdFormat.format(parse));
				redMineIssue.setCreatedOn(timestamp);
				redMineIssue.setUpdatedOn(timestamp);
				redMineIssue.setStartDate(sdf.parse(redMineExcelDto.getBeginDate()));
				redMineIssue.setDueDate(sdf.parse(redMineExcelDto.getEndDate()));
				
				/**
				 * 封装分配给XX以及目标项目
				 */
				String projectName = redMineExcelDto.getTargetProject();
				if(projectName!=null && !"".equals(projectName)){
					BlRedMineProject redMineProject = new BlRedMineProject();
					List<RedMineProject> queryByCondition = redMineProject.queryByCondition(" name = '"+projectName+"'");
					if(queryByCondition!=null && queryByCondition.size()>0){
						//查询到则给redMineIssue 中的projectID赋值，根据项目名称查询当只有一条记录
						redMineIssue.setProjectId(queryByCondition.get(0).getId());
					}
				}
				//封装分配给XX
				String assignToId = redMineExcelDto.getAssignToId();
				if(assignToId!=null && !"".equals(assignToId)){
					BlRedMineUser blRedMineUser = new BlRedMineUser();
					List<RedMineUser> queryByConcat = blRedMineUser.queryByConcat(assignToId);
					if(queryByConcat!=null && queryByConcat.size()>0){
						//若查询到，暂且当不会有重名的情况出现
						redMineIssue.setAssignedToId(queryByConcat.get(0).getId());
					}
				}
			}
		}
		return redMineIssue;
	}
	
}
