package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.team3.wecare.entities.Admin;
import com.team3.wecare.entities.Complaint;
import com.team3.wecare.entities.EmailOtp;
import com.team3.wecare.entities.Jurisdiction;
import com.team3.wecare.entities.Officer;
import com.team3.wecare.entities.User;
import com.team3.wecare.exception.WeCareException;
import com.team3.wecare.repositories.EmailOtpRepository;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.internet.MimeMessage;

@ExtendWith(MockitoExtension.class)
class EmailSenderServiceImplTest {

	private static final long OTPTIME = 0;

	@Mock
	private EmailOtpRepository emailOtpRepository;

	@InjectMocks
	private EmailSenderServiceImpl emailSenderService = new EmailSenderServiceImpl();

	@Mock
	private JavaMailSender mailSender;

	@Mock
	private SimpleMailMessage simpleMailMessage;

	@Mock
	private Template template;

	@Mock
	private Configuration configuration;

	@Test
	void testSendSimpleEmail() {
		String toEmail = "harshavardhan.d@valtech.com";
		String subject = "Test Subject";
		String body = "Test Body";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		boolean result = emailSenderService.sendSimpleEmail(toEmail, subject, body);

		assertTrue(result);
		verify(mailSender).send(any(SimpleMailMessage.class));
	}

	@Test
	void testGenerateOtp() {
		String generatedOtp = emailSenderService.generateOtp();

		assertNotNull(generatedOtp);
		assertTrue(generatedOtp.matches("\\d{6}"));
		int otpValue = Integer.parseInt(generatedOtp);

		assertTrue(otpValue >= 100000 && otpValue <= 999999);
	}

	@Test
	void testValidateWithValidOtp() throws WeCareException {
		String email = "harsha@gmail.com";
		String otp = "935353";

		when(emailOtpRepository.findByEmailAndOtpAndExpirationDateAfter(eq(email), eq(otp), any()))
				.thenReturn(new EmailOtp());

		boolean result = emailSenderService.validate(email, otp);

		assertTrue(result);
		verify(emailOtpRepository).findByEmailAndOtpAndExpirationDateAfter(eq(email), eq(otp), any());
	}

	@Test
	void testValidateWithExpiredOtp() {
		String email = "harsha@gmail.com";
		String otp = "935353";

		when(emailOtpRepository.findByEmailAndOtpAndExpirationDateAfter(eq(email), eq(otp), any())).thenReturn(null);

		WeCareException exception = assertThrows(WeCareException.class, () -> {
			emailSenderService.validate(email, otp);
		});

		assertEquals("Otp is expired", exception.getMessage());
		verify(emailOtpRepository).findByEmailAndOtpAndExpirationDateAfter(eq(email), eq(otp), any());
	}

	@Test
	void testSaveOtpToDb() {
		String email = "harshavardhan.d@valtech.com";
		String otp = "779861";
		Date currentDate = new Date();
		EmailOtp expectedSavedEmailWithOtp = new EmailOtp();
		expectedSavedEmailWithOtp.setEmail(email);
		expectedSavedEmailWithOtp.setOtp(otp);
		expectedSavedEmailWithOtp.setCreationDate(currentDate);
		expectedSavedEmailWithOtp.setExpirationDate(new Date(currentDate.getTime() + OTPTIME * 1000L));
		when(emailOtpRepository.save(any(EmailOtp.class))).thenReturn(expectedSavedEmailWithOtp);

		EmailOtp result = emailSenderService.saveOtpToDb(email, otp);

		assertNotNull(result);
		assertEquals(expectedSavedEmailWithOtp, result);
		verify(emailOtpRepository).save(any(EmailOtp.class));
	}

	@Test
	void testSendEmailToUser() throws Exception {
		User user = new User("Amar123", "Amar", "Biradar", "amar@gmail.com", "9353761490", "Amar@123");
		Officer officer = new Officer("Govind", "jayanagar", "govind@gmail.com", "govind@123");
		Complaint complaint = new Complaint("jayanagar", "Near covent school", "water problem", "NA");
		when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
		when(configuration.getTemplate(anyString())).thenReturn(mock(Template.class));

		boolean result = emailSenderService.sendEmailToUser(user, officer, complaint);

		assertTrue(result);

		verify(mailSender).createMimeMessage();
		verify(mailSender).send(any(MimeMessage.class));
	}

	@Test
	void testGetEmailContent() throws Exception {
		User user = new User("Amar123", "Amar", "Biradar", "amar@gmail.com", "9353761490", "Amar@123");
		Officer officer = new Officer("Govind", "jayanagar", "govind@gmail.com", "govind@123");
		Complaint complaint = new Complaint("jayanagar", "Near covent school", "water problem", "NA");
		Map<String, Object> model = new HashMap<>();
		model.put("user", user);
		model.put("officer", officer);
		model.put("complaint", complaint);

		when(configuration.getTemplate("complaintEmail.ftlh")).thenReturn(template);
		doAnswer(invocation -> {
			((StringWriter) invocation.getArgument(1)).write("Processed Template");
			return null;
		}).when(template).process(any(), any());

		String result = emailSenderService.getEmailContent(user, officer, complaint);

		assertEquals("Processed Template", result);
	}

