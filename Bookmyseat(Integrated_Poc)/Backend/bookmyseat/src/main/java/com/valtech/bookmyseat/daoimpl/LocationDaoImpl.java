package com.valtech.bookmyseat.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.valtech.bookmyseat.dao.LocationDAO;
import com.valtech.bookmyseat.entity.Location;
import com.valtech.bookmyseat.exception.DataBaseAccessException;

@Repository
public class LocationDaoImpl implements LocationDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Location location) throws DataBaseAccessException {
		LOGGER.info("Executing the query to store the location details in the database");
		String insertQuery = "INSERT INTO LOCATION (LOCATION_NAME) VALUES (?)";
		LOGGER.debug("Executing the query to store to location query:{}", insertQuery);
		
		return jdbcTemplate.update(insertQuery, location.getLocationName());
	}

	@Override
	public List<Location> getAllLocation() throws DataBaseAccessException {
		LOGGER.info("Fetching all the location");
		String selectQry = "SELECT * FROM LOCATION";
		LOGGER.debug("Executing the query to fetch all location query:{}", selectQry);

		return jdbcTemplate.query(selectQry, new BeanPropertyRowMapper<>(Location.class));
	}

	@Override
	public int deleteLocation(int locationId) throws DataBaseAccessException{
		LOGGER.info("Deleting a particular location from the database");
		String deleteQry = "DELETE FROM LOCATION WHERE LOCATION_ID=?";
		LOGGER.debug("Executing the query to delete a perticular location query:{}", deleteQry);
				
		return jdbcTemplate.update(deleteQry, locationId);
	}

	@Override
	public int updateLocation(Location location, int locationId) throws DataBaseAccessException {
		LOGGER.info("Updating the location in te database");
		String updateQry = "UPDATE LOCATION SET LOCATION_NAME = ? WHERE LOCATION_ID=?";
		LOGGER.debug("Executing the query to update a location query:{}", updateQry);

		return jdbcTemplate.update(updateQry, location.getLocationName(), locationId);
	}
}