package com.valtech.assignment.Jdbc.components;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class OrdersDAOImpl implements OrdersDAO{

	
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public long count(int id) {
		String countQry = "SELECT COUNT(order_id) FROM Orders Where Cust_id=?";
		return new JdbcTemplate(dataSource).queryForObject(countQry, Long.class,id);		
	}

	@Override
	public void deleteOrder(int id) {
		
	}

	@Override
	public void updateOrder(Orders ord) {
		
	}

	@Override
	public void createOrder(Orders ord) {
		String createQry="INSERT INTO Orders(Order_id,cust_id,date) values(?,?,?)";
		new JdbcTemplate(dataSource).update(createQry,ord.getOrderId(),ord.getCustId(),ord.getDate());
		
	}

	@Override
	public void saveOrder(Orders ord) {
		
	}

	@Override
	public List<Orders> getAllOrders() {
		return null;
	}

	@Override
	public Orders getOrder(int id) {
		
		
		return null;
	}

	
}
