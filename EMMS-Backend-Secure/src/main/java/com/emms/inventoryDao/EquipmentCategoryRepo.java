package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.EquipmentCategories;

public interface EquipmentCategoryRepo extends JpaRepository<EquipmentCategories, Integer> {

}
