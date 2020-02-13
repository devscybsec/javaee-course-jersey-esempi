package it.cybsec.daos;

import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import it.cybsec.models.Studente;
import it.cybsec.models.dtos.StudenteDTO;
import it.cybsec.utils.AbstractDao;

public class StudenteDao extends AbstractDao<Studente, Integer> {

	public StudenteDao() { 
		super();
	}
	
	@Override
	public Studente recupera(Integer id) {
		return em.find(Studente.class, id);
	}

	@Override
	public List<Studente> recupera() {
		em.clear();
		CriteriaQuery<Studente> query = getCriteriaBuilder().createQuery(Studente.class);
		query.select(query.from(Studente.class));
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Studente> recupera(CriteriaQuery<Studente> query) {
		em.clear();
		return em.createQuery(query).getResultList();
	}
	
	@Override
	public void elimina(Studente studente) throws Exception {
		super.elimina(studente);
		studente.setCorsi(null);
	}
	
	public Long count() {
		TypedQuery<Long> count = em.createQuery("SELECT COUNT(s) FROM Studente s", Long.class);
		return count.getSingleResult();
	}
	
	public List<StudenteDTO> recuperaStudenteDTO() {
		
		TypedQuery<StudenteDTO> studenteDTOquery = em.createQuery(
				"SELECT new it.cybsec.models.dtos.StudenteDTO("
				+ "		s.nome, "
				+ "		s.cognome, "
				+ "		YEAR(CURRENT_DATE()) - YEAR(s.dataNascita) - "
				+ "		CASE "
				+ "			WHEN MONTH(CURRENT_DATE()) < MONTH(s.dataNascita) "
				+ "				 OR (MONTH(CURRENT_DATE()) = MONTH(s.dataNascita) "
				+ "					 AND DAY(CURRENT_DATE()) < DAY(s.dataNascita)) "
				+ "			THEN 1 "
				+ "			ELSE 0 "
				+ " 	END, "
				+ "		SUM(CASE WHEN c IS NULL THEN 0 ELSE 1 END), "
				+ "		AVG(CASE "
				+ "				WHEN p.dataNascita IS NULL "
				+ "				THEN 0 "
				+ "				ELSE (YEAR(CURRENT_DATE()) - YEAR(p.dataNascita) - "
				+ "					 CASE "
				+ "						 WHEN MONTH(CURRENT_DATE()) < MONTH(p.dataNascita) "
				+ "				 		 	  OR (MONTH(CURRENT_DATE()) = MONTH(p.dataNascita) "
				+ "					 		 	  AND DAY(CURRENT_DATE()) < DAY(p.dataNascita)) "
				+ "						 THEN 1 "
				+ "						 ELSE 0 "
				+ " 				 END) "
				+ "			END)"
				+ "		) "
				+ "FROM Studente s "
				+ "LEFT JOIN s.corsi c "
				+ "LEFT JOIN c.professore p "
				+ "GROUP BY s.id",
				StudenteDTO.class);
				
		return studenteDTOquery.getResultList();
	}
	
	public StudenteDTO recuperaStudenteDTO(Integer id) {
		
		TypedQuery<StudenteDTO> studenteDTOquery = em.createQuery(
				"SELECT new it.cybsec.models.dtos.StudenteDTO("
				+ "		s.nome, "
				+ "		s.cognome, "
				+ "		YEAR(CURRENT_DATE()) - YEAR(s.dataNascita) - "
				+ "		CASE "
				+ "			WHEN MONTH(CURRENT_DATE()) < MONTH(s.dataNascita) "
				+ "				 OR (MONTH(CURRENT_DATE()) = MONTH(s.dataNascita) "
				+ "					 AND DAY(CURRENT_DATE()) < DAY(s.dataNascita)) "
				+ "			THEN 1 "
				+ "			ELSE 0 "
				+ " 	END, "
				+ "		COUNT(c), "
				+ "		AVG(YEAR(CURRENT_DATE()) - YEAR(c.professore.dataNascita) - "
				+ "		CASE "
				+ "			WHEN MONTH(CURRENT_DATE()) < MONTH(c.professore.dataNascita) "
				+ "				 OR (MONTH(CURRENT_DATE()) = MONTH(c.professore.dataNascita) "
				+ "					 AND DAY(CURRENT_DATE()) < DAY(c.professore.dataNascita)) "
				+ "			THEN 1 "
				+ "			ELSE 0 "
				+ " 	END)) "
				+ "FROM Studente s "
				+ "JOIN s.corsi c "
				+ "WHERE s.id = :id "
				+ "GROUP BY s.id",
				StudenteDTO.class);
		studenteDTOquery.setParameter("id", id);
				
		return studenteDTOquery.getSingleResult();
	}

}
