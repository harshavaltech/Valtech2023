package com.valtech.bookmyseat.serviceimpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.valtech.bookmyseat.dao.BookingDAO;
import com.valtech.bookmyseat.dao.HolidayDAO;
import com.valtech.bookmyseat.dao.SeatDAO;
import com.valtech.bookmyseat.dao.ShiftDAO;
import com.valtech.bookmyseat.entity.Booking;
import com.valtech.bookmyseat.entity.BookingMapping;
import com.valtech.bookmyseat.entity.Holiday;
import com.valtech.bookmyseat.entity.Seat;
import com.valtech.bookmyseat.entity.Shift;
import com.valtech.bookmyseat.entity.User;
import com.valtech.bookmyseat.exception.CustomDataAccessException;
import com.valtech.bookmyseat.exception.DataBaseAccessException;
import com.valtech.bookmyseat.exception.SeatAlreadyBookedException;
import com.valtech.bookmyseat.model.BookedSeatModel;
import com.valtech.bookmyseat.model.BookingModel;
import com.valtech.bookmyseat.model.RestrainReserveOfUsersModel;
import com.valtech.bookmyseat.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingDAO bookingDAO;

	@Autowired
	private SeatDAO seatDAO;

	@Autowired
	private ShiftDAO shiftDAO;

	@Autowired
	private HolidayDAO holidayDAO;

	@Override
	public List<Booking> getUserPreferredSeats(int floorId, int projectId) {
		try {
			LOGGER.info("Fetching preferred Seats for logged in User !");

			return bookingDAO.userPreferredSeats(floorId, projectId);
		} catch (DataAccessException e) {
			throw new CustomDataAccessException("Failed to get User preferred Seats !", e);
		}
	}

	@Override
	public int createBooking(BookingModel booking, User user)
			throws DataBaseAccessException, SeatAlreadyBookedException {
		LOGGER.info("inserting the new booking");
		booking.setUserId(user.getUserId());
		Seat seat = seatDAO.findSeatById(booking.getSeatNumber(), booking.getFloorId());
		Shift shift = shiftDAO.findShiftByShiftId(booking.getShiftId());
		if (hasAlreadyBookedForDateCheck(user.getUserId(), booking.getStartDate(), booking.getEndDate())) {
			LOGGER.error("Seat already booked for this {} - {} date range", booking.getStartDate(),
					booking.getEndDate());
			throw new SeatAlreadyBookedException("You have already booked for this date range.");
		}
		int bookingId = bookingDAO.createBooking(booking, user, seat, shift);
		LOGGER.info("Successfully inserted the new booking");

		return bookingId;
	}

	@Override
	public void createBookingMapping(BookingModel booking, int bookingId) {
		LOGGER.info("Mapping Bookings based on booking date range");
		long numOfDays = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
		for (int i = 0; i <= numOfDays; i++) {
			LocalDate currentDate = booking.getStartDate().plusDays(i);
			boolean isHoliday = isHolidayforDate(currentDate);
			if (!isHoliday) {
				booking.setBookingDate(currentDate);
				bookingDAO.createBookingMapping(booking, bookingId);
				LOGGER.debug("Mapping bookings for a user for the date: {} for days: {}", currentDate, numOfDays);
			}
		}
		LOGGER.info("Bookings mapped");
	}

	@Override
	public boolean isHolidayforDate(LocalDate date) {
		List<Holiday> holidays = holidayDAO.getHolidayByDate();
		LOGGER.info("Checking holiday for the date");

		return holidays.stream().anyMatch(holiday -> holiday.getHolidayDate().equals(date));
	}

	@Override
	public boolean hasAlreadyBookedForDateCheck(int userId, LocalDate startDate, LocalDate endDate) {
		LOGGER.info("Checking whether seat is booked for particular date range");
		try {
			return bookingDAO.hasAlreadyBookedForDate(userId, startDate, endDate);
		} catch (RuntimeException e) {
			throw new DataBaseAccessException(
					"Failed to check whether seat is already booked for particular date range");
		}
	}

	@Override
	public List<BookingMapping> getAllBookingDetails() {
		LOGGER.info("Fetching Booking details!");

		return bookingDAO.getAllBookingDetails();
	}

	@Override
	public void approveUserAttendance(int userId) {
		try {
			LOGGER.info("Fetching approval User attendance !");
			bookingDAO.approvalAttendance(userId);
		} catch (DataAccessException e) {
			throw new CustomDataAccessException("Failed to update User's attendance !", e);
		}
	}

	@Override
	public List<BookedSeatModel> getAllBookedSeat(LocalDate startDate, LocalDate endDate)
			throws DataBaseAccessException {
		LOGGER.info("fetching the All Booked Seats ");
		List<BookedSeatModel> bookedSeats = bookingDAO.getAllBookedSeat(startDate, endDate);
		if (Objects.isNull(bookedSeats)) {
			throw new DataBaseAccessException("Error occured while fetching Booked Seats");
		}
		LOGGER.info("Successfully fetched the All Booked  Seats");

		return bookedSeats;
	}

	@Override
	public List<RestrainReserveOfUsersModel> getRestrainReservedDetailsOfUsers() {

		return bookingDAO.getReservedRestrainDetailsOfUsers();
	}
}