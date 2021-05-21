package com.emms.inventoryDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
	
	List<Equipment> findBySupplier(int id);
	List<Equipment> findByLocation(String  location);
	List<Equipment> findByDepartment(int department);
	Equipment findByAssetId(long assetId);
	Equipment findBySerialNumber(String serialNumber);
	List<Equipment> findByType (String type);

}
