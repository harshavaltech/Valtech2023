package com.valtech.assignment.Jdbc.components;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class CompanyDAOImpl implements CompanyDAO{

	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void createCompany(Company comp) {
		String createQry="INSERT INTO COMPANY(COMP_ID,ADDRESS,NAME) values(?,?,?)";
		new JdbcTemplate(dataSource).update(createQry,comp.getCompId(),comp.getAddress(),comp.getName());		
	}

	@Override
	public long count() {
		String countQry = "SELECT COUNT(comp_Id) FROM COMPANY";
		return new JdbcTemplate(dataSource).queryForObject(countQry, Long.class);	}

}
