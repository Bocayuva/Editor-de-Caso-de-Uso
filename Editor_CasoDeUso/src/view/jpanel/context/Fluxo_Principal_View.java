package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.jpanel.viewObject.FluxoVO;
import control.ECUGUI;
import model.Fluxo;

@SuppressWarnings("serial")
public class Fluxo_Principal_View extends JPanel {

	/* Vareaveis da Class */

	// Data
	private int idCasoDeUso;
	private boolean edit;
	private Vector<Fluxo> list;

	/* Construtor Default */

	public Fluxo_Principal_View(int idCasoDeUso, boolean edit) {
		this.idCasoDeUso = idCasoDeUso;
		this.list = null;
		this.edit = edit;
		iniPanel();
	}

	public enum TipoFluxoPrincipal {
		view, edit
	}

	/* Metodos Private */

	private void iniPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(north(), BorderLayout.NORTH);
		add(center(), BorderLayout.CENTER);
	}

	private Box north() {

		JLabel label = new JLabel("Fluxo Principal:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalStrut(10));
		horizontalBox.add(label);

		return horizontalBox;
	}

	private JPanel center() {

		Box verticalBox = Box.createVerticalBox();

		if (idCasoDeUso > 0)
			list = ECUGUI.getListFluxoPrincipal(idCasoDeUso);

		if (list == null) {
			Component horizontalStrut = Box.createHorizontalStrut(35);
			JLabel label = new JLabel("O caso de uso nao possui Fluxo Principal.");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setHorizontalTextPosition(SwingConstants.LEFT);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(horizontalStrut);
			horizontalBox.add(label);
			verticalBox.add(horizontalBox);
		} else {
			verticalBox.add(fluxo());
		}

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(5);
		flowLayout.setAlignment(FlowLayout.LEFT);

		JPanel center = new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(flowLayout);
		center.add(verticalBox);
		return center;
	}

	private Box fluxo() {

		Box verticalBox = Box.createVerticalBox();

		for (int index = 0; index < list.size(); index++) {
			// edit = false, por bug que nao deu tempo de corriguir
			edit = false;
			FluxoVO flx = new FluxoVO(20, list.get(index), edit);
			verticalBox.add(flx);
		}
		
		return verticalBox;
	}
}