package view.jmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.ECUGUI;

@SuppressWarnings("serial")
public class Menu extends JMenuBar implements ActionListener, MouseListener, Observer{
	
	/* Vareaveis da Class */

	private boolean botoesCasoDeUsoVisivel;
	private int idProjeto, idCasoDeUso;
	private JMenu arquivo;
	private JMenuItem arquivoNovoProjeto;
	private JMenuItem arquivoSairProjeto;
	private JMenuItem arquivoCarreguarProjeto;
	private JMenu projeto;
	private JMenuItem casoDeUsoVisualizarProjeto;
	private JMenuItem casoDeUsoCriar;
	private JMenu ator;
	private JMenuItem atorCriar;
	private JMenuItem atorExcluir;
	private JMenuItem atorAlterar;
	private JMenu casoDeUso;
	private JMenuItem casoDeUsoAdicionaFlxPrincipal;
	private JMenuItem casoDeUsoEditarFlx;
	private JMenuItem casoDeUsoExcluirFlx;
	private JMenuItem casoDeUsoAdicionaFlxExtensao;
	private JMenu Extensao;
	private JMenuItem ExtensaoAdiciona;
	private JMenuItem ExtensaoEditar;
	private JMenuItem extencaExcluir;
	
	/* Construtor Default */
	
	public Menu() {
		this.idProjeto = -1;
		this.idCasoDeUso = -1;
		this.botoesCasoDeUsoVisivel = false;
		ECUGUI.addPanelObserver(this);
		criaraInterface();
	}
		
	/* Metodos Private */
	
