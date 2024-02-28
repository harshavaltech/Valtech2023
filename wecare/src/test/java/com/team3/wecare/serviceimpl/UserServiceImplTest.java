package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.team3.wecare.entities.Roles;
import com.team3.wecare.entities.User;
import com.team3.wecare.exception.WeCareException;
import com.team3.wecare.models.UserModel;
import com.team3.wecare.repositories.RolesRepo;
import com.team3.wecare.repositories.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepo userRepo;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private RolesRepo rolesRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testGetUser() {
		int userId = 1;
		User user = new User("Govind123", "Govind", "Biradar", "govindbiradar2001@gmail.com", "9353761490",
				"govind@123");
		when(userRepo.getReferenceById(userId)).thenReturn(user);

		User result = userService.getUser(userId);

		assertNotNull(result);
		assertEquals(user, result);
	}

	@Test
	void testFindUserByEmail() {
		String validEmail = "govindbiradar2001@example.com";
		User userOfEmial = new User("Govind123", "Govind", "Biradar", "govindbiradar2001@gmail.com", "9353761490",
				"govind@123");

		when(userRepo.findByEmail(validEmail)).thenReturn(userOfEmial);

		User actualUser = userService.findUserByEmail(validEmail);
		assertEquals(userOfEmial, actualUser);
	}

	@Test
	void testUserCount() {
		when(userRepo.count()).thenReturn(5L);

		long result = userService.countUsers();

		assertEquals(5L, result);
	}

	@Test
	void testUpdatePassword_WithValidEmail_ShouldUpdatePassword() {
		String email = "gagana@gmail.com";
		String newPassword = "Gagana@123";
		User user = new User();
		when(userRepo.findByEmail(email)).thenReturn(user);

		userService.updatePassword(email, newPassword);

		verify(passwordEncoder).encode(newPassword);
		verify(userRepo).save(user);
	}

	@Test
	void testUpdatePassword_WithInvalidEmail_ShouldNotUpdatePassword() {
		String email = "gaganacm@gmail.com";
		String newPassword = "Gagana@1234";
		when(userRepo.findByEmail(email)).thenReturn(null);

		userService.updatePassword(email, newPassword);

		verify(userRepo, never()).save(any(User.class));
	}

	@Test
	void testSaveUserModel() {
		UserModel userModelDetails = new UserModel("Harsha123", "Harsha", "SD", "harsha@123");
		User user = new User(userModelDetails.getUserName(), userModelDetails.getFirstName(),
				userModelDetails.getLastName(), userModelDetails.getEmail(), userModelDetails.getPhone(),
				"encoded password");
		user.setRegisteredDate(LocalDateTime.now());
		Roles role = new Roles("ADMIN");
		user.setRole(role);
		when(rolesRepo.findRolesByroleName("USER")).thenReturn(role);
		when(passwordEncoder.encode("harsha@123")).thenReturn("encodedPassword");
		when(userRepo.save(any(User.class))).thenReturn(user);
		User result;
		try {
			result = userService.createUser(userModelDetails);

			assertEquals(result, user);

			verify(passwordEncoder).encode("harsha@123");
		} catch (WeCareException e) {

			e.printStackTrace();
		}
	}

	@Test
	void testGetAllUsers() {
		List<User> user = new ArrayList<User>();
		when(userRepo.findAll()).thenReturn(user);

		List<User> expectedUser = userService.getAllUsers();

		assertEquals(expectedUser, user);
	}

	@Test
	void testGetUserByDetails() {
		String email = "harsha@gmail.com";
		String phone = "9353761480";
		int userId = 1;
		User user = new User(phone, phone, phone, email, phone, phone);

		when(userRepo.getUserByDetails(email, phone, userId)).thenReturn(user);

		User expectedUser = userService.getUserByDetails(email, phone, userId);

		assertEquals(expectedUser, user);
	}

	@Test
	void testIsCurrentPasswordValid() {
		String email = "harsha@gmail.com";
		String currentPassword = "935376";
		User user = new User();
		user.setPassword(currentPassword);
		when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(true);
		when(userRepo.findByEmail(email)).thenReturn(user);

		Boolean matched = userService.isCurrentPasswordValid(email, currentPassword);

		assertTrue(matched);
	}

	@Test
	void testUpdateUserProfile() {
		UserModel userModel = new UserModel();
		userModel.setUserId(1);
		userModel.setUserName("harsha123");
		userModel.setFirstName("Harsha");
		userModel.setLastName("SD");
		userModel.setPhone("9353764895");
		userModel.setEmail("harsha@gmail.com");

		User user = new User();
		user.setUserId(userModel.getUserId());
		user.setUserName(userModel.getUserName());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setPhone(userModel.getPhone());
		user.setEmail(userModel.getEmail());

		when(userRepo.getReferenceById(1)).thenReturn(user);
		when(userRepo.save(any(User.class))).thenReturn(user);

		User updatedUser = userService.updateUserProfile(userModel);

		verify(userRepo).getReferenceById(1);
		verify(userRepo).save(user);

		assertEquals(user, updatedUser);
	}
}
