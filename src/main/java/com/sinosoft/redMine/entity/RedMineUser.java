package com.sinosoft.redMine.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * redMine相关用户信息表
 * @author pengju
 *
 */
public class RedMineUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//登录名
	private String login;
	
	//加密密码
	private String hashedPassword;
	
	//名
	private String firstname;
	
	//姓
	private String lastname;
	
	//主题
	private String admin;	
	
	//描述
	private Integer status;
	
	//最近登录时间
	private Timestamp lastLoginOn;
	
	//语言
	private String language;
	
	//不知道
	private Integer authSourceId;
	
	//创建时间
	private Timestamp createdOn;
	
	//修改时间
	private Timestamp updatedOn;	
	
	//类型
	private String type;
	
	//buzhidao
	private String identityUrl;
	
	//邮箱地址--不为空
	private String mailNotification;
	
	//不知道
	private String salt;
	
	//是否必须修改密码--不为空 默认0
	private Integer mustChangePasswd;
	
	//密码修改时间
	private Timestamp passwdChangedOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getLastLoginOn() {
		return lastLoginOn;
	}

	public void setLastLoginOn(Timestamp lastLoginOn) {
		this.lastLoginOn = lastLoginOn;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getAuthSourceId() {
		return authSourceId;
	}

	public void setAuthSourceId(Integer authSourceId) {
		this.authSourceId = authSourceId;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentityUrl() {
		return identityUrl;
	}

	public void setIdentityUrl(String identityUrl) {
		this.identityUrl = identityUrl;
	}

	public String getMailNotification() {
		return mailNotification;
	}

	public void setMailNotification(String mailNotification) {
		this.mailNotification = mailNotification;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Timestamp getPasswdChangedOn() {
		return passwdChangedOn;
	}

	public void setPasswdChangedOn(Timestamp passwdChangedOn) {
		this.passwdChangedOn = passwdChangedOn;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Integer getMustChangePasswd() {
		return mustChangePasswd;
	}

	public void setMustChangePasswd(Integer mustChangePasswd) {
		this.mustChangePasswd = mustChangePasswd;
	}
	
}
