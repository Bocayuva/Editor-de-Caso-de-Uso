package view.jframe;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.jpanel.context.Fluxo_Adicionar;
import view.jpanel.context.Fluxo_Editar;
import view.jpanel.context.Fluxo_Excluir;

@SuppressWarnings("serial")
public class FluxoFrame extends JFrame {

	/* Vareaveis da Class */
	private static int idCasoDeUso;
	private static FluxoFrame frame;

	/* Construtor Default */

	public FluxoFrame() {
	}

	public static FluxoFrame getInstance(TipoFluxoPanel TipoFluxoPanel,	int idCasoDeUso) {
		if (frame == null) {
			frame = new FluxoFrame();
			FluxoFrame.idCasoDeUso = idCasoDeUso;
			incializarFrame(TipoFluxoPanel);
		}
		return frame;
	}

	/* Enum da Class */

	public enum TipoFluxoPanel {
		AdicionaFluxoPrincipal, AdicionaFluxoExtensao, Editar, Excluir
	}

	/* Metodos Public */

	public static void encerrarFrame() {
		if(frame != null)
			frame.dispose();
		frame = null;
	}

	/* Metodos Private */

	private static void incializarFrame(TipoFluxoPanel TipoFluxoPanel) {

		frame.setSize(new Dimension(500, 350));
		frame.setPreferredSize(new Dimension(500, 350));
		frame.setMinimumSize(new Dimension(500, 350));
		frame.setBackground(Color.white);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		switch (TipoFluxoPanel) {
		case AdicionaFluxoExtensao:
			frame.setTitle("Adicionar ao Fluxo Principal");
			Fluxo_Adicionar panel1 = new Fluxo_Adicionar(false, idCasoDeUso);
			frame.add(panel1);
			break;
		case AdicionaFluxoPrincipal:
			frame.setTitle("Adicionar ao Fluxo Extensao");
			Fluxo_Adicionar panel2 = new Fluxo_Adicionar(true, idCasoDeUso);
			frame.add(panel2);
			break;
		case Editar:
			frame.setTitle("Editar de Fluxo "+idCasoDeUso);
			Fluxo_Editar panel3 = new Fluxo_Editar(false, idCasoDeUso);
			frame.add(panel3);
			break;
		case Excluir:
			frame.setTitle("Excluir de Fluxo "+idCasoDeUso);
			Fluxo_Excluir panel4 = new Fluxo_Excluir(false, idCasoDeUso);
			frame.add(panel4);
			break;
		default:
			break;
		}
	}
}