package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Projeto {
	
	/* Vareaveis da Class */
	
	private int idProjeto;
	private String nomeProjeto;
	private Date dataCriacao;
	private Date dataModificacao;
	
	/* Construtor Default */
	
	public Projeto() {
		this.idProjeto = -1;
		this.nomeProjeto = null;
		this.dataCriacao = null;
		this.dataModificacao = null;
	}
	
	/* Metodos public */
	
	//GET
	public int getIdProjeto() {
		return idProjeto;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public Date getDataModificacao() {
		return dataModificacao;
	}
	
	//SET
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
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
