package com.emms.dal.adapter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.emms.inventoryModel.Equipment;

public interface EquipmentDataAdapter {
	
	public List<Equipment> getAll ();
	public String getSupplierNameForDate(int id,Date purchasedate);
	public String getDepartmentNameForDate(int id, Date purchasedate);
	public Optional<Equipment> getEquipmentById (long id);
	public boolean getIdAvailability (long id);
	public Equipment saveEquipment (Equipment newEquipment);
	public Equipment updateEquipment (Equipment newEquipment);
	public long deleteById(long id);
	public long getAssetCount();
	public int getDepartmentAssetCount (int did);
	public int getTypeDeptAssetCount (int did, String type);
	public int getLocationAssetCount (String location);
	public int getSupplierAssetCount(int id);
	public int getUnderWarrantyAssetCount();
	public int getWarrantyVoidAssetCount();
	public List<Equipment> getEquipmentBySupplier (int id);
	public List<Equipment> getEquipmentForLocation (String location);
	public List<Equipment> getEquipmentForDepartment (int dept);
	public int equipmentDepartmentCount(int dept);
	public int equipmentLocationCount(String location);
	public List<Equipment> getEquipmentForLocationAndDepartment (String location, int department);
	public Equipment getEquipmentForAssetId(long assetId);
	public Equipment getEquipmentForSerialNumber (String serialNumber);
	public List<Equipment> getEquipmentForTimePeriod (String s, String en);
	public List<Equipment> getUnderWarrantyEquipment ();
	public List<Equipment> getNoWarrantyEquipment();

}
