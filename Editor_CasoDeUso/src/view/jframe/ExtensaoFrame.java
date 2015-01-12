package view.jframe;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.jpanel.context.Extensao_Criar;
import view.jpanel.context.Extensao_Editar;
import view.jpanel.context.Extensao_Excluir;

@SuppressWarnings("serial")
public class ExtensaoFrame extends JFrame{
	private static int idCasoDeUso;
	private static ExtensaoFrame frame;
	public ExtensaoFrame() {
		
	}
	
	public enum TipoExtensaoFrame {
		cria, edita, excluir
	}

	public static ExtensaoFrame getInstance(TipoExtensaoFrame TipoExtensaoFrame,int idCasoDeUso) {
		if (frame == null) {
			frame = new ExtensaoFrame();
			ExtensaoFrame.idCasoDeUso = idCasoDeUso;
			incializarFrame(TipoExtensaoFrame);
		}
		return frame;	
	}

	private static void incializarFrame(TipoExtensaoFrame tipoExtensaoFrame) {
		frame.setSize(new Dimension(500, 350));
		frame.setPreferredSize(new Dimension(500, 350));
		frame.setMinimumSize(new Dimension(500, 350));
		frame.setBackground(Color.white);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		switch (tipoExtensaoFrame) {
		case cria:
			frame.setTitle("Adicionar Extensao");
			Extensao_Criar panel = new Extensao_Criar(idCasoDeUso);
			frame.add(panel);
			break;
		case edita:
			frame.setTitle("Editar Extensao");
			Extensao_Editar panel2 = new Extensao_Editar(idCasoDeUso);
			frame.add(panel2);
			break;
		case excluir:
			frame.setTitle("Excluir Extensao");
			Extensao_Excluir panel3 = new Extensao_Excluir(idCasoDeUso);
			frame.add(panel3);
			break;
		default:
			break;
		}
	}
	public static void encerrarFrame() {
		frame.dispose();
		frame = null;
	}

}
