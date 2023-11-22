package com.valtech.assignment.Jdbc.components;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Items {
	@Id
	private int itemid;
	private String itemDesc;
	private int unitPrice;
	private int compId;
	
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Items(int itemid, String itemDesc, int unitPrice, int compId) {
		super();
		this.itemid = itemid;
		this.itemDesc = itemDesc;
		this.unitPrice = unitPrice;
		this.compId = compId;
	}

	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	

}
