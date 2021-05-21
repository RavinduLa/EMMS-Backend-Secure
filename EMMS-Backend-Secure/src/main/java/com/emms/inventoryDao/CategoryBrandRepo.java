package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.CategoryBrand;

public interface CategoryBrandRepo extends JpaRepository<CategoryBrand, Integer> {

}
