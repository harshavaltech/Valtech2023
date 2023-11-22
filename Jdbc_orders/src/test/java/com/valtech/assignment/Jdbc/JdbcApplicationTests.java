package com.valtech.assignment.Jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.valtech.assignment.Jdbc.components.Company;
import com.valtech.assignment.Jdbc.components.CompanyDAO;
import com.valtech.assignment.Jdbc.components.Customer;
import com.valtech.assignment.Jdbc.components.CustomerDAO;
import com.valtech.assignment.Jdbc.components.Items;
import com.valtech.assignment.Jdbc.components.ItemsDAO;
import com.valtech.assignment.Jdbc.components.OrderSummary;
import com.valtech.assignment.Jdbc.components.OrderSummaryDAO;
import com.valtech.assignment.Jdbc.components.Orders;
import com.valtech.assignment.Jdbc.components.OrdersDAO;

@SpringBootTest
class JdbcApplicationTests {
	
@Autowired
private CustomerDAO custDAO;
@Autowired
private OrdersDAO ordDAO;
@Autowired
private CompanyDAO compDAO;
@Autowired
private ItemsDAO itemsDAO;
@Autowired
private OrderSummaryDAO ordSumDAO;

	@Test
	void contextLoads() {
		custDAO.createCustomer(new Customer(1,"Banglore","abc",5667886));
		ordDAO.createOrder(new Orders(1,"11-11-2023",1));
		compDAO.createCompany(new Company(1,"Mysore","Haiers"));
		itemsDAO.createItem(new Items(1,"TV",30000,1));
		itemsDAO.createItem(new Items(2,"Fridge",50000,1));
		ordSumDAO.createOrderSummary(new OrderSummary(1,3,1,2));
		assertEquals(1, ordDAO.count(1));
		assertEquals(0, ordDAO.count(2));
		assertEquals(1, custDAO.count());
		assertEquals(1, compDAO.count());
		assertEquals(2, itemsDAO.count());
	}

}
