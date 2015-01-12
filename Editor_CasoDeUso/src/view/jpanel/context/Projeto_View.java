package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import model.View_InfoProjeto;
import view.tableModel.CasoDeUsoTM;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Projeto_View extends JPanel implements MouseListener, Observer {

	/* Vareaveis da Class */
	
	// Data
	private int idProjeto;
	private int indexTableSeleced;
	private int qtdUC;
	private int qtdEscopo;
	private int qtdAtores;
	private String nomeDoProjeto;
	@SuppressWarnings("unused")
	private String dataCriacao;
	@SuppressWarnings("unused")
	private String dataModificacao;
	private CasoDeUsoTM data;
	
	// Interface
	private JTable table;
	private JButton buttonCancelar;
	private JButton buttonExcluirProjeto;
	private JButton buttonCarregarProjeto;
	
	/* Construtor Default */

	public Projeto_View(int id) {
		this.idProjeto = id;
		this.indexTableSeleced = -1;
		this.qtdUC = 0;
		this.qtdEscopo = 0;
		this.nomeDoProjeto = null;
		this.dataCriacao = null;
		this.dataModificacao = null;
		this.data = new CasoDeUsoTM();
		ECUGUI.addPanelObserver(this);
		inicializaPanel();
	}

	/* Metodos Private */

	private void inicializaPanel() {
		getInformacao();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		inicializaNorte();
		inicializaContent();
		inicializaSouth();
	}

	private void inicializaNorte() {

		JLabel titulo = new JLabel("Projeto: " + nomeDoProjeto);
		titulo.setHorizontalAlignment(SwingConstants.NORTH_EAST);
		titulo.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setForeground(Color.BLACK);
		add(titulo, BorderLayout.NORTH);
	}

	private void inicializaContent() {

		
		JLabel dataCriacaoLabel = new JLabel("");
		JLabel dataModificacaoLabel = new JLabel("");
		JLabel qtdUCLabel = new JLabel("Qtd Total UC: " + this.qtdUC);
		JLabel qtdAtores = new JLabel("Qtd Atores: " + this.qtdAtores);
		JLabel qtdEscopoLabel = new JLabel("Qtd Total Escopo: " + this.qtdEscopo);

		JPanel centroInfo1 = new JPanel();
		centroInfo1.setBackground(Color.white);
		centroInfo1.setLayout(new GridLayout(1, 2));
		centroInfo1.add(dataCriacaoLabel);
		centroInfo1.add(dataModificacaoLabel);
		JPanel centroInfo2 = new JPanel();
		centroInfo2.setBackground(Color.white);
		centroInfo2.setLayout(new GridLayout(1, 2));
		centroInfo2.add(qtdAtores);
		centroInfo2.add(qtdUCLabel);
		centroInfo2.add(qtdEscopoLabel);

		JPanel centroInfo = new JPanel();
		centroInfo.setBackground(Color.white);
		centroInfo.setLayout(new GridLayout(2, 1));
		centroInfo.add(centroInfo1);
		centroInfo.add(centroInfo2);

		inicializaTabela();
		JScrollPane scrollPane = new JScrollPane(table);

		JPanel centro = new JPanel();
		centro.setBackground(Color.white);
		centro.setLayout(new BorderLayout());
		centro.add(centroInfo, BorderLayout.NORTH);
		centro.add(scrollPane, BorderLayout.CENTER);
		add(centro);
	}

	private void inicializaSouth() {
		JPanel south = new JPanel();
		south.setLayout(new GridLayout());
		south.setBackground(Color.white);
		add(south, BorderLayout.SOUTH);

		buttonCancelar = new JButton("Fechar Projeto");
		buttonCancelar.setName("FecharProjeto");
		buttonCancelar.addMouseListener(this);
		south.add(buttonCancelar);

		buttonExcluirProjeto = new JButton("Excluir Caso de Uso");
		buttonExcluirProjeto.setName("ExcluirCasoDeUso");
		buttonExcluirProjeto.addMouseListener(this);
		buttonExcluirProjeto.setEnabled(false);
		south.add(buttonExcluirProjeto);

		buttonCarregarProjeto = new JButton("Visualizar Caso de Uso");
		buttonCarregarProjeto.setName("VisualizarCasoDeUso");
		buttonCarregarProjeto.addMouseListener(this);
		buttonCarregarProjeto.setEnabled(false);
		south.add(buttonCarregarProjeto);
	}

	private void inicializaTabela() {		
		table = new JTable(data);
		for (int c = 0; c < table.getColumnCount(); c++) {
			Class<?> col_class = table.getColumnClass(c);
			table.setDefaultEditor(col_class, null); // remove editor
		}
		table.setFillsViewportHeight(true);
		table.setName("TabelaDeProjetos");
		table.addMouseListener(this);
	}

	private int getIdCasoDeUso(int index) {
		return (int) table.getValueAt(index, 0);
	}

	private void getInformacao() {
		View_InfoProjeto info 	= ECUGUI.getInformacoesProjeto(idProjeto);
		nomeDoProjeto 	= info.getNomeDoProjeto();
		qtdAtores 		= info.getQtdAtores();
		qtdUC 			= info.getQtdUC();
		qtdEscopo 		= info.getQtdEscopo();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dataCriacao 	= formatter.format(info.getDataCriacao());
		dataModificacao = formatter.format(info.getDataModificacao());
		
		CasoDeUsoTM tmp =  ECUGUI.getListagemCasoDeUsoDoProjeto(idProjeto);
		if(tmp != null)
			data = tmp;
	}

	/* Metodos Public */

	public void update(Observable observ, Object object) {
		this.removeAll();
		inicializaPanel();
		revalidate();
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("FecharProjeto")) {
			ECUGUI.IrInicialView();
		}
		if (e.getComponent().getName().equals("ExcluirCasoDeUso")) {
			if(indexTableSeleced!=-1) 
				ECUGUI.ExcluirCasoDeUso(getIdCasoDeUso(indexTableSeleced));
		}
		if (e.getComponent().getName().equals("VisualizarCasoDeUso")) {
			if(indexTableSeleced!=-1) {
				ECUGUI.IrCasoDeUso(getIdCasoDeUso(indexTableSeleced));
			}
		}
		if (e.getComponent().getName().equals("TabelaDeProjetos")) {
			indexTableSeleced = table.getSelectedRow();
			if(indexTableSeleced!=-1) {
				buttonExcluirProjeto.setEnabled(true);
				buttonCarregarProjeto.setEnabled(true);
			}
		}
	}

	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }
}