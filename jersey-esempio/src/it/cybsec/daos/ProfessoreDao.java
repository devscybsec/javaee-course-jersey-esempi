package it.cybsec.daos;

import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import it.cybsec.models.Professore;
import it.cybsec.models.dtos.ProfessoreDTO;
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
	
	public List<ProfessoreDTO> recuperaProfessoreDTO() {
		TypedQuery<ProfessoreDTO> professoreDTOquery = em.createNamedQuery("ProfessoreDTO", ProfessoreDTO.class);
		return professoreDTOquery.getResultList();
	}
	
	public ProfessoreDTO recuperaProfessoreDTO(Integer id) {
		TypedQuery<ProfessoreDTO> professoreDTOquery = em.createNamedQuery("ProfessoreDTOSingle", ProfessoreDTO.class);
		professoreDTOquery.setParameter("id", id);
		return professoreDTOquery.getSingleResult();
	}

}
