package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JOptionPane;

import logic.ECU;
import model.Ator;
import model.CasoDeUso;
import model.Extensao;
import model.Fluxo;
import model.Projeto;
import model.View_InfoProjeto;
import model.View_ListCasoDeUso;
import model.View_ListProjeto;
import view.jframe.AtorFrame;
import view.jframe.AtorFrame.TipoAtorPanel;
import view.jframe.ExtensaoFrame;
import view.jframe.ExtensaoFrame.TipoExtensaoFrame;
import view.jframe.FluxoFrame;
import view.jframe.FluxoFrame.TipoFluxoPanel;
import view.jframe.MainFrame;
import view.jframe.MainFrame.TipoPanel;
import view.tableModel.CasoDeUsoTM;
import view.tableModel.ProjetosTM;

public class ECUGUI extends Observable {
	
	/* Vareaveis da Class */

	private static ECUGUI controle = null;
	private static MainFrame mainFrame;

	// Informacoes do Projeto Ativo
	private static int IDProjeto;

	// Informacoes do Caso de Uso Ativo
	private static int IDCasoDeUso;

	/* Metodos Public */

	// /////////////////////////////////////////////////////////////////////////////
	// PROJETO

	// gerenciamento interface dos projetos

	public static void IrInicialView() {
		IDProjeto = -1;
		IDCasoDeUso = -1;
		mainFrame.setPanel(TipoPanel.inicial);
	}

	public static void IrProjeto() {
		IrProjeto(IDProjeto);
	}

	public static void IrProjeto(int idProjeto) {
		IDProjeto = idProjeto; // Load id do projeto
		IDCasoDeUso = -1;
		mainFrame.setID(IDProjeto, IDCasoDeUso);
		mainFrame.setPanel(TipoPanel.visualizarProjeto);
	}

	public static void IrCriarProjeto() {
		mainFrame.setPanel(TipoPanel.criarProjeto);
	}

	public static void IrCarregarProjeto() {
		mainFrame.setPanel(TipoPanel.carregarProjeto);
	}

	// gerenciamento operacoes dos projetos - SET

