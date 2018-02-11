package com.sinosoft.redMine.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * redMine�����¼������ͼ��ʵ��
 * @author pengju
 *
 */
public class RedMineIssue implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//����
	private Integer trackerId;
	
	//��Ŀ
	private Integer projectId;
	
	//����
	private String subject;	
	
	//����
	private String description;
	
	//���ʱ��
	private Date dueDate;
	
	//���
	private Integer categoryId;
	
	//״̬
	private Integer statusId;
	
	//ָ��
	private Integer assignedToId;
	
	//���ȼ�
	private Integer priorityId;	
	
	//�汾��
	private Integer fixedVersionId;
	
	//������
	private Integer authorId;
	
	//���汾
	private Integer lockVersion;
	
	//����ʱ��
	private Timestamp createdOn;
	
	//����ʱ��
	private Timestamp updatedOn;
	
	//��ʼʱ��
	private Date startDate;
	
	//��ɶ�
	private Integer doneRatio;
	
	//Ԥ�����ʱ��
	private Double estimatedHours;
	
	//���ڵ�Id
	private Integer parentId;
	
	//���ڵ�Id
	private Integer rootId;
	
	//��Id
	private Integer lft;
		
	//��Id
	private Integer rgt;
	
	//�Ƿ�˽�е�
	private Integer isPrivate;
	
	//�ر�ʱ��
	private Timestamp closedOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTrackerId() {
		return trackerId;
	}

	public void setTrackerId(Integer trackerId) {
		this.trackerId = trackerId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(Integer assignedToId) {
		this.assignedToId = assignedToId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getFixedVersionId() {
		return fixedVersionId;
	}

	public void setFixedVersionId(Integer fixedVersionId) {
		this.fixedVersionId = fixedVersionId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Integer lockVersion) {
		this.lockVersion = lockVersion;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(Integer doneRatio) {
		this.doneRatio = doneRatio;
	}

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
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

	public Integer getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Timestamp getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Timestamp closedOn) {
		this.closedOn = closedOn;
	}

	@Override
	public String toString() {
		return "RedMineIssue [id=" + id + ", trackerId=" + trackerId + ", projectId=" + projectId + ", subject="
				+ subject + ", description=" + description + ", dueDate=" + dueDate + ", categoryId=" + categoryId
				+ ", statusId=" + statusId + ", assignedToId=" + assignedToId + ", priorityId=" + priorityId
				+ ", fixedVersionId=" + fixedVersionId + ", authorId=" + authorId + ", lockVersion=" + lockVersion
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", startDate=" + startDate + ", doneRatio="
				+ doneRatio + ", estimatedHours=" + estimatedHours + ", parentId=" + parentId + ", rootId=" + rootId
				+ ", lft=" + lft + ", rgt=" + rgt + ", isPrivate=" + isPrivate + ", closedOn=" + closedOn + "]";
	}
	
}
