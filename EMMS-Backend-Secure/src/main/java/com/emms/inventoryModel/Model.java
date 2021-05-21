package com.emms.inventoryModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class Model {
	
	@Id
	@Column(name="model_id")
	private int modelId;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "brand")
	private String brand;
	
	public Model() {
		
	}

	public Model(int modelId, String model, String brand) {
		super();
		this.modelId = modelId;
		this.model = model;
		this.brand = brand;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Model [modelId=" + modelId + ", model=" + model + ", brand=" + brand + "]";
	}
	
	
}