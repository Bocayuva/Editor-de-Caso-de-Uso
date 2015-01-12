package view.jframe;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.jpanel.context.Ator_Criar;
import view.jpanel.context.Ator_Editar;
import view.jpanel.context.Ator_Excluir;

@SuppressWarnings("serial")
public class AtorFrame extends JFrame{

	/* Vareaveis da Class */
	private static AtorFrame frame;
	
	/* Construtor Default */
	
	public AtorFrame() { }

	public static AtorFrame getInstance(TipoAtorPanel TipoAtorPanel) {
		if (frame == null) {
			frame = new AtorFrame();
			incializarFrame(TipoAtorPanel);
		}
		return frame;
	}
	
	/* Enum da Class */

	public enum TipoAtorPanel {
		criarAtor, excluirAtor, alterarAtor
	}
	
	/* Metodos Public */
	
	public static void encerrarFrame() {
		frame.dispose();
		frame = null;
	}
	
	/* Metodos Private */
	
	private static void incializarFrame(TipoAtorPanel TipoAtorPanel) {
	
		frame.setSize(new Dimension(200, 130));
		frame.setBackground(Color.white);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		switch (TipoAtorPanel) {
		case criarAtor:
//			frame.setPreferredSize(new Dimension(200,200));
			frame.setMinimumSize(new Dimension(200,150));
			frame.setMaximumSize(new Dimension(200,150));
			frame.setTitle("Criar Novo Ator para o Projeto");
			Ator_Criar criarAtorPanel = new Ator_Criar();
			frame.add(criarAtorPanel);
			break;
		case excluirAtor:
			frame.setTitle("Excluir Ator do Projeto");
			Ator_Excluir excluirAtorPanel = new Ator_Excluir();
			frame.add(excluirAtorPanel);
			break;
		case alterarAtor:
			frame.setTitle("Alterar Ator do Projeto");
			Ator_Editar alterarAtorPanel = new Ator_Editar();
			frame.add(alterarAtorPanel);
			break;
		}
	}
}
