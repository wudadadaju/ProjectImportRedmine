package com.sinosoft.redMine.dto;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * excel导入的数据模板
 * @author pengju
 *
 */
@ExcelTarget("RedMineExcelDto")
public class RedMineExcelDto implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 标志号 */
	@Excel(name = "标识号" , needMerge = true)
	private String id;
	
	/** 任务名称 */
	@Excel(name = "名称" , needMerge = true)
	private String taskName;
	
	/** 大纲级别 */
	@Excel(name = "大纲级别" , needMerge = true)
	private String outLineLevel;
	
	/** 工期 */
	@Excel(name = "工期" , needMerge = true)
	private String workTime;
	
	/** 开始时间 */
	@Excel(name = "开始时间" , needMerge = true)
	private String beginDate;
	
	/** 完成时间 */
	@Excel(name = "完成时间" , needMerge = true)
	private String endDate;

	/** 备注 */
	@Excel(name = "备注" , needMerge = true)
	private String reMark;
	
	/** 分配给 */
	@Excel(name = "分配给" , needMerge = true)
	private String assignToId;
	
	/** 目标项目 */
	@Excel(name = "目标项目" , needMerge = true)
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
