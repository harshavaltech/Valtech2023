package com.valtech.bookmyseat.daoimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.valtech.bookmyseat.entity.User;
import com.valtech.bookmyseat.exception.DataBaseAccessException;
import com.valtech.bookmyseat.mapper.AdminDashBoardMapper;
import com.valtech.bookmyseat.mapper.UserRequestsMapper;
import com.valtech.bookmyseat.model.AdminDashBoardModel;
import com.valtech.bookmyseat.model.UserRequestsModel;

@ExtendWith(MockitoExtension.class)
class AdminDaoImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private AdminDaoImpl adminDaoImpl;

	@Test
	void testFetchDailyBookingDetails() throws DataBaseAccessException {
		List<AdminDashBoardModel> expectedList = new ArrayList<>();
		AdminDashBoardModel booking1 = new AdminDashBoardModel();
		booking1.setDate(LocalDate.now());
		booking1.setSeatsBooked(10);
		booking1.setTotalLunchBooked(5);
		booking1.setTotalTeaBooked(3);
		booking1.setTotalCoffeeBooked(2);
		booking1.setTotalParkingBooked(8);
		booking1.setTotalTwoWheelerParkingBooked(6);
		booking1.setTotalFourWheelerParkingBooked(2);
		booking1.setTotalDesktopBooked(7);
		expectedList.add(booking1);
		String expectedSql = "SELECT COUNT(DISTINCT b.seat_id) AS seats_booked, "
				+ "SUM(bm.lunch) AS total_lunch_booked, "
				+ "SUM(CASE WHEN bm.tea_coffee_type = 'TEA' THEN 1 ELSE 0 END) AS total_tea_booked, "
				+ "SUM(CASE WHEN bm.tea_coffee_type = 'COFFEE' THEN 1 ELSE 0 END) AS total_coffee_booked, "
				+ "SUM(bm.parking) AS total_parking_booked, "
				+ "SUM(CASE WHEN bm.parking_type = 'TWO_WHEELER' THEN 1 ELSE 0 END) AS total_two_wheeler_parking_booked, "
				+ "SUM(CASE WHEN bm.parking_type = 'FOUR_WHEELER' THEN 1 ELSE 0 END) AS total_four_wheeler_parking_booked, "
				+ "SUM(CASE WHEN bm.additional_desktop = 1 THEN 1 ELSE 0 END) AS total_desktop_booked "
				+ "FROM booking b " + "JOIN booking_mapping bm ON b.booking_id = bm.booking_id "
				+ "WHERE b.start_date <= CURDATE() AND b.end_date >= CURDATE()";

		when(jdbcTemplate.query(eq(expectedSql), any(AdminDashBoardMapper.class))).thenReturn(expectedList);
		List<AdminDashBoardModel> result = adminDaoImpl.fetchAdminDashboardDetails();
		assertEquals(expectedList, result);
	}

	@Test
	void testFetchUserRequests() throws DataBaseAccessException {
		List<UserRequestsModel> expectedList = new ArrayList<>();
		UserRequestsModel user1 = new UserRequestsModel();
		user1.setName("John Doe");
		user1.setUserID(1);
		user1.setEmailID("john.doe@example.com");
		user1.setRegsiterdDate(LocalDate.now());
		user1.setApprovalStatus("PENDING");
		expectedList.add(user1);
		String expectedSql = "SELECT CONCAT(first_name, ' ', last_name) AS name,user_id,email_id,registered_date,approval_status"
				+ " FROM user WHERE approval_status IN ('PENDING', 'APPROVED', 'REJECTED') AND role_id=2";
		when(jdbcTemplate.query(eq(expectedSql), any(UserRequestsMapper.class))).thenReturn(expectedList);
		List<UserRequestsModel> result = adminDaoImpl.fetchUserRequests();
		assertEquals(expectedList, result);
	}

	@Test
	void testUpdateUserRequests() throws DataBaseAccessException {
		User user = new User();
		String expectedSql = "UPDATE user SET approval_status = ?,modified_by=?,modified_date=? WHERE user_id = ?";
		int expectedResult = 1;
		when(jdbcTemplate.update(eq(expectedSql), anyString(), anyInt(), any(LocalDate.class), anyInt()))
				.thenReturn(expectedResult);
		int result = adminDaoImpl.updateUserRequests(user, 456);
		assertEquals(expectedResult, result);
	}
}
