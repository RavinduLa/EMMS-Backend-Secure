package com.emms.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.EquipmentDataAdapter;
import com.emms.inventoryModel.Equipment;

@Service
public class EquipmentApi {
	
	private EquipmentDataAdapter equipmentDataAdapter;
	
	@Autowired
	public EquipmentApi(EquipmentDataAdapter equipmentDataAdapter) {
		this.equipmentDataAdapter = equipmentDataAdapter;
	}
	
	
	//returns a list of all equipment
	public List<Equipment> getAllEquipment (){
		return equipmentDataAdapter.getAll();
	}
	
	//returns supplier name based on the given date
	public String getSupplierNameForDate (int id, Date purchasedate) {
		return equipmentDataAdapter.getSupplierNameForDate(id, purchasedate);
	}
	
	//returns department name based on the given date
	public String getDepartmentNameForDate(int id, Date purchasedate) {
		return equipmentDataAdapter.getDepartmentNameForDate(id, purchasedate);
	}
	
	//get equipment on the given id
	public Optional<Equipment> getEquipmentById (long id){
		return equipmentDataAdapter.getEquipmentById(id);
	}
	
	//gets the availability of the asset id
	public boolean getIdAvailability (long id) {
		return equipmentDataAdapter.getIdAvailability(id);
	}
	
	//saves an equipment on the database
	public Equipment addEquipment (Equipment equipment) {
		return equipmentDataAdapter.saveEquipment(equipment);
	}
	
	//update the equipment
	public Equipment updateEquipment (Equipment equipment) {
		return equipmentDataAdapter.updateEquipment(equipment);
	}
	
	//deletes an equipment based on id
	public long deleteById (long id) {
		return equipmentDataAdapter.deleteById(id);
	}
	
	//get asset count
	public long getAssetCount() {
		return equipmentDataAdapter.getAssetCount();
	}
	
	//returns number of assets for department id
	public int getDepartmentAssetCount(int did) {
		return equipmentDataAdapter.getDepartmentAssetCount(did);
	}
	
	//returns asset count for a given type and given department id
	public int getTypeDeptAssetCount (int did, String type) {
		return equipmentDataAdapter.getTypeDeptAssetCount(did, type);
	}
	
	//returns asset count for a given location
	public int getLocationAssetCount(String location) {
		return equipmentDataAdapter.getLocationAssetCount(location);
	}
	
	//returns the number of assets for a given supplier id.
	public int getSupplierAssetCount (int id) {
		return equipmentDataAdapter.getSupplierAssetCount(id);
	}
	
	//returns a count of assets under warranty
	public int getUnderWarrantyAssetCount() {
		return equipmentDataAdapter.getUnderWarrantyAssetCount();
	}
	
	//returns a count of assets without warranty
	public int getWarrantyVoidAssetCount() {
		return equipmentDataAdapter.getWarrantyVoidAssetCount();
	}
	
	//returns a list of equipment for a supplier id
	public List<Equipment> getEquipmentBySupplier (int id){
		return equipmentDataAdapter.getEquipmentBySupplier(id);
	}
	
	//returns a list of equipment for a location
	public List<Equipment> getEquipmentForLocation (String location){
		return equipmentDataAdapter.getEquipmentForLocation(location);
	}
	
	//returns a list of equipment for a department
	public List<Equipment> getEquipmentForDepartment (int dept){
		return equipmentDataAdapter.getEquipmentForDepartment(dept);
	}
	
	//gets equipment count for a department
	public int equipmentDepartmentCount(int dept) {
		return equipmentDataAdapter.equipmentDepartmentCount(dept);
	}
	
	//gets equipment count for a location
	public int equipmentLocationCount(String location) {
		return equipmentDataAdapter.equipmentLocationCount(location);
	}
	
	//returns equipment list based on the passed location and the department.
	//both department and equipment must match for an equipment to be returned.
	public List<Equipment> getEquipmentForLocationAndDepartment (String location, int department){
		return equipmentDataAdapter.getEquipmentForLocationAndDepartment(location, department);
	}
	
	//returns an equipment for a given id
	public Equipment getEquipmentForAssetId(long assetId) {
		return equipmentDataAdapter.getEquipmentForAssetId(assetId);
	}
	
	//returns an equipment for a given serial number
	public Equipment getEquipmentForSerialNumber (String serialNumber) {
		return equipmentDataAdapter.getEquipmentForSerialNumber(serialNumber);
	}
	
	//returns an equipment within a time period
	public List<Equipment> getEquipmentForTimePeriod(String s, String en){
		return equipmentDataAdapter.getEquipmentForTimePeriod(s, en);
	}
	
	//returns a list of items that are under warranty
	public List<Equipment> getUnderWarrantyEquipment(){
		Date currentDate = new Date();
		List<Equipment> allEquipment = getAllEquipment();
		List<Equipment> underWarrantyList = new ArrayList<Equipment>();
		
		for(Equipment e : allEquipment) {
			int warrantyMonths = e.getWarrantyMonths();
			Date purchaseDate = e.getPurchaseDate();
			
			Date warrantyEndDate = calculateWarrantyEndDate(purchaseDate, warrantyMonths);
			if(currentDate.before(warrantyEndDate)) {
				underWarrantyList.add(e);
			}
		}
		
		for(Equipment e: underWarrantyList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		return underWarrantyList;
	}
	
	//returns a list of items that are not under warranty
	public List<Equipment> getNoWarrantyEquipment(){
		Date currentDate = new Date();
		List<Equipment> allEquipment = getAllEquipment();
		List<Equipment> noWarrantyList = new ArrayList<Equipment>();
		
		for(Equipment e: allEquipment) {
			int warrantyMonths = e.getWarrantyMonths();
			Date purchaseDate = e.getPurchaseDate();
			
			Date warrantyEndDate = calculateWarrantyEndDate(purchaseDate, warrantyMonths);
			if(currentDate.after(warrantyEndDate)) {
				noWarrantyList.add(e);
			}
		}
		
		for(Equipment e: noWarrantyList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		return noWarrantyList;
	}
	
	
	//convert java util date to calendar type
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
	
	//convert calendar type to java util date type
	public static Date toDate(Calendar calender) {
		Date date = new Date();
		date = calender.getTime();
		return date;
	}
	
	//calculate the warranty ending date based on purchased date and number of warranty months.
	public static Date calculateWarrantyEndDate (Date purchaseDate, int warrantyMonths) {
		Calendar calenderPurchaseDate, calenderWarrantyEndDate ;
		calenderPurchaseDate = toCalendar(purchaseDate);
		System.out.println("Calender type date: " + calenderPurchaseDate.toString());
		calenderWarrantyEndDate  = calenderPurchaseDate;
		calenderWarrantyEndDate.add(Calendar.MONTH, warrantyMonths);
		Date warrantyEndDate = toDate(calenderWarrantyEndDate);
		
		System.out.println("Warranty end date: " + warrantyEndDate);
		return warrantyEndDate;
				
	}
	
	

}
