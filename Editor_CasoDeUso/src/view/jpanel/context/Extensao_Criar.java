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
public class Extensao_Criar extends JPanel implements MouseListener, ActionListener{
	
	/* Vareaveis Locais */
	//Dados
	private int idCasoDeUso;
	private Vector<CasoDeUso> listCDU;
	private Vector<Fluxo> listFluxo;
	private Extensao extensao;
	private Fluxo fluxo;
	private CasoDeUso casoExtensao;
	private String tipo;
	//Interface
	private JTextField textField;
	private JComboBox<String> comboBoxCDU ;
	private JComboBox<String> comboBoxFluxo;
	private JComboBox<String> comboBoxTipo ;
	JPanel center;
	public Extensao_Criar(int idCasoDeUso) {

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
		
		JLabel lblTitulo = new JLabel("Adi��o de uma Extens�o ao Caso De Uso");
		lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		centerPanel.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblSelecioneCDU = new JLabel("Selecione o Caso De Uso:");
		lblSelecioneCDU.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSelecioneCDU = new GridBagConstraints();
		gbc_lblSelecioneCDU.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneCDU.anchor = GridBagConstraints.WEST;
		gbc_lblSelecioneCDU.gridx = 1;
		gbc_lblSelecioneCDU.gridy = 3;
		centerPanel.add(lblSelecioneCDU, gbc_lblSelecioneCDU);
		
		listCDU = ECUGUI.getListagemCasosDeUsoIrmaos(idCasoDeUso);
		comboBoxCDU = new JComboBox<String>(ECUGUI.getListagemNomesCasosDeUsoIrmaos(listCDU));
		comboBoxCDU.setSelectedItem(null);
		comboBoxCDU.setActionCommand("comboBoxCDU");
		comboBoxCDU.addActionListener(this);
		GridBagConstraints gbc_comboBoxCDU = new GridBagConstraints();
		gbc_comboBoxCDU.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCDU.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCDU.gridx = 2;
		gbc_comboBoxCDU.gridy = 3;
		centerPanel.add(comboBoxCDU, gbc_comboBoxCDU);
		
		JLabel lblSelecioneAQual = new JLabel("Selecione o passo do fluxo:");
		lblSelecioneAQual.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSelecioneAQual = new GridBagConstraints();
		gbc_lblSelecioneAQual.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneAQual.anchor = GridBagConstraints.WEST;
		gbc_lblSelecioneAQual.gridx = 1;
		gbc_lblSelecioneAQual.gridy = 4;
		centerPanel.add(lblSelecioneAQual, gbc_lblSelecioneAQual);
		
		listFluxo = ECUGUI.getListFluxoExtensao(idCasoDeUso);
		comboBoxFluxo = new JComboBox<String>(ECUGUI.getListagemFluxoInPosicaoNome(listFluxo));
		comboBoxFluxo.setSelectedItem(null);
		comboBoxFluxo.setActionCommand("comboBoxFluxo");
		comboBoxFluxo.addActionListener(this);
		GridBagConstraints gbc_comboBoxFluxo = new GridBagConstraints();
		gbc_comboBoxFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFluxo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFluxo.gridx = 2;
		gbc_comboBoxFluxo.gridy = 4;
		centerPanel.add(comboBoxFluxo, gbc_comboBoxFluxo);
		
		JLabel lblSelecioneOTipo = new JLabel("Selecione o tipo de relação:");
		lblSelecioneOTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSelecioneOTipo = new GridBagConstraints();
		gbc_lblSelecioneOTipo.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneOTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneOTipo.gridx = 1;
		gbc_lblSelecioneOTipo.gridy = 5;
		centerPanel.add(lblSelecioneOTipo, gbc_lblSelecioneOTipo);
		
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addItem("Extend");
		comboBoxTipo.addItem("Include");
		comboBoxTipo.setSelectedItem(null);
		comboBoxTipo.setActionCommand("comboBoxTipo");
		comboBoxTipo.addActionListener(this);
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 2;
		gbc_comboBoxTipo.gridy = 5;
		centerPanel.add(comboBoxTipo, gbc_comboBoxTipo);
		
		JLabel lblInformeAlgumComentrio = new JLabel("Informe algum comentário:");
		lblSelecioneOTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblInformeAlgumComentrio = new GridBagConstraints();
		gbc_lblInformeAlgumComentrio.anchor = GridBagConstraints.WEST;
		gbc_lblInformeAlgumComentrio.insets = new Insets(0, 0, 0, 5);
		gbc_lblInformeAlgumComentrio.gridx = 1;
		gbc_lblInformeAlgumComentrio.gridy = 6;
		centerPanel.add(lblInformeAlgumComentrio, gbc_lblInformeAlgumComentrio);
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 6;
		centerPanel.add(textField, gbc_textField);

		return centerPanel;
	}
	
	private Box inicializaSul() {

		Component horizontalGlue = Box.createHorizontalGlue();

		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setName("Cancelar");
		Cancelar.addMouseListener(this);

		JButton Cadastrar = new JButton("Cadastrar");
		Cadastrar.setName("Cadastrar");
		Cadastrar.addMouseListener(this);
		
		Box sulPanel = Box.createHorizontalBox();
		sulPanel.add(Cancelar);
		sulPanel.add(horizontalGlue);
		sulPanel.add(Cadastrar);
		return sulPanel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("comboBoxCDU") && comboBoxCDU != null && comboBoxCDU.getSelectedItem() != null) {
			int num = comboBoxCDU.getSelectedIndex();
			casoExtensao = listCDU.get(num);
		}
		if(e.getActionCommand().equals("comboBoxFluxo") && comboBoxFluxo != null && comboBoxFluxo.getSelectedItem() != null) {
			int num = comboBoxFluxo.getSelectedIndex();
			fluxo = listFluxo.get(num);
		}
		if(e.getActionCommand().equals("comboBoxTipo") && comboBoxTipo != null && comboBoxTipo.getSelectedItem() != null) {
			tipo = comboBoxTipo.getSelectedItem().toString();
		}
		revalidate();
		repaint();
		
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Cancelar")) {
			ECUGUI.encerrarExtensaoFrame();
		}
		if (e.getComponent().getName().equals("Cadastrar")) {
			if (casoExtensao == null || textField== null || textField.equals("")
					||fluxo==null || tipo.equals("")) {				
				JOptionPane.showMessageDialog(null,"Dados Invalidos para o Cadastro");
				return;
			}
			extensao = new Extensao();
			extensao.setIdCasoDeUsoExtensao(casoExtensao.getIdCasoDeUso());
			extensao.setIdCasoDeUsoMaster(fluxo.getIdCasoDeUso());
			extensao.setIdFluxoPosicao(fluxo.getIdFluxo());
			extensao.setTipoExtensao(tipo);
			extensao.setInformacaoExtensao(textField.getText());
			ECUGUI.CriarExtensao(extensao);
		}
	}
	public void mousePressed(MouseEvent e) 	{	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) 	{	}
	public void mouseExited(MouseEvent e)  	{	}
}
