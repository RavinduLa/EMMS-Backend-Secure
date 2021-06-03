package com.emms.dal.adapterImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.archiveDao.SupplierArchiveRepository;
import com.emms.archiveModels.SupplierArchive;
import com.emms.dal.adapter.SupplierDataAdapter;
import com.emms.inventoryDao.SupplierRepository;
import com.emms.inventoryModel.Supplier;

@Component
public class SupplierDataAdapterImpl implements SupplierDataAdapter {
	
	//repository objects
	private SupplierRepository supplierRepository;
	private SupplierArchiveRepository supplierArchiveRepository;
	
	@Autowired
	public SupplierDataAdapterImpl(SupplierRepository supplierRepository, 
			SupplierArchiveRepository supplierArchiveRepository) {
		
		this.supplierRepository = supplierRepository;
		this.supplierArchiveRepository = supplierArchiveRepository;
	}

	//retrieves all suppliers
	@Override
	public List<Supplier> getAll() {
		// when retrieving suppliers retrieve all active suppliers
		//in previous version all suppliers were retrieved and then sorted here.
		List<Supplier> suppliers = supplierRepository.findByStatus("active");
		return suppliers;
	}

	//saves a new supplier to the database
	@Override
	public Supplier saveSupplier(Supplier supplier) {
		
		//instantiate supplier object
		Supplier sup = new Supplier();
		//generate new id
		int id = generateId();
		
		System.out.println("Setting supplier id: " + id);
		supplier.setSupplierId(id); //set supplier id
		supplier.setStatus("active");  //set supplier as active
		sup = supplierRepository.save(supplier);  //save the supplier
		System.out.println("adding supplier: " + sup);
		
		//instantiate supplier archive object
		SupplierArchive sa = new SupplierArchive();
		
		//set the archive object
		sa.setOriginalId(supplier.getSupplierId());
		sa.setSupplieName(supplier.getSupplierName());
		sa.setPhone(supplier.getPhone());
		sa.setEmail(supplier.getEmail());
		
		//get the date
		Date date = new Date();
		
		System.out.println("Date: " + date);
		sa.setCreatedDate(date); //set the created date 
		sa.setStatus("active"); //set the status as active
		supplierArchiveRepository.save(sa); //save the archive object
		
		return sup; //return supplier object
	}
	
	//generates a new id for the supplier
	public int generateId() {
		List<Supplier> supplierList = getAll();
		
		if (supplierList.isEmpty()) {
			return 1;
		}
		else {
			int supplierCount = supplierList.size();
			Supplier lastEnteredSupplier = supplierList.get(supplierCount - 1);
			int lastSupplierId = lastEnteredSupplier.getSupplierId();
			int newId = ++lastSupplierId;

			while (newId < lastSupplierId) {
				newId++;
			}

			return newId;
		}
	}

	//returns the supplier by given id
	@Override
	public Optional<Supplier> getSupllierById(int id) {
	
		return supplierRepository.findById(id);
	}

	
	//updates a supplier based on id
	@Override
	public Supplier updateSupplier(Supplier supplier, int id) {
		//get optional supplier object
		//Optional<Supplier> s = getSupllierById(id);
		
		//get the optional object to supplier object
		//Supplier sup  = s.get();
		
		
		//instantiate list of supplier archives
		List<SupplierArchive> saList = supplierArchiveRepository.findByOriginalId(id);
		
		//set status of all previous archives to history
		for(SupplierArchive e : saList) {
			e.setStatus("history");
			supplierArchiveRepository.save(e);
		}
		
		//instantiate supplier archive
		SupplierArchive sa = new SupplierArchive();
		
		sa.setOriginalId(id);
		sa.setSupplieName(supplier.getSupplierName());
		sa.setEmail(supplier.getEmail());
		sa.setPhone(supplier.getPhone());
		sa.setStatus("active");
		
		Date date = new Date();
		sa.setCreatedDate(date);
		
		System.out.println("Updating supplier archive...");
		supplierArchiveRepository.save(sa);
		
		supplier.setStatus("active");

		return supplierRepository.save(supplier);
	}

	//delete a supplier by id
	@Override
	public Supplier deleteSupplierById(int id) {
		//get supplier optional by id
		Optional<Supplier> supplierOp = supplierRepository.findById(id);
		//get supplier object
		Supplier supplier  = supplierOp.get();
		
		//instantiate supplier archive object
		SupplierArchive sa = new SupplierArchive();
		
		//instantiate supplier archive list
		List<SupplierArchive> saList =  new ArrayList<>();
		//get list by original id
		saList = supplierArchiveRepository.findByOriginalId(supplier.getSupplierId());
		
		for(SupplierArchive sup : saList) {
			sup.setStatus("history");
			supplierArchiveRepository.save(sup);
		}
		
		sa.setOriginalId(supplier.getSupplierId());
		sa.setSupplieName(supplier.getSupplierName());
		sa.setPhone(supplier.getPhone());
		sa.setEmail(supplier.getEmail());
		
		Date currentDate = new Date();
		
		sa.setCreatedDate(currentDate); //set the deleted date
		sa.setStatus("deleted"); //set the status as deleted in archive
		supplierArchiveRepository.save(sa); //save the archive
		
		supplier.setStatus("deleted");  //set the supplier status as deleted
		supplierRepository.save(supplier);//save the supplier
		
		System.out.println("Deleting supplier with id : " + id);
		
		return supplier; //return the supplier object
	}

}
