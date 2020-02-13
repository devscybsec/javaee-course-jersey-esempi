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

import it.cybsec.daos.ProfessoreDao;
import it.cybsec.models.Corso;
import it.cybsec.models.Professore;
import it.cybsec.models.dtos.ProfessoreDTO;

@Path("/professori")
public class ProfessoreJerseyController {
	
	private ProfessoreDao dao;
	
	public ProfessoreJerseyController() {
		this.dao = new ProfessoreDao();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professore findById(@PathParam("id") Integer id) {
		return this.dao.recupera(id);
	}
	
	@GET
	@Path("/{id}/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfessoreDTO findByIdStatistics(@PathParam("id") Integer id) {
		return this.dao.recuperaProfessoreDTO(id);
	}
	
	@GET
	@Path("/{id}/corsi")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Corso> findByIdGetCorsi(@PathParam("id") Integer id) {
		return this.dao.recupera(id).getCorsi();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professore> findByNomeCognome(
			@QueryParam("nome") String nome, 
			@QueryParam("cognome") String cognome
			) {
		CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<Professore> query = cb.createQuery(Professore.class);
		Root<Professore> professore = query.from(Professore.class);
		query.select(professore);
		Predicate exp = null;
		if (nome != null)
			exp = cb.equal(professore.get("nome"), nome);
		if (cognome != null) {
			Predicate exp2 = cb.equal(professore.get("cognome"), cognome);
			exp = (exp != null) ? cb.and(exp, exp2) : exp2;
		}
		if (exp != null)
			query.where(exp);
		return this.dao.recupera(query);
	}
	
	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProfessoreDTO> findStatistics() {
		return this.dao.recuperaProfessoreDTO();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professore insertProfessore(Professore professore) throws Exception {
		this.dao.nuovo(professore);
		return professore;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professore updateProfessore(Professore professore) throws Exception {
		if (professore.getId() == null)
			throw new NullPointerException("Inserire l'ID dell'oggetto da modificare.");
		this.dao.salva(professore);
		return professore;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professore updateProfessoreById(@PathParam("id") Integer id, Professore professore) throws Exception {
		professore.setId(id);
		this.dao.salva(professore);
		return professore;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professore deleteProfessoreById(@PathParam("id") Integer id) throws Exception {
		Professore professore = dao.recupera(id);
		this.dao.elimina(professore);
		return professore;
	}
}
