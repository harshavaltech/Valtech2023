package com.valtech.assignment.Jdbc.components;

import java.util.List;

public interface OrdersDAO {
	public long count(int id) ;
	void deleteOrder(int id);

	void updateOrder(Orders ord);

	void createOrder(Orders ord);

	void saveOrder(Orders ord);

	List<Orders> getAllOrders();

	Orders getOrder(int id) ;

}
