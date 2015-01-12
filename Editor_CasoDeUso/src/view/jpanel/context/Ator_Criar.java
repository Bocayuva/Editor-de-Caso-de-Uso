package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Ator;
import view.jframe.AtorFrame;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Ator_Criar extends JPanel implements MouseListener{

	/* Vareaveis da Class */
	
	// Data
	private JTextField nomeAtor;
	private Vector<Ator> tmpList;
	// Interface
	private JList<String> list;
	
	/* Construtor Default */
	
	public Ator_Criar() {
		this.tmpList = null;
		this.nomeAtor = null;
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
		tmpList = ECUGUI.getListAtores();
		if(tmpList != null)
        list = new JList<String>(ECUGUI.getListagemAtores(tmpList));
		else
			list = new JList<String>();
		list.setBackground(Color.white);
        list.setSelectedIndex(-1);
        list.setVisibleRowCount(3);
        list.setValueIsAdjusting(false);
        list.setFixedCellHeight(15);       
        JScrollPane scrollPane_1 = new JScrollPane(list);
        scrollPane_1.setMaximumSize(new Dimension(200, 115));
        scrollPane_1.setMinimumSize (new Dimension (200,115));
        add(scrollPane_1 , BorderLayout.NORTH);
	}
	
	private void inicializaCenter() {
		JLabel label = new JLabel("Digite o nome do Ator:");
		nomeAtor = new JTextField(20);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(200,130));
		panel.setMinimumSize(new Dimension(200,130));
		panel.setMaximumSize(new Dimension(200,130));
		panel.add(label, BorderLayout.NORTH);
		panel.add(nomeAtor, BorderLayout.CENTER);
		add(panel,BorderLayout.CENTER);
	}
	
	private void inicializaSouth() {
		JButton cancelar = new JButton("Cancelar");
		cancelar.setName("Cancelar");
		cancelar.addMouseListener(this);
		JButton criar = new JButton("Criar");
		criar.setName("Criar");
		criar.addMouseListener(this);
		
		JPanel south = new JPanel();
		south.setBackground(Color.white);
		south.setLayout(new GridLayout());
		south.add(cancelar);
		south.add(criar);
		add(south, BorderLayout.SOUTH);
	}
	
	/* Metodos Public */
	
	public void mouseClicked(MouseEvent e)  { 
		if (e.getComponent().getName().equals("Cancelar")) {
			this.nomeAtor = null;
			AtorFrame.encerrarFrame();
		}
		if (e.getComponent().getName().equals("Criar")) {
			String ator = nomeAtor.getText().toString();
			if(ator == null || ator.equals("")) {
				JOptionPane.showMessageDialog(null, "Nome do ator vazio");
				return;
			} else {
				if(nomeAtor.getText() != null )
					for (Ator atorT : tmpList) {
						if(atorT.getNomeAtor().equals(nomeAtor.getText()) == true){
							JOptionPane.showMessageDialog(null, "Nome do ator invalido, o nome ja existe");
							return;
						}
					}
				Ator at = new Ator();
				at.setNomeAtor(nomeAtor.getText());
				ECUGUI.CriarAtor(at);
			}
		}		
	}
	
	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }
}
