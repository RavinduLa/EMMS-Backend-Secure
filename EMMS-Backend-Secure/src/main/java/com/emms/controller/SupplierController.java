package com.emms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.SupplierApi;
import com.emms.inventoryModel.Supplier;

@RestController
@RequestMapping("api/inventory")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class SupplierController {
	
	private SupplierApi supplierApi; //supplier api object
	
	@Autowired
	public SupplierController(SupplierApi supplierApi) {
		this.supplierApi = supplierApi;
	}
	
	@RequestMapping(value = "allSuppliers")
	public List<Supplier> getAllSuppliers(){
		return supplierApi.getAllSuppliers();
	}
	
	@PostMapping(value = "addSupplier")
	public synchronized Supplier addSupplier (@RequestBody Supplier supplier) {
		return supplierApi.addSupplier(supplier);
	}
	
	@RequestMapping(value = "getSupplierById/{id}")
	public Optional<Supplier> getSupplierById (@PathVariable int id){
		return supplierApi.getSupplierById(id);
	}
	
	@GetMapping(value = "isSupplierAvailable/{name}")
	public synchronized boolean isSupplierAvailable(@PathVariable String name) {
		return supplierApi.isSupplierAvailable(name);
	}
	
	@PutMapping(value = "editSupplier/{id}")
	public Supplier updateSupplier (@RequestBody Supplier supplier, @PathVariable int id ) {
		return supplierApi.updateSupplier(supplier, id);
	}
	
	@DeleteMapping(value = "deleteSupplierById/{id}")
	public synchronized Supplier deleteSupplierById (@PathVariable int id) {
		return supplierApi.deleteSupplierById(id);
	}
	
	@GetMapping(value = "getSupplierNameForId/{id}")
	public String getSupplierNameForId(@PathVariable int  id) {
		return supplierApi.getSupplierNameForId(id);
	}

}
