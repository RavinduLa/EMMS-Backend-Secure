package com.emms.inventoryModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	
	@Id
	@Column(name= "did")
	private int did;
	
	@Column(name= "department_name")
	private String departmentName;
	
	@Column(name= "status")
	private String status;
	
	public Department() {
		
	}

	

	public Department(int did, String departmentName, String status) {
		super();
		this.did = did;
		this.departmentName = departmentName;
		this.status = status;
	}



	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
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
		return "Department [did=" + did + ", departmentName=" + departmentName + ", status=" + status + "]";
	}
	
	

}