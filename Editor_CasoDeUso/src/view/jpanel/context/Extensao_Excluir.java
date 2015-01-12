package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import model.CasoDeUso;
import model.Extensao;
import model.Fluxo;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Extensao_Excluir extends JPanel implements MouseListener, ActionListener{
	
	/* Vareaveis Locais */
	//Dados
	private int idCasoDeUso;
	private Vector<Extensao> listExt;
	private Extensao extensao;
	private Fluxo fluxo;
	private CasoDeUso casoExtensao;
	//Interface
	private JTextField textField;
	private JComboBox<String> comboBoxExt ;
	private JTextField textCDU ;
	private JTextField textFluxo;
	private JTextField textTipo ;
	JPanel center;
	public Extensao_Excluir(int idCasoDeUso) {

		this.idCasoDeUso = idCasoDeUso;
		JPanel center = inicializaCentro();
		Box south = inicializaSul();
		setPreferredSize(new Dimension(500, 350));
		setMinimumSize(new Dimension(500, 350));
		setMaximumSize(new Dimension(800, 350));
		setBackground(Color.white);
		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
	}
	private JPanel inicializaCentro() {	
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{8, 0, 0, 8, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel("Excluir Extensao do Caso de Uso");
		lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		centerPanel.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblSelecioneAExtenso = new JLabel("Selecione a Extensao:");
		GridBagConstraints gbc_lblSelecioneAExtenso = new GridBagConstraints();
		gbc_lblSelecioneAExtenso.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneAExtenso.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneAExtenso.gridx = 1;
		gbc_lblSelecioneAExtenso.gridy = 2;
		centerPanel.add(lblSelecioneAExtenso, gbc_lblSelecioneAExtenso);
				
		listExt = ECUGUI.getAllExtensaoOfMaster(idCasoDeUso);
		comboBoxExt = new JComboBox<String>(ECUGUI.getListagemNomesExtensao(listExt));
		comboBoxExt.setSelectedItem(null);
		comboBoxExt.setActionCommand("comboBoxExt");
		comboBoxExt.addActionListener(this);
		GridBagConstraints gbc_comboBoxExt = new GridBagConstraints();
		gbc_comboBoxExt.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxExt.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxExt.gridx = 2;
		gbc_comboBoxExt.gridy = 2;
		centerPanel.add(comboBoxExt, gbc_comboBoxExt);
		
		JLabel lblSelecioneOCaso = new JLabel("Caso de Uso Extensao");
		GridBagConstraints gbc_lblSelecioneOCaso = new GridBagConstraints();
		gbc_lblSelecioneOCaso.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneOCaso.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneOCaso.gridx = 1;
		gbc_lblSelecioneOCaso.gridy = 3;
		centerPanel.add(lblSelecioneOCaso, gbc_lblSelecioneOCaso);
		
		textCDU = new JTextField();
		GridBagConstraints gbc_textCDU = new GridBagConstraints();
		gbc_textCDU.insets = new Insets(0, 0, 5, 5);
		gbc_textCDU.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCDU.gridx = 2;
		gbc_textCDU.gridy = 3;
		centerPanel.add(textCDU, gbc_textCDU);
		textCDU.setColumns(10);
		textCDU.setEditable(false);
		
		JLabel lblSelecioneOFluxo = new JLabel("Fluxo Extendido");
		GridBagConstraints gbc_lblSelecioneOFluxo = new GridBagConstraints();
		gbc_lblSelecioneOFluxo.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneOFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneOFluxo.gridx = 1;
		gbc_lblSelecioneOFluxo.gridy = 4;
		centerPanel.add(lblSelecioneOFluxo, gbc_lblSelecioneOFluxo);
		
		textFluxo = new JTextField();
		GridBagConstraints gbc_textFluxo = new GridBagConstraints();
		gbc_textFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_textFluxo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFluxo.gridx = 2;
		gbc_textFluxo.gridy = 4;
		centerPanel.add(textFluxo, gbc_textFluxo);
		textFluxo.setColumns(10);
		textFluxo.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Tipo de Extens\u00E3o");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		centerPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		textTipo = new JTextField();
		GridBagConstraints gbc_textTipo = new GridBagConstraints();
		gbc_textTipo.insets = new Insets(0, 0, 5, 5);
		gbc_textTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTipo.gridx = 2;
		gbc_textTipo.gridy = 5;
		centerPanel.add(textTipo, gbc_textTipo);
		textTipo.setColumns(10);
		textTipo.setEditable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Coment\u00E1rio");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 6;
		centerPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText("");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 6;
		centerPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setEditable(false);
		return centerPanel;
	}
	
	private Box inicializaSul() {

		Component horizontalGlue = Box.createHorizontalGlue();

		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setName("Cancelar");
		Cancelar.addMouseListener(this);

		JButton Excluir = new JButton("Excluir");
		Excluir.setName("Excluir");
		Excluir.addMouseListener(this);
		
		Box sulPanel = Box.createHorizontalBox();
		sulPanel.add(Cancelar);
		sulPanel.add(horizontalGlue);
		sulPanel.add(Excluir);
		return sulPanel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("comboBoxExt") && comboBoxExt != null && comboBoxExt.getSelectedItem() != null) {
			int num = comboBoxExt.getSelectedIndex();
			extensao = listExt.get(num);
			casoExtensao = ECUGUI.getCasoDeUso(extensao.getIdCasoDeUsoExtensao());
			textCDU.setText(casoExtensao.getNomeCasoDeUso());
			fluxo = ECUGUI.getFluxo(extensao.getIdFluxoPosicao());
			String infoFluxo = ECUGUI.getPosicaoFluxo(fluxo) + ". " + fluxo.getInformacaoFluxo();
			textFluxo.setText(infoFluxo);
			textTipo.setText(extensao.getTipoExtensao());
			textField.setText(extensao.getInformacaoExtensao());
		}
		revalidate();
		repaint();
		
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Cancelar")) {
			ECUGUI.encerrarExtensaoFrame();
		}
		if (e.getComponent().getName().equals("Excluir")) {
			if (extensao == null || extensao.getIdExtensao() <= 0) {
				JOptionPane.showMessageDialog(null,"Dados Invalidos para o Cadastro");
				return;
			}
			extensao.setInformacaoExtensao(textField.getText());
			ECUGUI.ExcluirExtensao(extensao);
		}
	}
	public void mousePressed(MouseEvent e) 	{	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) 	{	}
	public void mouseExited(MouseEvent e)  	{	}
}
