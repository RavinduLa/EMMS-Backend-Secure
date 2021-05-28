package com.emms.dal.adapterImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.emms.archiveDao.DepartmentArchiveRepository;
import com.emms.archiveDao.SupplierArchiveRepository;
import com.emms.archiveModels.DepartmentArchive;
import com.emms.archiveModels.SupplierArchive;
import com.emms.dal.adapter.EquipmentDataAdapter;
import com.emms.inventoryDao.EquipmentRepository;

import com.emms.inventoryModel.Equipment;

public class EquipmentDataAdapterImpl implements EquipmentDataAdapter {
	
	private EquipmentRepository equipmentrepo;
	private SupplierArchiveRepository supplierArchiveRepository;
	private DepartmentArchiveRepository departmentArchiveRepository;
	
	@Autowired
	public EquipmentDataAdapterImpl(EquipmentRepository equipmentrepo, 
			SupplierArchiveRepository supplierArchiveRepository, 
			DepartmentArchiveRepository departmentArchiveRepository) {
		
		this.equipmentrepo = equipmentrepo;
		this.supplierArchiveRepository =  supplierArchiveRepository;
		this.departmentArchiveRepository = departmentArchiveRepository;
		
	}

	@Override
	public List<Equipment> getAll() {
		String result = equipmentrepo.findAll().toString();
		System.out.println("Returning all equipment");
		System.out.println(result);
		
		List<Equipment> equipmentList = new ArrayList<>();
		
		for(Equipment e: equipmentList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		return equipmentList;
	}

	@Override
	public String getSupplierNameForDate(int id, Date purchasedate) {
		List<SupplierArchive> suppliers = supplierArchiveRepository.findByOriginalId(id);
		String supplierName = "Yet to put";
		for(SupplierArchive s :suppliers) {
			Date createdDate = s.getCreatedDate();
			if(purchasedate.after(createdDate) || purchasedate.equals(createdDate)) {
				supplierName = s.getSupplieName();
			}
			else {
				System.out.println("Error: there is no created date before or on purchase date");
			}
		}
		return supplierName;
	}

	@Override
	public String getDepartmentNameForDate(int id, Date purchasedate) {
		List<DepartmentArchive> departments = departmentArchiveRepository.findByOriginalId(id);
		String departmentName = "Yet to put";
		
		for(DepartmentArchive d: departments) {
			Date createdDate = d.getCreatedDate();	
			if(purchasedate.after(createdDate) || purchasedate.equals(createdDate)) {
				departmentName = d.getDepartmentName();
			}
			else {
				System.out.println("Error: there is no created date for department before or on purchase date");
			}
		}
		return departmentName;
	}

	@Override
	public Optional<Equipment> getEquipmentById(long id) {
		Optional<Equipment> equipment = equipmentrepo.findById( id);
		
		System.out.println("Returning equipment for id");
		System.out.println(equipment.toString());
		
		return equipment;
	}

	@Override
	public boolean getIdAvailability(long id) {
		Optional<Equipment> e = equipmentrepo.findById(id);
		System.out.println("Equipment: " + e);
		
		if(e.isEmpty() ) {
			System.out.println("Return true");
			return true;
		}
		else {
			System.out.println("Return false");
			return false;
		}
	}

	@Override
	public Equipment saveEquipment(Equipment newEquipment) {
		System.out.println("saving equipment");
		System.out.println(newEquipment.toString());
		return equipmentrepo.save(newEquipment);
	}

	@Override
	public Equipment updateEquipment(Equipment newEquipment) {
		System.out.println("updating equipment");
		return equipmentrepo.save(newEquipment);
	}

	@Override
	public long deleteById(long id) {
		System.out.println("deleting equipment " + id);
		equipmentrepo.deleteById(id);
		return id;
	}

	@Override
	public long getAssetCount() {
		long count = equipmentrepo.count();
		System.out.println("Equipment count: " + count);
		return count;
	}

	@Override
	public int getDepartmentAssetCount(int did) {
		List<Equipment> equipmentList = getEquipmentForDepartment(did);
		return equipmentList.size();
	}

	@Override
	public int getTypeDeptAssetCount(int did, String type) {
		List<Equipment> equipmentList = getEquipmentForDepartment(did);
		int count = 0;
		for(Equipment e : equipmentList) {
			if(e.getType().equals(type)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getLocationAssetCount(String location) {
		List<Equipment> equipmentList = getEquipmentForLocation(location);
		return equipmentList.size();
	}

	@Override
	public int getSupplierAssetCount(int id) {
		List<Equipment> equipmentList = getEquipmentBySupplier(id);
		return equipmentList.size();
	}

	@Override
	public int getUnderWarrantyAssetCount() {
		List<Equipment> underWarrantyEList = getUnderWarrantyEquipment();
		return underWarrantyEList.size();
	}

	@Override
	public int getWarrantyVoidAssetCount() {
		List<Equipment>  noWarrantyEList = getNoWarrantyEquipment();
		return noWarrantyEList.size();
	}

	@Override
	public List<Equipment> getEquipmentBySupplier(int id) {
		List<Equipment> equipmentList = equipmentrepo.findBySupplier(id);
		System.out.println("Returning equipments for supplier,  "+ id+ " : ");
		
		for(Equipment e: equipmentList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		System.out.println(equipmentList.toString());
		return equipmentList;
	}

	@Override
	public List<Equipment> getEquipmentForLocation(String location) {
		List<Equipment> equipmentList = equipmentrepo.findByLocation(location.toLowerCase());
		System.out.println("Returning equipments for location,  "+ location+ " : ");
		
		for(Equipment e : equipmentList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		System.out.println(equipmentList.toString());
		return equipmentList;
	}

	@Override
	public List<Equipment> getEquipmentForDepartment(int dept) {
		List<Equipment> equipmentList = equipmentrepo.findByDepartment(dept);
		System.out.println("Returning equipments for department,  "+ dept+ " : ");
		
		for(Equipment e : equipmentList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		System.out.println(equipmentList.toString());
		return equipmentList;
	}

	@Override
	public int equipmentDepartmentCount(int dept) {
		List<Equipment> equipmentList = equipmentrepo.findByDepartment(dept);
		int count = equipmentList.size();
		return count;
	}

	@Override
	public int equipmentLocationCount(String location) {
		List<Equipment> equipmentList  = equipmentrepo.findByLocation(location);
		int count = equipmentList.size();
		return count;
	}

	@Override
	public List<Equipment> getEquipmentForLocationAndDepartment(String location, int department) {
		List<Equipment> lEquipmentList = equipmentrepo.findByLocation(location);
		List<Equipment> dEquipmentList = equipmentrepo.findByDepartment(department);
		List<Equipment> matchingEquipmentList = new ArrayList<Equipment>();
		
		for(Equipment e: lEquipmentList) {
			
			for(Equipment r: dEquipmentList) {
				if(e.getAssetId() == r.getAssetId()) {
					matchingEquipmentList.add(e);
				}
			}
		}
		
		if(matchingEquipmentList.isEmpty()) {
			System.out.println("The matching list is empty");
		}
		else {
			System.out.println("Returing the matching list : " + matchingEquipmentList.toString());
		}
		return matchingEquipmentList;
	}

	@Override
	public Equipment getEquipmentForAssetId(long assetId) {
		Equipment equipment  = equipmentrepo.findByAssetId(assetId);
		try {
			System.out.println("Returning equipment : " + equipment.toString() );
			return equipment;
		}catch (NullPointerException n) {
			System.out.println("Equipment is  null. The asset id is not found.");
			return new Equipment();
		}
	}

	@Override
	public Equipment getEquipmentForSerialNumber(String serialNumber) {
		Equipment equipment = equipmentrepo.findBySerialNumber(serialNumber);
		try {
			System.out.println("Returning equipment : " + equipment.toString() );
			return equipment;
		}catch (NullPointerException n) {
			System.out.println("Equipment is  null. The serial number is not found.");
			return new Equipment();
		}
	}

	@Override
	public List<Equipment> getEquipmentForTimePeriod(String s, String en) {
		
		Date start = new Date();
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(s);
		} catch (ParseException e1) {
			System.err.println("Error whe parsing start string to date");
			e1.printStackTrace();
		}
		Date end = new Date();
		try {
			end = new SimpleDateFormat("yyyy-MM-dd").parse(en);
		}catch (ParseException e1) {
			System.err.println("Error whe parsing end string to date");
			e1.printStackTrace();
		}
		
		List<Equipment> equipmentList = equipmentrepo.findAll();
		List<Equipment> sortedEquipment = new ArrayList<Equipment>();
		
		for(Equipment e: equipmentList) {
			Date purchaseDate = e.getPurchaseDate();
			//adds equipment purchased between start and end
			if( (purchaseDate.after(start) && purchaseDate.before(end)) ) {
				sortedEquipment.add(e);
			}
		}
		return sortedEquipment;
	}

	@Override
	public List<Equipment> getUnderWarrantyEquipment() {
		/*Date currentDate = new Date();
		List<Equipment> allEquipment = equipmentrepo.findAll();
		List<Equipment> underWarrantyList = new ArrayList<Equipment>();
		
		for(Equipment e: allEquipment) {
			
		}*/
		return null;
	}

	@Override
	public List<Equipment> getNoWarrantyEquipment() {
		// TODO Auto-generated method stub
		return null;
	}

}
