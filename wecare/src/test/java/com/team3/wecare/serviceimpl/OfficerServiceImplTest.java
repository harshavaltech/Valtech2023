package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.team3.wecare.entities.Complaint;
import com.team3.wecare.entities.Jurisdiction;
import com.team3.wecare.entities.Officer;
import com.team3.wecare.entities.Roles;
import com.team3.wecare.exception.WeCareException;
import com.team3.wecare.models.OfficerModel;
import com.team3.wecare.repositories.ComplaintRepo;
import com.team3.wecare.repositories.OfficerRepo;
import com.team3.wecare.repositories.RolesRepo;
import com.team3.wecare.service.EmailSenderService;

@ExtendWith(MockitoExtension.class)

class OfficerServiceImplTest {

	@Mock
	private OfficerRepo officerRepo;

	@Mock
	private ComplaintRepo compalintRepo;

	@Mock
	private EmailSenderService emailSenderService;

	@Mock
	private RolesRepo rolesRepo;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private OfficerServiceImpl officerService;

	@Test
	void testUpdateOfficer() {
		OfficerModel officerModel = new OfficerModel();
		officerModel.setOfficerId(1);
		officerModel.setOfficerName("Gagana");
		officerModel.setAddress("Hesarghatta");
		officerModel.setEmail("gaganacm@gmail.com");
		officerModel.setPhone("9535925188");

		Jurisdiction jurisdiction = new Jurisdiction();
		jurisdiction.setJuryId(1);

		Officer existingOfficer = new Officer();
		existingOfficer.setOfficerId(1);
		existingOfficer.setOfficerName("Gaganac");
		existingOfficer.setAddress("Jayanagar");
		existingOfficer.setEmail("gagana@gmail.com");
		existingOfficer.setPhone("9535921855");
		existingOfficer.setJuryOfficer(new Jurisdiction()); // Initial jurisdiction
		existingOfficer.setModifiedDate(LocalDateTime.now().minusDays(1));

		when(officerRepo.getReferenceById(1)).thenReturn(existingOfficer);
		when(officerRepo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		Officer result = officerService.updateOfficer(officerModel, jurisdiction);

		verify(officerRepo).getReferenceById(1);
		verify(officerRepo).save(any());

		assertEquals("Gagana", result.getOfficerName());
		assertEquals("Hesarghatta", result.getAddress());
		assertEquals("gaganacm@gmail.com", result.getEmail());
		assertEquals("9535925188", result.getPhone());
		assertEquals(jurisdiction, result.getJuryOfficer());
		assertNotNull(result.getModifiedDate());
	}

	@Test
	void testGetOfficer() {
		int officerId = 1;
		Officer officer = new Officer(officerId, "Praveen", "Bengaluru", "praveen@gmail.com", "9353761490");
		when(officerRepo.getReferenceById(officerId)).thenReturn(officer);

		Officer officerDetails = officerService.getOfficer(officerId);

		assertEquals(officerDetails.getOfficerName(), officer.getOfficerName());

	}

	@Test
	void testGetAllOfficers() {
		List<Officer> listOfOfficer = Arrays.asList(
				new Officer(1, "Praveen", "Bengaluru", "praveen@gmail.com", "9353761490"),
				new Officer(2, "kruthik", "Bengaluru", "kruthik@gmail.com", "6353761495"));

		when(officerRepo.findAll()).thenReturn(listOfOfficer);

		List<Officer> expectedListOfOfficer = officerService.getAllOfficers();

		assertEquals(expectedListOfOfficer, listOfOfficer);
	}

	@Test
	void testDeleteOfficer() {
		int officerId = 1;
		Officer officer = new Officer(officerId, "Praveen", "Bengaluru", "praveen@gmail.com", "9353761490");
		Optional<Officer> optionalOfficer = Optional.of(officer);
		List<Complaint> complaints = Arrays
				.asList(new Complaint("jayanagar", "jayanagar-layout", "water problem", "NA"));
		when(officerRepo.findById(officerId)).thenReturn(optionalOfficer);
		when(compalintRepo.getComplaintByOfficer(officer)).thenReturn(complaints);

		String result = officerService.deleteOfficer(officerId);

		assertNotEquals("Cannot delete Officer. Associated complaints exist.", result);
	}

	@Test
	void testCreateOfficerWithExistingEmail() throws Exception, WeCareException {
		OfficerModel officerModel = new OfficerModel();
		officerModel.setEmail("existing@email.com");
		officerModel.setPhone("NewPhone");

		Jurisdiction jurisdiction = new Jurisdiction();

		when(officerRepo.findByEmail(anyString())).thenReturn(new Officer());

		WeCareException exception = assertThrows(WeCareException.class,
				() -> officerService.createOfficer(officerModel, jurisdiction));

		verify(officerRepo, never()).save(any());
		verify(emailSenderService, never()).sendEmailToOfficer(any(), any());
		assertEquals("Officer with Email Id or Phone No. already Exists !", exception.getMessage());
	}

	@Test
	void testCreateOfficerWithExistingPhone() throws Exception, WeCareException {
		// Create sample input parameters with an existing phone
		OfficerModel officerModel = new OfficerModel();
		officerModel.setEmail("new@email.com");
		officerModel.setPhone("ExistingPhone");

		Jurisdiction jurisdiction = new Jurisdiction();

		when(officerRepo.findByEmail(anyString())).thenReturn(null);
		when(officerRepo.findOfficerByPhone(anyString())).thenReturn(new Officer());

		WeCareException exception = assertThrows(WeCareException.class,
				() -> officerService.createOfficer(officerModel, jurisdiction));

		verify(officerRepo, never()).save(any());
		verify(emailSenderService, never()).sendEmailToOfficer(any(), any());
		assertEquals("Officer with Email Id or Phone No. already Exists !", exception.getMessage());
	}

	@Test
	void testFindOfficerByEmail() {
		String email = "harsha@gmail.com";
		Officer officer = new Officer("Harsha", "Bengaluru", "harsha@gmail.com", "9353761490");

		when(officerRepo.findByEmail(email)).thenReturn(officer);

		Officer expectedOfficer = officerService.findOfficerByEmail(email);

		assertEquals(expectedOfficer, officer);
	}

	@Test
	void testFindOfficerByPhoneNumber() {
		String phone = "9353761490";
		Officer officer = new Officer("Harsha", "Bengaluru", "harsha@gmail.com", "9353761490");

		when(officerRepo.findOfficerByPhone(phone)).thenReturn(officer);

		Officer expectedOfficer = officerService.findOfficerByPhoneNumber(phone);

		assertEquals(expectedOfficer, officer);
	}

	@Test
	     void testCountOfficers() {
	        when(officerRepo.count()).thenReturn(10L);

	        Long result = officerService.countOfficers();

	        verify(officerRepo).count();

	        assertEquals(10L, result);
	    }

	@Test
	void testIsCurrentPasswordValid() {
		Officer mockedOfficer = new Officer();
		when(officerRepo.findByEmail(any())).thenReturn(mockedOfficer);
		when(passwordEncoder.matches(any(), any())).thenReturn(true);

		boolean isValid = officerService.isCurrentPasswordValid("gagana@gmail.com", "gagana@123");
		assertTrue(isValid);
	}

	@Test
	void testUpdatePassword() {
		Officer mockedOfficer = new Officer();
		when(officerRepo.findByEmail(any())).thenReturn(mockedOfficer);
		when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

		officerService.updatePassword("gaganacm@gmail.com", "gagana@1234");

		assertEquals("encodedPassword", mockedOfficer.getPassword());
	}

	@Test
	void testGetOfficerByDetails() {
		String email = "gagana@gmail.com";
		String phone = "9535925188";
		int officerId = 1;

		Officer expectedOfficer = new Officer();
		expectedOfficer.setOfficerId(officerId);
		expectedOfficer.setEmail(email);
		expectedOfficer.setPhone(phone);

		when(officerRepo.getOfficerByDetails(email, phone, officerId)).thenReturn(expectedOfficer);

		Officer result = officerService.getOfficerByDetails(email, phone, officerId);

		verify(officerRepo).getOfficerByDetails(email, phone, officerId);

		assertNotNull(result);
		assertEquals(officerId, result.getOfficerId());
		assertEquals(email, result.getEmail());
		assertEquals(phone, result.getPhone());
	}

	@Test
	void testUpdateOfficerProfile() {
		OfficerModel officerModel = new OfficerModel();
		officerModel.setOfficerId(1);
		officerModel.setOfficerName("Gagana");
		officerModel.setAddress("Hesarghatta");
		officerModel.setPhone("7337690657");
		officerModel.setEmail("gaganacm@gmail.com");

		Officer officerEntity = new Officer();
		officerEntity.setOfficerId(1);
		officerEntity.setOfficerName("Govind");
		officerEntity.setAddress("Jaynagar");
		officerEntity.setPhone("9966332211");
		officerEntity.setEmail("govind@gmail.com");

		when(officerRepo.getReferenceById(1)).thenReturn(officerEntity);
		when(officerRepo.save(any())).thenReturn(officerEntity);

		Officer result = officerService.updateOfficerProfile(officerModel);

		verify(officerRepo).getReferenceById(1);
		verify(officerRepo).save(officerEntity);

		assertEquals("Gagana", result.getOfficerName());
		assertEquals("Hesarghatta", result.getAddress());
		assertEquals("7337690657", result.getPhone());
		assertEquals("gaganacm@gmail.com", result.getEmail());
		assertNotNull(result.getModifiedDate());
	}

	@Test
	void testCreateOfficer_Success() throws Exception {
		OfficerModel officerModel = new OfficerModel();
		officerModel.setOfficerId(1);
		officerModel.setOfficerName("Gagana");
		officerModel.setAddress("Hesarghatta");
		officerModel.setEmail("gaganacm@gmail.com");
		officerModel.setPhone("9535925188");

		Jurisdiction jury = new Jurisdiction(); // You may need to create a mock for Jurisdiction if needed

		when(officerRepo.findByEmail(anyString())).thenReturn(null);
		when(officerRepo.findOfficerByPhone(anyString())).thenReturn(null);
		when(rolesRepo.findRolesByroleName("OFFICER")).thenReturn(new Roles("OFFICER"));
		when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

		when(officerRepo.save(any(Officer.class)))
				.thenReturn(new Officer("Gagana", "Bengaluru", "harsha@gmail.com", "9353761490"));

		Officer createdOfficer = officerService.createOfficer(officerModel, jury);

		verify(officerRepo, times(1)).findByEmail(anyString());
		verify(officerRepo, times(1)).findOfficerByPhone(anyString());
		verify(officerRepo, times(2)).save(any(Officer.class));
		verify(emailSenderService, times(1)).sendEmailToOfficer(any(Officer.class), any(Jurisdiction.class));

		assertNotNull(createdOfficer);
		assertEquals("Gagana", createdOfficer.getOfficerName());

	}

}