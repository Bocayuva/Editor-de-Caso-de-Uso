package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import view.tableModel.ProjetosTM;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Projeto_Carregar extends JPanel implements MouseListener, Observer {

	/* Vareaveis da Class */
	
	// Data
	private ProjetosTM data;
	private int indexTableSeleced;
	
	// Interface
	private JTable table;
	private JButton buttonExcluirProjeto;
	private JButton buttonCarregarProjeto;
	
	/* Construtor Default */

	public Projeto_Carregar() {
		super();
		this.table = null;
		this.data = null;
		this.indexTableSeleced = -1;
		this.data = null;
		ECUGUI.addPanelObserver(this);
		inicializaPanel();
	}
	
	/* Metodos Private */

	private void inicializaPanel() {
		setBackground(Color.white);
		setLayout(new BorderLayout());
		inicializaNorte();
		inicializaContent();
		inicializaSul();
	}

	private void inicializaNorte() {
		JLabel titulo = new JLabel("   Carregar Projeto");
		titulo.setHorizontalAlignment(SwingConstants.LEFT);
		titulo.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setForeground(Color.BLACK);
		add(titulo, BorderLayout.NORTH);
	}

	private void inicializaContent() {
		JPanel centro = new JPanel();
		centro.setBackground(Color.white);
		add(centro, BorderLayout.CENTER);

		inicializaTabela();
		
		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	private void inicializaSul() {
		JPanel sul = new JPanel();
		sul.setBackground(Color.white);
		add(sul, BorderLayout.SOUTH);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setName("Cancelar");
		buttonCancelar.addMouseListener(this);
		sul.add(buttonCancelar);

		buttonExcluirProjeto = new JButton("Excluir Projeto");
		buttonExcluirProjeto.setName("ExcluirProjeto");
		buttonExcluirProjeto.addMouseListener(this);
		buttonExcluirProjeto.setEnabled(false);
		sul.add(buttonExcluirProjeto);
		
		buttonCarregarProjeto = new JButton("Carregar Projeto");
		buttonCarregarProjeto.setName("CarregarProjeto");
		buttonCarregarProjeto.addMouseListener(this);
		buttonCarregarProjeto.setEnabled(false);
		sul.add(buttonCarregarProjeto);
	}

	private void inicializaTabela() {
			
		data =  ECUGUI.getlistagemProjetos();		
		table = new JTable(data);
		for (int c = 0; c < table.getColumnCount(); c++) {
			Class<?> col_class = table.getColumnClass(c);
			table.setDefaultEditor(col_class, null); // remove editor
		}
		table.setFillsViewportHeight(true);
		table.setName("TabelaDeProjetos");
		table.addMouseListener(this);
	}
	
	private int getIDprojeto(int index){
		return (int) table.getValueAt(index, 0) ;
	}
	
	/* Metodos Public */
	
	public void update(Observable o, Object arg) {
		this.removeAll();
		inicializaPanel();
		revalidate();
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Cancelar")) {
			ECUGUI.IrInicialView();
		}
		if (e.getComponent().getName().equals("ExcluirProjeto")) {
			ECUGUI.ExcluirProjeto(getIDprojeto(indexTableSeleced));
		}
		if (e.getComponent().getName().equals("CarregarProjeto")) {
			ECUGUI.IrProjeto(getIDprojeto(indexTableSeleced));
		}
		if (e.getComponent().getName().equals("TabelaDeProjetos")) {
	        indexTableSeleced = table.getSelectedRow();
			if(indexTableSeleced != -1){
				buttonCarregarProjeto.setEnabled(true);
				buttonExcluirProjeto.setEnabled(true);
			}
		}
	}

	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }
}
