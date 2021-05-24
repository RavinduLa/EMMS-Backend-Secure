package com.emms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.DepartmentApi;
import com.emms.inventoryModel.Department;

@RestController
@RequestMapping("api/department/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class DepartmentController {
	
	private DepartmentApi departmentApi;
	
	@Autowired
	public DepartmentController(DepartmentApi departmentApi) {
		this.departmentApi = departmentApi;
	}
	
	@GetMapping(value="allDepartments")
	public List<Department> getAllDepartments ()
	{
		return departmentApi.getAllDepartments();
	}
	
	@PostMapping("addDepartment")
	public synchronized Department addDepartment(Department department) {
		return departmentApi.addDepartment(department);
	}
	
	@GetMapping(value="getIdAvailability/{did}")
	public synchronized boolean getAvailability( @PathVariable  int did) {
		return departmentApi.getAvailability(did);
	}
	
	@DeleteMapping(value= "deleteDepartment/{did}")
	public synchronized int deleteDepartment (@PathVariable  int  did) {
		 return departmentApi.deleteDepartment(did);
	}
	
	@GetMapping(value = "getDepartmentNameById/{did}")
	public String getDepartmntNameById( @PathVariable int did) {
		return departmentApi.getDepartmentNameById(did);
	}
}
