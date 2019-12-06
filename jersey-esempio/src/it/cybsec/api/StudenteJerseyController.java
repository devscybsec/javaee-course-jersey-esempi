package it.cybsec.api;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.cybsec.daos.StudenteDao;
import it.cybsec.models.Corso;
import it.cybsec.models.Studente;

@Path("/studenti")
public class StudenteJerseyController {

	private StudenteDao dao;
	
	public StudenteJerseyController() {
		this.dao = new StudenteDao();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Studente findById(@PathParam("id") Integer id) {
		return this.dao.recupera(id);
	}
	
	@GET
	@Path("/{id}/corsi")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Corso> findByIdGetCorsi(@PathParam("id") Integer id) {
		return this.dao.recupera(id).getCorsi();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Studente> findByNomeCognome(
			@QueryParam("nome") String nome, 
			@QueryParam("cognome") String cognome
			) {
		CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Studente> query = cb.createQuery(Studente.class);
		Root<Studente> studente = query.from(Studente.class);
		query.select(studente);
		Predicate exp = null;
		if (nome != null)
			exp = cb.equal(studente.get("nome"), nome);
		if (cognome != null) {
			Predicate exp2 = cb.equal(studente.get("cognome"), cognome);
			exp = (exp != null) ? cb.and(exp, exp2) : exp2;
		}
		if (exp != null)
			query.where(exp);
		return this.dao.recupera(query);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Studente insertStudente(Studente studente) throws Exception {
		this.dao.nuovo(studente);
		return studente;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Studente updateStudente(Studente studente) throws Exception {
		if (studente.getId() == null)
			throw new NullPointerException("Inserire l'ID dell'oggetto da modificare.");
		this.dao.salva(studente);
		return studente;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Studente updateStudenteById(@PathParam("id") Integer id, Studente studente) throws Exception {
		studente.setId(id);
		this.dao.salva(studente);
		return studente;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Studente deleteStudenteById(@PathParam("id") Integer id) throws Exception {
		Studente studente = dao.recupera(id);
		this.dao.elimina(studente);
		return studente;
	}
	
}
