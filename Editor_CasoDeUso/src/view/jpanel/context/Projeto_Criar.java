package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.ECUGUI;

@SuppressWarnings("serial")
public class Projeto_Criar extends JPanel implements MouseListener {
	
	/* Vareaveis da Class */

	// Interface
	private JTextField nomeProjeto;

	/* Construtor Default */
	
	public Projeto_Criar() {
		this.nomeProjeto = null;
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
		JLabel titulo = new JLabel("   Criar Projeto");
		titulo.setHorizontalAlignment(SwingConstants.LEFT);
		titulo.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		titulo.setHorizontalAlignment(JLabel.LEFT);
		titulo.setForeground(Color.BLACK);
		add(titulo, BorderLayout.NORTH);
	}

	private void inicializaContent() {
		JPanel centro = new JPanel();
		centro.setBackground(Color.white);
		add(centro, BorderLayout.CENTER);
		GridBagLayout gbl_centro = new GridBagLayout();
		gbl_centro.columnWidths = new int[] {139, 309, 2};
		gbl_centro.rowHeights = new int[] {0, 0, 1};
		gbl_centro.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centro.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		centro.setLayout(gbl_centro);
		
		JLabel lblNomeDoProjeto = new JLabel("Nome do Projeto:");
		GridBagConstraints gbc_lblNomeDoProjeto = new GridBagConstraints();
		gbc_lblNomeDoProjeto.insets = new Insets(0, 0, 0, 5);
		gbc_lblNomeDoProjeto.gridx = 0;
		gbc_lblNomeDoProjeto.gridy = 1;
		centro.add(lblNomeDoProjeto, gbc_lblNomeDoProjeto);
		
		nomeProjeto = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		centro.add(nomeProjeto, gbc_textField_1);
		nomeProjeto.setColumns(20);
	}
	
	private void inicializaSul() {
		JPanel sul = new JPanel();
		sul.setBackground(Color.white);
		add(sul, BorderLayout.SOUTH);
		
		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setName("CancelarProjeto");
		buttonCancelar.addMouseListener(this);
		sul.add(buttonCancelar);
		
		JButton buttonCriar = new JButton("Criar Projeto");
		buttonCriar.setName("CriarProjeto");
		buttonCriar.addMouseListener(this);
		sul.add(buttonCriar);
	}
	
	/* Metodos Public */
	
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("CancelarProjeto")) {
			ECUGUI.IrInicialView();
		}
		if (e.getComponent().getName().equals("CriarProjeto")) {
			ECUGUI.CriarProjeto(nomeProjeto.getText());
		}
	}

	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }
}