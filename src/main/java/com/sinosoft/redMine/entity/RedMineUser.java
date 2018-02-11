package com.sinosoft.redMine.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * redMine����û���Ϣ��
 * @author pengju
 *
 */
public class RedMineUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//��¼��
	private String login;
	
	//��������
	private String hashedPassword;
	
	//��
	private String firstname;
	
	//��
	private String lastname;
	
	//����
	private String admin;	
	
	//����
	private Integer status;
	
	//�����¼ʱ��
	private Timestamp lastLoginOn;
	
	//����
	private String language;
	
	//��֪��
	private Integer authSourceId;
	
	//����ʱ��
	private Timestamp createdOn;
	
	//�޸�ʱ��
	private Timestamp updatedOn;	
	
	//����
	private String type;
	
	//buzhidao
	private String identityUrl;
	
	//�����ַ--��Ϊ��
	private String mailNotification;
	
	//��֪��
	private String salt;
	
	//�Ƿ�����޸�����--��Ϊ�� Ĭ��0
	private Integer mustChangePasswd;
	
	//�����޸�ʱ��
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
