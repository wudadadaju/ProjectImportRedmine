package com.sinosoft.redMine.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * redMine����û���Ϣ��
 * @author pengju
 *
 */
public class RedMineProject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//��Ŀ��
	private String name;
	
	//����
	private String description;
	
	//��ҳ
	private String homepage;
	
	//�Ƿ���
	private Integer isPublic;
	
	//����ĿId
	private Integer parentId;	
	
	//����ʱ��
	private Timestamp createdOn;
	
	//�޸�ʱ��
	private Timestamp updatedOn;
	
	//��־
	private String identifier;
	
	//״̬
	private Integer status;
	
	//��ڵ�
	private Integer lft;
	
	//�ҽڵ�
	private Integer rgt;	
	
	//��Ŀ��Ա������
	private Integer inheritMembers;
	
	//�汾�ţ�
	private Integer defaultVersionId;
	
	//Ĭ�� �ָ�˭��
	private Integer defaultAssignedToId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLft() {
		return lft;
	}

	public void setLft(Integer lft) {
		this.lft = lft;
	}

	public Integer getRgt() {
		return rgt;
	}

	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}

	public Integer getInheritMembers() {
		return inheritMembers;
	}

	public void setInheritMembers(Integer inheritMembers) {
		this.inheritMembers = inheritMembers;
	}

	public Integer getDefaultVersionId() {
		return defaultVersionId;
	}

	public void setDefaultVersionId(Integer defaultVersionId) {
		this.defaultVersionId = defaultVersionId;
	}

	public Integer getDefaultAssignedToId() {
		return defaultAssignedToId;
	}

	public void setDefaultAssignedToId(Integer defaultAssignedToId) {
		this.defaultAssignedToId = defaultAssignedToId;
	}

	
}
