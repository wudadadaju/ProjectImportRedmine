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
 * ����RedMineҵ���߼�
 * @author pengju
 *
 */
public class RedMineImportImpl {

	private int lftNodeId = 0;
	private int rgtNodeId = 0;
	
	private BlRedMineImport blRedMineImport;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
	
	
	/**
	 * ��ȡ���ݲ�������
	 * @return
	 */
	private BlRedMineImport getDBObj(){
		if(blRedMineImport==null){
			blRedMineImport = new BlRedMineImport();
		}
		return blRedMineImport;
	}
	
	/**
	 * ִ�е���ķ���
	 * 
	 * Logic:
	 * 		1���ȱ�š������ÿһ�����ݵ��ϼ���ϵ�������
	 * 		2�����ݲ㼶��ϵ����֯�����£������νṹ��Ҫ���ݡ�--->lft  rgt 
	 * 
	 */
	public void doImportIssue(String filePath){
		/**
		 * ����10���������Ӧ����ţ�����һ��ѭ����������ĳ�����صģ������������
		 */
		HashMap<Integer, Integer> outLineMap = new HashMap<Integer, Integer>();
		
		try {
			List<RedMineExcelDto> redMineExcelDtos = ImportExcel.importParamsToList(filePath);
			//��¼������������ţ�������ʹ��
			int queryMaxId = 0;
			
			//��ȡ��issue���ѯ����
			BlRedMineImport redMineImport = getDBObj();
			//��ȡ������excel�ļ���
			if(redMineExcelDtos!=null && redMineExcelDtos.size()>0){
				//��ѯ���˱�����������
				queryMaxId = redMineImport.queryMaxId();
//				������һ���ȼ������������жϵ�ǰ�ȼ�����һ�ȼ��Ƿ�һ��
//				int lastLevel = 0;
				
				for (int i=0;i<redMineExcelDtos.size();i++) {
					RedMineExcelDto redMineExcelDto = redMineExcelDtos.get(i);
					redMineExcelDto.setId((queryMaxId+i)+"");
					//����ν��д����縸�ڵ�id�����ڡ�����ת������ע��
					//����redMineExcelDto����ת��ΪRedMineIssue����
					RedMineIssue redMineIssue = switchLevelRelation(redMineExcelDto,outLineMap);
					redMineImport.insert(redMineIssue);
				}
			}
			
			String queryCondition = "id >= '"+ queryMaxId +"' and parent_id is null ";
			/**
			 * �������֮�������²���
			 * ���ݲ㼶��ϵ��Ϊÿһ������ŵ�lft  rgt��ֵ
			 */
			//�������
			int treeNodeId = queryMaxId;
			
			//���ҵ������������񣬲�ѯ�������е�������
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
			//��ڵ����0��ʱ��˵���������ǵ�һ�ν���˷��������ڱ��������ľ�����ͬ����Ľڵ�
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
				//���������£��ݹ�����ӽڵ�
				int diGuiNo=0;
				rgtNodeId = rgtNodeId + 1;
				RedMineIssues.get(i).setLft(lftNodeId);
				redMineImport.update(RedMineIssues.get(i), " and id = "+id );
				//�ݹ鴦���ӽڵ�
				int sonId = querySon(redMineImport, treeNodeId, RedMineIssueSon);
				id = sonId;
				
				updateTreeNode(redMineImport, id,diGuiNo);
				
			}else{//���û���ӽڵ㣬�������ô�lft  �Լ�rgt�ڵ�
				id = RedMineIssues.get(i).getId();
				RedMineIssues.get(i).setLft(lftNodeId);
				rgtNodeId = lftNodeId + 1;
				RedMineIssues.get(i).setRgt(rgtNodeId);
				//���´˽ڵ�
				redMineImport.update(RedMineIssues.get(i), " and id = "+id );
				//����Ǵ�ѭ�����һ��ֵʱ������ѭ����ͬ�ĸ������¼���������ڻ�ȡ�������lftֵ
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
			//���´˽ڵ�
			redMineImport.update(updateMineIssue, " and id = "+id );
			
			//ʲôʱ�������������������ϸ����ҽڵ�
			//���˽ڵ������ƽ���ڵ��rgt����ֵ��˵���˼�������ɣ������ߴ˼�ֻ�д�һ���ڵ�ʱ������������
			String queryCondition = "parent_id = '"+updateMineIssue.getParentId()+"' and rgt = '0'";
			List<RedMineIssue> endRedMainIssues = redMineImport.queryByCondition(queryCondition);
			if(endRedMainIssues==null || endRedMainIssues.size()==0){
				updateTreeNode(redMineImport,  updateMineIssue.getParentId(), diGuiNo);
			}
			
		}else{
			
		}
	}

