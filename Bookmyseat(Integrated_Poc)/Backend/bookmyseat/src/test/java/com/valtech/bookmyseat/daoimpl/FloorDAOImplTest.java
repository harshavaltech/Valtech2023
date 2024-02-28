package com.valtech.bookmyseat.daoimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.valtech.bookmyseat.daoimpl.FloorDAOImpl.FloorRowMapper;
import com.valtech.bookmyseat.entity.Floor;

@ExtendWith(MockitoExtension.class)
class FloorDAOImplTest {

	@Mock
	private ResultSet resultSet;

	@InjectMocks
	private FloorDAOImpl floorDAOImpl;

//	@Test
//	void testMapRow() throws SQLException {
//		int expectedFloorId = 1;
//		String expectedFloorName = "Test Floor";
//		when(resultSet.getInt("floor_id")).thenReturn(expectedFloorId);
//		when(resultSet.getString("floor_name")).thenReturn(expectedFloorName);
//		FloorRowMapper floorRowMapper = new FloorRowMapper();
//		Floor floor = floorRowMapper.mapRow(resultSet, 1);
//		assertNotNull(floor);
//		assertEquals(expectedFloorId, floor.getFloorId());
//		assertEquals(expectedFloorName, floor.getFloorName());
//		verify(resultSet).getInt("floor_id");
//		verify(resultSet).getString("floor_name");
//		verifyNoMoreInteractions(resultSet);
//	}
}
