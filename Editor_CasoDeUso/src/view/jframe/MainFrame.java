package view.jframe;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.jmenu.Menu;
import view.jpanel.context.CasoDeUso_Editar;
import view.jpanel.context.CasoDeUso_View;
import view.jpanel.context.Home;
import view.jpanel.context.Projeto_Carregar;
import view.jpanel.context.Projeto_Criar;
import view.jpanel.context.Projeto_View;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/* Vareaveis da Class */
	
	private int idProjeto, idCasoDeUso;
	
	private int MIN_LARG_DEFAULT;
	private int MIN_ALT_DEFAULT;
	private int LARG_DEFAULT;
	private int ALT_DEFAULT;

	private Menu mainMenu;

	private Home mainPanel;
	private	Projeto_Criar criarProjetoPanel;
	private Projeto_Carregar carregarProjetoPanel;
	private Projeto_View visualizarProjetoPanel;
	private CasoDeUso_View visualizarCasoDeUso;
	private CasoDeUso_Editar casoDeUso;
	
	/* Construtor Default */

	public MainFrame() {
		this.idProjeto = -1;
		this.idCasoDeUso = -1;
		this.LARG_DEFAULT = 800;
		this.ALT_DEFAULT = 500;
		this.MIN_LARG_DEFAULT = 800;
		this.MIN_ALT_DEFAULT = 500;
		
		inicializaFrame();
		inicializaBarraMenu();
		this.pack();
	}

	/* Enum da Class */

	public enum TipoPanel {
		inicial, criarProjeto, carregarProjeto, visualizarProjeto,
		criarCasoDeUso, visualizarCasoDeUso, editarCasoDeUso
	}
	
	/* Metodos Private */

	private void inicializaFrame() {
		mainPanel = new Home();
		mainPanel.setName("main");
		getContentPane().add(mainPanel);

		setTitle("Editor de Caso de Uso");
		setBackground(Color.white);
		setSize(new Dimension(LARG_DEFAULT, ALT_DEFAULT));
		setMinimumSize(new Dimension(MIN_LARG_DEFAULT, MIN_ALT_DEFAULT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void inicializaBarraMenu() {
		mainMenu = new Menu();
		add(this.mainMenu);
		setJMenuBar(this.mainMenu);
	}
	
	/* Metodos Public */

	public void setID(int idProjeto, int idCasoDeUso) {	
		this.idProjeto = idProjeto; 
		this.idCasoDeUso = idCasoDeUso; 
	}

	public void setPanel(TipoPanel tipoPanel) {
		this.getContentPane().removeAll();
		switch (tipoPanel) {
		case inicial:
			this.mainMenu.setVisualizarBotaoProjeto(false);
			this.mainPanel = new Home();
			this.mainPanel.setName("inicial");
			this.add(mainPanel);
			
			break;
		case criarProjeto:
			this.mainMenu.setVisualizarBotaoProjeto(false);
			this.criarProjetoPanel = new Projeto_Criar();
			this.criarProjetoPanel.setName("criarProjeto");
			this.add(criarProjetoPanel);
			
			break;
		case carregarProjeto:
			this.mainMenu.setVisualizarBotaoProjeto(false);
			this.carregarProjetoPanel = new Projeto_Carregar();
			this.carregarProjetoPanel.setName("carregarProjeto");
			this.add(carregarProjetoPanel);
			break;
		case visualizarProjeto:
			this.mainMenu.setID(idProjeto, idCasoDeUso);
			this.mainMenu.setVisualizarBotaoProjeto(true);
			this.visualizarProjetoPanel = new Projeto_View(idProjeto);
			this.visualizarProjetoPanel.setName("visualizarProjeto");
			this.add(visualizarProjetoPanel);
			break;
		case criarCasoDeUso:
			this.mainMenu.setVisualizarBotaoCasoDeUso(false);
			this.casoDeUso = new CasoDeUso_Editar(0,true);
			this.casoDeUso.setName("criarCasoDeUso");
			this.add(casoDeUso);
			break;
		case editarCasoDeUso:
			this.mainMenu.setID(idProjeto,idCasoDeUso);
			this.mainMenu.setVisualizarBotaoCasoDeUso(true);
			this.casoDeUso = new CasoDeUso_Editar(idCasoDeUso,true);
			this.casoDeUso.setName("editarCasoDeUso");
			this.add(casoDeUso);
			break;
		case visualizarCasoDeUso:
			this.mainMenu.setID(idProjeto,idCasoDeUso);
			this.mainMenu.setVisualizarBotaoCasoDeUso(true);
			this.visualizarCasoDeUso = new CasoDeUso_View(idCasoDeUso);
			this.visualizarCasoDeUso.setName("visualizarCasoDeUso");
			this.add(visualizarCasoDeUso);
			break;
		}
		this.revalidate();
		this.repaint();
	}
}