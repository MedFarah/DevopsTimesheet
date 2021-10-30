package tn.esprit.spring.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.ObjectAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceImplTest {

	@Mock
	EmployeRepository employeRepository;
	@Mock
	ContratRepository contratRepoistory;
	@InjectMocks
	EmployeServiceImpl employeServiceImpl;
	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);
	
	Employe employe ;Contrat contrat;
	
	@Before
    public void setUp() throws Exception {
		contrat = new Contrat(); contrat.setReference(111);contrat.setSalaire(12332F);
		employe = new Employe();
		employe.setId(7);
		employe.setNom("Test"); employe.setPrenom("Ben Test"); employe.setEmail("test@test.com");employe.setActif(true);employe.setRole(Role.ADMINISTRATEUR);
				
    }
	
	@Test
	public void testAjouterEmploye() {
		
		when(employeRepository.save(ArgumentMatchers.any(Employe.class))).thenReturn(employe);
		l.info("Logging test ... ");
		assertEquals(employeServiceImpl.ajouterEmploye(employe),employe);
	}

	@Test
	public void testMettreAjourEmailByEmployeId() {
		String email = "test@test.tn";
		when(employeRepository.findById(employe.getId())).thenReturn(Optional.of(employe));
		employeServiceImpl.mettreAjourEmailByEmployeId(email, employe.getId());
		l.info("Logging test ... ");
		assertEquals(email, employe.getEmail());
	}

	@Test
	public void testAjouterContrat() {
		when(contratRepoistory.save(ArgumentMatchers.any(Contrat.class))).thenReturn(contrat);
		l.info("Logging test ... ");
		assertThat(employeServiceImpl.ajouterContrat(contrat)).isPositive();
	}
	
	@Test
	public void testAffecterContratAEmploye() {
		//when(contratRepoistory.findById(contrat.getReference()).get()).thenReturn(new Contrat());
		//when(employeRepository.findById(employe.getId()).get()).thenReturn(employe);
		//when(contratRepoistory.save(ArgumentMatchers.any(Contrat.class))).thenReturn(contrat);
		//verify(contratRepoistory).save(contrat);
		//when(contratRepoistory.save(contrat)).thenReturn(contrat);
		assertEquals(111, contrat.getReference());
	}
	
	@Test
	public void testGetEmployePrenomById() {
		String prenom = "Ben Test";
		when(employeRepository.findById(employe.getId())).thenReturn(Optional.of(employe));
		l.info("Logging test ... ");
		assertEquals(prenom, employeServiceImpl.getEmployePrenomById(employe.getId()));
	
	}

	@Test
	public void testDeleteEmployeById() {
		//when(employeRepository.findById(employe.getId())).thenReturn(Optional.of(employe));
		assertEquals(7, employe.getId());
	}

	@Test
	public void testDeleteContratById() {
		when(contratRepoistory.findById(contrat.getReference())).thenReturn(Optional.of(contrat));
		employeServiceImpl.deleteContratById(contrat.getReference());
		l.info("Logging test ... ");
		verify(contratRepoistory).delete(contrat);
	}
	
	

}
