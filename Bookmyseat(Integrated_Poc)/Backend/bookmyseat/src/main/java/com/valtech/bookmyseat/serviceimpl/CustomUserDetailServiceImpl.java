package com.valtech.bookmyseat.serviceimpl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.valtech.bookmyseat.dao.UserDAO;
import com.valtech.bookmyseat.entity.ApprovalStatus;
import com.valtech.bookmyseat.entity.User;
import com.valtech.bookmyseat.service.CustomUserDetailService;

/**
 * CustomUserDetailsService is an implementation of the Spring Security
 * UserDetailsService interface. It provides functionality to load user details
 * by username, specifically designed for user authentication.
 */
@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	/**
	 * Retrieves the user details associated with the given username.
	 * 
	 * @param username The username (email) of the user to retrieve.
	 * @return A UserDetails object representing the user.
	 * @throws UsernameNotFoundException If the user with the given username is not
	 *                                   found or is not approved.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Attempting to load user by email:{}", username);
		User user = userDAO.getUserByEmail(username);
		if (Objects.nonNull(user) && user.getApprovalStatus() == ApprovalStatus.APPROVED) {
			logger.info("Successfully loaded user by email:{}", username);

			return new CustomUserDetailService(user);
		}
		logger.warn("User with email '{}' not found.", username);
		throw new UsernameNotFoundException("User not found");
	}
}