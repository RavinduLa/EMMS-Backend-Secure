package com.emms.archiveModels;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="supplier_archive")
public class SupplierArchive {
	
	@Id
	@Column(name="archive_id")
	private int archiveId;
	@Column(name = "original_id")
	private int originalId;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	@Column(name="status")
	private String status;
	
	public SupplierArchive() {
		
	}
	public SupplierArchive(int originalId, Date createdDate, String supplierName, String phone, String email,
			String status) {
		super();
		this.originalId = originalId;
		this.createdDate = createdDate;
		this.supplierName = supplierName;
		this.phone = phone;
		this.email = email;
		this.status = status;
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
	public String getSupplieName() {
		return supplierName;
	}
	public void setSupplieName(String supplieName) {
		this.supplierName = supplieName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SupplierArchive [archiveId=" + archiveId + ", originalId=" + originalId + ", createdDate=" + createdDate
				+ ", supplierName=" + supplierName + ", phone=" + phone + ", email=" + email + ", status=" + status
				+ "]";
	}
	
}