	/**
	 * ��װ���ض��󣬶���ν��д����縸�ڵ�id�����ڡ�����ת������ע��
	 * @param redMineExcelDto
	 * @param outLineMap
	 * @param lastLevel
	 * @return
	 * @throws ParseException 
	 */
	private RedMineIssue switchLevelRelation(RedMineExcelDto redMineExcelDto,HashMap<Integer, Integer> outLineMap) throws ParseException {
		//���ض���
		RedMineIssue redMineIssue = new RedMineIssue();
		if(redMineExcelDto!=null){
			String outLineLevel = redMineExcelDto.getOutLineLevel();
			if(outLineLevel!=null && !"".equals(outLineLevel)){
				Integer nowLevel = Integer.valueOf(outLineLevel);
				//����list���ϱ����ģ�һ���ǰ���1~N����׼���ģ����Դ˴����Լ������ȡ�ϼ�������������
				outLineMap.put(nowLevel, Integer.valueOf(redMineExcelDto.getId()));
				if(nowLevel!=1){
					redMineIssue.setParentId(outLineMap.get(nowLevel-1));
				}
				redMineIssue.setId(Integer.valueOf(redMineExcelDto.getId()));
				redMineIssue.setDescription(redMineExcelDto.getReMark());
				//�˴���workTimeΪ ��15 Days�� ��ʽ�� 
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
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
				java.util.Date parse = sdf.parse(redMineExcelDto.getBeginDate());
				Timestamp timestamp = Timestamp.valueOf(sdFormat.format(parse));
				redMineIssue.setCreatedOn(timestamp);
				redMineIssue.setUpdatedOn(timestamp);
				redMineIssue.setStartDate(sdf.parse(redMineExcelDto.getBeginDate()));
				redMineIssue.setDueDate(sdf.parse(redMineExcelDto.getEndDate()));
				
				/**
				 * ��װ�����XX�Լ�Ŀ����Ŀ
				 */
				String projectName = redMineExcelDto.getTargetProject();
				if(projectName!=null && !"".equals(projectName)){
					BlRedMineProject redMineProject = new BlRedMineProject();
					List<RedMineProject> queryByCondition = redMineProject.queryByCondition(" name = '"+projectName+"'");
					if(queryByCondition!=null && queryByCondition.size()>0){
						//��ѯ�����redMineIssue �е�projectID��ֵ��������Ŀ���Ʋ�ѯ��ֻ��һ����¼
						redMineIssue.setProjectId(queryByCondition.get(0).getId());
					}
				}
				//��װ�����XX
				String assignToId = redMineExcelDto.getAssignToId();
				if(assignToId!=null && !"".equals(assignToId)){
					BlRedMineUser blRedMineUser = new BlRedMineUser();
					List<RedMineUser> queryByConcat = blRedMineUser.queryByConcat(assignToId);
					if(queryByConcat!=null && queryByConcat.size()>0){
						//����ѯ�������ҵ��������������������
						redMineIssue.setAssignedToId(queryByConcat.get(0).getId());
					}
				}
			}
		}
		return redMineIssue;
	}
	
}
