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
import com.sinosoft.redMine.entity.RedMineUser;
import com.sinosoft.redMine.util.MysqlUtil;


/**
 * 查询user
 * @author pengju
 *
 */
public class BlRedMineUser {

	/**
	 * 按主键查询
	 * @param condition
	 * @return
	 */
	public RedMineUser queryByPrimary(int id){
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        RedMineUser dto = null;
        try {  
            stm= connection.createStatement();  
            String sql="select * from users where id = '"+id+"' ";  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	dto = new RedMineUser();
            	Integer userId = rs.getInt("id");
            	String login = rs.getString("login");
            	String hashedPassword = rs.getString("hashed_password");
            	String firstname = rs.getString("firstname");
            	String lastname = rs.getString("lastname");
            	String admin = rs.getString("admin");
            	Integer status = rs.getInt("status");
            	Timestamp lastLoginOn = rs.getTimestamp("last_login_on");
            	String language = rs.getString("language");
            	Integer authSourceId = rs.getInt("auth_source_id");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	String type = rs.getString("type");
            	String identityUrl = rs.getString("identity_url");
            	String mailNotification = rs.getString("mail_notification");
            	String salt = rs.getString("salt");
            	int mustChangePasswd = rs.getInt("must_change_passwd");
            	Timestamp passwdChangedOn = rs.getTimestamp("passwd_changed_on");
            	
            	dto.setAdmin(admin);
            	dto.setAuthSourceId(authSourceId);
            	dto.setCreatedOn(createdOn);
            	dto.setFirstname(firstname);
            	dto.setHashedPassword(hashedPassword);
            	dto.setId(userId);
            	dto.setIdentityUrl(identityUrl);
            	dto.setLanguage(language);
            	dto.setLastLoginOn(lastLoginOn);
            	dto.setLastname(lastname);
            	dto.setLogin(login);
            	dto.setMailNotification(mailNotification);
            	dto.setMustChangePasswd(mustChangePasswd);
            	dto.setPasswdChangedOn(passwdChangedOn);
            	dto.setSalt(salt);
            	dto.setStatus(status);
            	dto.setType(type);
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
	 * @returnconcat(lastname,'', firstname) = 
	 */
	public List<RedMineUser> queryByCondition(String condition){
		List<RedMineUser> mineIssues = new ArrayList<RedMineUser>();
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        try {  
            stm= connection.createStatement();  
            String sql="select * from users where 1=1 and " + condition;  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	RedMineUser dto = new RedMineUser();
            	Integer userId = rs.getInt("id");
            	String login = rs.getString("login");
            	String hashedPassword = rs.getString("hashed_password");
            	String firstname = rs.getString("firstname");
            	String lastname = rs.getString("lastname");
            	String admin = rs.getString("admin");
            	Integer status = rs.getInt("status");
            	Timestamp lastLoginOn = rs.getTimestamp("last_login_on");
            	String language = rs.getString("language");
            	Integer authSourceId = rs.getInt("auth_source_id");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	String type = rs.getString("type");
            	String identityUrl = rs.getString("identity_url");
            	String mailNotification = rs.getString("mail_notification");
            	String salt = rs.getString("salt");
            	int mustChangePasswd = rs.getInt("must_change_passwd");
            	Timestamp passwdChangedOn = rs.getTimestamp("passwd_changed_on");
            	
            	dto.setAdmin(admin);
            	dto.setAuthSourceId(authSourceId);
            	dto.setCreatedOn(createdOn);
            	dto.setFirstname(firstname);
            	dto.setHashedPassword(hashedPassword);
            	dto.setId(userId);
            	dto.setIdentityUrl(identityUrl);
            	dto.setLanguage(language);
            	dto.setLastLoginOn(lastLoginOn);
            	dto.setLastname(lastname);
            	dto.setLogin(login);
            	dto.setMailNotification(mailNotification);
            	dto.setMustChangePasswd(mustChangePasswd);
            	dto.setPasswdChangedOn(passwdChangedOn);
            	dto.setSalt(salt);
            	dto.setStatus(status);
            	dto.setType(type);
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
	
	/**
	 * 按条件查询
	 * @param condition
	 * @return
	 */
	public List<RedMineUser> queryByConcat(String concatName){
		List<RedMineUser> mineIssues = new ArrayList<RedMineUser>();
		Connection connection=MysqlUtil.getConnection();  
        Statement stm=null;  
        ResultSet rs=null;  
        try {  
            stm= connection.createStatement();  
            String sql="select * from users where concat(lastname,'', firstname) = '" + concatName+"' ";  
            rs=stm.executeQuery(sql);  
            while(rs.next()){
            	RedMineUser dto = new RedMineUser();
            	Integer userId = rs.getInt("id");
            	String login = rs.getString("login");
            	String hashedPassword = rs.getString("hashed_password");
            	String firstname = rs.getString("firstname");
            	String lastname = rs.getString("lastname");
            	String admin = rs.getString("admin");
            	Integer status = rs.getInt("status");
            	Timestamp lastLoginOn = rs.getTimestamp("last_login_on");
            	String language = rs.getString("language");
            	Integer authSourceId = rs.getInt("auth_source_id");
            	Timestamp createdOn = rs.getTimestamp("created_on");
            	Timestamp updatedOn = rs.getTimestamp("updated_on");
            	String type = rs.getString("type");
            	String identityUrl = rs.getString("identity_url");
            	String mailNotification = rs.getString("mail_notification");
            	String salt = rs.getString("salt");
            	int mustChangePasswd = rs.getInt("must_change_passwd");
            	Timestamp passwdChangedOn = rs.getTimestamp("passwd_changed_on");
            	
            	dto.setAdmin(admin);
            	dto.setAuthSourceId(authSourceId);
            	dto.setCreatedOn(createdOn);
            	dto.setFirstname(firstname);
            	dto.setHashedPassword(hashedPassword);
            	dto.setId(userId);
            	dto.setIdentityUrl(identityUrl);
            	dto.setLanguage(language);
            	dto.setLastLoginOn(lastLoginOn);
            	dto.setLastname(lastname);
            	dto.setLogin(login);
            	dto.setMailNotification(mailNotification);
            	dto.setMustChangePasswd(mustChangePasswd);
            	dto.setPasswdChangedOn(passwdChangedOn);
            	dto.setSalt(salt);
            	dto.setStatus(status);
            	dto.setType(type);
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
