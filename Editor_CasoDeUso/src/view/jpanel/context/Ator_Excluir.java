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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Ator;
import view.jframe.AtorFrame;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Ator_Excluir extends JPanel implements MouseListener, ActionListener{
	
	/* Vareaveis da Class */
	
	// Data
	private Vector<Ator> list;
	private Ator ator;
	
	// Inteface
	private JComboBox<String> combo;
	private JButton deletar;
	
	/* Construtor Default */
	
	public Ator_Excluir() {
		this.list = null;
		this.combo = null;
		this.ator = null;
		inicializaComboBoxPanel();
		inicializarPanel();
	}
	
	/* Metodos Private */
	
	private void inicializarPanel() {
		setBackground(Color.white);
		setLayout(new BorderLayout());
		inicializaComboBoxPanel();
		inicializaBotoes();
	}
	
	private void inicializaComboBoxPanel() {
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
	
	private void inicializaBotoes() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setName("Cancelar");
		cancelar.addMouseListener(this);
		deletar = new JButton("Deletar");
		deletar.setName("Deletar");
		deletar.addMouseListener(this);
		deletar.setEnabled(false);
		
		JPanel south = new JPanel();
		south.setBackground(Color.white);
		south.setLayout(new GridLayout());
		south.add(cancelar);
		south.add(deletar);
		add(south, BorderLayout.SOUTH);
	}
	
	/* Metodos Public */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("combo") && combo.getSelectedItem() != null){
			int num =  combo.getSelectedIndex();
			ator = list.get(num);
			deletar.setEnabled(true);
		}
	}
	
	public void mouseClicked(MouseEvent e)  { 

		if (e.getComponent().getName().equals("Cancelar")) {
			AtorFrame.encerrarFrame();
		}
		if (e.getComponent().getName().equals("Deletar")) {
			if(ator == null) {
				JOptionPane.showMessageDialog(null, "Ator invalido, selecione um ator");
				return;
			}
			ECUGUI.ExcluirAtor(ator);
		}
	}
	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }


}
