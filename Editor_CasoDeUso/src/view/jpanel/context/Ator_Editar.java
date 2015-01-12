package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Ator;
import view.jframe.AtorFrame;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Ator_Editar extends JPanel implements MouseListener, ActionListener{
	
	/* Vareaveis da Class */
	
	// Data
	private Ator ator;
	private Vector<Ator> list;

	// Interface
	private JComboBox<String> combo;
	private JTextField novoNomeAtor;
	private JButton btnAlterar;
	/* Construtor Default */
	
	public Ator_Editar() {
		this.list = null;
		this.ator = null;
		inicializaPanel();
	}
	
	/* Metodos Private */
	
	private void inicializaPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		inicializaNorth();
		inicializaCenter();
		inicializaSouth();
	}
	
	private void inicializaNorth() {
		
		list = ECUGUI.getListAtores();
		if(list != null)
			combo = new JComboBox<String>(ECUGUI.getListagemAtores(list));
		else
			combo = new JComboBox<String>();
		combo.addActionListener(this);
		combo.setActionCommand("combo");
		combo.setSelectedItem(null);
		combo.setBackground(Color.white);
		add(combo, BorderLayout.NORTH);
	}
	
	private void inicializaCenter() {
		JLabel label = new JLabel("Digite o nome do Ator:");
		add(label, BorderLayout.CENTER);
		novoNomeAtor = new JTextField(20);
		if(ator != null)
			novoNomeAtor.setText(ator.getNomeAtor());
		else
			novoNomeAtor.setText("");
		novoNomeAtor.setEditable(false);
		add(novoNomeAtor, BorderLayout.CENTER);
	}
	
	private void inicializaSouth() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setName("Cancelar");
		cancelar.addMouseListener(this);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setName("Alterar");
		btnAlterar.addMouseListener(this);
		btnAlterar.setEnabled(false);
		
		JPanel south = new JPanel();
		south.setBackground(Color.white);
		south.setLayout(new GridLayout());
		south.add(cancelar);
		south.add(btnAlterar);
		add(south, BorderLayout.SOUTH);
	}
	
	/* Metodos Public */
	
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("combo") && combo.getSelectedItem() != null){
			int num =  combo.getSelectedIndex();
			ator = list.get(num);
			
			novoNomeAtor.setText(ator.getNomeAtor());
			novoNomeAtor.setEditable(true);
			btnAlterar.setEnabled(true);
			revalidate();
			repaint();
		}
	}
	
	public void mouseClicked(MouseEvent e)  { 
		if (e.getComponent().getName().equals("Cancelar")) {
			AtorFrame.encerrarFrame();
		}
		if (e.getComponent().getName().equals("Alterar")) {
			if(ator == null || novoNomeAtor.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Nome do ator invalido");
				return;
			} else if(novoNomeAtor.getText() != null ) {
					for (Ator atorT : list) {
						if(atorT.getNomeAtor().equals(novoNomeAtor.getText()) == true) {
							JOptionPane.showMessageDialog(null, "Nome do ator invalido, o nome ja existe");
							return;
						}
					} 
			} 
			ator.setNomeAtor(novoNomeAtor.getText());
			ECUGUI.EditarAtor(ator);		
		}
	}
	
	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }
}
