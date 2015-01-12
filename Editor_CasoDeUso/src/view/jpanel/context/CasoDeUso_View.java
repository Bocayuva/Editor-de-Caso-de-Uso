package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.CasoDeUso;
import view.jpanel.context.Extensao_View.TipoExtensaoPanel;
import control.ECUGUI;

@SuppressWarnings("serial")
public class CasoDeUso_View extends JPanel implements MouseListener, Observer {

	/* Vareaveis da Class */

	// Data
	private int idCasoDeUso;
	private String nomeCasoDeUsoText;
	private String objetivoText;
	private String escopoText;
	private String nivelText;
	private String atorPrimario;
	private String atorSuporte;
	private String preCondicaoText;
	private String posCondicaoText;
	
	// Interface
	private JPanel south;
	private JPanel informacaoPane;
	private JScrollPane panelPane;
	
	/* Construtor Default */

	public CasoDeUso_View(int idCasoDeUso) {
		super();
		this.idCasoDeUso = idCasoDeUso;
		this.nomeCasoDeUsoText = null;
		this.objetivoText = null;
		this.escopoText = null;
		this.nivelText = null;
		this.atorPrimario = null;
		this.atorSuporte = null;
		this.preCondicaoText = null;
		this.posCondicaoText = null;
		ECUGUI.addPanelObserver(this);
		setCarregarDados();
		inicializaPanel();
	}

	/* Metodos Private */

	public void inicializaPanel() {

		inicializaNorth();
		inicializaCenter();
		inicializaSouth();

		setBackground(Color.white);
		setLayout(new BorderLayout());
		add(informacaoPane, BorderLayout.NORTH);
		add(panelPane, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
	}

	private void inicializaNorth() {
		
		JLabel lblCasoDeUso = new JLabel("Caso de Uso:");
		lblCasoDeUso.setHorizontalAlignment(SwingConstants.LEFT);
		lblCasoDeUso.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCasoDeUso = new GridBagConstraints();
		gbc_lblCasoDeUso.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCasoDeUso.insets = new Insets(0, 0, 5, 5);
		gbc_lblCasoDeUso.gridx = 1;
		gbc_lblCasoDeUso.gridy = 1;
		
		JLabel lblRespostaCasoDe = new JLabel(nomeCasoDeUsoText);
		GridBagConstraints gbc_lblRespostaCasoDe = new GridBagConstraints();
		gbc_lblRespostaCasoDe.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaCasoDe.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaCasoDe.gridx = 2;
		gbc_lblRespostaCasoDe.gridy = 1;

		JLabel lblObjetivo = new JLabel("Objetivo:");
		lblObjetivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjetivo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblObjetivo = new GridBagConstraints();
		gbc_lblObjetivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblObjetivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblObjetivo.gridx = 1;
		gbc_lblObjetivo.gridy = 2;
		
		JLabel lblRespostaObjetivo = new JLabel(objetivoText);
		GridBagConstraints gbc_lblRespostaObjetivo = new GridBagConstraints();
		gbc_lblRespostaObjetivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaObjetivo.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaObjetivo.gridx = 2;
		gbc_lblRespostaObjetivo.gridy = 2;
		
		JLabel lblEscopo = new JLabel("Escopo:");
		lblEscopo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEscopo = new GridBagConstraints();
		gbc_lblEscopo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEscopo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEscopo.gridx = 1;
		gbc_lblEscopo.gridy = 3;
		
		JLabel lblRespostaEscopo = new JLabel(escopoText);
		GridBagConstraints gbc_lblRespostaEscopo = new GridBagConstraints();
		gbc_lblRespostaEscopo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaEscopo.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaEscopo.gridx = 2;
		gbc_lblRespostaEscopo.gridy = 3;
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNivel = new GridBagConstraints();
		gbc_lblNivel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNivel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNivel.gridx = 1;
		gbc_lblNivel.gridy = 4;

		JLabel lblRespostaNivel = new JLabel(nivelText);
		lblRespostaNivel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRespostaNivel = new GridBagConstraints();
		gbc_lblRespostaNivel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaNivel.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaNivel.gridx = 2;
		gbc_lblRespostaNivel.gridy = 4;
		
		JLabel lblAtorPrimario = new JLabel("Ator Primario:");
		lblAtorPrimario.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAtorPrimario = new GridBagConstraints();
		gbc_lblAtorPrimario.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAtorPrimario.insets = new Insets(0, 0, 5, 5);
		gbc_lblAtorPrimario.gridx = 1;
		gbc_lblAtorPrimario.gridy = 5;
		
		JLabel lblRespostaAtorPrimario = new JLabel(atorPrimario);
		lblRespostaAtorPrimario.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRespostaAtorPrimario = new GridBagConstraints();
		gbc_lblRespostaAtorPrimario.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaAtorPrimario.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaAtorPrimario.gridx = 2;
		gbc_lblRespostaAtorPrimario.gridy = 5;
		
		JLabel lblAtorDeSuporte = new JLabel("Ator de Suporte:");
		lblAtorDeSuporte.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAtorDeSuporte = new GridBagConstraints();
		gbc_lblAtorDeSuporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAtorDeSuporte.insets = new Insets(0, 0, 5, 5);
		gbc_lblAtorDeSuporte.gridx = 1;
		gbc_lblAtorDeSuporte.gridy = 6;

		JLabel lblRespostaAtorSuporte = new JLabel(atorSuporte);
		lblRespostaAtorSuporte.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRespostaAtorSuporte = new GridBagConstraints();
		gbc_lblRespostaAtorSuporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaAtorSuporte.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaAtorSuporte.gridx = 2;
		gbc_lblRespostaAtorSuporte.gridy = 6;
		
		JLabel lblPreCondio = new JLabel("Pre Condição:");
		lblPreCondio.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPreCondio = new GridBagConstraints();
		gbc_lblPreCondio.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPreCondio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreCondio.gridx = 1;
		gbc_lblPreCondio.gridy = 7;
		
		JLabel lblRespostaPreCondicao = new JLabel(preCondicaoText);
		lblRespostaPreCondicao.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRespostaPreCondicao = new GridBagConstraints();
		gbc_lblRespostaPreCondicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaPreCondicao.insets = new Insets(0, 0, 5, 0);
		gbc_lblRespostaPreCondicao.gridx = 2;
		gbc_lblRespostaPreCondicao.gridy = 7;
		
		JLabel lblPosCondio = new JLabel("Pos Condição:");
		lblPosCondio.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPosCondio = new GridBagConstraints();
		gbc_lblPosCondio.insets = new Insets(0, 0, 0, 5);
		gbc_lblPosCondio.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPosCondio.gridx = 1;
		gbc_lblPosCondio.gridy = 8;

		JLabel lblRespostaPosCondicao = new JLabel(posCondicaoText);
		lblRespostaPosCondicao.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRespostaPosCondicao = new GridBagConstraints();
		gbc_lblRespostaPosCondicao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRespostaPosCondicao.gridx = 2;
		gbc_lblRespostaPosCondicao.gridy = 8;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 130, 404, 10};
		gridBagLayout.rowHeights = new int[]{0, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		informacaoPane = new JPanel();
		informacaoPane.setBackground(Color.WHITE);
		//informacaoPane.setPreferredSize(new Dimension(800, 500));
		//informacaoPane.setMinimumSize(new Dimension(800, 500));
		informacaoPane.setLayout(gridBagLayout);
		informacaoPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		informacaoPane.add(lblCasoDeUso, gbc_lblCasoDeUso);
		informacaoPane.add(lblRespostaCasoDe, gbc_lblRespostaCasoDe);
		informacaoPane.add(lblObjetivo, gbc_lblObjetivo);
		informacaoPane.add(lblRespostaObjetivo, gbc_lblRespostaObjetivo);
		informacaoPane.add(lblEscopo, gbc_lblEscopo);
		informacaoPane.add(lblRespostaEscopo, gbc_lblRespostaEscopo);
		informacaoPane.add(lblNivel, gbc_lblNivel);
		informacaoPane.add(lblRespostaNivel, gbc_lblRespostaNivel);
		informacaoPane.add(lblAtorPrimario, gbc_lblAtorPrimario);
		informacaoPane.add(lblRespostaAtorPrimario, gbc_lblRespostaAtorPrimario);
		informacaoPane.add(lblAtorDeSuporte, gbc_lblAtorDeSuporte);
		informacaoPane.add(lblRespostaAtorSuporte, gbc_lblRespostaAtorSuporte);
		informacaoPane.add(lblPreCondio, gbc_lblPreCondio);
		informacaoPane.add(lblRespostaPreCondicao, gbc_lblRespostaPreCondicao);
		informacaoPane.add(lblPosCondio, gbc_lblPosCondio);
		informacaoPane.add(lblRespostaPosCondicao, gbc_lblRespostaPosCondicao);	
	}

	private void inicializaCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		
		panel.add(new Extensao_View(idCasoDeUso, TipoExtensaoPanel.view), BorderLayout.NORTH);
		panel.add(new Fluxo_Principal_View(idCasoDeUso, false), BorderLayout.CENTER);
		panel.add(new Fluxo_Extensao_View(idCasoDeUso, false), BorderLayout.SOUTH);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JPanel center = new JPanel();
		center.setLayout(flowLayout);
		center.setBackground(Color.white);
		center.add(panel);
		panelPane = new JScrollPane(center);
		panelPane.setBorder(null);

	}

