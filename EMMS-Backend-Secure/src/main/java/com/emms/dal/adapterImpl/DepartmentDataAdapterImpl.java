package com.emms.dal.adapterImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.archiveDao.DepartmentArchiveRepository;
import com.emms.archiveModels.DepartmentArchive;
import com.emms.dal.adapter.DepartmentDataAdapter;
import com.emms.inventoryDao.DepartmentRepo;
import com.emms.inventoryModel.Department;

@Component
public class DepartmentDataAdapterImpl implements DepartmentDataAdapter {
	
	private DepartmentRepo departmentRepo;
	private DepartmentArchiveRepository departmentArchiveRepository;
	
	@Autowired
	public DepartmentDataAdapterImpl(DepartmentRepo departmentRepo, DepartmentArchiveRepository departmentArchiveRepository) {
		
		this.departmentRepo = departmentRepo;
		this.departmentArchiveRepository = departmentArchiveRepository;
	}
	
	

	@Override
	public List<Department> getAll() {
		List<Department> departmentList =    departmentRepo.findAll();
		System.out.println("Returning department list : " + departmentList);
		return departmentList;
	}

	@Override
	public Department save(Department department) {
		
		department.setStatus("active");
		Department dep = departmentRepo.save(department);
		
		DepartmentArchive da = new DepartmentArchive();
		da.setOriginalId(dep.getDid());
		da.setDepartmentName(dep.getDepartmentName());
		
		Date date = new Date();
		da.setCreatedDate(date);
		da.setStatus("active");
		
		DepartmentArchive daSaved = new DepartmentArchive();
		
		System.out.println("Saved department : " + dep.toString());
		System.out.println("Saved department archive: " + daSaved.toString()  );
		return dep;
	}

	@Override
	public Optional<Department> findbyId(int id) {
		System.out.println("Returning department for id : " + id);
		return departmentRepo.findById(id);
	}

	@Override
	public String getNameByid(int id) {
		Department department = new Department();
		department = findbyId(id).get();
		return department.getDepartmentName();
	}

	@Override
	public int removeDepartment(int id) {
		
		System.out.println("Archiving department id : " + id);
		
		Department department =  findbyId(id).get();
		DepartmentArchive da = new DepartmentArchive();
		
		List<DepartmentArchive> daList = new ArrayList<>();
		daList =  departmentArchiveRepository.findByOriginalId(department.getDid());
		
		for(DepartmentArchive d: daList) {
			d.setStatus("history");
			departmentArchiveRepository.save(d);
		}
		
		da.setOriginalId(department.getDid());
		da.setDepartmentName(department.getDepartmentName());
		
		Date currentDate = new Date();
		da.setCreatedDate(currentDate);
		da.setStatus("deleted");
		departmentArchiveRepository.save(da);
		
		department.setStatus("deleted");
		departmentRepo.save(department);
		
		return id;
	}

}
