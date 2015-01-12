package model;

public class Fluxo {

	/* Vareaveis da Class */

	private int idFluxo;
	private int idCasoDeUso;
	private int idFluxoMaster;
	private int posicaoFluxo;
	private Boolean tipoAternativo;
	private String informacaoFluxo;

	/* Construtor Default */

	public Fluxo() {
		this.idFluxoMaster = 0;
		this.idFluxo = 0;
		this.posicaoFluxo = 0;
		this.tipoAternativo = false;
		this.informacaoFluxo = null;
	}

	public Fluxo(int idCasoDeUso, int posicaoFluxo) {
		this.idCasoDeUso = idCasoDeUso;
		this.posicaoFluxo = posicaoFluxo;
		this.idFluxoMaster = 0;
		this.idFluxo = 0;
		this.informacaoFluxo = null;
		this.tipoAternativo = false;
	}
	
	public Fluxo(int idCasoDeUso, int idFluxoMaster, int posicaoFluxo) {
		this.idCasoDeUso = idCasoDeUso;
		this.idFluxoMaster = idFluxoMaster;
		this.posicaoFluxo = posicaoFluxo;
		this.idFluxo = 0;
		this.informacaoFluxo = null;
		this.tipoAternativo = false;
	}

	/* Metodos public */

	// GET
	public int getIdCasoDeUso() {
		return idCasoDeUso;
	}

	public int getIdFluxoMaster() {
		return idFluxoMaster;
	}

	public int getIdFluxo() {
		return idFluxo;
	}

	public int getPosicaoFluxo() {
		return posicaoFluxo;
	}

	public Boolean getTipoAternativo() {
		return tipoAternativo;
	}

	public String getInformacaoFluxo() {
		return informacaoFluxo;
	}

	// SET
	public void setIdCasoDeUso(int idCasoDeUso) {
		this.idCasoDeUso = idCasoDeUso;
	}

	public void setIdFluxoMaster(int idMasterFluxo) {
		this.idFluxoMaster = idMasterFluxo;
	}

	public void setIdFluxo(int idFluxo) {
		this.idFluxo = idFluxo;
	}

	public void setPosicao(int posicao) {
		this.posicaoFluxo = posicao;
	}

	public void setTipoAternativo(Boolean tipoAternativo) {
		this.tipoAternativo = tipoAternativo;
	}

	public void setInformacao(String informacao) {
		this.informacaoFluxo = informacao;
	}
}
