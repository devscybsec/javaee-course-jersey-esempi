package it.cybsec.utils;

import java.util.*;
import java.util.function.Consumer;

import javax.persistence.*;
import javax.persistence.criteria.*;

public interface DataAccessObject<O,K> {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("courses-jpa");
		
	default void execute(Consumer<EntityManager> action) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			action.accept(em);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	default void executeInsert(O obj) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	default void executeUpdate(O obj) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	default void executeDelete(O obj) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	EntityManager getEntityManager();
	O recupera(K id);
	List<O> recupera();
	List<O> recupera(CriteriaQuery<O> query);
	void nuovo(O obj) throws Exception;
	void salva(O obj) throws Exception;
	void elimina(O obj) throws Exception;
	
}
