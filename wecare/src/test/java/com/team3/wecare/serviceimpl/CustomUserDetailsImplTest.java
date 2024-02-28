package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.team3.wecare.customlogindetails.CustomAdminDetails;
import com.team3.wecare.customlogindetails.CustomOfficerDetails;
import com.team3.wecare.customlogindetails.CustomUserDetails;
import com.team3.wecare.entities.Admin;
import com.team3.wecare.entities.Officer;
import com.team3.wecare.entities.User;
import com.team3.wecare.repositories.AdminRepo;
import com.team3.wecare.repositories.OfficerRepo;
import com.team3.wecare.repositories.UserRepo;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsImplTest {

	@Mock
	private UserRepo userRepo;

	@Mock
	private OfficerRepo officerRepo;

	@Mock
	private AdminRepo adminRepo;

	@InjectMocks
	private CustomUserDetailsImpl userDetailsServiceImpl;

	@Test
	void testLoadUserByUsername_UserFound() {
		String username = "Krutik@gmial.com";
		User user = new User();
		user.setEmail(username);
		when(userRepo.findByEmail(username)).thenReturn(user);

		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

		assert (userDetails instanceof CustomUserDetails);
		verify(userRepo, times(1)).findByEmail(username);
	}

	@Test
	void testLoadUserByUsername_OfficerFound() {
		String username = "Krutik@gmial.com";
		Officer officer = new Officer();
		officer.setEmail(username);
		when(officerRepo.findByEmail(username)).thenReturn(officer);

		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

		assert (userDetails instanceof CustomOfficerDetails);
		verify(officerRepo).findByEmail(username);
	}

	@Test
	void testLoadUserByUsername_AdminFound() {
		String username = "Krutik@gmial.com";
		Admin admin = new Admin();
		admin.setEmail(username);
		when(adminRepo.findByEmail(username)).thenReturn(admin);

		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

		assert (userDetails instanceof CustomAdminDetails);
		verify(adminRepo).findByEmail(username);
	}

	@Test
	void testLoadUserByUsername_UserNotFound() {
		String username = "Krutik@gmial.com";
		when(userRepo.findByEmail(username)).thenReturn(null);
		when(officerRepo.findByEmail(username)).thenReturn(null);
		when(adminRepo.findByEmail(username)).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> userDetailsServiceImpl.loadUserByUsername(username));

		verify(userRepo).findByEmail(username);
		verify(officerRepo).findByEmail(username);
		verify(adminRepo).findByEmail(username);
	}
}
