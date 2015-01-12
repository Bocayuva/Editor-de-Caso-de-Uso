package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Home extends JPanel{
	
	/* Vareaveis da Class */
	
	// Interface
	private JLabel titulo;
	
	/* Construtor Default */
	
	public Home () { 
		this.titulo = new JLabel("Editor Caso de Uso",SwingConstants.CENTER);
		this.titulo.setFont(new Font("Arial", Font.PLAIN, 30));
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.add(titulo, BorderLayout.CENTER);
		this.setVisible(true);
	}

	/* Metodos Public */
	public JPanel getPanel() {
		return this;
	}
	
	public String getTitulo() {
		return titulo.getText();
	}	
}
