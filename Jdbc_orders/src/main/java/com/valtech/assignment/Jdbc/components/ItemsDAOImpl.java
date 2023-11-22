package com.valtech.assignment.Jdbc.components;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class ItemsDAOImpl implements ItemsDAO{

	@Autowired
	DataSource dataSource;
	@Override
	public long count() {
		String countQry = "SELECT COUNT(itemid) FROM ITEMS";
		return new JdbcTemplate(dataSource).queryForObject(countQry, Long.class);
	}

	@Override
	public void deleteItem(int id) {
		String deleteQry="DELETE FROM ITEMS WHERE itemid=?";
		new JdbcTemplate(dataSource).update(deleteQry,id);		
	}

	@Override
	public void updateItem(Items item) {
		String updateQry="UPDATE ITEMS SET itemid=?,comp_Id=?,item_desc=?,unit_price=? WHERE itemid=?";
		new JdbcTemplate(dataSource).update(updateQry,item.getItemid(),item.getCompId(),item.getItemDesc(),item.getUnitPrice(),item.getItemid());		
	}

	@Override
	public void createItem(Items item) {
		String createQry="INSERT INTO ITEMS(ITEMID,COMP_ID,ITEM_DESC,UNIT_PRICE) values(?,?,?,?)";
		new JdbcTemplate(dataSource).update(createQry,item.getItemid(),item.getCompId(),item.getItemDesc(),item.getUnitPrice());
				
	}

	@Override
	public List<Items> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Items getItem(int id) {
		return null;
	}
	

}