	private void inicializaSouth() {
		south = new JPanel();
		south.setLayout(new GridLayout());
		south.setBackground(Color.white);

		JButton btnCancelarCDU = new JButton("Sair Caso de Uso");
		btnCancelarCDU.setName("CancelarCasoDeUso");
		btnCancelarCDU.addMouseListener(this);
		south.add(btnCancelarCDU);

		JButton btnEditarCDU = new JButton("Editar o Caso de Uso");
		btnEditarCDU.setName("EditarCasoDeUso");
		btnEditarCDU.addMouseListener(this);
		south.add(btnEditarCDU);
	}

	private void setCarregarDados() {

		CasoDeUso cdu = ECUGUI.getCasoDeUso(idCasoDeUso);
		
		if(cdu != null) {
			this.nomeCasoDeUsoText = cdu.getNomeCasoDeUso();
			this.objetivoText = cdu.getObjetivo();
			this.escopoText = cdu.getEscopo();
			this.nivelText = cdu.getNivel();
			this.preCondicaoText = cdu.getPreCondicao();
			this.posCondicaoText = cdu.getPosCondicao();
			this.atorPrimario = cdu.getAtorPrimario();
			this.atorSuporte = cdu.getAtorSuporte();
		} else {
			this.nomeCasoDeUsoText = "";
			this.objetivoText = "";
			this.escopoText = "";
			this.nivelText = "";
			this.preCondicaoText = "";
			this.posCondicaoText = "";
			this.atorPrimario = "";
			this.atorSuporte = "";
		}
		
	}

	/* Metodos Public */

	public void update(Observable o, Object arg) {
		setCarregarDados();
		removeAll();
		inicializaPanel();
		revalidate();
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("CancelarCasoDeUso")) {
			ECUGUI.IrProjeto();
		}
		if (e.getComponent().getName().equals("EditarCasoDeUso")) {
			ECUGUI.IrEditarCasoDeUso(idCasoDeUso);
		}
	}

	public void mousePressed(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {	}
}
