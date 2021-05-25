package com.emms.dal.adapter;

import java.util.List;
import java.util.Optional;

import com.emms.inventoryModel.Model;

public interface ModelDataAdapter {
	
	public List<Model> getAll ();
	public Optional<Model> getModelById(int id);
	public Model saveModel (Model model);
	public int deleteModel (int id);

}
