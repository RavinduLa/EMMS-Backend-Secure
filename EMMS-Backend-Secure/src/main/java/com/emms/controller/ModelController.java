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

import com.emms.api.ModelApi;
import com.emms.inventoryModel.Model;

@RestController
@RequestMapping("api/inventory")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class ModelController {
	
	private ModelApi modelApi;
	
	@Autowired
	public ModelController ( ModelApi modelApi )
	{
		this.modelApi = modelApi;
	}
	
	@GetMapping(value="allModels")
	public List<Model> getAllModels(){
		return modelApi.getAllModels();
	}
	
	@GetMapping(value = "getModelById/{id}")
	public Optional<Model> getModelById (@PathVariable int id){
		return modelApi.getModelById(id);
		
	}
	
	@PostMapping(value="addModel")
	public synchronized Model addModel(@RequestBody Model  model) {
		return modelApi.addModel(model);
	}
	
	@DeleteMapping(value="deleteModelById/{id}")
	public synchronized int deleteModel(@PathVariable int id) {
		return modelApi.deleteModel(id);
	}
	
	@GetMapping(value="isModelAvailable/{model}")
	public synchronized boolean isModelAvailable(@PathVariable String model) {
		return modelApi.isModelAvailable(model);
	}
	
	@GetMapping(value="getModelsForBrand/{brand}")
	public List<Model> getModelsForBrands (@PathVariable String brand){
		return modelApi.getModelsForBrands(brand);
	}

}
