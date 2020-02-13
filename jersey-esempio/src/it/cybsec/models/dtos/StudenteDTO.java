package it.cybsec.models.dtos;

public class StudenteDTO {
	
	private String nome;
	private String cognome;
	private Integer eta;
	private Long numeroCorsiSeguiti;
	private Double etaMediaProfessori;
	
	public StudenteDTO() {}
	
	public StudenteDTO(
			String nome,
			String cognome,
			int eta,
			long numeroCorsiSeguiti, 
			double etaMediaProfessori) {	
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.numeroCorsiSeguiti = numeroCorsiSeguiti;
		this.etaMediaProfessori = etaMediaProfessori;	
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

	public Long getNumeroCorsiSeguiti() {
		return numeroCorsiSeguiti;
	}

	public void setNumeroCorsiSeguiti(Long numeroCorsiSeguiti) {
		this.numeroCorsiSeguiti = numeroCorsiSeguiti;
	}

	public Double getEtaMediaProfessori() {
		return etaMediaProfessori;
	}

	public void setEtaMediaProfessori(Double etaMediaProfessori) {
		this.etaMediaProfessori = etaMediaProfessori;
	}
	

}
