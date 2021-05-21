package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	
	Department findByDid(int id);

}
