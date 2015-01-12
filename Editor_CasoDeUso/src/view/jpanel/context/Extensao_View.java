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

import model.Extensao;
import view.jpanel.viewObject.ExtensaoVO;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Extensao_View extends JPanel {

	private int idCasoDeUso;
	private TipoExtensaoPanel tipo;
	private Vector<Extensao> extensaoList;
	
	public enum TipoExtensaoPanel {
		view, edit
	}
	
	public Extensao_View() {
		this.idCasoDeUso = -1;
		iniPanel();
	}
	
	public Extensao_View(int idCasoDeUso, TipoExtensaoPanel tipoExtensaoPanel) {
		this.idCasoDeUso = idCasoDeUso;
		this.tipo = tipoExtensaoPanel;
		this.extensaoList = null;
		iniPanel();
	}
	
	private void iniPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(north(), BorderLayout.NORTH);
		add(center(), BorderLayout.CENTER);
	}
	
	private Box north() {

		Component horizontalStrut = Box.createHorizontalStrut(10);

		JLabel label = new JLabel("Extensao:");		
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(label);
		return horizontalBox;
	}
	
	private JPanel center() {
		
		Box verticalBox = Box.createVerticalBox();
		
		if(idCasoDeUso > 0)
			extensaoList = ECUGUI.getAllExtensaoOfMaster(idCasoDeUso);
		
		if(extensaoList == null) {
			Component horizontalStrut = Box.createHorizontalStrut(35);

			JLabel label = new JLabel("O caso de uso nao possui nenhuma Extensao.");		
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setHorizontalTextPosition(SwingConstants.LEFT);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(horizontalStrut);
			horizontalBox.add(label);
			verticalBox.add(horizontalBox);
		} else {
			switch (tipo) {
			case edit:
				verticalBox.add(centerEdit(extensaoList)); 
				break;
			case view:
				verticalBox.add(centerView(extensaoList)); 
				break;
			}
		}
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(5);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JPanel center = new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(flowLayout);
		center.add(verticalBox);
		center.setVisible(true);
		return center;
	}
	
	private Box centerView(Vector<Extensao> extensaoList) {
		
		Box verticalBox = Box.createVerticalBox();
		
		for(int index=0; index < extensaoList.size() ; index++) {
			ExtensaoVO box = new ExtensaoVO(extensaoList.get(index),false);
			verticalBox.add(box);
		}

		return verticalBox;
	}	
	
	private Box centerEdit(Vector<Extensao> extensaoList) {
		Box verticalBox = Box.createVerticalBox();
		
		for(int index=0; index < extensaoList.size() ; index++) {
			ExtensaoVO box = new ExtensaoVO(extensaoList.get(index),true);
			verticalBox.add(box);
		}
		
		return verticalBox;
	}	
}
