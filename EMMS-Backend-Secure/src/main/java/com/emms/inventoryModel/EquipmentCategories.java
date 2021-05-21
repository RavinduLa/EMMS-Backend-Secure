package com.emms.inventoryModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="equipment_categories")
public class EquipmentCategories {
	
	@Id
	@Column(name="category_id")
	private int categoryId;
	@Column(name="category_name")
	private String categoryName;
	
	public EquipmentCategories() {
		
	}
	public EquipmentCategories(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "EquipmentCategories [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	

}
