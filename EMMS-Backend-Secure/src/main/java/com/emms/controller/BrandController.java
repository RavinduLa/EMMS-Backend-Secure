package com.emms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.BrandApi;
import com.emms.inventoryModel.Brand;

@RestController
@RequestMapping("api/inventory/")
public class BrandController {
	
	private BrandApi brandApi;
	
	@Autowired
	public BrandController( BrandApi brandApi ) {
		this.brandApi = brandApi;
	}

	
	@GetMapping(value="allBrands")
	public List<Brand> getAllBrands (){
		return brandApi.getAllBrands();
	}
	
	@PostMapping(value="addBrand")
	public synchronized Brand addbrand (@RequestBody  Brand brand) {
		return brandApi.addBrand(brand);
	}
	
	
	@GetMapping(value="isBrandAvailable/{name}")
	public synchronized boolean isNameAvailable (@PathVariable  String name) {
		return brandApi.isNameAvailable(name);
	}
	
	@GetMapping(value="/getBrandById/{id}")
	public Optional<Brand> getBrandById(@PathVariable  int id){
		return brandApi.getBrandById(id);
	}
	
	@DeleteMapping(value="/deleteBrand/{id}")
	public synchronized int deleteBrandyId(@PathVariable int id) {
		return brandApi.deleteBrandById(id);
	}
}
