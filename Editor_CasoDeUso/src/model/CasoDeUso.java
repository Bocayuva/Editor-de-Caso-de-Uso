package model;

public class CasoDeUso {

	/* Vareaveis da Class */

	private int idProjeto;
	private int idCasoDeUso;
	private String nomeCasoDeUso;
	private String objetivo;
	private String escopo;
	private String nivel;
	private Ator atorPrimario;
	private Ator atorSuporte;
	private String preCondicao;
	private String posCondicao;

	/* Construtor Default */

	public CasoDeUso() {
		this.nomeCasoDeUso = null;
		this.objetivo = null;
		this.escopo = null;
		this.nivel = null;
		this.atorPrimario = new Ator();
		this.atorSuporte = new Ator();
		this.preCondicao = null;
		this.posCondicao = null;
		this.idProjeto = 0;
		this.idCasoDeUso = 0;
	}

	/* Metodos public */

	// GET
	public int getIdProjeto() {
		return idProjeto;
	}

	public int getIdCasoDeUso() {
		return idCasoDeUso;
	}

	public String getNomeCasoDeUso() {
		return nomeCasoDeUso;
	}

	public void setNomeCasoDeUso(String nome) {
		this.nomeCasoDeUso = nome;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public String getEscopo() {
		return escopo;
	}

	public String getNivel() {
		return nivel;
	}

	public Ator getClassAtorPrimario() {
		return this.atorPrimario;
	}

	public Ator getClassAtorSuporte() {
		return this.atorSuporte;
	}
	
	public int getIdAtorPrimario() {
		return atorPrimario.getIdAtor();
	}

	public int getIdAtorSuporte() {
		return atorSuporte.getIdAtor();
	}

	public String getAtorPrimario() {
		return atorPrimario.getNomeAtor();
	}

	public String getAtorSuporte() {
		return atorSuporte.getNomeAtor();
	}

	public String getPreCondicao() {
		return preCondicao;
	}

	public String getPosCondicao() {
		return posCondicao;
	}

	// SET
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public void setIdCasoDeUso(int idCasoDeUso) {
		this.idCasoDeUso = idCasoDeUso;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public void setAtorPrimario(Ator atorPrimario) {
		this.atorPrimario = null;
		this.atorPrimario = atorPrimario;
	}

	public void setAtorSuporte(Ator atorSuporte) {
		this.atorSuporte = null;
		this.atorSuporte = atorSuporte;
	}
	
	public void setIdAtorPrimario(int idAtorPrimario) {
		this.atorPrimario.setIdAtor(idAtorPrimario);
	}

	public void setIdAtorSuporte(int idAtorSuporte) {
		this.atorSuporte.setIdAtor(idAtorSuporte);
	}

	public void setPreCondicao(String preCondicao) {
		this.preCondicao = preCondicao;
	}

	public void setPosCondicao(String posCondicao) {
		this.posCondicao = posCondicao;
	}

	@Override
	public String toString() {
		return "CasoDeUso [idProjeto=" + idProjeto + ", idCasoDeUso="
				+ idCasoDeUso + ", nomeCasoDeUso=" + nomeCasoDeUso
				+ ", objetivo=" + objetivo + ", escopo=" + escopo + ", nivel="
				+ nivel + ", atorPrimario=" + atorPrimario.getIdAtor() + ", atorSuporte="
				+ atorSuporte.getIdAtor() + ", preCondicao=" + preCondicao
				+ ", posCondicao=" + posCondicao + "]";
	}
}
