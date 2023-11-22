package com.valtech.assignment.Jdbc.components;

import java.util.List;
public interface ItemsDAO {
	public long count() ;

	void deleteItem(int id);

	void updateItem(Items item);

	void createItem(Items item);

	
	List<Items> getAllItems();

	Items getItem(int id) ;
}
