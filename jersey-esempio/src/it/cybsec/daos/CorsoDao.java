package it.cybsec.daos;

import java.util.*;

import javax.persistence.criteria.*;

import it.cybsec.models.Corso;
import it.cybsec.utils.AbstractDao;

public class CorsoDao extends AbstractDao<Corso, Integer> {

	public CorsoDao() { 
		super();
	}
	
	@Override
	public Corso recupera(Integer id) {
		return em.find(Corso.class, id);
	}

	@Override
	public List<Corso> recupera() {
		em.clear();
		CriteriaQuery<Corso> query = getCriteriaBuilder().createQuery(Corso.class);
		query.select(query.from(Corso.class));
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Corso> recupera(CriteriaQuery<Corso> query) {
		em.clear();
		return em.createQuery(query).getResultList();
	}
	
	@Override
	public void elimina(Corso corso) throws Exception {
		super.elimina(corso);
		corso.setStudenti(null);
	}

}
