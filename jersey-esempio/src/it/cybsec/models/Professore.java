package it.cybsec.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

import it.cybsec.utils.Date2StringSerializer;
import it.cybsec.utils.String2DateDeserializer;


/**
 * The persistent class for the professori database table.
 * 
 */
@Entity
@Table(name="professori")
public class Professore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cognome;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nascita")
	@JsonSerialize(using = Date2StringSerializer.class, as=Date.class)
	@JsonDeserialize(using = String2DateDeserializer.class, as=Date.class)
	private Date dataNascita;

	private String nome;

	//bi-directional many-to-one association to Corso
	@OneToMany(mappedBy="professore")
	@JsonIgnoreProperties({"professore"})
	private List<Corso> corsi;

	public Professore() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return this.dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Corso> getCorsi() {
		return this.corsi;
	}

	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	public Corso addCorsi(Corso corsi) {
		getCorsi().add(corsi);
		corsi.setProfessore(this);

		return corsi;
	}

	public Corso removeCorsi(Corso corsi) {
		getCorsi().remove(corsi);
		corsi.setProfessore(null);

		return corsi;
	}

}