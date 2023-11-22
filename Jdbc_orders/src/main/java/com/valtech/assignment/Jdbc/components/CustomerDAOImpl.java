package com.valtech.assignment.Jdbc.components;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class CustomerDAOImpl implements CustomerDAO {
	
	
@Autowired
DataSource dataSource;
	
	
	
	
	@Override
	public long count() {
		
		String countQry = "SELECT COUNT(cust_Id) FROM CUSTOMER";
		return new JdbcTemplate(dataSource).queryForObject(countQry, Long.class);
	}

	@Override
	public void deleteCustomer(int id) {
		String deleteQry="DELETE FROM Customer WHERE cust_Id=?";
		new JdbcTemplate(dataSource).update(deleteQry,id);
	}

	@Override
	public void updateCustomer(Customer cust) {
		String updateQry="UPDATE CUSTOMER SET cust_id=?,address=?,name=?,phno=? WHERE cust_Id=?";
		new JdbcTemplate(dataSource).update(updateQry,cust.getCustId(),cust.getAddress(),cust.getName(),cust.getPhno());
		
	}

	@Override
	public void createCustomer(Customer cust) {
		String createQry="INSERT INTO CUSTOMER(CUST_ID,ADDRESS,NAME,PHNO) values(?,?,?,?)";
		new JdbcTemplate(dataSource).update(createQry,cust.getCustId(),cust.getAddress(),cust.getName(),cust.getPhno());
		
	}

	@Override
	public void saveCustomer(Customer cust) {
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		return null;
	}

	@Override
	public Customer getCustomer(int id) {
		return null;
	}
//	public static void main(String[] args) {
//		CustomerDAO customer=new CustomerDAOImpl();
//		customer.createCustomer(new Customer(1,"Banglore","abc",5667886));
//		
//	}
	
	
	
	
	

}
