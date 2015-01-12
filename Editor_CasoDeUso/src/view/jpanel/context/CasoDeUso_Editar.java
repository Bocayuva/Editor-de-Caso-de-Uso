package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Ator;
import model.CasoDeUso;
import view.jpanel.context.Extensao_View.TipoExtensaoPanel;
import control.ECUGUI;

@SuppressWarnings("serial")
public class CasoDeUso_Editar extends JPanel implements
		MouseListener, ActionListener, Observer {

	/* Vareaveis da Class */

	// Data
	private boolean edit;
	private CasoDeUso casoDeUso;
	private Vector<Ator> list;
	private String listagemAtoresPrincipal[];
	private ArrayList<String> listagemAtoresSecundario;
	
	// Interface
	private JTextField casoDeUsoTextField;
	private JTextField ObjetivoTextField;
	private JTextField escopoTextField;
	private JTextField nivelTextField;
	private JTextField preCondicaoTextField;
	private JTextField posCondicaoTextField;
	private JScrollPane panelPane;
	private JPanel south;
	private JPanel informacaoPane;
	private JComboBox<String> atoPrimarioComboBox;
	private JComboBox<String> atorSecundarioComboBox;
	private Box horizontalBox_4;
	private Box horizontalBox_5;
	private int idCasoDeUso;

	/* Construtor Default */

	public CasoDeUso_Editar(int idCasoDeUso, boolean edit) {
		this.edit = false;
		this.edit = edit;
		this.idCasoDeUso = idCasoDeUso;
		ECUGUI.addPanelObserver(this);
		
		if(idCasoDeUso > 0) {
			casoDeUso = ECUGUI.getCasoDeUso(idCasoDeUso);
		} else	{
			casoDeUso = new CasoDeUso();
			casoDeUso.setIdProjeto(ECUGUI.getIdProjeto());
		}
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

	private JPanel inicializaNorth() {

		informacaoPane = new JPanel();
		informacaoPane.setBackground(Color.WHITE);
		informacaoPane.setLayout(new GridLayout(0, 1, 0, 0));
		add(informacaoPane, BorderLayout.NORTH);

		// //Label - Caso De Uso
		Box horizontalBox = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox);

		JLabel casoDeUsoLabel = new JLabel("Caso de Uso:");
		casoDeUsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		casoDeUsoLabel.setMinimumSize(new Dimension(105, 16));
		casoDeUsoLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox.add(casoDeUsoLabel);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);

		casoDeUsoTextField = new JTextField();
		if (casoDeUso.getNomeCasoDeUso() != null)
			casoDeUsoTextField.setText(casoDeUso.getNomeCasoDeUso());
			
		horizontalBox.add(casoDeUsoTextField);
		casoDeUsoTextField.setColumns(10);

		// /Label - Objetivo
		Box horizontalBox_1 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_1);

		JLabel objetivoLabel = new JLabel("Objetivo:");
		objetivoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		objetivoLabel.setMinimumSize(new Dimension(105, 20));
		objetivoLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_1.add(objetivoLabel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut);

		ObjetivoTextField = new JTextField();
		if (casoDeUso.getObjetivo() != null)
			ObjetivoTextField.setText(casoDeUso.getObjetivo());
	
			
		horizontalBox_1.add(ObjetivoTextField);
		ObjetivoTextField.setColumns(10);

		// /Label - Escopo
		Box horizontalBox_2 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_2);

		JLabel escopoLabel = new JLabel("Escopo:");
		escopoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		escopoLabel.setMinimumSize(new Dimension(105, 20));
		escopoLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_2.add(escopoLabel);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);

		if (casoDeUso.getEscopo() != null)
			escopoTextField = new JTextField(casoDeUso.getEscopo());
		else
			escopoTextField = new JTextField( );
		horizontalBox_2.add(escopoTextField);
		escopoTextField.setColumns(10);

		// /Label - Nivel
		Box horizontalBox_3 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_3);

		JLabel nivelLabel = new JLabel("Nivel:");
		nivelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nivelLabel.setMinimumSize(new Dimension(105, 20));
		nivelLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_3.add(nivelLabel);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_7);

		if (casoDeUso.getNivel() != null)
			nivelTextField = new JTextField(casoDeUso.getNivel());
		else
			nivelTextField = new JTextField( );
		horizontalBox_3.add(nivelTextField);
		nivelTextField.setColumns(10);

		// /COMBOBOX - Ator de Principal
		horizontalBox_4 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_4);

		JLabel atorPrimairoLabel = new JLabel("Ator Primario:");
		atorPrimairoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		atorPrimairoLabel.setMinimumSize(new Dimension(105, 20));
		atorPrimairoLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_4.add(atorPrimairoLabel);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_6);

		// Carrega os dados dos atores disponiveis
		setComboBox();
		if(listagemAtoresPrincipal != null || listagemAtoresPrincipal.length > 0) {
			atoPrimarioComboBox = new JComboBox<String>(listagemAtoresPrincipal);
			atoPrimarioComboBox.setSelectedItem(casoDeUso.getAtorPrimario());
		} else
			atoPrimarioComboBox = new JComboBox<String>();
		atoPrimarioComboBox.setEnabled(true);
		atoPrimarioComboBox.addActionListener(this);
		atoPrimarioComboBox.setActionCommand("atoPrimarioComboBox");
		
		horizontalBox_4.add(atoPrimarioComboBox);

		// /COMBOBOX - Ator de Suporte
		horizontalBox_5 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_5);

		JLabel atorSecundarioLabel = new JLabel("Ator de Suporte:");
		atorSecundarioLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		atorSecundarioLabel.setMinimumSize(new Dimension(105, 20));
		atorSecundarioLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_5.add(atorSecundarioLabel);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_3);
		
		String[] stockArr = new String[listagemAtoresSecundario.size()];
		stockArr = listagemAtoresSecundario.toArray(stockArr);
		
		if(stockArr != null )
			atorSecundarioComboBox = new JComboBox<String>(stockArr);
		else
			atorSecundarioComboBox = new JComboBox<String>();
		atorSecundarioComboBox.addActionListener(this);
		atorSecundarioComboBox.setActionCommand("atorSecundarioComboBox");
		if (casoDeUso.getIdAtorPrimario() == 0) {
			atorSecundarioComboBox.setEnabled(false);
			atorSecundarioComboBox.setSelectedItem(null);
		} else if (casoDeUso.getIdAtorPrimario() != casoDeUso.getIdAtorSuporte()) {
			atorSecundarioComboBox.setSelectedItem(casoDeUso.getAtorSuporte());
			atorSecundarioComboBox.setEnabled(true);
		} else {
			atorSecundarioComboBox.setSelectedItem(null);
		}

		horizontalBox_5.add(atorSecundarioComboBox);

		// LABEL - Pre Condicao
		Box horizontalBox_6 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_6);

		JLabel preCondicaoLabel = new JLabel("Pre Condição:");
		preCondicaoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		preCondicaoLabel.setMinimumSize(new Dimension(105, 20));
		preCondicaoLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_6.add(preCondicaoLabel);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_4);
		
		preCondicaoTextField = new JTextField();
		if(casoDeUso.getPreCondicao() != null)
			preCondicaoTextField.setText(casoDeUso.getPreCondicao());
			
		horizontalBox_6.add(preCondicaoTextField);
		preCondicaoTextField.setColumns(10);

		// LABEL - Pos Condicao
		Box horizontalBox_7 = Box.createHorizontalBox();
		informacaoPane.add(horizontalBox_7);

		JLabel posCondicaoLabel = new JLabel("Pos Condição:");
		posCondicaoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		posCondicaoLabel.setMinimumSize(new Dimension(105, 20));
		posCondicaoLabel.setPreferredSize(new Dimension(105, 16));
		horizontalBox_7.add(posCondicaoLabel);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_7.add(horizontalStrut_5);

		posCondicaoTextField = new JTextField();
		if(casoDeUso.getPosCondicao() != null)
			posCondicaoTextField.setText(casoDeUso.getPosCondicao());
			
		horizontalBox_7.add(posCondicaoTextField);
		posCondicaoTextField.setColumns(10);

		if(edit == false) {
			casoDeUsoTextField.setEditable(false);
			ObjetivoTextField.setEditable(false);
			escopoTextField.setEditable(false);
			nivelTextField.setEditable(false);
			preCondicaoTextField.setEditable(false);
			posCondicaoTextField.setEditable(false);
			atoPrimarioComboBox.setEnabled(false);
			atorSecundarioComboBox.setEnabled(false);
		}
		
		return informacaoPane;
	}

	private void inicializaCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		panel.add(new Extensao_View(casoDeUso.getIdCasoDeUso(), TipoExtensaoPanel.view), BorderLayout.NORTH);
		panel.add(new Fluxo_Principal_View(casoDeUso.getIdCasoDeUso(), edit), BorderLayout.CENTER);
		panel.add(new Fluxo_Extensao_View(casoDeUso.getIdCasoDeUso(), edit), BorderLayout.SOUTH);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JPanel center = new JPanel();
		center.setLayout(flowLayout);
		center.setBackground(Color.white);

		center.setBorder(null);
		center.add(panel);
		panelPane = new JScrollPane(center);
	}

	private void inicializaSouth() {
		south = new JPanel();
		south.setLayout(new GridLayout());
		south.setBackground(Color.white);

		JButton btnCancelarCDU = new JButton("Sair do Caso de Uso");
		btnCancelarCDU.setName("CancelarCasoDeUso");
		btnCancelarCDU.addMouseListener(this);
		south.add(btnCancelarCDU);

		JButton btnAtualizarCDU = new JButton("Atualizar Caso de Uso");
		if(idCasoDeUso <= 0)
			btnAtualizarCDU.setText("Criar Caso de Uso");
		btnAtualizarCDU.setName("AtualizarCasoDeUso");
		btnAtualizarCDU.addMouseListener(this);
		south.add(btnAtualizarCDU);
	}

	private void setComboBox() {
		list = ECUGUI.getListAtores();
		
		listagemAtoresPrincipal = ECUGUI.getListagemAtores(list);

		// inicializa a listagem secundaria
		if (listagemAtoresSecundario != null)
			listagemAtoresSecundario.removeAll(listagemAtoresSecundario);

		if (listagemAtoresPrincipal != null)
			listagemAtoresSecundario = new ArrayList<String>(
					Arrays.asList(listagemAtoresPrincipal.clone()));

		// remove o ator selecionado em principal
		if (atoPrimarioComboBox != null && atoPrimarioComboBox.getSelectedItem() != null) {
			listagemAtoresSecundario.remove(atoPrimarioComboBox.getSelectedItem());
			atorSecundarioComboBox.setEnabled(true);
		}
	}

	private void setDado() {
				
		if (casoDeUsoTextField.getText() != null)
			casoDeUso.setNomeCasoDeUso(casoDeUsoTextField.getText());

		if (ObjetivoTextField.getText() != null)
			casoDeUso.setObjetivo(ObjetivoTextField.getText());

		if (escopoTextField.getText() != null)
			casoDeUso.setEscopo(escopoTextField.getText());

		if (nivelTextField.getText() != null)
			casoDeUso.setNivel(nivelTextField.getText());

		if (preCondicaoTextField.getText() != null) 
			casoDeUso.setPreCondicao(preCondicaoTextField.getText());
		
		if (posCondicaoTextField.getText() != null)
			casoDeUso.setPosCondicao(posCondicaoTextField.getText());
	}
	
	/* Metodos Public */

	public void update(Observable o, Object arg) {
		setDado();
		removeAll();
		inicializaPanel();
		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();

		if (ac.equals("atoPrimarioComboBox") && atoPrimarioComboBox.getSelectedItem() != null){
			int num =  atoPrimarioComboBox.getSelectedIndex();
			casoDeUso.setAtorPrimario( list.get(num));
			
			if(casoDeUso.getIdAtorPrimario() == casoDeUso.getIdAtorSuporte()) {
				Ator nv = new Ator();
				casoDeUso.setAtorSuporte(nv);
				atorSecundarioComboBox.setSelectedItem(null);
			}
			
			atorSecundarioComboBox.setEnabled(true);
			revalidate();
			repaint();
		}
		else if (ac.equals("atorSecundarioComboBox") && atorSecundarioComboBox.getSelectedItem() != null){
			Ator tmp =  ECUGUI.getAtor(ECUGUI.getIdProjeto(), atorSecundarioComboBox.getSelectedItem().toString());
			casoDeUso.setAtorSuporte(tmp);
			
			if(casoDeUso.getIdAtorPrimario() == casoDeUso.getIdAtorSuporte()) {
				Ator nv = new Ator();
				casoDeUso.setAtorSuporte(nv);
				atoPrimarioComboBox.setSelectedItem(null);
			}
			
			revalidate();
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName().equals("CancelarCasoDeUso")) {
			ECUGUI.IrProjeto();
		}
		if (e.getComponent().getName().equals("AtualizarCasoDeUso")) {
			setDado();
			if(idCasoDeUso <= 0)
				ECUGUI.CriarCasoDeUso(casoDeUso);
			else
				ECUGUI.EditarCasoDeUso(casoDeUso);
				
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
