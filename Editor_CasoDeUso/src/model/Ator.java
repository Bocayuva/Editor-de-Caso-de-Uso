package model;

public class Ator {

	/* Vareaveis da Class */
	
	private int idProjeto;
	private int idAtor;
	private String nomeAtor;

	/* Construtor Default */
	
	public Ator() {
		this.idProjeto = 0;
		this.idAtor = 0;
		this.nomeAtor = null;
	}
	
	/* Metodos public */

	//GET
	public String getNomeAtor() {
		return nomeAtor;
	}
	public int getIdProjeto() {
		return idProjeto;
	}
	
	public int getIdAtor() {
		return idAtor;
	}
	//SET
	public void setNomeAtor(String ator) {
		this.nomeAtor = ator;
	}
	public void setIdProjeto(int idProjeto) {
		
		this.idProjeto = idProjeto;
	}

	public void setIdAtor(int idAtor) {
		this.idAtor = idAtor;
	}
}