	public static void CriarProjeto(String nomeProjeto) {

		if (nomeProjeto == null) {
			JOptionPane.showMessageDialog(null, "Nome do Projeto Invalido");
			return;
		}

		// try {
			IDProjeto = ECU.setCriarProjeto(nomeProjeto);
			mainFrame.setID(IDProjeto, IDCasoDeUso);
			mainFrame.setPanel(TipoPanel.visualizarProjeto);
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	public static void ExcluirProjeto(int idProjeto) {

		if (idProjeto < 0) {
			JOptionPane.showMessageDialog(null, "Nome do Projeto Invalido");
			return;
		}

		// try {
			ECU.setExcluirProjeto(idProjeto);
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	// funcoes de requisicao de dados sobre o projeto - GET

	public static int getIdProjeto(){
		return IDProjeto;
	}
	
	public static ProjetosTM getlistagemProjetos() {
		Vector<Projeto> listagem = ECU.getlistProjetos();

		if (listagem == null || listagem.size() < 0)
			return new ProjetosTM();

		ArrayList<View_ListProjeto> resultado = new ArrayList<View_ListProjeto>();

		for (int i = 0; i < listagem.size(); i++) {
			View_ListProjeto data = new View_ListProjeto();
			data.setIdProjeto(listagem.get(i).getIdProjeto());
			data.setNomeProjeto(listagem.get(i).getNomeProjeto());
			data.setDataCriacao(listagem.get(i).getDataCriacao());
			data.setDataModificacao(listagem.get(i).getDataModificacao());
			resultado.add(data);
		}

		return new ProjetosTM(resultado);
	}

	public static CasoDeUsoTM getListagemCasoDeUsoDoProjeto(
			int idProjeto) {

		if (idProjeto < 0) {
			JOptionPane.showMessageDialog(null, "ID do Projeto Invalido");
			return null;
		}

		ArrayList<View_ListCasoDeUso> listagem = ECU
				.getListCasoDeUsoDoProjeto(idProjeto);
		if (listagem == null || listagem.size() < 0)
			return new CasoDeUsoTM();

		return new CasoDeUsoTM(listagem);
	}

	public static View_InfoProjeto getInformacoesProjeto(int idProjeto) {

		if (idProjeto < 0)
			JOptionPane.showMessageDialog(null, "ID do Projeto Invalido");

		return ECU.getInformacoesProjeto(idProjeto);
	}

	// /////////////////////////////////////////////////////////////////////////////
	// ATOR

	// gerenciamento interface do ator

	public static void IrCriarAtor() {
		AtorFrame.getInstance(TipoAtorPanel.criarAtor);
	}

	public static void IrAlterarAtor() {
		AtorFrame.getInstance(TipoAtorPanel.alterarAtor);
	}

	public static void IrExcluirAtor() {
		AtorFrame.getInstance(TipoAtorPanel.excluirAtor);
	}

	// gerenciamento operacoes dos ator - SET

	public static void CriarAtor(Ator ator) {

		if (ator == null || ator.getNomeAtor() == null || ator.getNomeAtor().equals("")) {
				JOptionPane.showMessageDialog(null, "Dados Invalido (CriarAtor)");
				return;
			}

		// try {
			ator.setIdProjeto(IDProjeto);
			ECU.setCriarAtor(ator);
			update();
			AtorFrame.encerrarFrame();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	public static void EditarAtor(Ator ator) {

		if (ator == null || ator.getIdProjeto() <= 0 ||
			ator.getIdAtor() <= 0 || ator.getNomeAtor() == null || 
			ator.getNomeAtor().equals("")) {
			JOptionPane.showMessageDialog(null, "Dados Invalido (EditarAtor)");
			return;
		}

		// try {
			ECU.setEditarAtor(ator);
			AtorFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	public static void ExcluirAtor(Ator ator) {

		if (ator == null || ator.getIdProjeto() <= 0 ||
				ator.getIdAtor() <= 0 || ator.getNomeAtor() == null || 
				ator.getNomeAtor().equals("")) {
				JOptionPane.showMessageDialog(null, "Dados Invalido (EditarAtor)");
				return;
			}

		// try {
			ECU.setExcluirAtor(ator);
			AtorFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	// funcoes de requisicao de dados sobre o ator - GET

	public static Ator getAtor(int idProjeto, String nome) {
		
		if(idProjeto <= 0 || nome == null)
			return null;
		
		return ECU.getAtor(idProjeto, nome);
	}

	public static String[] getListagemAtores(Vector<Ator> list) {
		
		if(list == null || list.size() == 0) {
			return  new String[0];
		}
		
		String[] retorno = new String[list.size()];
		for (int i=0 ; i < list.size() ; i++) {
			retorno[i] = new String();
			retorno[i] = list.get(i).getNomeAtor();
		}
		
		return retorno;
	}

	public static Vector<Ator> getListAtores() {
		
		return ECU.getListAtor(IDProjeto);
	}
	
	// /////////////////////////////////////////////////////////////////////////////
	// CASOSDEUSOS

	// gerenciamento interface do casos de usos

	public static void IrCriarCasoDeUso() {
		mainFrame.setPanel(TipoPanel.criarCasoDeUso);
	}

	public static void IrCasoDeUso(int idCasoDeUso) {
		IDCasoDeUso = idCasoDeUso;
		mainFrame.setID(IDProjeto, IDCasoDeUso);
		mainFrame.setPanel(TipoPanel.visualizarCasoDeUso);
	}

	public static void IrEditarCasoDeUso(int idCasoDeUso) {
		IDCasoDeUso = idCasoDeUso;
		mainFrame.setID(IDProjeto, IDCasoDeUso);
		mainFrame.setPanel(TipoPanel.editarCasoDeUso);
	}

	// gerenciamento operacoes dos casos de usos - SET

	public static void CriarCasoDeUso(CasoDeUso casoDeUso) {

		if (casoDeUso == null || casoDeUso.getIdCasoDeUso() < 0 || casoDeUso.getNomeCasoDeUso() == null || casoDeUso.getObjetivo() == null
				|| casoDeUso.getEscopo() == null || casoDeUso.getNivel() == null || casoDeUso.getIdAtorPrimario() == 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( InserirCasoDeUso )");
			return;
		}

		// try {
			IDCasoDeUso = ECU.setCriarCasoDeUso(casoDeUso);
	
			mainFrame.setID(IDProjeto, IDCasoDeUso);
			mainFrame.setPanel(TipoPanel.visualizarCasoDeUso);
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	public static void ExcluirCasoDeUso(int idCasoDeUso) {// TODO TEST

		if (idCasoDeUso < 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido (ExcluirCasoDeUso)");
			return;
		}
		
		// try {
			ECU.setExcluirCasoDeUso(IDProjeto, idCasoDeUso);
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	public static void EditarCasoDeUso(CasoDeUso casoDeUso) {// TODO TEST

		if (casoDeUso == null || casoDeUso.getIdCasoDeUso() <= 0 || casoDeUso.getNomeCasoDeUso() == null || casoDeUso.getObjetivo() == null
				|| casoDeUso.getEscopo() == null || casoDeUso.getNivel() == null || casoDeUso.getIdAtorPrimario() == 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( EditarCasoDeUso )");
			return;
		}

		// try {
			ECU.setEditarCasoDeUso(casoDeUso);
			update();
			mainFrame.setPanel(TipoPanel.visualizarCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }

	}

	// funcoes de requisicao de dados sobre o caso de uso - GET

	public static CasoDeUso getCasoDeUso(int idCasoDeUso) {

		if (idCasoDeUso < 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getCasoDeUso )");
			return null;
		}

		// try {
			CasoDeUso retorno = ECU.getCasoDeUso(IDProjeto, idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }	
			
		return retorno;
	}

	public static int getQtdCasoDeUso(int idProjeto) {
		
		if (idProjeto < 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getQtdCasoDeUso )");
			return 0;
		}
		
		// try {
			int retorno = ECU.getQtdCasoDeUso(idProjeto);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return retorno;
	}

	public static Vector<CasoDeUso> getListagemCasosDeUsoIrmaos(int idCasoDeUso){//yang
		if (idCasoDeUso < 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getListagemCasosDeUsoIrmaos )  ");
			return null;
		}
		Vector<CasoDeUso> retorno = ECU.getListCasosDeUsoIrmaos(idCasoDeUso);
		return retorno;
	}
	
	public static String[] getListagemNomesCasosDeUsoIrmaos (Vector<CasoDeUso> listaCDU){
		String[] retorno;
		
		if(listaCDU == null ) {
			return new String[0];
		} else {
			retorno = new String[listaCDU.size()];
		}
		
		for (int i = 0; i < listaCDU.size(); i++) {
			retorno[i] = new String();
			String tmp;
			if(listaCDU.get(i).getNomeCasoDeUso() == null)
				tmp = "";
			else
				tmp = listaCDU.get(i).getNomeCasoDeUso();
			
			retorno[i] = tmp;
		}
		
		return retorno;		
	}
	
	////////////////////////////////////////////////////////////////////////////
	// FLUXO

	// gerenciamento interface do casos de usos

	public static void IrExcluirFluxo() {
		FluxoFrame.getInstance(TipoFluxoPanel.Excluir,
				IDCasoDeUso);
	}

	public static void IrEditarFluxo() {
		FluxoFrame
				.getInstance(TipoFluxoPanel.Editar, IDCasoDeUso);
	}

	public static void IrAdicionaFluxoPrincipal() {
		FluxoFrame.getInstance(TipoFluxoPanel.AdicionaFluxoPrincipal,
				IDCasoDeUso);
	}

	public static void IrAdicionaFluxoExtensao() {
		FluxoFrame.getInstance(TipoFluxoPanel.AdicionaFluxoExtensao,
				IDCasoDeUso);
	}

	public static void encerrarFluxoFrame() {
		FluxoFrame.encerrarFrame();
	}
	
	// gerenciamento operacoes dos fluxos - SET

	public static void AdicionarFluxo(Fluxo fluxo) {// TODO - TEST - CARLOS
		if (fluxo == null || fluxo.getIdFluxoMaster() < 0 || 
			fluxo.getPosicaoFluxo() <= 0 || fluxo.getInformacaoFluxo() == null ) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( AdicionarFluxo ) ");
			return;
		}
		// try {	
			ECU.setAdicionaFluxo(fluxo);
			System.out.println(mainFrame.getContentPane());
			update();
			System.out.println(mainFrame.getContentPane()+" dsadas");
			FluxoFrame.encerrarFrame();

		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	public static void EditarFluxo(Fluxo fluxo) {// TODO - TEST - CARLOS
		
		if (fluxo == null || fluxo.getIdCasoDeUso() <= 0 || 
			fluxo.getIdFluxoMaster() < 0 || fluxo.getPosicaoFluxo() <= 0 || 
			fluxo.getInformacaoFluxo() == null ) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( EditarFluxo ) ");
			return;
		}
		
		// try {
			ECU.setEditarFluxo(fluxo);
			FluxoFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}
	
	public static void ExcluirFluxo(Fluxo fluxo) {// TODO - TEST - CARLOS

		if (fluxo == null || fluxo.getIdCasoDeUso() <= 0 || 
			fluxo.getIdFluxoMaster() < 0 || fluxo.getPosicaoFluxo() <= 0 || 
			fluxo.getInformacaoFluxo() == null ) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( ExcluirFluxo ) ");
			return;
		}
		
		// try {
			ECU.setExcluirFluxo(fluxo);
			FluxoFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	}

	// funcoes de requisicao de dados sobre os fluxo - GET

	public static int getQtdFluxoPrincipal(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getQtdFluxoPrincipal ) ");
			return 0;
		}
		
		// try {
			int retorno = ECU.getSizeFluxoPrincipal(idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return retorno;
	}
	
	public static int getQtdFluxoExtensao(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getQtdFluxoExtensao ) ");
			return 0;
		}
		
