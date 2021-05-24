package com.emms.dal.adapter;

import java.util.List;
import java.util.Optional;

import com.emms.inventoryModel.Brand;

public interface BrandDataAdapter {
	
	public List<Brand> getAllBrands ();
	public Brand save(Brand brand);
	public Optional<Brand> getById(int id);
	public int deleteById (int id);

}
