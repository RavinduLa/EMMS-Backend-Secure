package com.emms.dal.adapterImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.dal.adapter.ModelDataAdapter;
import com.emms.inventoryDao.ModelRepo;
import com.emms.inventoryModel.Model;

@Component
public class ModelDataAdapterImpl implements ModelDataAdapter {
	
	private ModelRepo modelRepo;
	
	@Autowired
	public ModelDataAdapterImpl(ModelRepo modelRepo) {
		this.modelRepo = modelRepo;
	}

	@Override
	public List<Model> getAll() {
		List<Model> modelList = modelRepo.findAll();
		System.out.println("returning model list : "+ modelList.toString());
		return modelList;
	}

	@Override
	public Optional<Model> getModelById(int id) {
		Optional<Model> model = modelRepo.findById(id);
		System.out.println("Returning model : "+ model.toString());
		return model;
	}

	@Override
	public Model saveModel(Model model) {
		model.setModelId(generateId());
		System.out.println("saving model: " + model.toString());
		return modelRepo.save(model);
	}
	
	public int generateId() {
		List<Model> allModels  = modelRepo.findAll();
		if(allModels.isEmpty()) {
			return 1;
		}
		else {
			int numberOfModels = allModels.size();
			int newId = 0;
			
			Model lastEnteredModel = allModels.get(numberOfModels-1);
			int lastId = lastEnteredModel.getModelId();
			
			newId = ++lastId;
			while(newId < lastId) {
				newId++;
			}
			return newId;
		}
	}

	@Override
	public int deleteModel(int id) {
		System.out.println("Deleting model: "+ id);
		modelRepo.deleteById(id);
		return id;
	}

}
