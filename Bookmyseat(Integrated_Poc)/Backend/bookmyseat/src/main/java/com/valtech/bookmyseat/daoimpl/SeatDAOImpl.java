package com.valtech.bookmyseat.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.valtech.bookmyseat.dao.FloorDAO;
import com.valtech.bookmyseat.dao.SeatDAO;
import com.valtech.bookmyseat.entity.Seat;
import com.valtech.bookmyseat.exception.DataBaseAccessException;

@Repository
public class SeatDAOImpl implements SeatDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(SeatDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private FloorDAO floorDAO;

	public class SeatRowMapper implements RowMapper<Seat> {

		@Override
		public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
			Seat seat = new Seat();
			seat.setSeatId(rs.getInt("seat_id"));
			seat.setSeatNumber(rs.getInt("seat_number"));
			seat.setFloor(floorDAO.getFloorById(rs.getInt("floor_id")));

			return seat;
		}
	}

	@Override
	public List<Seat> findAvailableSeatsByFloorOnDate(int floorId, LocalDate startDate, LocalDate endDate) {
		String selectQuery = "SELECT * FROM SEAT S WHERE S.FLOOR_ID = ? AND S.SEAT_ID  IN ( "
				+ "SELECT B.SEAT_ID FROM BOOKING B WHERE (CURDATE() BETWEEN B.START_DATE AND B.END_DATE) "
				+ "OR (? <= B.START_DATE AND ? >= B.END_DATE) " + "OR (? >= B.START_DATE AND ? <= B.END_DATE) "
				+ "OR (? >= B.START_DATE AND ? >= B.END_DATE) " + "OR (? <= B.START_DATE AND ? <= B.END_DATE) )";

		return jdbcTemplate.query(selectQuery, new SeatRowMapper(), floorId, startDate, endDate, startDate, endDate,
				startDate, endDate, startDate, endDate);
	}

	@Override
	public Seat findSeatById(int seatNumber, int floorId) {
		String selectQuery = "SELECT * FROM SEAT WHERE SEAT_NUMBER = ? AND FLOOR_ID = ?";

		return jdbcTemplate.queryForObject(selectQuery, new SeatRowMapper(), seatNumber, floorId);
	}

	@Override
	public int findSeatId(int seatNumber, int floorId) throws DataBaseAccessException {
		LOGGER.info("Retrieving the seatId");
		String sql = "SELECT seat_id FROM seat WHERE seat_number = ? AND floor_id = ?";
		Integer existingSeatId = jdbcTemplate.queryForObject(sql, Integer.class, seatNumber, floorId);

		return existingSeatId != null ? existingSeatId : -1;
	}
}