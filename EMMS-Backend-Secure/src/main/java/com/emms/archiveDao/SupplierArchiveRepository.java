package com.emms.archiveDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.archiveModels.SupplierArchive;

public interface SupplierArchiveRepository extends JpaRepository<SupplierArchive, Integer> {

	List<SupplierArchive> findByOriginalId(int id);
}
