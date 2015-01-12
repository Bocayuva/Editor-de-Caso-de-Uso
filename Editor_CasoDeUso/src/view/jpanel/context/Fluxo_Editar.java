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
public class Fluxo_Editar extends Templete_FluxoPanel implements MouseListener,
		ActionListener {

	/* Vareaveis da Class */

	// Data
	private String[] listagemFluxo;
	private String[] listagemModificadaFluxo;
	private Fluxo fluxo;
	private Fluxo fluxoPosicao;
	private Fluxo fluxoPosicaoNovo;
	private Vector<Fluxo> list;
	private TipoEdicao opcaoEdicaoSelecionado;

	// Interface
	private Box southBox;
	private JLabel lblFluxo;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel radioPanel;
	private JPanel centerInformacaoPanel;
	private JButton btnAlterar;
	private ButtonGroup group;
	private JTextField informacaoTextField;
	private JRadioButton rdbtnAlternativo;
	private JRadioButton rdbtnSequencial;
	private JRadioButton rdbtnConteudo;
	private JRadioButton rdbtnPosicao;
	private JComboBox<String> comboBox;
	private JComboBox<String> posicaoComboBox;
	private JComboBox<String> posicaoNovaComboBox;

	private enum TipoEdicao {
		informacao, posicao
	}

	private JPanel inicializaCenterPosicaoPanel() {

		JLabel lblTituloInfoFluxo = new JLabel(
				"Alterar a ordem na posicao do Fluxo");
		lblTituloInfoFluxo.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		GridBagConstraints gbc_lblTituloInfoFluxo = new GridBagConstraints();
		gbc_lblTituloInfoFluxo.gridwidth = 2;
		gbc_lblTituloInfoFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloInfoFluxo.gridx = 1;
		gbc_lblTituloInfoFluxo.gridy = 0;

		JLabel lblSelecioneOFluxo = new JLabel("Selecione o Fluxo:");
		GridBagConstraints gbc_lblSelecioneOFluxo = new GridBagConstraints();
		gbc_lblSelecioneOFluxo.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneOFluxo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneOFluxo.gridx = 1;
		gbc_lblSelecioneOFluxo.gridy = 2;

		list = ECUGUI.getListAllFluxo(getIdCasoDeUso());
		listagemFluxo = ECUGUI.getListagemFluxoInPosicaoNome(list);
		if (list != null)
			posicaoComboBox = new JComboBox<String>(listagemFluxo);
		else
			posicaoComboBox = new JComboBox<String>();
		posicaoComboBox.setActionCommand("posicaoComboBox");
		posicaoComboBox.addActionListener(this);
		posicaoComboBox.setSelectedItem(null);

		GridBagConstraints gbc_posicaoComboBox = new GridBagConstraints();
		gbc_posicaoComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_posicaoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_posicaoComboBox.gridx = 2;
		gbc_posicaoComboBox.gridy = 2;

		JLabel lblSelecioneANova = new JLabel(
				"Selecione a nova posicao do Fluxo:");
		GridBagConstraints gbc_lblSelecioneANova = new GridBagConstraints();
		gbc_lblSelecioneANova.anchor = GridBagConstraints.EAST;
		gbc_lblSelecioneANova.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneANova.gridx = 1;
		gbc_lblSelecioneANova.gridy = 3;

		if (listagemModificadaFluxo != null)
			posicaoNovaComboBox = new JComboBox<String>(listagemModificadaFluxo);
		else
			posicaoNovaComboBox = new JComboBox<String>();
		posicaoNovaComboBox.setActionCommand("posicaoNovaComboBox");
		posicaoNovaComboBox.addActionListener(this);
		posicaoNovaComboBox.setEnabled(false);
		posicaoNovaComboBox.setSelectedItem(null);
		GridBagConstraints gbc_posicaoNovaComboBox = new GridBagConstraints();
		gbc_posicaoNovaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_posicaoNovaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_posicaoNovaComboBox.gridx = 2;
		gbc_posicaoNovaComboBox.gridy = 3;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 8, 227, 0, 8, 0 };
		gridBagLayout.rowHeights = new int[] { 65, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };

		JPanel centerPosicioPanel = new JPanel();
		centerPosicioPanel.setLayout(gridBagLayout);
		centerPosicioPanel.setBackground(Color.WHITE);
		centerPosicioPanel.add(lblTituloInfoFluxo, gbc_lblTituloInfoFluxo);
		centerPosicioPanel.add(lblSelecioneOFluxo, gbc_lblSelecioneOFluxo);
		centerPosicioPanel.add(posicaoComboBox, gbc_posicaoComboBox);
		centerPosicioPanel.add(lblSelecioneANova, gbc_lblSelecioneANova);
		centerPosicioPanel.add(posicaoNovaComboBox, gbc_posicaoNovaComboBox);

		return centerPosicioPanel;
	}

	private JPanel inicializaCenterInformacaoPanel() {

		centerInformacaoPanel = new JPanel();
		centerInformacaoPanel.setPreferredSize(new Dimension(800, 500));
		centerInformacaoPanel.setMinimumSize(new Dimension(800, 500));
		centerInformacaoPanel.setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 8, 201, 281, 0, 8 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		centerInformacaoPanel.setLayout(gridBagLayout);

		JLabel lblTitulo = null;
		lblTitulo = new JLabel("Editar informacao do Fluxo");
		lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		centerInformacaoPanel.add(lblTitulo, gbc_lblTitulo);

		JLabel lblLblcombobox = new JLabel("Selecione o fluxo:");
		lblLblcombobox.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLblcombobox = new GridBagConstraints();
		gbc_lblLblcombobox.insets = new Insets(0, 0, 5, 5);
		gbc_lblLblcombobox.anchor = GridBagConstraints.WEST;
		gbc_lblLblcombobox.gridx = 1;
		gbc_lblLblcombobox.gridy = 3;
		centerInformacaoPanel.add(lblLblcombobox, gbc_lblLblcombobox);

		list = ECUGUI.getListAllFluxo(getIdCasoDeUso());
		if (list != null)
			comboBox = new JComboBox<String>(
					ECUGUI.getListagemFluxoInPosicaoNome(list));
		else
			comboBox = new JComboBox<String>();
		comboBox.addActionListener(this);
		comboBox.setActionCommand("ComBox");
		comboBox.setSelectedItem(null);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		centerInformacaoPanel.add(comboBox, gbc_comboBox);

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

		lblFluxo = new JLabel("Fluxo: ");
		lblFluxo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLblposicao = new GridBagConstraints();
		gbc_lblLblposicao.anchor = GridBagConstraints.WEST;
		gbc_lblLblposicao.insets = new Insets(0, 0, 5, 5);
		gbc_lblLblposicao.gridx = 1;
		gbc_lblLblposicao.gridy = 6;
		centerInformacaoPanel.add(lblFluxo, gbc_lblLblposicao);

		informacaoTextField = new JTextField();
		informacaoTextField.setColumns(10);
		informacaoTextField.setEditable(false);
		GridBagConstraints gbc_txtInformacao = new GridBagConstraints();
		gbc_txtInformacao.insets = new Insets(0, 0, 5, 5);
		gbc_txtInformacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInformacao.gridx = 2;
		gbc_txtInformacao.gridy = 6;
		centerInformacaoPanel.add(informacaoTextField, gbc_txtInformacao);

		return centerInformacaoPanel;
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

		if (fluxo == null) {
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

	public Fluxo_Editar(boolean edit, int idCasoDeUso) {
		super(edit, idCasoDeUso);
	}

	@Override
	public JPanel north(boolean edit) {
		JLabel lblTipoDeEdicao = new JLabel("Tipo de Edicao no Fluxo:");
		lblTipoDeEdicao.setBackground(Color.WHITE);
		lblTipoDeEdicao.setHorizontalAlignment(SwingConstants.LEFT);

		rdbtnPosicao = new JRadioButton("Posicao");
		rdbtnPosicao.setBackground(Color.WHITE);
		rdbtnPosicao.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnPosicao.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnPosicao.setActionCommand("rdbPosicao");
		rdbtnPosicao.addActionListener(this);

		rdbtnConteudo = new JRadioButton("Informacao");
		rdbtnConteudo.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnConteudo.setBackground(Color.WHITE);
		rdbtnConteudo.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnConteudo.setActionCommand("rdbConteudo");
		rdbtnConteudo.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPosicao);
		group.add(rdbtnConteudo);
		group.clearSelection();

		if (opcaoEdicaoSelecionado != null
				&& opcaoEdicaoSelecionado.equals(TipoEdicao.posicao))
			rdbtnPosicao.setSelected(true);
		else if (opcaoEdicaoSelecionado != null
				&& opcaoEdicaoSelecionado.equals(TipoEdicao.informacao))
			rdbtnConteudo.setSelected(true);

		JSeparator espacoInicial = new JSeparator();
		espacoInicial.setMinimumSize(new Dimension(50, 0));

		northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.add(lblTipoDeEdicao);
		northPanel.add(espacoInicial);
		northPanel.add(rdbtnPosicao);
		northPanel.add(rdbtnConteudo);

		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		flowLayout.setHgap(12);
		flowLayout.setAlignment(FlowLayout.LEFT);
		return northPanel;
	}

	@Override
	public JPanel center(boolean edit) {

		if (centerPanel != null) {
			centerPanel.removeAll();
			centerPanel = null;
		}
		if (opcaoEdicaoSelecionado == null) {
			centerPanel = new JPanel();
			centerPanel.setBackground(Color.white);
			centerPanel.setLayout(new FlowLayout());
			centerPanel.setMinimumSize(new Dimension(500, 200));
			centerPanel.setPreferredSize(new Dimension(500, 200));
		} else {
			switch (opcaoEdicaoSelecionado) {
			case informacao:
				centerPanel = inicializaCenterInformacaoPanel();
				break;
			case posicao:
				centerPanel = inicializaCenterPosicaoPanel();
				break;
			}
		}
		return centerPanel;
	}

	@Override
	public JPanel south(boolean edit) {
		Component espaco = Box.createHorizontalGlue();

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setName("btnCancelar");
		btnCancelar.addMouseListener(this);

		btnAlterar = new JButton("Confirmar Alteracao");
		btnAlterar.setName("btnConfirmarAlteracao");
		btnAlterar.addMouseListener(this);

		southBox = Box.createHorizontalBox();
		southBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		southBox.add(btnCancelar);
		southBox.add(espaco);
		southBox.add(btnAlterar);

		JPanel south = new JPanel();
		south.setLayout(new FlowLayout());
		south.setBackground(Color.white);
		south.add(southBox);
		return south;
	}

	public void actionPerformed(ActionEvent e) {

		String ac = e.getActionCommand();
		if (ac.equals("rdbPosicao")) {

			opcaoEdicaoSelecionado = TipoEdicao.posicao;
			fluxo = null;
			fluxoPosicao = null;
			fluxoPosicaoNovo = null;
			reloadView();

		} else if (ac.equals("rdbConteudo")) {

			opcaoEdicaoSelecionado = TipoEdicao.informacao;
			fluxo = null;
			fluxoPosicao = null;
			fluxoPosicaoNovo = null;
			reloadView();

		} else if (ac.equals("posicaoComboBox") && posicaoComboBox != null
				&& posicaoComboBox.getSelectedItem() != null) {

			int num = posicaoComboBox.getSelectedIndex();
			if (fluxoPosicao != list.get(num)) {
				posicaoNovaComboBox.setSelectedItem(null);
			}
			fluxoPosicao = null;
			fluxoPosicaoNovo = null;
			fluxoPosicao = list.get(num);

			listagemModificadaFluxo = ECUGUI
					.getListagemFluxoInPosicaoNome(list);
			posicaoNovaComboBox.removeAllItems();
			for (String s : listagemModificadaFluxo) {
				posicaoNovaComboBox.addItem(s);
			}
			posicaoNovaComboBox.setEnabled(true);
			posicaoNovaComboBox.setSelectedItem(null);

		} else if (ac.equals("posicaoNovaComboBox")
				&& posicaoNovaComboBox != null
				&& posicaoNovaComboBox.getSelectedItem() != null) {

			int num = posicaoNovaComboBox.getSelectedIndex();
			fluxoPosicaoNovo = null;
			fluxoPosicaoNovo = list.get(num);

		} else if (ac.equals("ComBox") && comboBox.getSelectedItem() != null) {

			int num = comboBox.getSelectedIndex();
			fluxo = list.get(num);

			// limpa tudo
			group.clearSelection();
			informacaoTextField.setText("");

			// habilita a edicao
			rdbtnAlternativo.setEnabled(true);
			rdbtnSequencial.setEnabled(true);
			informacaoTextField.setEditable(true);

			if (fluxo != null) {

				lblFluxo.setText("Fluxo " + ECUGUI.getPosicaoFluxo(fluxo)
						+ " :");

				if (fluxo.getTipoAternativo() == true)
					rdbtnAlternativo.setSelected(true);
				else
					rdbtnSequencial.setSelected(true);

				informacaoTextField.setText(fluxo.getInformacaoFluxo());
			}

			revalidate();
			repaint();
		} else if (ac.equals("rdbtnAlternativo")) {
			fluxo.setTipoAternativo(true);
			rdbtnAlternativo.setSelected(true);
			revalidate();
			repaint();
		} else if (ac.equals("rdbtnSequencial")) {
			rdbtnSequencial.setSelected(true);
			fluxo.setTipoAternativo(false);

			revalidate();
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("btnCancelar")) {
			ECUGUI.encerrarFluxoFrame();
		}
		if (e.getComponent().getName().equals("btnConfirmarAlteracao")) {

			if ((fluxoPosicao != null && fluxoPosicaoNovo != null
					&& posicaoNovaComboBox.getSelectedItem() == null && posicaoComboBox
					.getSelectedItem() == null)
					|| (fluxo != null && (comboBox.getSelectedItem() == null || fluxo
							.getInformacaoFluxo().equals("") == true))) {
				JOptionPane.showMessageDialog(null,
						"Dados Invalidos para o Edicao");
			}

			if (fluxo != null) {
				fluxo.setInformacao(informacaoTextField.getText());
				ECUGUI.EditarFluxo(fluxo);
			} else {
				if (fluxoPosicao.getIdFluxoMaster() == fluxoPosicaoNovo
						.getIdFluxoMaster()) {

					int pos = fluxoPosicao.getPosicaoFluxo();

					fluxoPosicao.setPosicao(fluxoPosicaoNovo.getPosicaoFluxo());
					fluxoPosicaoNovo.setPosicao(pos);
				} else {

					int pos = fluxoPosicao.getPosicaoFluxo();
					int master = fluxoPosicao.getIdFluxoMaster();
					boolean alt = fluxoPosicao.getTipoAternativo();

					fluxoPosicao.setIdFluxoMaster(fluxoPosicaoNovo
							.getIdFluxoMaster());
					fluxoPosicao.setPosicao(fluxoPosicaoNovo.getPosicaoFluxo());
					fluxoPosicao.setTipoAternativo(fluxoPosicaoNovo
							.getTipoAternativo());

					fluxoPosicaoNovo.setIdFluxoMaster(master);
					fluxoPosicaoNovo.setPosicao(pos);
					fluxoPosicaoNovo.setTipoAternativo(alt);
				}
				ECUGUI.EditarFluxo(fluxoPosicao);
				ECUGUI.EditarFluxo(fluxoPosicaoNovo);
			}

		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
