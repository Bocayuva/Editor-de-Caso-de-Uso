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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Fluxo;
import control.ECUGUI;

@SuppressWarnings("serial")
public class Fluxo_Adicionar extends Templete_FluxoPanel implements MouseListener, ActionListener{
	
	/* Vareaveis da Class */
	// Data
	private Vector<Fluxo> list;
	private Vector<Fluxo> list2;
	private Fluxo fluxo;
	private Fluxo fluxo2;
	
	// Interface
	private JLabel lblFluxo;
	private JPanel radioPanel;
	private JRadioButton rdbtnAlternativo;
	private JRadioButton rdbtnSequencial;
	private JTextField informacaoTextField;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox2;
	private ButtonGroup group;
	private JPanel centerInformacaoPanel;
	
	public Fluxo_Adicionar(boolean principal, int idCasoDeUso) {
		super(principal, idCasoDeUso);
	}
	
	@Override
	public JPanel north(boolean edit) {
		
		return null;
	}
	
	@Override
	public JPanel center(boolean principal) {
		
		centerInformacaoPanel = new JPanel();
		centerInformacaoPanel.setPreferredSize(new Dimension(800, 500));
		centerInformacaoPanel.setMinimumSize(new Dimension(800, 500));
		centerInformacaoPanel.setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{8, 201, 281, 0, 8};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerInformacaoPanel.setLayout(gridBagLayout);
		
		JLabel lblTitulo  = null;
		if(principal == true)
			lblTitulo = new JLabel("Inserir uma informacao no Fluxo Principal");
		else
			lblTitulo = new JLabel("Inserir uma informacao no Fluxo Extensao");
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
		
		if(principal == true) {
			list2 = ECUGUI.getListFluxoPrincipalPlus(getIdCasoDeUso());
			comboBox2 = new JComboBox<String>(ECUGUI.getListagemFluxoInPosicaoNome(list2));
			comboBox2.addActionListener(this);
			comboBox2.setActionCommand("Plus");
			comboBox2.setSelectedItem(null);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 3;
			centerInformacaoPanel.add(comboBox2, gbc_comboBox);
		} else {
			list = ECUGUI.getListAllFluxo(getIdCasoDeUso());
			comboBox = new JComboBox<String>(ECUGUI.getListagemFluxoInPosicaoNome(list));
			comboBox.addActionListener(this);
			comboBox.setActionCommand("ListAllFluxo");
			comboBox.setSelectedItem(null);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 3;
			centerInformacaoPanel.add(comboBox, gbc_comboBox);
			
			JLabel lblLblflxExt = new JLabel("Selecione o fluxo Extensao:");
			lblLblflxExt.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			GridBagConstraints gbc_lblLblflxExt = new GridBagConstraints();
			gbc_lblLblflxExt.insets = new Insets(0, 0, 5, 5);
			gbc_lblLblflxExt.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblLblflxExt.gridx = 1;
			gbc_lblLblflxExt.gridy = 4;
			centerInformacaoPanel.add(lblLblflxExt, gbc_lblLblflxExt);

			comboBox2 = new JComboBox<String>();
			comboBox2.setSelectedItem(null);
			comboBox2.setEnabled(false);
			GridBagConstraints gbc_comboBox2 = new GridBagConstraints();
			gbc_comboBox2.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox2.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox2.gridx = 2;
			gbc_comboBox2.gridy = 4;
			centerInformacaoPanel.add(comboBox2, gbc_comboBox2);
	
			JLabel lblTipoFluxo = new JLabel("Tipo de Fluxo: ");
			lblTipoFluxo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			GridBagConstraints gbc_lblLblTiposicao = new GridBagConstraints();
			gbc_lblLblTiposicao.anchor = GridBagConstraints.WEST;
			gbc_lblLblTiposicao.insets = new Insets(0, 0, 5, 5);
			gbc_lblLblTiposicao.gridx = 1;
			gbc_lblLblTiposicao.gridy = 5;
			centerInformacaoPanel.add(lblTipoFluxo, gbc_lblLblTiposicao);
		
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 5;
			centerInformacaoPanel.add(radioPanel(), gbc_panel);
		}		
		
		lblFluxo = new JLabel("Fluxo: ");
		lblFluxo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLblposicao = new GridBagConstraints();
		gbc_lblLblposicao.anchor = GridBagConstraints.WEST;
		gbc_lblLblposicao.insets = new Insets(0, 0, 5, 5);
		gbc_lblLblposicao.gridx = 1;
		gbc_lblLblposicao.gridy = 6;
		centerInformacaoPanel.add(lblFluxo, gbc_lblLblposicao);
		
		if(informacaoTextField == null || fluxo2 == null) {
			informacaoTextField = new JTextField();
		}
		informacaoTextField.setColumns(10);
		informacaoTextField.setEditable(false);
		GridBagConstraints gbc_txtInformacao = new GridBagConstraints();
		gbc_txtInformacao.insets = new Insets(0, 0, 5, 5);
		gbc_txtInformacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInformacao.gridx = 2;
		gbc_txtInformacao.gridy = 6;
		centerInformacaoPanel.add(informacaoTextField, gbc_txtInformacao);
		
		JLabel lblOFluxoAntigo = new JLabel("* O Fluxo antigo selecionado sera movido para a posicao seguinte na ordem do Fluxo Principal");
		lblOFluxoAntigo.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		GridBagConstraints gbc_lblOFluxoAntigo = new GridBagConstraints();
		gbc_lblOFluxoAntigo.anchor = GridBagConstraints.WEST;
		gbc_lblOFluxoAntigo.gridwidth = 2;
		gbc_lblOFluxoAntigo.insets = new Insets(0, 0, 0, 5);
		gbc_lblOFluxoAntigo.gridx = 1;
		gbc_lblOFluxoAntigo.gridy = 7;
		centerInformacaoPanel.add(lblOFluxoAntigo, gbc_lblOFluxoAntigo);
		
		return centerInformacaoPanel;
	}

