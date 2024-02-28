package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.team3.wecare.entities.Complaint;
import com.team3.wecare.entities.Jurisdiction;
import com.team3.wecare.entities.Officer;
import com.team3.wecare.entities.Status;
import com.team3.wecare.entities.User;
import com.team3.wecare.models.ComplaintModel;
import com.team3.wecare.repositories.ComplaintRepo;
import com.team3.wecare.repositories.OfficerRepo;
import com.team3.wecare.service.ComplaintService;
import com.team3.wecare.service.EmailSenderService;
import com.team3.wecare.service.OfficerService;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceImplTest {

	@Mock
	private ComplaintRepo complaintRepo;

	@Mock
	private OfficerRepo officerRepo;

	@Mock
	private OfficerService officerService;

	@Mock
	private EmailSenderService emailService;

	@InjectMocks
	private ComplaintService complaintService = new ComplaintServiceImpl();

	@Test
	void testGetComplaint() {
		int complaintId = 1;
		Complaint expectedComplaint = new Complaint();
		expectedComplaint.setComplId(complaintId);
		when(complaintRepo.getReferenceById(complaintId)).thenReturn(expectedComplaint);

		Complaint result = complaintService.getComplaint(complaintId);

		assertEquals(expectedComplaint, result);
	}

	@Test
	void testGetAllComplaints() {
		List<Complaint> expectedComplaintList = Arrays.asList(
				new Complaint("Bidar", "Bhalki", "Water Problem", "Issue"),
				new Complaint("Bidar", "Bhalki", "Water Problem", "Issue"));
		when(complaintRepo.findAll()).thenReturn(expectedComplaintList);

		List<Complaint> result = complaintService.getAllComplaints();

		assertEquals(expectedComplaintList, result);
	}

	@Test
	void testGetComplaintsByUser() {
		User user = new User("Govind123", "Govind", "Biradar", "govindbiradar2001@gmail.com", "9353761490",
				"govind@123");
		Complaint complaint1 = new Complaint("jayanagar", "jayanagar-101", "Water Problem", "Issue");
		Complaint complaint2 = new Complaint("vijaynagar", "vijaynagar-101", "Water Problem", "Issue");
		user.setComplaint(List.of(complaint1, complaint2));
		List<Complaint> result = complaintService.getComplaintsByUser(user);

		assertEquals(0, result.size());
	}

	@Test
	void testCreateComplaint() throws Exception {
		Jurisdiction jurisdiction = new Jurisdiction();
		jurisdiction.setArea("jayanagar");

		User user = new User();
		user.setFirstName("Govind");

		ComplaintModel complaintModel = new ComplaintModel();
		complaintModel.setAddress("Bengaluru");
		complaintModel.setLandmark("Near convent");
		complaintModel.setIssue("water problem");
		complaintModel.setComment("NA");

		Officer officer = new Officer();
		officer.setOfficerId(1);

		when(officerService.getOfficer(anyInt())).thenReturn(officer);
		when(officerRepo.findOfficerIdWithMinComplaintCount(anyInt())).thenReturn(officer.getOfficerId());
		when(complaintRepo.save(any(Complaint.class))).thenAnswer(invocation -> {
			Complaint savedComplaint = invocation.getArgument(0);
			savedComplaint.setComplId(1);
			return savedComplaint;
		});

		Complaint createdComplaint = complaintService.createComplaint(complaintModel, jurisdiction, user);

		assertNotNull(createdComplaint.getComplId());
		assertEquals(Status.WAITING, createdComplaint.getStatus());
		verify(complaintRepo, times(2)).save(any(Complaint.class));
		verify(emailService).sendEmailToUser(eq(user), eq(officer), any(Complaint.class));
	}

	@Test
	void testGetCountOfClosedComplaints() {
		long expectedCount = 10L;
		when(complaintRepo.countByStatus(Status.CLOSED)).thenReturn(expectedCount);

		long actualCount = complaintService.getCountOfClosedComplaints();

		assertEquals(expectedCount, actualCount);
		verify(complaintRepo).countByStatus(Status.CLOSED);
	}

	@Test
	void testGetCountOfClosedComplaintsNoClosedComplaints() {
		long expectedCount = 0L;
		when(complaintRepo.countByStatus(Status.CLOSED)).thenReturn(expectedCount);

		long actualCount = complaintService.getCountOfClosedComplaints();

		assertEquals(expectedCount, actualCount);
		verify(complaintRepo).countByStatus(Status.CLOSED);
	}

	@Test
	void testCountComplaints() {
		when(complaintRepo.count()).thenReturn(5L);

		long result = complaintService.countComplaints();
		assertEquals(5L, result);
	}

	@Test
	void testGetComplaintByOfficer() {
		List<Complaint> complaint = Arrays.asList(new Complaint("Bidar", "Bhalki", "Water Problem", "Issue"),
				new Complaint("Bidar", "Bhalki", "Water Problem", "Issue"));
		Officer officer = new Officer();
		when(complaintRepo.getComplaintByOfficer(officer)).thenReturn(complaint);

		List<Complaint> expectComplaints = complaintService.getComplaintByOfficer(officer);

		assertEquals(expectComplaints, complaint);
	}

	@Test
	void testSaveComplaint() throws Exception {
		Complaint complaint = new Complaint("jayanagar", "Near covent school", "water problem", "NA");
		User user = new User("Amar123", "Amar", "Biradar", "amar@gmail.com", "9353761490", "Amar@123");

		complaintService.saveComplaint(complaint, user);

		verify(emailService).sendResponseToUser(eq(user), eq(complaint));
		verify(complaintRepo).save(eq(complaint));
	}

	@Test
	void testUpdateComplaintStatus() throws Exception {
		int complaintId = 1;
		Status newStatus = Status.CLOSED;
		User user = new User("Amar123", "Amar", "Biradar", "amar@gmail.com", "9353761490", "Amar@123");
		Complaint complaint = new Complaint();
		complaint.setComplId(complaintId);
		complaint.setStatus(Status.OPEN);
		when(complaintRepo.getReferenceById(complaintId)).thenReturn(complaint);
		complaintService.updateComplaintStatus(complaintId, newStatus, user);

		assertEquals(newStatus, complaint.getStatus());
		verify(emailService, times(1)).sendStatusToUser(eq(user), eq(complaint));
		verify(complaintRepo, times(1)).save(eq(complaint));
	}
}
