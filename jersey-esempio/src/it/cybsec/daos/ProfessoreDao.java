package it.cybsec.daos;

import java.util.*;

import javax.persistence.criteria.*;

import it.cybsec.models.Professore;
import it.cybsec.utils.AbstractDao;

public class ProfessoreDao extends AbstractDao<Professore, Integer> {

	public ProfessoreDao() { 
		super();
	}
	
	@Override
	public Professore recupera(Integer id) {
		return em.find(Professore.class, id);
	}

	@Override
	public List<Professore> recupera() {
		em.clear();
		CriteriaQuery<Professore> query = getCriteriaBuilder().createQuery(Professore.class);
		query.select(query.from(Professore.class));
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Professore> recupera(CriteriaQuery<Professore> query) {
		em.clear();
		return em.createQuery(query).getResultList();
	}
	
	@Override
	public void elimina(Professore professore) throws Exception {
		super.elimina(professore);
		professore.setCorsi(null);
	}

}
