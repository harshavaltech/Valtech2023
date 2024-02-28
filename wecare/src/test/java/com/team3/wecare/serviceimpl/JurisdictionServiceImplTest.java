package com.team3.wecare.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.team3.wecare.entities.Jurisdiction;
import com.team3.wecare.entities.Officer;
import com.team3.wecare.models.JurisdictionModel;
import com.team3.wecare.repositories.ComplaintRepo;
import com.team3.wecare.repositories.JurisdictionRepo;
import com.team3.wecare.repositories.OfficerRepo;
import com.team3.wecare.service.JurisdictionService;

@ExtendWith(MockitoExtension.class)
class JurisdictionServiceImplTest {

	@Mock
	private JurisdictionRepo juryRepo;

	@Mock
	private OfficerRepo officerRepo;

	@Mock
	private ComplaintRepo complaintRepo;
	
	@Captor
    private ArgumentCaptor<Jurisdiction> jurisdictionCaptor;

	@InjectMocks
	private JurisdictionService jurisdictionService = new JurisdictionServiceImpl();

	@Test
	public void testUpdateJurisdiction() {
	    JurisdictionModel jurisdictionModel = new JurisdictionModel();
	    jurisdictionModel.setJuryId(1);
	    jurisdictionModel.setArea("Banshankri");
	    jurisdictionModel.setWard("Ward-108");
	    jurisdictionModel.setLayout("Manasa-Layout");

	    Jurisdiction jurisdictionEntity = new Jurisdiction();
	    jurisdictionEntity.setJuryId(1);
	    jurisdictionEntity.setArea("Vijanagar");
	    jurisdictionEntity.setWard("Ward-111");
	    jurisdictionEntity.setLayout("Maruthi-Layout");

	    when(juryRepo.getReferenceById(1)).thenReturn(jurisdictionEntity);
	    when(juryRepo.save(any())).thenReturn(jurisdictionEntity);

	    Jurisdiction result = jurisdictionService.updateJurisdiction(jurisdictionModel);

	    verify(juryRepo).save(jurisdictionCaptor.capture());

	    Jurisdiction capturedJurisdiction = jurisdictionCaptor.getValue();
	    assertEquals("Banshankri", capturedJurisdiction.getArea());
	    assertEquals("Ward-108", capturedJurisdiction.getWard());
	    assertEquals("Manasa-Layout", capturedJurisdiction.getLayout());
	    assertNotNull(capturedJurisdiction.getModifiedDate());

	    assertEquals("Banshankri", result.getArea());  
	    assertEquals("Ward-108", result.getWard());
	    assertEquals("Manasa-Layout", result.getLayout());
	    assertNotNull(result.getModifiedDate());
	}

	@Test
	void testGetJurisdiction() {
		int juryId = 1;
		Jurisdiction expectedJurisdiction = new Jurisdiction("vijaynagar", "ganesh mandir", "3rd cross road");

		when(juryRepo.getReferenceById(juryId)).thenReturn(expectedJurisdiction);
		Jurisdiction retrievedJurisdiction = jurisdictionService.getJurisdiction(juryId);

		assertEquals(expectedJurisdiction, retrievedJurisdiction);
	}

	@Test
	void testGetAllJurisdictions() {
		Jurisdiction jurisdiction1 = new Jurisdiction("vijaynagar", "ganesh mandir", "3rd cross road");
		Jurisdiction jurisdiction2 = new Jurisdiction("vijaynagar", "ganesh mandir", "3rd cross road");
		List<Jurisdiction> expectedJurisdictions = Arrays.asList(jurisdiction1, jurisdiction2);

		when(juryRepo.findAll()).thenReturn(expectedJurisdictions);
		List<Jurisdiction> allJurisdictions = jurisdictionService.getAllJurisdictions();

		assertEquals(expectedJurisdictions, allJurisdictions);
	}

