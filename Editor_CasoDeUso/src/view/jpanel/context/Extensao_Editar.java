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
public class Extensao_Editar extends JPanel implements MouseListener, ActionListener{
	
	/* Vareaveis Locais */
	//Dados
	private int idCasoDeUso;
	private Vector<Extensao> listExt;
	private Vector<CasoDeUso> listCDU;
	private Vector<Fluxo> listFluxo;
	private Extensao extensao;
	private Fluxo fluxo;
	private CasoDeUso casoExtensao;
	private String tipo;
	//Interface
	private JTextField textField;
	private JComboBox<String> comboBoxExt ;
	private JComboBox<String> comboBoxCDU ;
	private JComboBox<String> comboBoxFluxo;
	private JComboBox<String> comboBoxTipo ;
	JPanel center;
	public Extensao_Editar(int idCasoDeUso) {

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
		
		JLabel lblTitulo = new JLabel("Edi\u00E7\u00E3o de Extens\u00E3o ao Caso de Uso");
		lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		centerPanel.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblSelecioneAExtenso = new JLabel("Selecione a Extens\u00E3o a ser modificada");
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
		
		JLabel lblSelecioneOCaso = new JLabel("Selecione o Caso de Uso");
		GridBagConstraints gbc_lblSelecioneOCaso = new GridBagConstraints();
		gbc_lblSelecioneOCaso.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneOCaso.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneOCaso.gridx = 1;
		gbc_lblSelecioneOCaso.gridy = 3;
		centerPanel.add(lblSelecioneOCaso, gbc_lblSelecioneOCaso);
		
		listCDU = ECUGUI.getListagemCasosDeUsoIrmaos(idCasoDeUso);
		comboBoxCDU = new JComboBox<String>(ECUGUI.getListagemNomesCasosDeUsoIrmaos(listCDU));
		comboBoxCDU.setSelectedItem(null);
		comboBoxCDU.setActionCommand("comboBoxFluxo");
		comboBoxCDU.addActionListener(this);
		comboBoxCDU.setEnabled(false);
		GridBagConstraints gbc_comboBoxCDU = new GridBagConstraints();
		gbc_comboBoxCDU.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCDU.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCDU.gridx = 2;
		gbc_comboBoxCDU.gridy = 3;
		centerPanel.add(comboBoxCDU, gbc_comboBoxCDU);
		
		JLabel lblSelecioneOFluxo = new JLabel("Selecione o Fluxo Extendido");
		GridBagConstraints gbc_lblSelecioneOFluxo = new GridBagConstraints();
		gbc_lblSelecioneOFluxo.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneOFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneOFluxo.gridx = 1;
		gbc_lblSelecioneOFluxo.gridy = 4;
		centerPanel.add(lblSelecioneOFluxo, gbc_lblSelecioneOFluxo);
		
		listFluxo = ECUGUI.getListFluxoExtensao(idCasoDeUso);
		comboBoxFluxo = new JComboBox<String>(ECUGUI.getListagemFluxoInPosicaoNome(listFluxo));
		comboBoxFluxo.setSelectedItem(null);
		comboBoxFluxo.setActionCommand("comboBoxFluxo");
		comboBoxFluxo.addActionListener(this);
		comboBoxFluxo.setEnabled(false);
		GridBagConstraints gbc_comboBoxFluxo = new GridBagConstraints();
		gbc_comboBoxFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFluxo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFluxo.gridx = 2;
		gbc_comboBoxFluxo.gridy = 4;
		centerPanel.add(comboBoxFluxo, gbc_comboBoxFluxo);
		
		JLabel lblNewLabel = new JLabel("Selecione o Tipo de Extens\u00E3o");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		centerPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addItem("Extend");
		comboBoxTipo.addItem("Include");
		comboBoxTipo.setSelectedItem(null);
		comboBoxTipo.setActionCommand("comboBoxTipo");
		comboBoxTipo.addActionListener(this);
		comboBoxTipo.setEnabled(false);
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 2;
		gbc_comboBoxTipo.gridy = 5;
		centerPanel.add(comboBoxTipo, gbc_comboBoxTipo);
		
		JLabel lblNewLabel_1 = new JLabel("Informe o Coment\u00E1rio");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 6;
		centerPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText("");
		textField.setEnabled(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 6;
		centerPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		return centerPanel;
	}
	
	private Box inicializaSul() {

		Component horizontalGlue = Box.createHorizontalGlue();

		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setName("Cancelar");
		Cancelar.addMouseListener(this);

		JButton Alterar = new JButton("Alterar");
		Alterar.setName("Alterar");
		Alterar.addMouseListener(this);
		
		Box sulPanel = Box.createHorizontalBox();
		sulPanel.add(Cancelar);
		sulPanel.add(horizontalGlue);
		sulPanel.add(Alterar);
		return sulPanel;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("comboBoxExt") && comboBoxExt != null && comboBoxExt.getSelectedItem() != null) {
			int num = comboBoxExt.getSelectedIndex();
			extensao = listExt.get(num);
			System.out.println("Extensao|E "+extensao.getIdExtensao()+" CE "+extensao.getIdCasoDeUsoExtensao()+" CM "+extensao.getIdCasoDeUsoMaster()+" F "+extensao.getIdFluxoPosicao()+" T "+extensao.getTipoExtensao()+" I "+extensao.getInformacaoExtensao());
			casoExtensao = ECUGUI.getCasoDeUso(extensao.getIdCasoDeUsoExtensao());
			comboBoxCDU.setSelectedItem(casoExtensao.getNomeCasoDeUso());
			comboBoxCDU.setEnabled(true);
			fluxo = ECUGUI.getFluxo(extensao.getIdFluxoPosicao());
			String infoFluxo = ECUGUI.getPosicaoFluxo(fluxo) + ". " + fluxo.getInformacaoFluxo();
			comboBoxFluxo.setSelectedItem(infoFluxo);
			comboBoxFluxo.setEnabled(true);
			comboBoxTipo.setSelectedItem(extensao.getTipoExtensao());
			comboBoxTipo.setEnabled(true);
			textField.setText(extensao.getInformacaoExtensao());
			textField.setEnabled(true);
		}
		if(e.getActionCommand().equals("comboBoxCDU") && comboBoxCDU != null && comboBoxCDU.getSelectedItem() != null) {
			int num = comboBoxCDU.getSelectedIndex();
			casoExtensao = listCDU.get(num);
			extensao.setCasoDeUsoExtensao(casoExtensao);
		}
		if(e.getActionCommand().equals("comboBoxFluxo") && comboBoxFluxo != null && comboBoxFluxo.getSelectedItem() != null) {
			int num = comboBoxFluxo.getSelectedIndex();
			fluxo = listFluxo.get(num);
			extensao.setFluxo(fluxo);
		}
		if(e.getActionCommand().equals("comboBoxTipo") && comboBoxTipo != null && comboBoxTipo.getSelectedItem() != null) {
			tipo = comboBoxTipo.getSelectedItem().toString();
			extensao.setTipoExtensao(tipo);
		}
		revalidate();
		repaint();
		
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Cancelar")) {
			ECUGUI.encerrarExtensaoFrame();
		}
		if (e.getComponent().getName().equals("Alterar")) {
			if(extensao.getIdExtensao()<=0||extensao.getIdCasoDeUsoMaster()<=0||extensao.getIdCasoDeUsoExtensao()<=0
					||extensao.getIdFluxoPosicao()<=0||extensao.getIdExtensao()<=0||extensao.getTipoExtensao().equals("")||extensao.getInformacaoExtensao().equals("")
					|| textField== null || textField.equals("")){				
				JOptionPane.showMessageDialog(null,"Dados Invalidos para o Cadastro");
				return;
			}
			extensao.setInformacaoExtensao(textField.getText());
			 System.out.println("Extensao|E "+extensao.getIdExtensao()+" CE "+extensao.getIdCasoDeUsoExtensao()+" CM "+extensao.getIdCasoDeUsoMaster()+" F "+extensao.getIdFluxoPosicao()+" T "+extensao.getTipoExtensao()+" I "+extensao.getInformacaoExtensao());
			ECUGUI.EditarExtensao(extensao);
		}
	}
	public void mousePressed(MouseEvent e) 	{	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) 	{	}
	public void mouseExited(MouseEvent e)  	{	}
}
