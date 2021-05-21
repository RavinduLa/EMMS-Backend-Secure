package com.emms.archiveModels;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department_archive")
public class DepartmentArchive {
	
	@Id
	@Column(name = "archive_id")
	private int archiveId;
	@Column(name= "original_id")
	private int originalId;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="department_name")
	private String departmentName;
	@Column(name="status")
	private String status;
	
	public DepartmentArchive() {
		
	}
	public DepartmentArchive(int archiveId, int originalId, Date createdDate, String departmentName, String status) {
		super();
		this.archiveId = archiveId;
		this.originalId = originalId;
		this.createdDate = createdDate;
		this.departmentName = departmentName;
		this.status = status;
	}
	public int getArchiveId() {
		return archiveId;
	}
	public void setArchiveId(int archiveId) {
		this.archiveId = archiveId;
	}
	public int getOriginalId() {
		return originalId;
	}
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DepartmentArchive [archiveId=" + archiveId + ", originalId=" + originalId + ", createdDate="
				+ createdDate + ", departmentName=" + departmentName + ", status=" + status + "]";
	}
	
	
}
