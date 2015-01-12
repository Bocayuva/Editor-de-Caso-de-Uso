package view.tableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.View_ListProjeto;

@SuppressWarnings("serial")
public class ProjetosTM extends AbstractTableModel {

	/* Vareaveis da Class */

	private List<View_ListProjeto> dados;
	private String[] colunas = { "ID Projeto", "Nome Projeto", "Data Criacao", "Data Modificacao" };

	/* Construtor Default */

	public ProjetosTM() {
		dados = new ArrayList<View_ListProjeto>();
	}
	
	public ProjetosTM(ArrayList<View_ListProjeto> listagem) {
		dados = listagem;
		this.fireTableDataChanged();
	}
	/* Metodos Public */
	
	public View_ListProjeto get(int linha){
	    return this.dados.get(linha);
	}
	
	public void addRow(View_ListProjeto p) {
		this.dados.add(p);
		this.fireTableDataChanged();
	}

	public void removeRow(int linha){
	    this.dados.remove(linha);
	    this.fireTableRowsDeleted(linha, linha);
	}
	
	public String getColumnName(int num) {
		return this.colunas[num];
	}

	public int getRowCount() {
		return dados.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public Object getValueAt(int linha, int coluna) {
		DateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
		switch (coluna) {
		case 0:
			return dados.get(linha).getIdProjeto();
		case 1:
			return dados.get(linha).getNomeProjeto();
		case 2:
			return formatter.format(dados.get(linha).getDataCriacao());
		case 3:
			return formatter.format(dados.get(linha).getDataModificacao());
		}
		return null;
	}
}