package it.cybsec.models.dtos;

public class ProfessoreDTO {

	private String nome;
	private String cognome;
	private Integer eta;
	private Long numeroCorsi;
	private Long numeroStudentiTotali;

	public ProfessoreDTO(String nome, String cognome, Integer eta, Long numeroCorsi, Long numeroStudentiTotali) {
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.numeroCorsi = numeroCorsi;
		this.numeroStudentiTotali = numeroStudentiTotali;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Integer getEta() {
		return eta;
	}
	public void setEta(Integer eta) {
		this.eta = eta;
	}
	public Long getNumeroCorsi() {
		return numeroCorsi;
	}
	public void setNumeroCorsi(Long numeroCorsi) {
		this.numeroCorsi = numeroCorsi;
	}
	public Long getNumeroStudentiTotali() {
		return numeroStudentiTotali;
	}
	public void setNumeroStudentiTotali(Long numeroStudentiTotali) {
		this.numeroStudentiTotali = numeroStudentiTotali;
	}
	
}
