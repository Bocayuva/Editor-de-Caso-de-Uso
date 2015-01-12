package model;

public class Extensao {

	/* Vareaveis da Class */

	private int idExtensao;
	private String tipoExtensao;
	private String informacaoExtensao;
	private int idCasoDeUsoExtensao;
	private int idCasoDeUsoMaster;
	private int idFluxoPosicao;
	private CasoDeUso casoExtensao,casoMaster;
	private Fluxo fluxo;
	
	
	/* Construtor Default */

	public Extensao() {
		super();
		this.setIdExtensao(0);
		this.tipoExtensao = null;
		this.informacaoExtensao = null;
		this.idCasoDeUsoExtensao = 0;
		this.idFluxoPosicao = 0;
		this.idCasoDeUsoMaster = 0;
	}

	/* Metodos public */

	// GET
	public int getIdExtensao() {
		return idExtensao;
	}
	public String getInformacaoExtensao() {
		return informacaoExtensao;
	}
	public int getIdCasoDeUsoExtensao() {
		return idCasoDeUsoExtensao;
	}
	public int getIdCasoDeUsoMaster() {
		return idCasoDeUsoMaster;
	}
	public int getIdFluxoPosicao() {
		if(fluxo == null)
			return idFluxoPosicao;
		else
			return fluxo.getIdFluxo();
	}
	public String getTipoExtensao() {
		return tipoExtensao;
	}
	public CasoDeUso getCasoDeUsoExtensao() {
		return casoExtensao;
	}
	public CasoDeUso getCasoDeUsoMaster() {
		return casoMaster;
	}
	public Fluxo getFluxo() {
		return fluxo;
	}
	//SET
	public void setIdFluxoPosicao(int idFluxo) {
		this.idFluxoPosicao = idFluxo;
	}
	public void setIdExtensao(int idExtensao) {
		this.idExtensao = idExtensao;
	}
	public void setInformacaoExtensao(String informacaoExtensao) {
		this.informacaoExtensao = informacaoExtensao;
	}
	public void setIdCasoDeUsoMaster(int casoDeUsoMaster) {
		this.idCasoDeUsoMaster = casoDeUsoMaster;
	}
	public void setIdCasoDeUsoExtensao(int casoDeUsoExtensao) {
		this.idCasoDeUsoExtensao = casoDeUsoExtensao;
	}
	public void setTipoExtensao(String tipoExtensao) {
		this.tipoExtensao = tipoExtensao;
	}



	



	public void setCasoDeUsoExtensao(CasoDeUso casoExtensao) {
		this.casoExtensao = casoExtensao;
		this.idCasoDeUsoExtensao = casoExtensao.getIdCasoDeUso();
	}
	public void setCasoDeUsoMaster(CasoDeUso casoMaster) {
		this.casoMaster = casoMaster;
		this.idCasoDeUsoMaster = casoMaster.getIdCasoDeUso();
	}
	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
		this.idFluxoPosicao = fluxo.getIdFluxo();
	}
	
}
