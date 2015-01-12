package model;

public class View_ListCasoDeUso {
	/* Vareaveis da Class */
	
	int idCasoDeUso;
	int qtdExtencoes;
	int qtdInclude;
	String nomeCasoDeUso;
	String escopo;
	
	/* Construtor Default */
	
	public View_ListCasoDeUso() {
		this.idCasoDeUso = 0;
		this.qtdExtencoes = 0;
		this.qtdInclude = 0;
		this.nomeCasoDeUso = null;
		this.escopo = null;
	}
	
	/* Metodos Public */
	
	//GET
	public int getIdCasoDeUso() {
		return idCasoDeUso;
	}
	public int getQtdExtencoes() {
		return qtdExtencoes;
	}

	public int getQtdInclude() {
		return qtdInclude;
	}
	public String getNomeCasoDeUso() {
		return nomeCasoDeUso;
	}
	public String getEscopo() {
		return escopo;
	}
	
	//SET
	public void setIdCasoDeUso(int idCasoDeUso) {
		this.idCasoDeUso = idCasoDeUso;
	}
	public void setQtdExtencoes(int qtdExtencoes) {
		this.qtdExtencoes = qtdExtencoes;
	}

	public void setQtdInclude(int qtdInclude) {
		this.qtdInclude = qtdInclude;
	}
	public void setNomeCasoDeUso(String nomeCasoDeUso) {
		this.nomeCasoDeUso = nomeCasoDeUso;
	}
	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}
}
