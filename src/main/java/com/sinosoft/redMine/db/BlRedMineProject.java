package com.sinosoft.redMine.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.sinosoft.redMine.entity.RedMineIssue;
import com.sinosoft.redMine.entity.RedMineProject;
import com.sinosoft.redMine.entity.RedMineUser;
import com.sinosoft.redMine.util.MysqlUtil;


/**
 * 查询Project
 * @author pengju
 *
 */
public class BlRedMineProject {

	/**
	 * 按主键查询
	 * @param condition
	 * @return
	 */
	public RedMineProject queryByPrimary(int id){
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        RedMineProject dto = null;
        try {  
            stm= connection.createStatement();  
            String sql="select * from projects where id = '"+id+"' ";  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	dto = new RedMineProject();
            	Integer projectId = rs.getInt("id");
            	String name = rs.getString("name");
            	String description = rs.getString("description");
            	String homepage = rs.getString("homepage");
            	Integer isPublic = rs.getInt("is_public");
            	Integer parentId = rs.getInt("parent_id");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	String identifier = rs.getString("identifier");
            	Integer status = rs.getInt("status");
            	Integer lft = rs.getInt("lft");
            	Integer rgt = rs.getInt("rgt");
            	Integer inheritMembers = rs.getInt("inherit_members");
            	Integer defaultVersionId = rs.getInt("default_version_id");
            	Integer defaultAssignedToId = rs.getInt("default_assigned_to_id");
            	
            	dto.setCreatedOn(createdOn);
            	dto.setDefaultAssignedToId(defaultAssignedToId);
            	dto.setDefaultVersionId(defaultVersionId);
            	dto.setDescription(description);
            	dto.setHomepage(homepage);
            	dto.setId(projectId);
            	dto.setIdentifier(identifier);
            	dto.setInheritMembers(inheritMembers);
            	dto.setIsPublic(isPublic);
            	dto.setLft(lft);
            	dto.setName(name);
            	dto.setParentId(parentId);
            	dto.setRgt(rgt);
            	dto.setStatus(status);
            	dto.setUpdatedOn(updatedOn);
            	
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally {
            MysqlUtil.release(connection, stm, rs);  
        }  
		return dto;
	}
	
	
	/**
	 * 按条件查询
	 * @param condition
	 * @return
	 */
	public List<RedMineProject> queryByCondition(String condition){
		List<RedMineProject> mineIssues = new ArrayList<RedMineProject>();
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        try {  
            stm= connection.createStatement();  
            String sql="select * from projects where 1=1 and " + condition;  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	RedMineProject dto = new RedMineProject();
            	Integer projectId = rs.getInt("id");
            	String name = rs.getString("name");
            	String description = rs.getString("description");
            	String homepage = rs.getString("homepage");
            	Integer isPublic = rs.getInt("is_public");
            	Integer parentId = rs.getInt("parent_id");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	String identifier = rs.getString("identifier");
            	Integer status = rs.getInt("status");
            	Integer lft = rs.getInt("lft");
            	Integer rgt = rs.getInt("rgt");
            	Integer inheritMembers = rs.getInt("inherit_members");
            	Integer defaultVersionId = rs.getInt("default_version_id");
            	Integer defaultAssignedToId = rs.getInt("default_assigned_to_id");
            	
            	dto.setCreatedOn(createdOn);
            	dto.setDefaultAssignedToId(defaultAssignedToId);
            	dto.setDefaultVersionId(defaultVersionId);
            	dto.setDescription(description);
            	dto.setHomepage(homepage);
            	dto.setId(projectId);
            	dto.setIdentifier(identifier);
            	dto.setInheritMembers(inheritMembers);
            	dto.setIsPublic(isPublic);
            	dto.setLft(lft);
            	dto.setName(name);
            	dto.setParentId(parentId);
            	dto.setRgt(rgt);
            	dto.setStatus(status);
            	dto.setUpdatedOn(updatedOn);
            	
            	mineIssues.add(dto);
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally {
            MysqlUtil.release(connection, stm, rs);  
        }  
		return mineIssues;
	}
	
	
}
