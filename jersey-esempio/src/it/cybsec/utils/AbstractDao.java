package it.cybsec.utils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public abstract class AbstractDao<O, K>  implements DataAccessObject<O, K> {

	protected EntityManager em;

	public AbstractDao() {
		em = emf.createEntityManager();
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}	
	
	@Override
	public void nuovo(O obj) throws Exception  {
		execute(entityManager -> entityManager.persist(obj));
	}

	@Override
	public void salva(O obj) throws Exception  {
		execute(entityManager -> entityManager.merge(obj));
	}

	@Override
	public void elimina(O obj) throws Exception  {
		execute(entityManager -> entityManager.remove(obj));
	}
	
}
