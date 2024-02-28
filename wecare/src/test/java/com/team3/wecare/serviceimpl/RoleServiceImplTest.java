package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.team3.wecare.entities.Roles;
import com.team3.wecare.repositories.RolesRepo;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

	@Mock
	private RolesRepo rolesRepo;
	@InjectMocks
	private RoleServiceImpl roleService = new RoleServiceImpl();

	@Test
	void testGetRoles() {
		String roleName = "ADMIN";
		Roles expectedRoles = new Roles(1, roleName);
		when(rolesRepo.findRolesByroleName(roleName)).thenReturn(expectedRoles);

		Roles retrievedRoles = roleService.getRoles(roleName);

		assertEquals(retrievedRoles.getRoleId(), expectedRoles.getRoleId());
	}
}