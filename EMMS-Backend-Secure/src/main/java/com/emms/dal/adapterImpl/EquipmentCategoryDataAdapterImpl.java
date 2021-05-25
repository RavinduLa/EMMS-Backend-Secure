package com.emms.dal.adapterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.dal.adapter.EquipmentCategoryDataAdapter;
import com.emms.inventoryDao.BrandRepo;
import com.emms.inventoryDao.CategoryBrandRepo;
import com.emms.inventoryDao.EquipmentCategoryRepo;
import com.emms.inventoryModel.Brand;
import com.emms.inventoryModel.CategoryBrand;
import com.emms.inventoryModel.EquipmentCategories;

@Component
public class EquipmentCategoryDataAdapterImpl implements EquipmentCategoryDataAdapter {
	
	private EquipmentCategoryRepo categoryRepo;
	private CategoryBrandRepo cbRepo;
	private BrandRepo brandRepo;
	
	@Autowired
	public EquipmentCategoryDataAdapterImpl(EquipmentCategoryRepo categoryRepo, 
			CategoryBrandRepo cbRepo, BrandRepo brandRepo) {
		this.categoryRepo = categoryRepo;
		this.cbRepo =  cbRepo;
		this.brandRepo = brandRepo;
	}

	@Override
	public List<EquipmentCategories> getAll() {
		List<EquipmentCategories> catList =  categoryRepo.findAll();
		System.out.println("Returning all equipment categories : " + catList.toString());
		return catList;
	}

	@Override
	public Optional<EquipmentCategories> getCategoryById(int id) {
		Optional<EquipmentCategories> category =  categoryRepo.findById(id);
		System.out.println("Returning equipment : " + category.toString());
		return category;
	}

	@Override
	public EquipmentCategories saveEquipmentCategory(EquipmentCategories category) {
		category.setCategoryId(generateId());
		System.out.println("Savning category : " +  category.toString());
		return category;
	}
	
	public int generateId() {
		List<EquipmentCategories> allCategories = getAll();	
		int numberOfCategoriess = allCategories.size();
		int newId = 0;
		
		if(allCategories.isEmpty()) {
			return 1;
		}
		else {
			EquipmentCategories lastEnteredCategory = allCategories.get(numberOfCategoriess-1);
			
			int lastId = lastEnteredCategory.getCategoryId();
			
			newId = ++lastId;
			
			while(newId < lastId) {
				newId++;
			}
			
			return newId;
		}
	}

	@Override
	public int deleteCategory(int id) {
		System.out.println("Deleting category with id: " + id);
		categoryRepo.deleteById(id);
		return id;
	}

	@Override
	public List<CategoryBrand> getAllEntriesForBrandCategories() {
		List<CategoryBrand> cbList =  cbRepo.findAll();
		System.out.println("Returning all combinations for brands and categories: ");
		System.out.println(cbList.toString());
		return cbList;
	}

	@Override
	public CategoryBrand addBrandToCategory(CategoryBrand cb) {
		cb.setId(generateBrandCategoryId());
		CategoryBrand savedCb =   cbRepo.save(cb);
		System.out.println("Saved combo : " + savedCb.toString());
		return savedCb;
	}

	//generates an id for brand category combo
	public int generateBrandCategoryId() {
		List<CategoryBrand> cbList = cbRepo.findAll();
		int numberOfEntries = cbList.size();
		
		if(cbList.isEmpty()) {
			return 1;
		}
		else {
			int newId = 0;
			CategoryBrand lastEntry = cbList.get(numberOfEntries -1);
			int lastId = lastEntry.getId();
			newId = ++lastId;
			
			while(newId < lastId) {
				newId++;
			}
			return newId;
		}
	}
	
	@Override
	public int deleteBrandCategoryById(int id) {
		System.out.println("Deleting combo : " + id);
		cbRepo.deleteById(id);
		return id;
	}

	@Override
	public List<Brand> getBrandsForCategory(String category) {
		List<Brand> brandList;
		List<Brand> returningBrandList = new ArrayList<Brand>();
		List<CategoryBrand> cbList = cbRepo.findAll();
		
		String brandName;
		
		brandList = brandRepo.findAll();
		
		for(CategoryBrand cb: cbList) {
			if(cb.getCategory().equals(category)) {
				brandName = cb.getBrand();
				
				for(Brand brand:brandList) {
					if(brand.getBrandName().equals(brandName)) {
						returningBrandList.add(brand);
					}
				}
			}
		}
		System.out.println("Returning brand list for category :  " + category );
		System.out.println(returningBrandList.toString());
		return returningBrandList;
	}

	@Override
	public synchronized boolean doesEntryExist(String brand, String category) {
		List<CategoryBrand> cbList = cbRepo.findAll();
		
		for(CategoryBrand cb : cbList) {
			if( category.equals(cb.getCategory()  )) {
				if(brand.equals(cb.getBrand())) {
					return true;
				}
			}
		}
		return false;
	}

}
