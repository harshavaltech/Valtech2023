package com.valtech.assignment.Jdbc.components;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderSummary {
	@Id
	private int orderSummaryId;
	private int quantity;
	private int orderId;
	private int itemId;
	
	public OrderSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderSummary(int orderSummaryId, int quantity, int orderId, int itemId) {
		super();
		this.orderSummaryId = orderSummaryId;
		this.quantity = quantity;
		this.orderId = orderId;
		this.itemId = itemId;
	}

	public int getOrderSummaryId() {
		return orderSummaryId;
	}
	public void setOrderSummaryId(int orderSummaryId) {
		this.orderSummaryId = orderSummaryId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	

}
