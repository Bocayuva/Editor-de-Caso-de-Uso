package view.jpanel.viewObject;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Extensao;
import control.ECUGUI;

@SuppressWarnings("serial")
public class ExtensaoVO extends JPanel implements MouseListener{
	
	private Extensao extensao;
	private JLabel lblPosicao;
	private JLabel lblInfo;
	private JLabel lblTipo;
	private JTextField txtInfo;
	private JComboBox<String> cbxTipo;
	private JButton btnExcluir ;
	
	public ExtensaoVO(Extensao extensao, Boolean edit) {
		this.extensao = extensao;
		
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setBackground(Color.WHITE);
		this.setLayout(flowLayout);
		draw(edit);
	}
	
	private void draw(boolean edit) {
		if(edit)
			drawEdit();
		else
			drawView();
	}
	
	public void drawView() {
		
		
		if(extensao.getInformacaoExtensao()== null)
			lblInfo = new JLabel();
		else
			lblInfo = new JLabel(extensao.getInformacaoExtensao());
		
		if(extensao.getIdFluxoPosicao() == 0)
			lblTipo = new JLabel();
		else {
			String posicao = ECUGUI.getPosicaoFluxo(extensao.getFluxo());
			lblTipo = new JLabel(" Fluxo: "+posicao);
		}
		
		if(extensao.getTipoExtensao()== null)
			lblPosicao = new JLabel();
		else
			lblPosicao = new JLabel(" Tipo: "+extensao.getTipoExtensao());

		Box horizontalBox = Box.createHorizontalBox();
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblPosicao);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblInfo);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		this.add(horizontalBox);
	}
	
	private void drawEdit() {

		if(extensao.getIdFluxoPosicao() == 0)
			lblTipo = new JLabel();
		else {
			String posicao = ECUGUI.getPosicaoFluxo(extensao.getFluxo());
			lblTipo = new JLabel("("+posicao+")");
		}
		
		String[] listTipo = new String[]{"Includ","Extensao"};
		if(extensao.getTipoExtensao()== null) {
			cbxTipo = new JComboBox<String>(listTipo);
			cbxTipo.setSelectedItem(null);
		} else {
			cbxTipo = new JComboBox<String>(listTipo);
			cbxTipo.setSelectedItem(lblTipo);
		}
		
		if(extensao.getInformacaoExtensao()== null)
			txtInfo = new JTextField();
		else
			txtInfo = new JTextField(extensao.getInformacaoExtensao());
		
		btnExcluir = new JButton("Remover");
		btnExcluir.setName("Remover");
		btnExcluir.addMouseListener(this);
		btnExcluir.setVisible(false);
		
		Box horizontalBox = Box.createHorizontalBox();
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(txtInfo);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblPosicao);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblTipo);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(btnExcluir);
	}
	
	public Extensao getExtensao() {
		return extensao;
	}
	
	public void setEdit(boolean opcion) {
		btnExcluir.setVisible(opcion);
		revalidate();
		repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Remover")) {
			ECUGUI.ExcluirExtensao(extensao);
		}		
	}
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) {	}
}
