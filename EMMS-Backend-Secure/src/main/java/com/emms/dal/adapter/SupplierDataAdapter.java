package com.emms.dal.adapter;

import java.util.List;
import java.util.Optional;

import com.emms.inventoryModel.Supplier;

public interface SupplierDataAdapter {
	
	public List<Supplier> getAll();
	public Supplier saveSupplier(Supplier supplier);
	public Optional<Supplier> getSupllierById (int id);
	public Supplier updateSupplier (Supplier supplier, int id);
	public Supplier deleteSupplierById(int id);

}
