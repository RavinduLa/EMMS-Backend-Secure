package com.emms.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.EquipmentCategoryDataAdapter;
import com.emms.inventoryModel.Brand;
import com.emms.inventoryModel.CategoryBrand;
import com.emms.inventoryModel.EquipmentCategories;

@Service
public class EquipmentCategoryApi {
	
	private EquipmentCategoryDataAdapter equipmentCategoryDataAdapter;
	
	@Autowired
	public EquipmentCategoryApi( EquipmentCategoryDataAdapter equipmentCategoryDataAdapter ) {
		this.equipmentCategoryDataAdapter =  equipmentCategoryDataAdapter;
	}
	
	public List<EquipmentCategories> getAllCategories(){
		return equipmentCategoryDataAdapter.getAll();
	}
	
	public Optional<EquipmentCategories> getCategoryById (int id){
		return equipmentCategoryDataAdapter.getCategoryById(id);
	}
	
	public EquipmentCategories  addCategory (EquipmentCategories category) {
		return equipmentCategoryDataAdapter.saveEquipmentCategory(category);
	}
	
	public int deleteCategory (int id) {
		return equipmentCategoryDataAdapter.deleteCategory(id);
	}
	
	public List<CategoryBrand> getAllEntriesForBrandCategories (){
		return equipmentCategoryDataAdapter.getAllEntriesForBrandCategories();
	}
	
	public boolean addBrandToCategory ( CategoryBrand cb) {
		System.out.println("Saving brand cat :" + cb.toString());
		String brand = cb.getBrand();
		String category = cb.getCategory();
		
		boolean doesEntryExist = equipmentCategoryDataAdapter.doesEntryExist(brand, category);
		
		if(doesEntryExist == false) {
			
			System.out.println("combo is not in the db. Saving combo");
			equipmentCategoryDataAdapter.addBrandToCategory(cb);
			return true;
			
		}
		else {
			
			System.out.println("combo is  in the db. Returning false");
			return false;
			
		}
	}
	
	public int deleteBrandCategoryById ( int id) {
		return equipmentCategoryDataAdapter.deleteBrandCategoryById(id);
	}
	
	public List<Brand> getBrandsForCategory (String category){
		return equipmentCategoryDataAdapter.getBrandsForCategory(category);
	}
	
	//checks whether the category is already there.
	public boolean isNameAvailable (String name) {
		name = name.toLowerCase();
		
		List<EquipmentCategories> categoryList = equipmentCategoryDataAdapter.getAll();
		
		for (EquipmentCategories category : categoryList) {
			String categoryName = category.getCategoryName();
			categoryName = categoryName.toLowerCase();
			if(categoryName.equals(name)  ) {
				System.out.println("Category is already in the database. Returning false");
				return false;
			}
		}
		
		System.out.println("Category is not in the database. Returning true.");
		return true;
	}
	
	

}
