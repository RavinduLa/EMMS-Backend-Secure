package com.emms.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.ModelDataAdapter;
import com.emms.inventoryModel.Model;

@Service
public class ModelApi {
	
	private ModelDataAdapter modelDataAdapter;
	
	@Autowired
	public ModelApi (ModelDataAdapter modelDataAdapter ) {
		this.modelDataAdapter = modelDataAdapter;
	}
	
	public List<Model> getAllModels (){
		return modelDataAdapter.getAll();
	}
	
	public Optional<Model> getModelById (int id)
	{
		return modelDataAdapter.getModelById(id);
	}
	
	public Model addModel (Model model) {
		return modelDataAdapter.saveModel(model);
	}
	
	public int deleteModel (int id) {
		return modelDataAdapter.deleteModel(id);
	}
	
	public boolean isModelAvailable (String model) {
		model = model.toLowerCase();
		List<Model> modelList = modelDataAdapter.getAll();
		
		for(Model m : modelList) {
			String modelName = m.getModel();
			modelName.toLowerCase();
			if(modelName.equals(model)) {
				System.out.println("Model is already in the database. Returning false");
				return false;
			}
		}
		
		System.out.println("Model is not in the database. Returning true.");
		return true;
	}
	
	public List<Model> getModelsForBrands (String brand ){
		brand = brand.toLowerCase();
		List<Model> modelList = modelDataAdapter.getAll();
		List<Model> filteredModelList = new ArrayList<Model>();
		
		for(Model m: modelList) {
			String brandName = m.getBrand().toLowerCase();
			if(brandName.equals(brand)) {
				filteredModelList.add(m);
			}
		}
		
		System.out.println("Returning model list for brand : "+ brand);
		
		return filteredModelList;
	}
}
