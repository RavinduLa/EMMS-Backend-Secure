package com.emms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.EquipmentCategoryApi;
import com.emms.inventoryModel.Brand;
import com.emms.inventoryModel.CategoryBrand;
import com.emms.inventoryModel.EquipmentCategories;

@RestController
@RequestMapping("api/inventory/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class EquipmentCategoryController {
	
	private EquipmentCategoryApi equipmentCategoryApi;
	
	@Autowired
	public EquipmentCategoryController(EquipmentCategoryApi equipmentCategoryApi) {
		this.equipmentCategoryApi = equipmentCategoryApi;
	}
	
	@GetMapping(value= "allCategories")
	public List<EquipmentCategories> getAllCategories(){
		return equipmentCategoryApi.getAllCategories();
	}
	
	@GetMapping(value = "getCatById/{id}")
	public Optional<EquipmentCategories> getCategoryById (@PathVariable int id){
		return equipmentCategoryApi.getCategoryById(id);
	}
	
	@PostMapping(value= "addCategory")
	public synchronized EquipmentCategories  addCategory (@RequestBody EquipmentCategories category) {
		return equipmentCategoryApi.addCategory(category);
	}
	
	@DeleteMapping(value="deleteCategoryById/{id}")
	public synchronized int deleteCategory(@PathVariable int id) {
		return equipmentCategoryApi.deleteCategory(id);
	}
	
	@GetMapping(value="allCategoryBrandCombinations")
	public List<CategoryBrand> getAllEntriesForBrandCategories(){
		return equipmentCategoryApi.getAllEntriesForBrandCategories();
	}
	
	@PostMapping("addBrandToCategory")
	public synchronized boolean addBrandToCategory(@RequestBody CategoryBrand cb) {
		return equipmentCategoryApi.addBrandToCategory(cb);
	}
	
	@DeleteMapping(value="deleteBrandCategoryById/{id}")
	public synchronized int deleteBrandCategoryById(@PathVariable int id) {
		return equipmentCategoryApi.deleteBrandCategoryById(id);
	}
	
	@GetMapping(value="getBrandsForCategory/{category}")
	public List<Brand> getBrandsForCategory (@PathVariable String category) {
		return equipmentCategoryApi.getBrandsForCategory(category);
	}
	
	@GetMapping("isCategoryAvailable/{name}")
	public synchronized boolean isNameAvailable(@PathVariable String  name) {
		
		return equipmentCategoryApi.isNameAvailable(name);
	}
	
	

}
