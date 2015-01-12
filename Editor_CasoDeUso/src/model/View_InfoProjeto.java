package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class View_InfoProjeto {

	public String nomeDoProjeto;
	public Integer qtdAtores;
	public Integer qtdUC;
	public Integer qtdEscopo;
	public Date dataCriacao;
	public Date dataModificacao;
	
	//GET
	public String getNomeDoProjeto() {
		return nomeDoProjeto;
	}
	public Integer getQtdAtores() {
		return qtdAtores;
	}
	public Integer getQtdUC() {
		return qtdUC;
	}
	public Integer getQtdEscopo() {
		return qtdEscopo;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public Date getDataModificacao() {
		return dataModificacao;
	}
	
	//SET
	public void setNomeDoProjeto(String setNomeDoProjeto) {
		this.nomeDoProjeto = setNomeDoProjeto;
	}
	public void setQtdAtores(Integer setQtdAtores) {
		this.qtdAtores = setQtdAtores;
	}
	public void setQtdUC(Integer qtdUC) {
		this.qtdUC = qtdUC;
	}
	public void setQtdEscopo(Integer qtdEscopo) {
		this.qtdEscopo = qtdEscopo;
	}
	public void setDataCriacao(Date dataCriacao) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dataCriacao = formatter.parse(formatter.format(dataCriacao));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setDataModificacao(Date dataModificacao) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dataModificacao = formatter.parse(formatter.format(dataModificacao));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
