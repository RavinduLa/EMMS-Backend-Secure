package com.emms.archiveDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.archiveModels.DepartmentArchive;

public interface DepartmentArchiveRepository extends JpaRepository<DepartmentArchive, Integer> {
	
	List<DepartmentArchive> findByOriginalId(int id);

}