	@Override
	public JPanel south(boolean edit) {
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
		
		JPanel sul = new JPanel();
		sul.setLayout(new FlowLayout());
		sul.setBackground(Color.white);
		sul.add(sulPanel);
		return sul;
	}
	
	private JPanel radioPanel() {

		rdbtnAlternativo = new JRadioButton("Alternativo");
		rdbtnAlternativo.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnAlternativo.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnAlternativo.setBackground(Color.WHITE);
		rdbtnAlternativo.setActionCommand("rdbtnAlternativo");
		rdbtnAlternativo.addActionListener(this);
		rdbtnAlternativo.setEnabled(false);
		
		rdbtnSequencial = new JRadioButton("Sequencial");
		rdbtnSequencial.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnSequencial.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnSequencial.setBackground(Color.WHITE);
		rdbtnSequencial.setActionCommand("rdbtnSequencial");
		rdbtnSequencial.addActionListener(this);
		rdbtnSequencial.setEnabled(false);
		
		group = new ButtonGroup();
		group.add(rdbtnAlternativo);
		group.add(rdbtnSequencial);
		
		if(fluxo2 == null) {
			group.clearSelection();
		}
				
		JSeparator espacoInicial = new JSeparator();
		espacoInicial.setMinimumSize(new Dimension(50, 0));
		
		radioPanel = new JPanel();
		radioPanel.setBackground(Color.WHITE);
		radioPanel.add(espacoInicial);
		radioPanel.add(rdbtnAlternativo);
		radioPanel.add(rdbtnSequencial);

		FlowLayout flowLayout = (FlowLayout) radioPanel.getLayout();
		flowLayout.setHgap(12);
		flowLayout.setAlignment(FlowLayout.LEFT);
		return radioPanel;
	}
	
	/* Metodos Public */

	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("ListAllFluxo")){
			if( comboBox.getSelectedItem() != null) {
				int nun = comboBox.getSelectedIndex();
				fluxo = list.get(nun);
				fluxo2 = null;
				
				list2 = ECUGUI.getListFluxoPlus(fluxo);
				comboBox2.removeAllItems();
				for(String s:ECUGUI.getListagemFluxoInPosicaoNome(list2)){
					comboBox2.addItem(s);
				}
				comboBox2.addActionListener(this);
				comboBox2.setActionCommand("Plus");
				comboBox2.setEnabled(true);
				comboBox2.setSelectedItem(null);
				group.clearSelection();
				rdbtnAlternativo.setEnabled(false);
				rdbtnSequencial.setEnabled(false);
				informacaoTextField.setText("");
				informacaoTextField.setEditable(false);
				
				comboBox2.validate();
				comboBox2.repaint();
				centerInformacaoPanel.validate();
				centerInformacaoPanel.repaint();
			
				revalidate();
				repaint();
			}
			
		} else if (ac.equals("Plus")){
			
			if(comboBox2.getSelectedItem() != null) {
				int num = comboBox2.getSelectedIndex();
				fluxo2 = list2.get(num);
				if(fluxo != null && fluxo.getIdFluxoMaster() == 0) {
					fluxo2.setTipoAternativo(true);
				}
				lblFluxo.setText("Fluxo " + ECUGUI.getPosicaoFluxo(fluxo2) + " :");
				if(group!=null) {
					group.clearSelection();
					rdbtnAlternativo.setEnabled(true);
					rdbtnSequencial.setEnabled(true);
					if(fluxo2.getTipoAternativo()==true) {
						rdbtnAlternativo.setSelected(true);
						informacaoTextField.setEditable(true);
					}
				} else {
					informacaoTextField.setEditable(true);
				}
				informacaoTextField.setText("");
				revalidate();
				repaint();
			}
		} else if (ac.equals("rdbtnAlternativo"))  {
			fluxo2.setTipoAternativo(true);
			informacaoTextField.setEditable(true);
			revalidate();
			repaint();
		} else if (ac.equals("rdbtnSequencial")) {
			fluxo2.setTipoAternativo(false);
			informacaoTextField.setEditable(true);
			revalidate();
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("Cancelar")) {
			ECUGUI.encerrarFluxoFrame();
		}
		if (e.getComponent().getName().equals("Cadastrar")) {
			if (fluxo2 == null || informacaoTextField == null || informacaoTextField.equals("")) {				
				JOptionPane.showMessageDialog(null,"Dados Invalidos para o Cadastro");
				return;
			}
			fluxo2.setInformacao(informacaoTextField.getText());
			ECUGUI.AdicionarFluxo(fluxo2);
		}
	}

	public void mousePressed(MouseEvent e)  {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e)  {	}
	public void mouseExited(MouseEvent e)   {	}
}
