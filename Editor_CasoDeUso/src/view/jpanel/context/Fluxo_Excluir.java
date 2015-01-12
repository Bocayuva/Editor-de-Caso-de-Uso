package view.jpanel.context;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Fluxo;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Fluxo_Excluir extends Templete_FluxoPanel implements MouseListener, ActionListener {

	/* Vareaveis da Class */
	// Data
	private Vector<Fluxo> list;
	private Fluxo fluxo;
	
	// Interface
	private JComboBox<String> comboBox;
	
	public Fluxo_Excluir(boolean edit, int idCasoDeUso) {
		super(edit,idCasoDeUso);
	}

	@Override
	public JPanel north(boolean edit) {
		return null;
	}
	
	@Override
	public JPanel center(boolean edit) {
		JPanel centerInformacaoPanel = new JPanel();
		centerInformacaoPanel.setPreferredSize(new Dimension(800, 500));
		centerInformacaoPanel.setMinimumSize(new Dimension(800, 500));
		centerInformacaoPanel.setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{8, 201, 281, 0, 8};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerInformacaoPanel.setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel("Excluir Fluxo");
		lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		centerInformacaoPanel.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblLblcombobox = new JLabel("Selecione o fluxo desejado:");
		lblLblcombobox.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLblcombobox = new GridBagConstraints();
		gbc_lblLblcombobox.insets = new Insets(0, 0, 5, 5);
		gbc_lblLblcombobox.anchor = GridBagConstraints.WEST;
		gbc_lblLblcombobox.gridx = 1;
		gbc_lblLblcombobox.gridy = 3;
		centerInformacaoPanel.add(lblLblcombobox, gbc_lblLblcombobox);
		
		list = ECUGUI.getListAllFluxo(getIdCasoDeUso());
		if(list == null)
			comboBox = new JComboBox<String>();
		else
			comboBox = new JComboBox<String>(ECUGUI.getListagemFluxoInPosicaoNome(list));
		comboBox.setActionCommand("posicaoComboBox");
		comboBox.addActionListener(this);
		comboBox.setSelectedItem(null);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		centerInformacaoPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblOFluxoAntigo = new JLabel("* O Fluxo posterior do selecionado sera movido para a posicao do fluxo exluido");
		lblOFluxoAntigo.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		GridBagConstraints gbc_lblOFluxoAntigo = new GridBagConstraints();
		gbc_lblOFluxoAntigo.anchor = GridBagConstraints.WEST;
		gbc_lblOFluxoAntigo.gridwidth = 2;
		gbc_lblOFluxoAntigo.insets = new Insets(0, 0, 0, 5);
		gbc_lblOFluxoAntigo.gridx = 1;
		gbc_lblOFluxoAntigo.gridy = 6;
		centerInformacaoPanel.add(lblOFluxoAntigo, gbc_lblOFluxoAntigo);
		
		return centerInformacaoPanel;
	}

	@Override
	public JPanel south(boolean edit) {
		Component horizontalGlue = Box.createHorizontalGlue();

		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setName("Cancelar");
		Cancelar.addMouseListener(this);

		JButton excluir = new JButton("Excluir");
		excluir.setName("Excluir");
		excluir.addMouseListener(this);
		
		Box sulPanel = Box.createHorizontalBox();
		sulPanel.add(Cancelar);
		sulPanel.add(horizontalGlue);
		sulPanel.add(excluir);
		
		JPanel sul = new JPanel();
		sul.setLayout(new FlowLayout());
		sul.setBackground(Color.white);
		sul.add(sulPanel);
		return sul;
	}
	
	/* Metodos Public */
	
	public void actionPerformed(ActionEvent e) {
		if (comboBox != null && comboBox.getSelectedItem() != null) {
			int num = comboBox.getSelectedIndex();
			fluxo = list.get(num);
			revalidate();
			repaint();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Cancelar")) {
			ECUGUI.encerrarFluxoFrame();
		}
		if (e.getComponent().getName().equals("Excluir")) {
			if (fluxo == null) {				
				JOptionPane.showMessageDialog(null,"Dados Invalidos para o Cadastro");
				return;
			}
			ECUGUI.ExcluirFluxo(fluxo);
		}
	}

	public void mousePressed(MouseEvent e)  {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e)  {	}
	public void mouseExited(MouseEvent e)   {	}
}
