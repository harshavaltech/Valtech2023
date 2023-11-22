package com.valtech.assignment.Jdbc.components;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {
	@Id
	private int orderId;
	private String date;
	private int custId;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders(int orderId, String date, int custId) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.custId = custId;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	

}
