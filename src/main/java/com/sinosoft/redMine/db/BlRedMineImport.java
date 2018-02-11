package com.sinosoft.redMine.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;
import com.sinosoft.redMine.entity.RedMineIssue;
import com.sinosoft.redMine.util.MysqlUtil;


/**
 * 导入数据存库
 * @author pengju
 *
 */
public class BlRedMineImport {
	
    private static Properties property;  
    static{  
        String url=MysqlUtil.class.getClassLoader().getResource("ImportTool.properties").getPath();  
        try {  
            property=new Properties();  
            property.load(new FileInputStream(url));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
	
	public RedMineIssue queryByPrimary(int id){
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        RedMineIssue dto = null;
        try {  
            stm= connection.createStatement();  
            String sql="select * from issues where id = '"+id+"' ";  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	dto = new RedMineIssue();
            	Integer issueId = rs.getInt("id");
            	Integer trackerId = rs.getInt("tracker_id");
            	Integer projectId = rs.getInt("project_id");
            	String subject = rs.getString("subject");
            	String description = rs.getString("description");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Date startDate = rs.getDate("start_date");
            	Integer lft = rs.getInt("lft");
            	Integer rgt = rs.getInt("rgt");
            	
            	
            	Date dueDate = rs.getDate("due_date");
            	int statusId = rs.getInt("status_id");
            	int assignedToId = rs.getInt("assigned_to_id");
            	int priorityId = rs.getInt("priority_id");
            	int fixedVersionId = rs.getInt("fixed_version_id");
            	int authorId = rs.getInt("author_id");
            	int lockVersion = rs.getInt("lock_version");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	int doneRatio = rs.getInt("done_ratio");
            	double estimatedHours = rs.getDouble("estimated_hours");
            	int parentId = rs.getInt("parent_id");
            	int rootId = rs.getInt("root_id");
            	int isPrivate = rs.getInt("is_private");
            	Timestamp closedOn = rs.getTimestamp("closed_on");
            	dto.setDueDate(dueDate);
            	dto.setStatusId(statusId);
            	dto.setAssignedToId(assignedToId);
            	dto.setPriorityId(priorityId);
            	dto.setFixedVersionId(fixedVersionId);
            	dto.setAuthorId(authorId);
            	dto.setLockVersion(lockVersion);
            	dto.setUpdatedOn(updatedOn);
            	dto.setDoneRatio(doneRatio);
            	dto.setEstimatedHours(estimatedHours);
            	dto.setParentId(parentId);
            	dto.setRootId(rootId);
            	dto.setIsPrivate(isPrivate);
            	dto.setClosedOn(closedOn);
            	
            	
            	dto.setId(issueId);
            	dto.setTrackerId(trackerId);
            	dto.setProjectId(projectId);
            	dto.setSubject(subject);
            	dto.setDescription(description);
            	dto.setCreatedOn(createdOn);
            	dto.setStartDate(startDate);
            	dto.setLft(lft);
            	dto.setRgt(rgt);
            	
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally {
            MysqlUtil.release(connection, stm, rs);  
        }  
		return dto;
	}
	
	/**
	 * 查询当前自增长最大序号
	 * @param id
	 * @return
	 */
	public int queryMaxId(){
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        int nextId = 0;
        try {  
            stm= connection.createStatement();  
            String sql="SELECT Auto_increment FROM information_schema.`TABLES` WHERE Table_Schema='bitnami_redmine' AND table_name = 'issues'";  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	nextId = rs.getInt("Auto_increment");
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally {
            MysqlUtil.release(connection, stm, rs);  
        }  
		return nextId;
	}
	
	/**
	 * 往数据库插入数据
	 * @param redMineIssue
	 */	
	public static int insert(RedMineIssue redMineIssue) {
		Connection connection=MysqlUtil.getConnection();  
	    int i = 0;
	    String sql = "insert into issues "
	    		+ "(id,tracker_id,project_id,subject,description,due_date,status_id,priority_id,author_id,lock_version,"
	    		+ "created_on,updated_on,start_date,done_ratio,estimated_hours,parent_id,root_id,lft,rgt,is_private,closed_on,assigned_to_id) "
	    		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        pstmt.setInt(1, redMineIssue.getId());
	        pstmt.setInt(2, property.getProperty("workType")==null?4:Integer.parseInt(property.getProperty("workType")));//trackId 任务类型，1位错误，4为需求-其他 14-项目管理
	        pstmt.setInt(3, redMineIssue.getProjectId());//哪个项目，写死
	        pstmt.setString(4, redMineIssue.getSubject());
	        pstmt.setString(5, redMineIssue.getDescription());
	        pstmt.setDate(6, new java.sql.Date(redMineIssue.getDueDate().getTime()));
	        pstmt.setInt(7, 1);
	        pstmt.setInt(8, 2);//priorityId
	        pstmt.setInt(9, 1);//authorId
	        pstmt.setInt(10, 1);//lockVersion
	        pstmt.setTimestamp(11, redMineIssue.getCreatedOn());
	        pstmt.setTimestamp(12, redMineIssue.getUpdatedOn());
	        pstmt.setDate(13, new java.sql.Date(redMineIssue.getStartDate().getTime()));
	        pstmt.setInt(14, redMineIssue.getDoneRatio()==null?0:redMineIssue.getDoneRatio());
	        pstmt.setDouble(15, redMineIssue.getEstimatedHours());
	        if(redMineIssue.getParentId()==null){
	        	pstmt.setNull(16,Types.INTEGER);
	        }else{
	        	pstmt.setInt(16, redMineIssue.getParentId());
	        }
	        pstmt.setInt(17, 1);//rootId
	        pstmt.setInt(18, redMineIssue.getLft()==null?0:redMineIssue.getLft());//lft
	        pstmt.setInt(19, redMineIssue.getRgt()==null?0:redMineIssue.getRgt());//rgt
	        pstmt.setInt(20, redMineIssue.getIsPrivate()==null?0:redMineIssue.getIsPrivate());
	        pstmt.setTimestamp(21, redMineIssue.getClosedOn());
	        pstmt.setInt(22, redMineIssue.getAssignedToId()==null?0:redMineIssue.getAssignedToId());
	        
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	/**
	 * 按条件查询
	 * @param condition
	 * @return
	 */
	public List<RedMineIssue> queryByCondition(String condition){
		List<RedMineIssue> mineIssues = new ArrayList<RedMineIssue>();
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        try {  
            stm= connection.createStatement();  
            String sql="select * from issues where 1=1 and " + condition;  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	RedMineIssue dto = new RedMineIssue();
            	Integer issueId = rs.getInt("id");
            	Integer trackerId = rs.getInt("tracker_id");
            	Integer projectId = rs.getInt("project_id");
            	String subject = rs.getString("subject");
            	String description = rs.getString("description");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Date startDate = rs.getDate("start_date");
            	Integer lft = rs.getInt("lft");
            	Integer rgt = rs.getInt("rgt");
            	
            	Date dueDate = rs.getDate("due_date");
            	int statusId = rs.getInt("status_id");
            	int assignedToId = rs.getInt("assigned_to_id");
            	int priorityId = rs.getInt("priority_id");
            	int fixedVersionId = rs.getInt("fixed_version_id");
            	int authorId = rs.getInt("author_id");
            	int lockVersion = rs.getInt("lock_version");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	int doneRatio = rs.getInt("done_ratio");
            	double estimatedHours = rs.getDouble("estimated_hours");
            	int parentId = rs.getInt("parent_id");
            	int rootId = rs.getInt("root_id");
            	int isPrivate = rs.getInt("is_private");
            	Timestamp closedOn = rs.getTimestamp("closed_on");
            	dto.setDueDate(dueDate);
            	dto.setStatusId(statusId);
            	dto.setAssignedToId(assignedToId);
            	dto.setPriorityId(priorityId);
            	dto.setFixedVersionId(fixedVersionId);
            	dto.setAuthorId(authorId);
            	dto.setLockVersion(lockVersion);
            	dto.setUpdatedOn(updatedOn);
            	dto.setDoneRatio(doneRatio);
            	dto.setEstimatedHours(estimatedHours);
            	dto.setParentId(parentId);
            	dto.setRootId(rootId);
            	dto.setIsPrivate(isPrivate);
            	dto.setClosedOn(closedOn);
            	
            	
            	dto.setId(issueId);
            	dto.setTrackerId(trackerId);
            	dto.setProjectId(projectId);
            	dto.setSubject(subject);
            	dto.setDescription(description);
            	dto.setCreatedOn(createdOn);
            	dto.setStartDate(startDate);
            	dto.setLft(lft);
            	dto.setRgt(rgt);
            	
            	mineIssues.add(dto);
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally {
            MysqlUtil.release(connection, stm, rs);  
        }  
		return mineIssues;
	}
	
	
	/**
	 * 更细数据
	 * @param redMineIssue
	 */	
	public static int update(RedMineIssue redMineIssue,String condition) {
		Connection connection=MysqlUtil.getConnection();  
	    int i = 0;
	    String sql = "update issues "
	    		+ "set id = ?,tracker_id = ?,project_id = ?,subject = ?,description = ?,due_date = ?,status_id = ?,priority_id = ?,author_id = ?,lock_version = ?,"
	    		+ "created_on = ?,updated_on = ?,start_date = ?,done_ratio = ?,estimated_hours = ?,parent_id = ?,root_id = ?,lft = ?,rgt = ?,is_private = ?,closed_on = ? "
	    		+ "where 1=1 " + condition;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        pstmt.setInt(1, redMineIssue.getId());
	        pstmt.setInt(2, 3);//trackId 任务类型，1位错误，3为支持
	        pstmt.setInt(3, 1);//哪个项目，写死
	        pstmt.setString(4, redMineIssue.getSubject());
	        pstmt.setString(5, redMineIssue.getDescription());
	        pstmt.setDate(6, new java.sql.Date(redMineIssue.getDueDate().getTime()));
	        pstmt.setInt(7, 1);
	        pstmt.setInt(8, 2);//priorityId
	        pstmt.setInt(9, 1);//authorId
	        pstmt.setInt(10, 1);//lockVersion
	        pstmt.setTimestamp(11, redMineIssue.getCreatedOn());
	        pstmt.setTimestamp(12, redMineIssue.getUpdatedOn());
	        pstmt.setDate(13, new java.sql.Date(redMineIssue.getStartDate().getTime()));
	        pstmt.setInt(14, redMineIssue.getDoneRatio()==null?0:redMineIssue.getDoneRatio());
	        pstmt.setDouble(15, redMineIssue.getEstimatedHours());
	        if(redMineIssue.getParentId()==null){
	        	pstmt.setNull(16,Types.INTEGER);
	        }else{
	        	pstmt.setInt(16, redMineIssue.getParentId());
	        }
	        pstmt.setInt(17, 1);//rootId
	        pstmt.setInt(18, redMineIssue.getLft()==null?0:redMineIssue.getLft());//lft
	        pstmt.setInt(19, redMineIssue.getRgt()==null?0:redMineIssue.getRgt());//rgt
	        pstmt.setInt(20, redMineIssue.getIsPrivate()==null?0:redMineIssue.getIsPrivate());
	        pstmt.setTimestamp(21, redMineIssue.getClosedOn());
	        
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	
}
