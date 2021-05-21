package com.emms.inventoryModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category_brand")
public class CategoryBrand {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="category")
	private String category;
	@Column(name="brand")
	private String brand;
	
	public CategoryBrand() {
		
	}

	public CategoryBrand(int id, String category, String brand) {
		super();
		this.id = id;
		this.category = category;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "CategoryBrand [id=" + id + ", category=" + category + ", brand=" + brand + "]";
	}

	
	

}
