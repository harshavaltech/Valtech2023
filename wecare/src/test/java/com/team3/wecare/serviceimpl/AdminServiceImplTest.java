package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.team3.wecare.entities.Admin;
import com.team3.wecare.entities.Roles;
import com.team3.wecare.models.AdminModel;
import com.team3.wecare.repositories.AdminRepo;
import com.team3.wecare.repositories.RolesRepo;
import com.team3.wecare.service.AdminService;
import com.team3.wecare.service.EmailSenderService;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

	@Mock
	private AdminRepo adminRepo;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private RolesRepo rolesRepo;

	@Captor
	private ArgumentCaptor<Admin> adminCaptor;

	@InjectMocks
	private AdminService adminService = new AdminServiceImpl();

	@Mock
	private EmailSenderService emailService = new EmailSenderServiceImpl();

	@Test
	void testFindAdminByEmail() {
		String email = "krutik@gmail.com";
		Admin adminDetails = new Admin(1, "kruthik", "kruthik@gmail.com", "kruthik123", LocalDateTime.now(),
				LocalDateTime.now());
		when(adminRepo.findByEmail(email)).thenReturn(adminDetails);

		Admin listOfAdmin = adminService.findAdminByEmail(email);

		assertEquals(listOfAdmin, adminDetails);
	}

	@Test
	void testIsCurrentPasswordValid() {
		Admin mockedAdmin = new Admin();
		when(adminRepo.findByEmail(any())).thenReturn(mockedAdmin);
		when(passwordEncoder.matches(any(), any())).thenReturn(true);

		boolean isValid = adminService.isCurrentPasswordValid("test@example.com", "currentPassword");

		assertTrue(isValid);
	}

	@Test
	void testUpdatePassword() {
		Admin mockedAdmin = new Admin();
		when(adminRepo.findByEmail(any())).thenReturn(mockedAdmin);
		when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
		adminService.updatePassword("test@example.com", "newPassword");

		assertEquals("encodedPassword", mockedAdmin.getPassword());
	}

	@Test
	void testCreateAdmin_SuccessfulCreation() throws Exception {
		AdminModel adminModel = new AdminModel("harsha@gmail.com", "9898989898");
		adminModel.setAdminName("Harsha");
		when(adminRepo.findByEmail(anyString())).thenReturn(null);
		when(rolesRepo.findRolesByroleName(anyString())).thenReturn(new Roles("ADMIN"));
		when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
		when(adminRepo.save(any(Admin.class))).thenReturn(new Admin());
		when(emailService.sendEmailToAdmin(any(Admin.class))).thenReturn(true);

		Admin result = adminService.createAdmin(adminModel);

		assertNotNull(result);

		verify(adminRepo).findByEmail(anyString());
		verify(rolesRepo).findRolesByroleName(anyString());
		verify(passwordEncoder).encode(anyString());
		verify(adminRepo, times(2)).save(any(Admin.class));
	}
}
