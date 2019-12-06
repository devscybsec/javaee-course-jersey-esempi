package it.cybsec.api;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import it.cybsec.daos.*;
import it.cybsec.models.*;

@Path("/corsi")
public class CorsoJerseyController {

	private CorsoDao dao;
	private ProfessoreDao daoProfessore;
	private StudenteDao daoStudente;
	
	public CorsoJerseyController() {
		this.dao = new CorsoDao();
		this.daoProfessore = new ProfessoreDao();
		this.daoStudente = new StudenteDao();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Corso findById(@PathParam("id") Integer id) {
		return this.dao.recupera(id);
	}
	
	@GET
	@Path("/{id}/professore")
	@Produces(MediaType.APPLICATION_JSON)
	public Professore findByIdGetProfessore(@PathParam("id") Integer id) {
		return this.dao.recupera(id).getProfessore();
	}
	
	@GET
	@Path("/{id}/studenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Studente> findByIdGetStudenti(@PathParam("id") Integer id) {
		return this.dao.recupera(id).getStudenti();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Corso> findByNomeProfessoreStudente(
			@QueryParam("nome") String nome, 
			@QueryParam("idprofessore") Integer idProfessore, 
			@QueryParam("idstudente") Integer idStudente
			)
	{
		CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Corso> query = cb.createQuery(Corso.class);
		Root<Corso> corso = query.from(Corso.class);
		query.select(corso);
		Predicate exp = null;
		if (nome != null)
			exp = cb.equal(corso.get("nome"), nome);
		if (idProfessore != null) {
			Professore professore = daoProfessore.recupera(idProfessore);
			Predicate exp2 = cb.equal(corso.get("professore"), professore);
			exp = (exp != null) ? cb.and(exp, exp2) : exp2;
		}
		if (idStudente != null) {
			Studente studente =  daoStudente.recupera(idStudente);
			Predicate exp2 = cb.isMember(studente, corso.get("studenti"));
			exp = (exp != null) ? cb.and(exp, exp2) : exp2;
		}
		if (exp != null)
			query.where(exp);
		return this.dao.recupera(query);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Corso insertCorso(Corso corso) throws Exception {
		this.dao.nuovo(corso);
		return corso;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Corso updateCorso(Corso corso, @Context HttpServletResponse response) throws Exception {
		if (corso.getId() == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    response.flushBuffer();
		    return null;
		}
			//throw new NullPointerException("Inserire l'ID dell'oggetto da modificare.");
		this.dao.salva(corso);
		return corso;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Corso updateCorsoById(@PathParam("id") Integer id, Corso corso) throws Exception {
		corso.setId(id);
		this.dao.salva(corso);
		return corso;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Corso deleteCorsoById(@PathParam("id") Integer id) throws Exception {
		Corso corso = dao.recupera(id);
		this.dao.elimina(corso);
		return corso;
	}
}