	@Test
	public void testCreateJurisdiction() {
		JurisdictionModel jurisdictionModel = new JurisdictionModel();
		jurisdictionModel.setArea("Vijaynagar");
		jurisdictionModel.setWard("Ward-101");
		jurisdictionModel.setLayout("Appu-Layout");

		when(juryRepo.save(any())).thenAnswer(invocation -> {
			Jurisdiction savedJurisdiction = invocation.getArgument(0);
			savedJurisdiction.setJuryId(1);
			return savedJurisdiction;
		});

		Jurisdiction result = jurisdictionService.createJurisdiction(jurisdictionModel);

		verify(juryRepo).save(ArgumentMatchers.argThat(argument -> {
			assertEquals("Vijaynagar", argument.getArea());
			assertEquals("Ward-101", argument.getWard());
			assertEquals("Appu-Layout", argument.getLayout());
			assertNotNull(argument.getRegisteredDate());
			return true;
		}));

		assertNotNull(result);
		assertEquals(1L, result.getJuryId()); // Assuming the ID set in the mock behavior
		assertEquals("Vijaynagar", result.getArea());
		assertEquals("Ward-101", result.getWard());
		assertEquals("Appu-Layout", result.getLayout());
		assertNotNull(result.getRegisteredDate());
	}

	@Test
	public void testDeleteJurisdictionById() {
		int jurisdictionId = 1;

		Jurisdiction jurisdiction = new Jurisdiction();
		jurisdiction.setJuryId(jurisdictionId);
		List<Officer> officers = Collections.singletonList(new Officer());
		jurisdiction.setOfficers(officers);

		when(juryRepo.findById(jurisdictionId)).thenReturn(Optional.of(jurisdiction));
		when(complaintRepo.findByJuryComplaint(jurisdiction)).thenReturn(Collections.emptyList());

		String result = jurisdictionService.deleteJurisdictionById(jurisdictionId);

		verify(juryRepo).findById(jurisdictionId);
		verify(complaintRepo).findByJuryComplaint(jurisdiction);
		verify(officerRepo).saveAll(officers);
		verify(juryRepo).delete(jurisdiction);

		assertNull(result);
	}

	@Test
	void testGetJuryByDetails() {
		Jurisdiction jurisdiction = new Jurisdiction(1, "vijaynagar", "vijaynagar-101", "maruthi nagar");
		String area = jurisdiction.getArea();
		String ward = jurisdiction.getWard();
		String layout = jurisdiction.getLayout();
		Jurisdiction expectedJurisdiction = new Jurisdiction(1, "vijaynagar", "vijaynagar-101", "maruthi nagar");

		when(juryRepo.getJurisdictionByDetails(area, ward, layout)).thenReturn(expectedJurisdiction);

		Jurisdiction result = jurisdictionService.getJuryByDetails(jurisdiction.getArea(), jurisdiction.getWard(),
				jurisdiction.getLayout());
		assertEquals(expectedJurisdiction, result);
	}

	@Test
	void testGetWardsByArea() {
		String area = "vijaynagar";
		List<String> wards = Arrays.asList("vijaynagar-101", "vijaynagar-102");

		when(juryRepo.findWardsByArea(area)).thenReturn(wards);

		List<String> wardRelatedToArea = jurisdictionService.getWardsByArea(area);
		assertEquals(wardRelatedToArea, wards);
	}

	@Test
	void testGetLayoutByWard() {
		String ward = "vijaynagar-101";
		List<String> layouts = Arrays.asList("Maruthi Nagar", "Ashok Nagar");

		when(juryRepo.findLayoutsByWard(ward)).thenReturn(layouts);

		List<String> layoutRelatedtoWardsd = jurisdictionService.getLayoutByWard(ward);
		assertEquals(layoutRelatedtoWardsd, layouts);
	}

	@Test
	void testGetDistinctArea() {
		List<String> expectedAreas = Arrays.asList("jayanagar", "vijaynagar");

		when(juryRepo.findDistinctAreas()).thenReturn(expectedAreas);

		List<String> result = jurisdictionService.getDistinctArea();
		assertEquals(2, result.size());
	}

	@Test
    public void testCountJurisdictions() {
		
        when(juryRepo.count()).thenReturn(10L); 

        Long result =jurisdictionService.countJurisdictions();

        verify(juryRepo).count();

        assertEquals(10L, result); 
	}
}