package com.valtech.bookmyseat.daoimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.valtech.bookmyseat.dao.RoleDAO;
import com.valtech.bookmyseat.entity.Booking;
import com.valtech.bookmyseat.entity.BookingMapping;
import com.valtech.bookmyseat.entity.Project;
import com.valtech.bookmyseat.entity.User;
import com.valtech.bookmyseat.exception.DataBaseAccessException;
import com.valtech.bookmyseat.mapper.BookingDetailsOfUserForAdminAndUserReportMapper;
import com.valtech.bookmyseat.mapper.UserRowMapper;
import com.valtech.bookmyseat.model.BookingDetailsOfUserForAdminAndUserReport;
import com.valtech.bookmyseat.model.UserModel;

@ExtendWith(MockitoExtension.class)
class UserDAOImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private RoleDAO roleDAO;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserDaoImpl userDAOImpl;

	@Test
	void testGetUserByEmail() throws SQLException {
		String emailId = "test@example.com";
		User expectedUser = new User();
		when(jdbcTemplate.queryForObject(anyString(), any(UserRowMapper.class), any(Object[].class)))
				.thenReturn(expectedUser);
		User actualUser = userDAOImpl.getUserByEmail(emailId);
		verify(jdbcTemplate).queryForObject(anyString(), any(UserRowMapper.class), any(Object[].class));
		assertEquals(expectedUser, actualUser);
	}

	@Test
	void testFindUserByuserId() {
		int userId = 1;
		User expectedUser = new User();
		expectedUser.setUserId(userId);
		when(jdbcTemplate.queryForObject(anyString(), any(UserRowMapper.class), any(Object[].class)))
				.thenReturn(expectedUser);
		User actualUser = userDAOImpl.findUserByuserId(userId);
		verify(jdbcTemplate).queryForObject(anyString(), any(UserRowMapper.class), any(Object[].class));
		assertEquals(expectedUser, actualUser);
	}

	@Test
	void testIsEmailExists_EmailExists() {
		String emailId = "test@example.com";
		int count = 1;
		when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(emailId))).thenReturn(count);
		boolean result = userDAOImpl.isEmailExists(emailId);
		assertTrue(result);
	}

	@Test
	void testIsEmailExists_EmailDoesNotExist() {
		String emailId = "nonexistent@example.com";
		when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(emailId)))
				.thenThrow(EmptyResultDataAccessException.class);
		boolean result = userDAOImpl.isEmailExists(emailId);
		assertFalse(result);
	}

	@Test
	void testIsEmployeeIdExists_Exists() {
		int userId = 1;
		when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(userId))).thenReturn(1);
		boolean result = userDAOImpl.isEmployeeIdExists(userId);
		assertTrue(result);
	}

	@Test
	void testIsEmployeeIdExists_NotExists() {
		int userId = 1;
		when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(userId))).thenReturn(0);
		boolean result = userDAOImpl.isEmployeeIdExists(userId);
		assertFalse(result);
	}

	@Test
	void testIsEmployeeIdExists_Exception() {
		int userId = 1;
		when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(userId)))
				.thenThrow(EmptyResultDataAccessException.class);
		boolean result = userDAOImpl.isEmployeeIdExists(userId);
		assertFalse(result);
	}

	@Test
	void testUpdateUser() {
		Project project = new Project();
		project.setProjectId(1001);
		UserModel userModel = new UserModel();
		userModel.setUserId(1);
		userModel.setPhoneNumber(1234567890);
		userModel.setProject(project);
		userDAOImpl.updateUser(userModel);
		assertNotNull(userModel);
	}

	@SuppressWarnings("unchecked")
	@Test
	void testGetUserById_Success() throws SQLException {
		int userId = 123;
		long phoneNumber = 1234567890L;
		ResultSet resultSet = mock(ResultSet.class);
		when(resultSet.getInt("user_id")).thenReturn(userId);
		when(resultSet.getLong("phone_number")).thenReturn(phoneNumber);
		when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenAnswer(invocation -> {
			RowMapper<User> rowMapper = invocation.getArgument(1);
			return rowMapper.mapRow(resultSet, 0);
		});
		User actualUser = userDAOImpl.getUserById(userId);
		verify(jdbcTemplate).queryForObject(anyString(), any(RowMapper.class), eq(userId));
		assertNotNull(actualUser);
		assertEquals(userId, actualUser.getUserId());
		assertEquals(phoneNumber, actualUser.getPhoneNumber());
	}

	@Test
	void testGetUserSeatInfo() throws DataBaseAccessException {
		when(jdbcTemplate.queryForList(anyString())).thenReturn(getMockedUserSeatInfo());
		List<Map<String, Object>> result = userDAOImpl.getUserSeatInfo();
		verify(jdbcTemplate).queryForList(
				"SELECT booking.booking_id, booking.booking_status, user.first_name, user.user_id, seat.seat_number, seat.floor_id, booking.start_date, booking.end_date "
						+ "FROM booking " + "JOIN user ON booking.user_id = user.user_id "
						+ "JOIN seat ON booking.seat_id = seat.seat_id");
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	private List<Map<String, Object>> getMockedUserSeatInfo() {
		List<Map<String, Object>> data = new ArrayList<>();
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("user_id", 1);
		rowData.put("user_name", "John Doe");
		rowData.put("booking_status", "True");
		rowData.put("seat_number", 15);
		rowData.put("booking_id", 2);
		data.add(rowData);
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test
	void testGetAllUserDetails() throws SQLException {
		ResultSet resultSet = mock(ResultSet.class);
		when(jdbcTemplate.query(anyString(), any(RowMapper.class), any(Object.class)))
				.thenReturn(Arrays.asList(resultSet));
		List<User> userDetailsList = userDAOImpl.getAllUserDetails(1);
		assertEquals(1, userDetailsList.size());
	}

	@Test
	void testGetBookingHistoryByUserId_Success() throws DataBaseAccessException {
		List<BookingDetailsOfUserForAdminAndUserReport> expectedBookingHistoryList = new ArrayList<>();
		BookingDetailsOfUserForAdminAndUserReport booking1 = new BookingDetailsOfUserForAdminAndUserReport();
		booking1.setUserName("John Doe");
		booking1.setUserId(123);
		booking1.setBookingType("DAILY");
		booking1.setSeatNumber(20);
		booking1.setFloorName("Floor 1");
		booking1.setStartTime(LocalTime.now());
		booking1.setEndTime(LocalTime.now());
		booking1.setStartDate(LocalDate.now());
		booking1.setEndDate(LocalDate.now());
		booking1.setTeaCoffeeType("COFFEE");
		booking1.setParkingType("FOUR_WHEELER");
		booking1.setLunch(true);
		expectedBookingHistoryList.add(booking1);
		String expectedSql = "SELECT CONCAT(U.first_name, ' ', U.last_name) AS user_name,U.user_id,B.booking_type,B.booking_id, "
				+ "S.seat_number,F.floor_name,SH.start_time,SH.end_time,B.start_date,B.booking_status, "
				+ "B.end_date,MAX(BM.tea_coffee_type) AS tea_coffee_type, "
				+ "MAX(BM.parking_type) AS parking_type,MAX(BM.lunch) AS lunch FROM user U "
				+ "JOIN booking B ON U.user_id = B.user_id "
				+ "JOIN booking_mapping BM ON B.booking_id = BM.booking_id JOIN seat S ON B.seat_id = S.seat_id "
				+ "JOIN floor F ON S.floor_id = F.floor_id JOIN shift SH ON B.shift_id = SH.shift_id "
				+ "WHERE U.user_id = ?  GROUP BY U.user_id, S.seat_number, F.floor_name, "
				+ "SH.start_time, SH.end_time, B.start_date, B.end_date,B.booking_type,B.booking_id,B.booking_status;";
		when(jdbcTemplate.query(eq(expectedSql), any(BookingDetailsOfUserForAdminAndUserReportMapper.class),
				eq(booking1.getUserId()))).thenReturn(expectedBookingHistoryList);
		List<BookingDetailsOfUserForAdminAndUserReport> result = userDAOImpl
				.getBookingHistoryByUserId(booking1.getUserId());
		verify(jdbcTemplate).query(eq(expectedSql), any(BookingDetailsOfUserForAdminAndUserReportMapper.class),
				eq(booking1.getUserId()));
		assertEquals(expectedBookingHistoryList, result);
	}

	@Test
	void testGetUserDashboardDetails() throws DataAccessException {
		User user = new User();
		user.setUserId(123);
		Booking booking = new Booking();
		booking.setUser(user);
		List<Booking> expectedBookingList = new ArrayList<>();
		expectedBookingList.add(booking);
		List<BookingMapping> actualBookingList = userDAOImpl.getUserDashboardDetails(123);
		assertNotNull(actualBookingList);
	}

	@Test
	void testUpdateUserPassword() throws DataAccessException {
		int userId = 123;
		String newPassword = "newPassword";
		int expectedAffectedRows = 1;
		when(jdbcTemplate.update(anyString(), anyString(), anyInt())).thenReturn(expectedAffectedRows);
		userDAOImpl.updateUserPassword(userId, newPassword);
		verify(jdbcTemplate, times(1)).update("UPDATE USER SET PASSWORD=? WHERE user_id=?", newPassword, userId);
	}
}