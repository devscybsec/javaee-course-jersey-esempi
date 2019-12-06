package it.cybsec.daos;

import java.util.*;

import javax.persistence.criteria.*;

import it.cybsec.models.Studente;
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

}
