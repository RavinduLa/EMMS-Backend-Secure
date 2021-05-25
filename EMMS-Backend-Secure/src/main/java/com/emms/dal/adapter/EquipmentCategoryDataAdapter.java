package com.emms.dal.adapter;

import java.util.List;
import java.util.Optional;

import com.emms.inventoryModel.Brand;
import com.emms.inventoryModel.CategoryBrand;
import com.emms.inventoryModel.EquipmentCategories;

public interface EquipmentCategoryDataAdapter {
	
	public List<EquipmentCategories> getAll();
	public Optional<EquipmentCategories> getCategoryById(int id);
	public EquipmentCategories saveEquipmentCategory(EquipmentCategories equipmentCategories);
	public int deleteCategory(int id);
	public List<CategoryBrand>  getAllEntriesForBrandCategories();
	public CategoryBrand addBrandToCategory (CategoryBrand cb);
	public int deleteBrandCategoryById (int id);
	public List<Brand>  getBrandsForCategory(String category);
	public boolean doesEntryExist(String brand, String category);

}
