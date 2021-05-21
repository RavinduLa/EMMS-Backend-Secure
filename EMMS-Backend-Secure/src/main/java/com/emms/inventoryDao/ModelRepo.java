package com.emms.inventoryDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emms.inventoryModel.Model;

public interface ModelRepo extends JpaRepository<Model, Integer> {

}
