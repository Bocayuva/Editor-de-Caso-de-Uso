package view.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.View_ListCasoDeUso;

@SuppressWarnings("serial")
public class CasoDeUsoTM extends AbstractTableModel {

	/* Vareaveis da Class */

	private List<View_ListCasoDeUso> dados;
	private String[] colunas = { "idCasoDeUso", "nomeCasoDeUso", "escopo",
			"qtdInclude", "qtdExtencoes" };

	/* Construtor Default */

	public CasoDeUsoTM() {
		dados = new ArrayList<View_ListCasoDeUso>();
	}
	
	public CasoDeUsoTM(ArrayList<View_ListCasoDeUso> listagem) {
		dados = listagem;
		this.fireTableDataChanged();
	}
	
	/* Metodos Public */
	
	public View_ListCasoDeUso get(int linha){
	    return this.dados.get(linha);
	}
	
	public void addRow(View_ListCasoDeUso p) {
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
		switch (coluna) {
		case 0:
			return dados.get(linha).getIdCasoDeUso();
		case 1:
			return dados.get(linha).getNomeCasoDeUso();
		case 2:
			return dados.get(linha).getEscopo();
		case 3:
			return dados.get(linha).getQtdInclude();
		case 4:
			return dados.get(linha).getQtdExtencoes();
		}
		return null;
	}
}