	private void criaraInterface() {
		// Cria o botao Projeto
		arquivo = new JMenu("Arquivo");
		arquivo.setName("menuArquivo");
		arquivo.addMouseListener(this);
		arquivo.setVisible(true);
		add(arquivo);

		// Cria os botoes internos do Projeto
		arquivoNovoProjeto = new JMenuItem("Criar Novo Projeto");
		arquivoNovoProjeto.setActionCommand("menuItemCriarProjeto");
		arquivoNovoProjeto.addActionListener(this);
		arquivoNovoProjeto.setVisible(true);
		arquivo.add(arquivoNovoProjeto);

		arquivoCarreguarProjeto = new JMenuItem("Visualizar Projetos");
		arquivoCarreguarProjeto.setActionCommand("menuItemCarreguarProjeto");
		arquivoCarreguarProjeto.addActionListener(this);
		arquivoCarreguarProjeto.setVisible(true);
		arquivo.add(arquivoCarreguarProjeto);
		
		arquivoSairProjeto = new JMenuItem("Encerrar o Editor UC");
		arquivoSairProjeto.setActionCommand("menuItemSairAplicacao");
		arquivoSairProjeto.addActionListener(this);
		arquivoSairProjeto.setVisible(true);
		arquivo.add(arquivoSairProjeto);
		
		// Cria o botoes Caso de Uso
		projeto = new JMenu("Projeto");
		projeto.setName("menuCasoDeUso");
		projeto.addMouseListener(this);
		projeto.setVisible(false);
		add(projeto);
		
		casoDeUsoVisualizarProjeto = new JMenuItem("Informacoes do Projeto");
		casoDeUsoVisualizarProjeto.setActionCommand("menuItemVisualizarProjeto");
		casoDeUsoVisualizarProjeto.addActionListener(this);
		casoDeUsoVisualizarProjeto.setVisible(true);
		projeto.add(casoDeUsoVisualizarProjeto);
		
		casoDeUsoCriar = new JMenuItem("Criar Novo Caso de Uso");
		casoDeUsoCriar.setActionCommand("menuItemCriarCasoDeUso");
		casoDeUsoCriar.addActionListener(this);
		casoDeUsoCriar.setVisible(true);
		projeto.add(casoDeUsoCriar);
		
		// Cria o botoes Ator
		ator = new JMenu("Ator");
		ator.setName("menuAtor");
		ator.addMouseListener(this);
		ator.setVisible(false);
		add(ator);
		
		atorCriar = new JMenuItem("Criar Ator");
		atorCriar.setActionCommand("menuItemCriarAtor");
		atorCriar.addActionListener(this);
		atorCriar.setVisible(true);
		ator.add(atorCriar);

		atorAlterar = new JMenuItem("Alterar Ator");
		atorAlterar.setActionCommand("menuItemAlterarAtor");
		atorAlterar.addActionListener(this);
		atorAlterar.setVisible(true);
		ator.add(atorAlterar);
		
		atorExcluir = new JMenuItem("Excluir Ator");
		atorExcluir.setActionCommand("menuItemExcluirAtor");
		atorExcluir.addActionListener(this);
		atorExcluir.setVisible(true);
		ator.add(atorExcluir);
		
		// Cria o botoes Caso de Uso
		casoDeUso = new JMenu("Fluxo");
		casoDeUso.setName("menuCasoDeUso");
		casoDeUso.addMouseListener(this);
		casoDeUso.setVisible(false);
		add(casoDeUso);

		casoDeUsoAdicionaFlxPrincipal = new JMenuItem("Adiciona ao Fluxo Principal");
		casoDeUsoAdicionaFlxPrincipal.setActionCommand("menuItemFluxoAdicionaPrincipal");
		casoDeUsoAdicionaFlxPrincipal.addActionListener(this);
		casoDeUsoAdicionaFlxPrincipal.setEnabled(true);
		casoDeUso.add(casoDeUsoAdicionaFlxPrincipal);

		casoDeUsoAdicionaFlxExtensao = new JMenuItem("Adiciona ao Fluxo Extensao");
		casoDeUsoAdicionaFlxExtensao.setActionCommand("menuItemFluxoAdicionaExtensao");
		casoDeUsoAdicionaFlxExtensao.addActionListener(this);
		casoDeUsoAdicionaFlxExtensao.setEnabled(false);
		casoDeUso.add(casoDeUsoAdicionaFlxExtensao);
		
		casoDeUso.addSeparator();
		
		casoDeUsoEditarFlx = new JMenuItem("Editar Fluxo");
		casoDeUsoEditarFlx.setActionCommand("menuItemFluxoEditar");
		casoDeUsoEditarFlx.addActionListener(this);
		casoDeUsoEditarFlx.setEnabled(false);
		casoDeUso.add(casoDeUsoEditarFlx);
		
		casoDeUso.addSeparator();
		
		casoDeUsoExcluirFlx = new JMenuItem("Excluir Fluxo");
		casoDeUsoExcluirFlx.setActionCommand("menuItemFluxoExcluir");
		casoDeUsoExcluirFlx.addActionListener(this);
		casoDeUsoExcluirFlx.setEnabled(false);
		casoDeUso.add(casoDeUsoExcluirFlx);
				
		// Cria o botoes Extensao
		Extensao = new JMenu("Extensao");
		Extensao.setName("menuExtensao");
		Extensao.addMouseListener(this);
		Extensao.setVisible(false);
		add(Extensao);
		
		ExtensaoAdiciona = new JMenuItem("Adiciona	uma Extensao");
		ExtensaoAdiciona.setActionCommand("menuItemExtensaoAdiciona");
		ExtensaoAdiciona.addActionListener(this);
		ExtensaoAdiciona.setEnabled(false);
		Extensao.add(ExtensaoAdiciona);

		ExtensaoEditar = new JMenuItem("Editar	uma Extensao");
		ExtensaoEditar.setActionCommand("menuItemExtensaoEditar");
		ExtensaoEditar.addActionListener(this);
		ExtensaoEditar.setEnabled(false);
		Extensao.add(ExtensaoEditar);
		
		extencaExcluir = new JMenuItem("Excluir	uma Extensao");
		extencaExcluir.setActionCommand("menuItemExtensaoExcluir");
		extencaExcluir.addActionListener(this);
		extencaExcluir.setEnabled(false);
		Extensao.add(extencaExcluir);
	}
	
	/* Metodos Public */

	public void setID(int idProjeto, int idCasoDeUso) {
		this.idProjeto = idProjeto;
		this.idCasoDeUso = idCasoDeUso;
	}
	
	public void setVisualizarBotaoProjeto(boolean opcao) {
		if(opcao == false) {
			this.projeto.setVisible(false);
			this.ator.setVisible(false);
		} else {
			this.projeto.setVisible(true);
			this.ator.setVisible(true);
		}
		this.casoDeUso.setVisible(false);
		this.Extensao.setVisible(false);
		this.revalidate();
		this.repaint();
	}
	
