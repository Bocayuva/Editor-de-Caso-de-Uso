package view.jpanel.viewObject;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Fluxo;
import control.ECUGUI;

@SuppressWarnings("serial")
public class FluxoVO extends JPanel implements MouseListener{
	
	private Fluxo fluxo;
	private int padding;
	
	private JLabel lblInfo;
	private JLabel lblPosicao;
	private JTextField txtInfo;
	
	public FluxoVO(int padding, Fluxo fluxo, boolean edit) {
		this.padding = padding;
		this.fluxo = fluxo;
		factory(edit);
	}
	
	private void factory(boolean edit) {
		if(!edit)
			drawView();
		else
			drawEdit();
	}
	private void drawView() {
		
		// Posicao
		String posicao = ECUGUI.getPosicaoFluxo(fluxo);
		if(posicao==null)
			lblPosicao = new JLabel();
		else
			lblPosicao = new JLabel(posicao);
		
		// Informacao
		if(fluxo.getInformacaoFluxo() == null)
			lblInfo = new JLabel();
		else
			lblInfo = new JLabel(fluxo.getInformacaoFluxo());
				
		// Adicao no Panel
		Box horizontalBox = Box.createHorizontalBox();
		Component horizontalStrut = Box.createHorizontalStrut(padding);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblPosicao);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblInfo);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setBackground(Color.WHITE);
		this.setLayout(flowLayout);
		this.add(horizontalBox);
	}
	
	private void drawEdit() {
		
		String posicao = ECUGUI.getPosicaoFluxo(fluxo);
		if(posicao==null)
			lblPosicao = new JLabel();
		else
			lblPosicao = new JLabel(posicao);
		
		if(fluxo.getInformacaoFluxo() == null) {
			txtInfo = new JTextField();
		} else {
			txtInfo = new JTextField(fluxo.getInformacaoFluxo());
		}

		JButton btnExcluir = new JButton("Remover");
		btnExcluir.setName("Remover");
		btnExcluir.addMouseListener(this);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setName("Atualizar");
		btnAtualizar.addMouseListener(this);
		
		Box horizontalBox = Box.createHorizontalBox();
		Component horizontalStrut = Box.createHorizontalStrut(padding);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(lblPosicao);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(txtInfo);
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(btnAtualizar);
		horizontalBox.add(btnExcluir);
		
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setBackground(Color.WHITE);
		this.setLayout(flowLayout);
		this.add(horizontalBox);
		this.setVisible(true);
	}
	
	public Fluxo getFluxo() {
		return fluxo;
	}
		
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Remover")) {
			ECUGUI.ExcluirFluxo(fluxo);
		}		
		if (e.getComponent().getName().equals("Atualizar")) {
			fluxo.setInformacao(txtInfo.getText());
			ECUGUI.EditarFluxo(fluxo);
		}	
	}
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) {	}
}