	@Test
	void testGetResponseContent() throws Exception {
		User user = new User("Amar123", "Amar", "Biradar", "amar@gmail.com", "9353761490", "Amar@123");
		Complaint complaint = new Complaint("jayanagar", "Near covent school", "water problem", "NA");

		when(configuration.getTemplate("responseEmail.ftlh")).thenReturn(template);
		doAnswer(invocation -> {
			((StringWriter) invocation.getArgument(1)).write("Processed Template");
			return null;
		}).when(template).process(any(), any());

		String result = emailSenderService.getResponseContent(user, complaint);

		assertEquals("Processed Template", result);
	}

	@Test
	void testGetEmailOfficerContent() throws Exception {
		Officer officer = new Officer("Govind", "jayanagar", "govind@gmail.com", "govind@123");
		Jurisdiction jury = new Jurisdiction(1, "vijaynagar", "ganesh mandir", "3rd cross road");
		Map<String, Object> model = new HashMap<>();
		model.put("officer", officer);
		model.put("jury", jury);
		when(configuration.getTemplate("officerEmail.ftlh")).thenReturn(template);
		doAnswer(invocation -> {
			((StringWriter) invocation.getArgument(1)).write("Processed Template");
			return null;
		}).when(template).process(any(), any());
		String result = emailSenderService.getEmaiOfficerContent(officer, jury);

		assertEquals("Processed Template", result);

	}

	@Test
	void testSendEmailToOfficer() throws Exception {
		Officer officer = new Officer("Govind", "jayanagar", "govind@gmail.com", "govind@123");
		Jurisdiction jurisdiction = new Jurisdiction(1, "vijaynagar", "ganesh mandir", "3rd cross road");
		when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
		when(configuration.getTemplate(anyString())).thenReturn(mock(Template.class));

		boolean result = emailSenderService.sendEmailToOfficer(officer, jurisdiction);

		assertTrue(result);

		verify(mailSender).createMimeMessage();
		verify(mailSender).send(any(MimeMessage.class));
	}

	@Test
	void testGetStatusContent()
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, Exception {
		User user = new User("Amar123", "Amar", "Biradar", "amar@gmail.com", "9353761490", "Amar@123");
		Complaint complaint = new Complaint("jayanagar", "Near covent school", "water problem", "NA");

		when(configuration.getTemplate("statusEmail.ftlh")).thenReturn(template);
		doAnswer(invocation -> {
			((StringWriter) invocation.getArgument(1)).write("Processed Template");
			return null;
		}).when(template).process(any(), any());

		String result = emailSenderService.getStatusContent(user, complaint);

		assertEquals("Processed Template", result);

	}

	@Test
	void testSendStatusToUser() throws Exception {
		Complaint complaint = mock(Complaint.class);
		User user = mock(User.class);
		when(complaint.getUser()).thenReturn(user);
		when(user.getEmail()).thenReturn("amar@gmail.com");
		when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
		when(configuration.getTemplate(anyString())).thenReturn(mock(Template.class));

		boolean result = emailSenderService.sendStatusToUser(user, complaint);

		assertTrue(result);

		verify(mailSender).createMimeMessage();
		verify(mailSender).send(any(MimeMessage.class));
	}

	@Test
	void testSendResponseToUser() throws Exception {
		Complaint complaint = mock(Complaint.class);
		User user = mock(User.class);
		when(complaint.getUser()).thenReturn(user);
		when(user.getEmail()).thenReturn("amar@gmail.com");
		when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
		when(configuration.getTemplate(anyString())).thenReturn(mock(Template.class));

		boolean result = emailSenderService.sendResponseToUser(user, complaint);

		assertTrue(result);

		verify(mailSender).createMimeMessage();
		verify(mailSender).send(any(MimeMessage.class));

	}

	@Test
	void testGetEmailOtp() {
		String email = "harsha@gmail.com";
		String otp = "935353";
		EmailOtp mockEmailOtp = new EmailOtp();
		when(emailOtpRepository.findByEmailAndOtpAndExpirationDateAfter(eq(email), eq(otp), any(Date.class)))
				.thenReturn(mockEmailOtp);

		EmailOtp result = emailSenderService.getEmailOtp(email, otp);

		assertNotNull(result);
		assertEquals(mockEmailOtp, result);

		verify(emailOtpRepository).findByEmailAndOtpAndExpirationDateAfter(eq(email), eq(otp), any(Date.class));
	}

	@Test
	void testGetEmailContentOfAdmin() throws Exception {
		Admin adminDetails = new Admin(1, "kruthik", "kruthik@gmail.com", "kruthik123", LocalDateTime.now(),
				LocalDateTime.now());
		Map<String, Object> model = new HashMap<>();
		model.put("admin", adminDetails);
		when(configuration.getTemplate("adminEmail.ftlh")).thenReturn(template);
		doAnswer(invocation -> {
			((StringWriter) invocation.getArgument(1)).write("Processed Template");
			return null;
		}).when(template).process(any(), any());
		String result = emailSenderService.getEmailContentOfAdmin(adminDetails);

		assertEquals("Processed Template", result);

	}

	@Test
	void testSendEmailToAdmin() throws Exception {
		Admin adminDetails = new Admin(1, "kruthik", "kruthik@gmail.com", "kruthik123", LocalDateTime.now(),
				LocalDateTime.now());
		when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
		when(configuration.getTemplate(anyString())).thenReturn(mock(Template.class));

		boolean result = emailSenderService.sendEmailToAdmin(adminDetails);

		assertTrue(result);

		verify(mailSender).createMimeMessage();
		verify(mailSender).send(any(MimeMessage.class));

	}

}
