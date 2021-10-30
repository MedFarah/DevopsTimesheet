package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import tn.esprit.spring.entities.Entreprise;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	@Autowired
	IEntrepriseService entrepriseservice;
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Test
	public void testAjouterEntreprise () {
		Entreprise entreprise=new Entreprise("name","raisonSocial");
		entrepriseservice.ajouterEntreprise(entreprise);
		assertThat(entreprise.getId()).isGreaterThan(0);
		entrepriseservice.deleteEntrepriseById(entreprise.getId());
	}
	@Test
	public void testGetName() {
		Entreprise entreprise= entrepriseservice.getEntrepriseById(1);
	//assertThat(entreprise.getName()).isEqualTo("name0");
	assertNotNull(entreprise.getName());
	l.info(" entreprise : "+ entreprise.getName());
	}
	@Test
	public void testListDepartementByEntreprise() {
		List<String> departement = entrepriseservice.getAllDepartementsNamesByEntreprise(1);
	assertThat(departement).size().isGreaterThan(0);
	}
	@Test
	public void testDeleteEntrepriseById()
	{
		Entreprise entreprise=new Entreprise("name","raisonSocial");
		entrepriseservice.ajouterEntreprise(entreprise);		
		entrepriseservice.deleteEntrepriseById(entreprise.getId());
		assertEquals("raisonSocial", entreprise.getRaisonSocial());
		
	}
	
	

}