	public void setVisualizarBotaoCasoDeUso(boolean opcao) {
		if(opcao == false) {
			setVisualizacaoFluxo();
			setVisualizacaoExtensao();
			this.casoDeUso.setVisible(false);
			this.Extensao.setVisible(false);
		} else {
			setVisualizacaoFluxo();
			setVisualizacaoExtensao();
			this.casoDeUso.setVisible(true);
			this.Extensao.setVisible(true);
		}
		this.revalidate();
		this.repaint();
	}
	
	private void setVisualizacaoExtensao() {
	
		int num = 0;
		if(idProjeto > 0)
			num = ECUGUI.getQtdCasoDeUso(idProjeto);
	
		if(num > 0) {
			ExtensaoAdiciona.setEnabled(true);
			ExtensaoEditar.setEnabled(true);
			extencaExcluir.setEnabled(true);
		} else {
			ExtensaoAdiciona.setEnabled(false);
			ExtensaoEditar.setEnabled(false);
			extencaExcluir.setEnabled(false);
		}
	}
	
	private void setVisualizacaoFluxo() {
		
		int num = 0;
		if(idCasoDeUso > 0)
			num = ECUGUI.getQtdFluxoPrincipal(idCasoDeUso);
		
		if(num > 0) {
			casoDeUsoEditarFlx.setEnabled(true);
			casoDeUsoExcluirFlx.setEnabled(true);
			casoDeUsoAdicionaFlxExtensao.setEnabled(true);
		} else {
			casoDeUsoEditarFlx.setEnabled(false);
			casoDeUsoExcluirFlx.setEnabled(false);
			casoDeUsoAdicionaFlxExtensao.setEnabled(false);
		}
	}
	
	public boolean getStatusVisualizarBotoesDeJogo() {
		return this.botoesCasoDeUsoVisivel;
	}

	public void update(Observable o, Object arg) {
		setVisualizacaoExtensao();
		setVisualizacaoFluxo();
		revalidate();
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("menuItemCriarProjeto".equals(e.getActionCommand())) {
			ECUGUI.IrCriarProjeto();
		}
		if ("menuItemCarreguarProjeto".equals(e.getActionCommand())) {
			ECUGUI.IrCarregarProjeto();
		}
		if ("menuItemSairAplicacao".equals(e.getActionCommand())) {
			ECUGUI.EncerrarEditorCasoUso();
		}
		if ("menuItemVisualizarProjeto".equals(e.getActionCommand())) {
			ECUGUI.IrProjeto();
		}
		if ("menuItemCriarCasoDeUso".equals(e.getActionCommand())) {
			ECUGUI.IrCriarCasoDeUso();
		}
		if ("menuItemCriarAtor".equals(e.getActionCommand())) {
			ECUGUI.IrCriarAtor();
		}
		if ("menuItemAlterarAtor".equals(e.getActionCommand())) {
			ECUGUI.IrAlterarAtor();
		}
		if ("menuItemExcluirAtor".equals(e.getActionCommand())) {
			ECUGUI.IrExcluirAtor();
		}
		if ("menuItemFluxoAdicionaPrincipal".equals(e.getActionCommand())) {
			ECUGUI.IrAdicionaFluxoPrincipal();
		}
		if ("menuItemFluxoAdicionaExtensao".equals(e.getActionCommand())) {
			ECUGUI.IrAdicionaFluxoExtensao();
		}
		if ("menuItemFluxoEditar".equals(e.getActionCommand())) {
			ECUGUI.IrEditarFluxo();
		}
		if ("menuItemFluxoExcluir".equals(e.getActionCommand())) {
			ECUGUI.IrExcluirFluxo();
		}
		if ("menuItemExtensaoAdiciona".equals(e.getActionCommand())) {
			ECUGUI.IrCriarExtensao();
		}
		if ("menuItemExtensaoEditar".equals(e.getActionCommand())) {
			ECUGUI.IrEditarExtensao();
		}
		if ("menuItemExtensaoExcluir".equals(e.getActionCommand())) {
			ECUGUI.IrExcluirExtensao();
		}
	}
	
	public void mouseClicked(MouseEvent e)  { }
	public void mousePressed(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e)  { }
	public void mouseExited(MouseEvent e)   { }
}
