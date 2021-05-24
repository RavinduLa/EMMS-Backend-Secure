package com.emms.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.emms.dal.adapter.BrandDataAdapter;

import com.emms.inventoryModel.Brand;

public class BrandApi {
	
	private BrandDataAdapter brandDataAdapter;
	
	@Autowired
	public BrandApi(BrandDataAdapter brandDataAdapter) {
		this.brandDataAdapter = brandDataAdapter;
	}
	
	//retrieves all the brands
	public List<Brand> getAllBrands (){
		List<Brand> brandList = brandDataAdapter.getAllBrands();
		System.out.println("Returning brand list : from API" + brandList.toString());
		return brandList;
	}
	
	//adds a brand to the database
	public Brand addBrand (Brand brand) {
		return brandDataAdapter.save(brand);
	}
	
	//checks whether the brand name is available
	public boolean isNameAvailable (String name) {
		name = name.toLowerCase();
		List<Brand> brandList = getAllBrands();
		
		for(Brand brand : brandList) {
			String brandName = brand.getBrandName();
			brandName = brandName.toLowerCase();
			if(brandName.equals(name)) {
				System.out.println("Brand is already in the database. Returning false");
				return false;
			}
		}
		
		System.out.println("Brand is not in the database. Returning true.");
		return true;
	}
	
	
	//gets brand by id
	public Optional<Brand> getBrandById(int id) {
		return brandDataAdapter.getById(id);
	}
	
	//deletes a brand by id
	public int deleteBrandById (int id) {
		return brandDataAdapter.deleteById(id);
	}

}