		// try {
			int retorno = ECU.getSizeFluxoExtensao(idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return retorno;
	}
	
	public static int[] getPaddingFluxo(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getPaddingFluxo ) ");
			return null;
		}
		
		int sizePaddALT = 20;		
		int sizePaddSEQ = 15;
		Fluxo anterior = null;
		Map<Integer,Integer>paiPad = new HashMap<Integer, Integer>();
		int paddingAnterior = 0;
		
		Vector<Fluxo> listFP = getListFluxoPrincipal(idCasoDeUso);
		Vector<Fluxo> listFE = getListFluxoExtensao(idCasoDeUso);
		if(listFP == null || listFE == null)
			return null;
		
		for (Fluxo fluxo : listFP) {
			paiPad.put(new Integer(fluxo.getIdFluxo()), new Integer(sizePaddALT));
		}
		
		int[] vecPadding = new int[listFE.size()];
		
		for (int index=0 ; index< listFE.size() ; index++) {
			
			Fluxo flx = listFE.get(index);
			
			if(anterior == null) {
				paddingAnterior = paddingAnterior + sizePaddALT;
				vecPadding[index] = paddingAnterior;
				anterior = flx;
				paiPad.put(new Integer(flx.getIdFluxoMaster()), new Integer(paddingAnterior));
			} else if(anterior.getIdFluxoMaster() == flx.getIdFluxoMaster()) {
				vecPadding[index] = paddingAnterior;
				anterior = flx;
			} else {
				boolean have = paiPad.containsKey(flx.getIdFluxoMaster());
				if(have == true) {
					int paddingPai = paiPad.get(flx.getIdFluxoMaster());
					paddingAnterior = paddingPai;
					vecPadding[index] = paddingAnterior;
					anterior = flx;
					paiPad.put(new Integer(flx.getIdFluxoMaster()), new Integer(paddingAnterior));
				} else {
					if(flx.getTipoAternativo() == true)
						paddingAnterior = paddingAnterior + sizePaddALT;
					else
						paddingAnterior = paddingAnterior + sizePaddSEQ;
					vecPadding[index] = paddingAnterior;
					anterior = flx;
					paiPad.put(new Integer(flx.getIdFluxoMaster()), new Integer(paddingAnterior));
				}
			}
		}
		return vecPadding;
	}
	
	public static String getPosicaoFluxo(Fluxo fluxo) {
		
		if (fluxo == null || fluxo.getIdFluxoMaster() < 0 || fluxo.getPosicaoFluxo() <= 0 ) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getPosicaoFluxo )  "
					+" fluxo: "+fluxo
					);
			return null;
		}
		
		Fluxo voce = fluxo;
		String result = null;
						
		if(voce.getIdFluxoMaster() == 0) {												// Nao tem pai (FP)
			result = voce.getPosicaoFluxo()+".";										// RESULT = "1."
		} else {																		// Tem pai (FE)
			if(voce.getTipoAternativo() == true) {										// ALTERNATIVO 	"1a."	(1 = posPai | a = posFlux)
				result = ""+ECU.getFluxo(voce.getIdFluxoMaster()).getPosicaoFluxo()
						+""+getLetraEquivaletePosicao(voce.getPosicaoFluxo())+"."; 		// RESULT = "1a."
			} else {																	// NAO ALTERNATIVO	"1." (pai == anterior)															
				result = voce.getPosicaoFluxo() + ".";									// RESULT = voce.posicao
			}
		}
		
		return result;
	}	
				
	public static String[] getListagemFluxoInPosicaoNome(Vector<Fluxo> listaFluxo) { 
				
		String[] retorno;
		
		if(listaFluxo == null ) {
			return new String[0];
		} else {
			retorno = new String[listaFluxo.size()];
		}
		
		for (int i = 0; i < listaFluxo.size(); i++) {
			retorno[i] = new String();
			String tmp;
			if(listaFluxo.get(i).getInformacaoFluxo() == null)
				tmp = "";
			else
				tmp = listaFluxo.get(i).getInformacaoFluxo();
			
			retorno[i] = ECUGUI.getPosicaoFluxo(listaFluxo.get(i)) + ". " + tmp;
		}
		
		return retorno;
	}
		
	public static Vector<Fluxo> getListAllFluxo(int idCasoDeUso) { 
		
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getListAllFluxo ) ");
			return null;
		}
		Vector<Fluxo> list = null;
		// try {
			Vector<Fluxo> tmp = ECU.getListFluxoPrincipal(idCasoDeUso);
			Vector<Fluxo> tmp2 = ECU.getListAllFluxoExtensao(idCasoDeUso);
			if(tmp != null) {
				list = new Vector<Fluxo>();
				list.addAll(tmp);
				list.addAll(tmp2);
			}
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
	
		return list;
	}
	
	public static Vector<Fluxo> getListFluxoPlus(Fluxo fluxo) {
		if (fluxo == null || fluxo.getIdFluxo() <= 0)
			return null;
		
		Vector<Fluxo> list = ECU.getListFluxoExtensao(fluxo.getIdCasoDeUso(), fluxo.getIdFluxo());
		if(list == null) {
			Fluxo novo = new Fluxo(fluxo.getIdCasoDeUso(),fluxo.getIdFluxo(),1);
			list = new Vector<Fluxo>();
			list.add(novo);
		} else {
			Fluxo tmp = list.get(list.size()-1);
			Fluxo novo = new Fluxo(tmp.getIdCasoDeUso(),tmp.getIdFluxoMaster(),tmp.getPosicaoFluxo()+1);
			novo.setTipoAternativo(tmp.getTipoAternativo());
			list.add(novo);
		}
		return list;
	}
	
	public static Vector<Fluxo> getListFluxoPrincipal(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getListFluxoPrincipal ) ");
			return null;
		}
		
		// try {
			Vector<Fluxo> retorno = ECU.getListFluxoPrincipal(idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return retorno;
	}
	
	public static Vector<Fluxo> getListFluxoPrincipalPlus(int idCasoDeUso) {
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getListFluxoPrincipalPlus ) ");
			return null;
		}
		
		// try {
			Fluxo flx = null;
			Vector<Fluxo> list = ECU.getListFluxoPrincipal(idCasoDeUso);
			if(list == null) {
				flx = new Fluxo(idCasoDeUso, 1);
				list = new Vector<Fluxo>();
				list.add(flx);
			} else {
				flx = new Fluxo(idCasoDeUso, list.size()+1);
				list.add(flx);
			}
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
			
		return list;
	}
	
	public static Vector<Fluxo> getListFluxoExtensao(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getListFluxoExtensao ) ");
			return null;
		}
		// try {
			Vector<Fluxo> retorno = ECU.getListAllFluxoExtensao(idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return retorno;
	}
	
	public static Fluxo getFluxo(int idFluxo) {

		if (idFluxo < 0) {
			JOptionPane.showMessageDialog(null, "Dados Invalido ( getFluxo ) ");
			return null;
		}

		// try {
		Fluxo retorno = ECU.getFluxo(idFluxo);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }	
			
		return retorno;
	}
	// /////////////////////////////////////////////////////////////////////////////
	// Extensao

	// gerenciamento interface do Extensao

	public static void IrCriarExtensao() {
		ExtensaoFrame.getInstance(TipoExtensaoFrame.cria, IDCasoDeUso);
	}

	public static void IrEditarExtensao() {
		ExtensaoFrame.getInstance(TipoExtensaoFrame.edita, IDCasoDeUso);
	}

	public static void IrExcluirExtensao() {
		ExtensaoFrame.getInstance(TipoExtensaoFrame.excluir, IDCasoDeUso);
	}

	public static void encerrarExtensaoFrame() {
		ExtensaoFrame.encerrarFrame();
	}
	
	// gerenciamento operacoes das Extensao - SET

	public static void CriarExtensao(Extensao extensao) {
		if(extensao == null || extensao.getIdCasoDeUsoExtensao()<=0 || extensao.getIdCasoDeUsoMaster()<=0
				|| extensao.getIdFluxoPosicao() <0|| extensao.getTipoExtensao().equals(null) 
				|| extensao.getInformacaoExtensao().equals("")){
			JOptionPane.showMessageDialog(null, "Extensao com Dados Invalido ( CriarExtensao ) ");
			return;
		}
		// try {
			ECU.setCriarExtensao(extensao);
			ExtensaoFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }

	}

	public static void EditarExtensao(Extensao extensao) {
		if(extensao.getIdExtensao()<=0||extensao.getIdCasoDeUsoMaster()<=0||extensao.getIdCasoDeUsoExtensao()<=0
				||extensao.getIdFluxoPosicao()<=0||extensao.getIdExtensao()<=0
				||extensao.getTipoExtensao().equals("")||extensao.getInformacaoExtensao().equals("")) {
			JOptionPane.showMessageDialog(null, "Extensao com Dados Invalido ( EditarExtensao ) ");
			return;
		}
		// try {
			ECU.setEditarExtensao(extensao);
			ExtensaoFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }

	}

	public static void ExcluirExtensao(Extensao extensao) {

		if (extensao == null || extensao.getIdExtensao() <= 0) {
			JOptionPane.showMessageDialog(null, "Extensao com Dado Invalido");
			return;
		}
		// try {
			ECU.setExcluirExtensao(extensao);
			ExtensaoFrame.encerrarFrame();
			update();
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }

	}

	// funcoes de requisicao dos dados sobre as Extensao - GET

	public static int getQtdExtensao(int idProjeto) {
		
		if (idProjeto <= 0)
			JOptionPane.showMessageDialog(null, "Extensao com Dado Invalido");
		
		// try {
			int resultado = ECU.getQtdExtensao(idProjeto);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
			
		return resultado;
	}

	public static Vector<Extensao> getListExtensao(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0)
			JOptionPane.showMessageDialog(null, "Extensao com Dado Invalido");
		
		// try {
			Vector<Extensao> resultado = ECU.getListExtensao(idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return resultado;
	}
	
	public static Vector<Extensao> getAllExtensaoOfMaster(int idCasoDeUso) {
		
		if (idCasoDeUso <= 0)
			JOptionPane.showMessageDialog(null, "Extensao com Dado Invalido");
		
		// try {
			Vector<Extensao> resultado = ECU.getListExtensaoOfMaster(idCasoDeUso);
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(null, e.getMessage()); }
		
		return resultado;
	}
	
	public static String[] getListagemNomesExtensao(Vector<Extensao> listExt){//Yang
		String[] retorno;
		
		if(listExt == null ) {
			return new String[0];
		} else {
			retorno = new String[listExt.size()];
		}
		
		for (int i = 0; i < listExt.size(); i++) {
			retorno[i] = new String();
			String tmp;
			if(listExt.get(i)== null)
				tmp = "";
			else
				tmp = listExt.get(i).getInformacaoExtensao(); 
			
			retorno[i] = tmp;
		}
		
		return retorno;		
	}


	// /////////////////////////////////////////////////////////////////////////////

	// FUNCAO Close Application

	public static void EncerrarEditorCasoUso() {
		System.exit(0);
	}

	// FUNCAO Observer

	public static void addPanelObserver(Observer o) {
		controle.addObserver(o);
	}

	public static ECUGUI getInstance() {
		if (controle == null) {
			controle = new ECUGUI();
			mainFrame = new MainFrame();
			new ECU();
			new AtorFrame();
			IDProjeto = -1;
		}
		return controle;
	}

	/* Metodos Private */
	
	private static void update() {
		controle.setChanged();
		controle.notifyObservers();
		ECU.setAtualizaDataProjeto(IDProjeto);
	}

	private static String getLetraEquivaletePosicao(int posicaoFluxo) {
		
		if(posicaoFluxo > 26)
			return "-"+posicaoFluxo+".";
		else if(posicaoFluxo < 0)
			return null;
		
		char letra = (char) ('a'+(posicaoFluxo-1));
		String ret = ""+letra;
		
		return ret;
	}
	
	/* Main Method */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ECUGUI.getInstance();
			}
		});
	}
 

}