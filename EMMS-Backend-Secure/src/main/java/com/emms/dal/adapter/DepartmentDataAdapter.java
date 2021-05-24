package com.emms.dal.adapter;

import java.util.List;
import java.util.Optional;

import com.emms.inventoryModel.Department;

public interface DepartmentDataAdapter {
	
	public List<Department> getAll();
	public Department save(Department department);
	public Optional<Department> findbyId(int id);
	public String getNameByid(int id);
	public int removeDepartment(int id);

}
