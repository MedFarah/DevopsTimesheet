package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
	@Autowired
	IEmployeService es;
	@Autowired
	EmployeRepository er;
	
	private static final String NAME = "BenMansour";
	private static final String SURNAME = "Ahmed";
	private static final String EMAIL = "hmedM@esprit.tn";

	private static final Logger l = LogManager.getLogger(EmployeeTest.class);

	@Test
	public void verifTaille() {
		List<Employe> employes = es.getAllEmployes();
		assertTrue(!employes.isEmpty());
		String s = "Taille: " + employes.size();
		l.info(s);
	}


	@Test
	public void testAjout() {
		Integer i = es.getNombreEmployeJPQL();

		
		Employe emp = new Employe();
		emp.setNom(NAME);
		emp.setPrenom(SURNAME);
		emp.setEmail(EMAIL);
		emp = es.ajouterEmploye(emp);
		String s = "Nbr" + es.getNombreEmployeJPQL();
		l.info(s);
		assertEquals(i + (long) 1, es.getNombreEmployeJPQL());
		er.delete(emp);
	}
	
	@Test
	public void testModif() {
		Employe emp = new Employe();
		emp.setNom(NAME);
		emp.setPrenom(SURNAME);
		emp.setEmail(EMAIL);
		emp = es.ajouterEmploye(emp);
		emp.setPrenom("abbas");
		emp = er.save(emp);
		assertEquals("abbas" ,emp.getPrenom());
		er.delete(emp);
	}
	
	@Test
	public void testSuppr() {
		Employe emp = new Employe();
		emp.setNom(NAME);
		emp.setPrenom(SURNAME);
		emp.setEmail(EMAIL);
		emp = es.ajouterEmploye(emp);
		Integer i = es.getNombreEmployeJPQL();
		er.delete(emp);
		assertEquals(i - (long) 1, es.getNombreEmployeJPQL());
		
	}
}
