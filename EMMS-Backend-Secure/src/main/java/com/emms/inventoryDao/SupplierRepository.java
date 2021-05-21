package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
	
	Supplier findBySupplierId(int id);

}
