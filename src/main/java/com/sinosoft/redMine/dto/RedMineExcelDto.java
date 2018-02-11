package com.sinosoft.redMine.dto;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * excel���������ģ��
 * @author pengju
 *
 */
@ExcelTarget("RedMineExcelDto")
public class RedMineExcelDto implements Serializable{

	private static final long serialVersionUID = 1L;

	/** ��־�� */
	@Excel(name = "��ʶ��" , needMerge = true)
	private String id;
	
	/** �������� */
	@Excel(name = "����" , needMerge = true)
	private String taskName;
	
	/** ��ټ��� */
	@Excel(name = "��ټ���" , needMerge = true)
	private String outLineLevel;
	
	/** ���� */
	@Excel(name = "����" , needMerge = true)
	private String workTime;
	
	/** ��ʼʱ�� */
	@Excel(name = "��ʼʱ��" , needMerge = true)
	private String beginDate;
	
	/** ���ʱ�� */
	@Excel(name = "���ʱ��" , needMerge = true)
	private String endDate;

	/** ��ע */
	@Excel(name = "��ע" , needMerge = true)
	private String reMark;
	
	/** ����� */
	@Excel(name = "�����" , needMerge = true)
	private String assignToId;
	
	/** Ŀ����Ŀ */
	@Excel(name = "Ŀ����Ŀ" , needMerge = true)
	private String targetProject;
	
	
	public String getReMark() {
		return reMark;
	}


	public void setReMark(String reMark) {
		this.reMark = reMark;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getOutLineLevel() {
		return outLineLevel;
	}


	public void setOutLineLevel(String outLineLevel) {
		this.outLineLevel = outLineLevel;
	}


	public String getWorkTime() {
		return workTime;
	}


	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}


	public String getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getAssignToId() {
		return assignToId;
	}


	public void setAssignToId(String assignToId) {
		this.assignToId = assignToId;
	}


	public String getTargetProject() {
		return targetProject;
	}


	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}
	
}
