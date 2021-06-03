package com.emms.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.SupplierDataAdapter;
import com.emms.inventoryModel.Supplier;

@Service
public class SupplierApi {
	
	//data adapter object
	private SupplierDataAdapter supplierDdataAdapter;
	
	@Autowired
	public SupplierApi (SupplierDataAdapter supplierDdataAdapter) {
		this.supplierDdataAdapter = supplierDdataAdapter;
	}
	
	//returns a list of all the suppliers
	public List<Supplier> getAllSuppliers(){
		return supplierDdataAdapter.getAll();
	}
	
	//add a supplier to the database
	public Supplier addSupplier (Supplier supplier) {
		return supplierDdataAdapter.saveSupplier(supplier);
	}
	
	//get a supplier by id
	public Optional<Supplier> getSupplierById (int id){
		return supplierDdataAdapter.getSupllierById(id);
	}
	
	//checks whether the supplier name is available
	public boolean isSupplierAvailable(String name) {
		name = name.toLowerCase();
		List<Supplier> supplierList = getAllSuppliers();
		
		for(Supplier supplier: supplierList) {
			String supName = supplier.getSupplierName().toLowerCase();
			if (supName.equals(name)) {
				System.out.println("Supplier name is unavailable. Returning false.");
				return false;
			}
		}
		System.out.println("Supplier name is available. Returning true.");
		return true;
	}
	
	//updates a supplier
	public Supplier updateSupplier (Supplier supplier, int id) {
		return supplierDdataAdapter.updateSupplier(supplier, id);
	}
	
	//deletes supplier by id
	public Supplier deleteSupplierById (int id) {
		return supplierDdataAdapter.deleteSupplierById(id);
	}
	
	//get the supplier name by id
	public String getSupplierNameForId(int id) {
		Supplier supplier = new Supplier();
		supplier = getSupplierById(id).get();
		
		return supplier.getSupplierName(); //return the name
	}

}
