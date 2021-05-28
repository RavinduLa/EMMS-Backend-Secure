package com.emms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.EquipmentApi;
import com.emms.inventoryModel.Equipment;

@RestController
@RequestMapping("api/inventory")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class EquipmentController {
	
	private EquipmentApi equipmentApi;
	
	@Autowired
	public EquipmentController(EquipmentApi equipmentApi) {
		this.equipmentApi =  equipmentApi;
	}
	
	@GetMapping("equipment")
	public List<Equipment> getAllEquipment(){
		return equipmentApi.getAllEquipment();
	}
	
	@GetMapping(value="/getEquipmentById/{id}")
	public Optional<Equipment> getEquipmentById(@PathVariable long id){
		return equipmentApi.getEquipmentById(id);
	}
	
	@GetMapping(value = "/checkIdAvailability/{id}")
	public synchronized boolean getIdAvailability (@PathVariable long id) {
		return equipmentApi.getIdAvailability(id);
	}
	
	@PostMapping("addEquipment")
	public synchronized Equipment addEquipment( @RequestBody Equipment newEquipment) {
		return equipmentApi.addEquipment(newEquipment);
	}
	
	@PostMapping("updateEquipment")
	public synchronized Equipment updateEquipment (@RequestBody Equipment newEquipment) {
		return equipmentApi.updateEquipment(newEquipment);
	}
	
	@DeleteMapping(value="/deleteEquipment/{id}")
	public synchronized long deleteById(@PathVariable long id) {
		return equipmentApi.deleteById(id);
	}
	
	@GetMapping(value = "assetCount")
	public long getAssetCount() {
		return equipmentApi.getAssetCount();
	}
	
	@GetMapping(value = "getDepartmentAssetCount/{did}")
	public int getDepartmentAssetCount(@PathVariable int did) {
		return equipmentApi.getDepartmentAssetCount(did);
	}
	
	@GetMapping(value="getTypeDeptAssetCount/{did}/{type}")
	public int getTypeDeptAssetCount (@PathVariable int did,@PathVariable String type) {
		return equipmentApi.getTypeDeptAssetCount(did, type);
	}
	
	@GetMapping(value = "getLocationAssetCount/{location}")
	public int getLocationAssetCount(@PathVariable String location) {
		return equipmentApi.getLocationAssetCount(location);
	}
	
	@GetMapping(value= "getSupplierAssetCount")
	public int getSupplierAssetCount(@PathVariable  int id) {
		return equipmentApi.getSupplierAssetCount(id);
	}
	
	@GetMapping(value = "underWarrantyCount")
	public int getUnderWarrantyAssetCount() {
		return equipmentApi.getUnderWarrantyAssetCount();
	}
	
	@GetMapping(value = "noWarrantyCount")
	public int getWarrantyVoidAssetCount() {
		return equipmentApi.getWarrantyVoidAssetCount();
	}
	
	@GetMapping(value = "getEquipmentForSupplier/{id}")
	public List<Equipment> getEquipmentBySupplier (@PathVariable int  id){
		return equipmentApi.getEquipmentBySupplier(id);
	}
	
	@GetMapping(value = "getEquipmentForLocation/{location}")
	public List<Equipment> getEquipmentForLocation (@PathVariable String location){
		return equipmentApi.getEquipmentForLocation(location);
	}
	
	@GetMapping(value = "/getEquipmentForDepartment/{dept}")
	public List<Equipment> getEquipmentForDepartment (@PathVariable int dept){
		return equipmentApi.getEquipmentForDepartment(dept);
	}
	
	@GetMapping(value= "equipmentDepartmentCount/{dept}")
	public int equipmentDepartmentCount(@PathVariable int dept) {
		return equipmentApi.equipmentDepartmentCount(dept);
	}
	
	@GetMapping(value= "equipmentLocationCount/{location}")
	public int equipmentLocationCount(@PathVariable  String location) {
		return equipmentApi.equipmentLocationCount(location);
	}
	
	@GetMapping(value = "getEquipmentForLocationAndDepartment/{location}/{department}")
	public List<Equipment> getEquipmentForLocationAndDepartment (@PathVariable String location, 
			@PathVariable int department	){
		return equipmentApi.getEquipmentForLocationAndDepartment(location, department);
	}
	
	@GetMapping(value = "getEquipmentForAssetId/{assetId}")
	public Equipment getEquipmentForAssetId (@PathVariable long assetId) {
		return equipmentApi.getEquipmentForAssetId(assetId);
	}
	
	@GetMapping(value = "getEquipmentForSerialNumber/{serialNumber}")
	public Equipment getEquipmentForSerialNumber (@PathVariable String serialNumber) {
		return equipmentApi.getEquipmentForSerialNumber(serialNumber);
	}
	
	@GetMapping(value = "getEquipmentForTimePeriod/{s}/{en}")
	public List<Equipment> getEquipmentForTimePeriod (@PathVariable String s, @PathVariable String en){
		return equipmentApi.getEquipmentForTimePeriod(s, en);
	}
	
	@GetMapping(value = "getUnderWarrantyEquipment")
	public List<Equipment> getUnderWarrantyEquipment(){
		return equipmentApi.getUnderWarrantyEquipment();
	}
	
	@GetMapping(value = "getNoWarrantyEquipment")
	public List<Equipment> getNoWarrantyEquipment(){
		return equipmentApi.getNoWarrantyEquipment();
	}
	
	
	
	

}
