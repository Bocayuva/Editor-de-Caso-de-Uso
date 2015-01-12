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
public class Fluxo_Extensao_View extends JPanel{
	
	/* Vareaveis da Class */
	
	// Data
	private int idCasoDeUso;
	private Vector<Fluxo> extensaoList;
	
	// Interface
		
	/* Construtor Default */
	
	public Fluxo_Extensao_View(int idCasoDeUso, boolean edit) {
		this.idCasoDeUso = idCasoDeUso;
		this.extensaoList = null;
		iniPanel(edit);
	}
	
	/* Metodos Private */
	
	private void iniPanel(boolean edit) {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(north(), BorderLayout.NORTH);
		add(center(edit), BorderLayout.CENTER);
	}
	
	private Box north() {
		
		JLabel label = new JLabel("Fluxo de Extensao:");		
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalStrut(10));
		horizontalBox.add(label);

		return horizontalBox;
	}
	
	private JPanel center(boolean edit) {
		
		Box verticalBox = Box.createVerticalBox();
		
		if(idCasoDeUso > 0)
			extensaoList = ECUGUI.getListFluxoExtensao(idCasoDeUso);

		if(extensaoList == null) {
			Component horizontalStrut = Box.createHorizontalStrut(35);
			JLabel label = new JLabel("O caso de uso nao possui Fluxo Extensão.");		
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setHorizontalTextPosition(SwingConstants.LEFT);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(horizontalStrut);
			horizontalBox.add(label);
			verticalBox.add(horizontalBox);
		} else {
			// edit = false, por bug que nao deu tempo de corriguir
			edit = false;
			verticalBox = centerView(extensaoList, edit);
		}
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(5);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JPanel center = new JPanel();
		center.setBackground(Color.white);
		center.setLayout(flowLayout);
		center.add(verticalBox);
		return center;
	}
	
	private Box centerView(Vector<Fluxo> extensaoList, boolean edit) {
		
		Box verticalBox = Box.createVerticalBox();
		int[] padding = ECUGUI.getPaddingFluxo(idCasoDeUso);
		for (int index = 0; index < extensaoList.size(); index++) {
			FluxoVO flx = new FluxoVO(padding[index], extensaoList.get(index), edit);
			verticalBox.add(flx);
		}

		return verticalBox;
	}
}