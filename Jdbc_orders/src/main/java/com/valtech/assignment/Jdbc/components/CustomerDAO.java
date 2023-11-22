package com.valtech.assignment.Jdbc.components;

import java.util.List;



public interface CustomerDAO {
	public long count() ;

	void deleteCustomer(int id);

	void updateCustomer(Customer cust);

	void createCustomer(Customer cust);

	void saveCustomer(Customer cust);

	List<Customer> getAllCustomers();

	Customer getCustomer(int id) ;

}
