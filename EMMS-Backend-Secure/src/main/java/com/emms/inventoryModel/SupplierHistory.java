package com.emms.inventoryModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier_history")
public class SupplierHistory {
	
	@Id
	@Column(name = "logid")
	private int logId;
	@Column(name = "supplier_id")
	private int supplierId;
	@Column(name = "date")
	private Date date;
	@Column(name = "user")
	private String user;
	@Column(name = "old_value")
	private String oldValue;
	@Column(name="new_value")
	private String newValue;
	
	public SupplierHistory() {
		
	}
	public SupplierHistory(int logId, int supplierId, Date date, String user, String oldValue, String newValue) {
		super();
		this.logId = logId;
		this.supplierId = supplierId;
		this.date = date;
		this.user = user;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	@Override
	public String toString() {
		return "SupplierHistory [logId=" + logId + ", supplierId=" + supplierId + ", date=" + date + ", user=" + user
				+ ", oldValue=" + oldValue + ", newValue=" + newValue + "]";
	}
	
}
