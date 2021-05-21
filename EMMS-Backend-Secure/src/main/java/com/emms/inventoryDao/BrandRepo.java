package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.Brand;

public interface BrandRepo extends JpaRepository<Brand, Integer> {

}
