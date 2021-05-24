package com.emms.dal.adapterImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.emms.dal.adapter.BrandDataAdapter;
import com.emms.inventoryDao.BrandRepo;
import com.emms.inventoryModel.Brand;

public class BrandDataAdapterImpl implements BrandDataAdapter {
	
	private BrandRepo brandRepo;
	
	@Autowired
	public BrandDataAdapterImpl (BrandRepo brandRepo) {
		this.brandRepo =  brandRepo;
	}

	@Override
	public List<Brand> getAllBrands() {
		List<Brand> brandList = brandRepo.findAll();
		System.out.println("Returning all brands from brand data adapter impl : " + brandList.toString());
		return brandList;
	}

	@Override
	public Brand save(Brand brand) {
		brand.setBrandId(generateId());
		Brand savedBrand = brandRepo.save(brand);
		System.out.println("Savng brand : " +savedBrand.toString());
		return savedBrand;
	}
	
	//used to generate a new id
	public int generateId() {
		List<Brand> allBrands = getAllBrands();
		
		if(allBrands.isEmpty())
		{
			return 1;
		}
		else {
			int numberOfBrands = allBrands.size();
			int newId = 0;
			
			Brand lastEnteredBrand = allBrands.get(numberOfBrands-1);
			int lastId = lastEnteredBrand.getBrandId();
			
			newId = ++lastId;
			while(newId < lastId) {
				newId++;
			}
			return newId;
		}
	}

	@Override
	public Optional<Brand> getById(int id) {
		Optional<Brand> brand  = brandRepo.findById(id);
		System.out.println("Returning brand for id : " + id);
		System.out.println(brand.toString());
		return brand;
	}

	@Override
	public int deleteById(int id) {
		System.out.println("Deleting brand :  " +id);
		brandRepo.deleteById(id);
		return id;
	}

}
