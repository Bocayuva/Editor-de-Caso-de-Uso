package logic;

import java.util.ArrayList;
import java.util.Vector;

import model.Ator;
import model.CasoDeUso;
import model.Extensao;
import model.Fluxo;
import model.View_InfoProjeto;
import model.View_ListCasoDeUso;
import model.Projeto;

public class ECU {

	/* Vareaveis da Class */

	private static AtorDao daoAtor;
	private static CasoDeUsoDao daoCasoDeUso;
	private static ExtensaoDao daoExtensao;
	private static FluxoDao daoFluxo;
	private static ProjetoDao daoProjeto;

	/* Construtor Default */

	public ECU() {
		daoAtor = new AtorDao();
		daoCasoDeUso = new CasoDeUsoDao();
		daoExtensao = new ExtensaoDao();
		daoFluxo = new FluxoDao();
		daoProjeto = new ProjetoDao();
	}

	/* Metodos Public */

	// PROJETO
	
	// SET
	
	public static int setCriarProjeto(String nomeProjeto) {

		if (nomeProjeto == null)
			throw new IllegalArgumentException("Dado inserio Invalido");

		Projeto projeto = new Projeto();
		projeto.setNomeProjeto(nomeProjeto);
		if (daoProjeto.buscar(projeto) == null) {
			daoProjeto.insere(projeto);
			projeto = daoProjeto.buscar(projeto);
			return projeto.getIdProjeto();
		}
		return -1;
	}

	public static void setEditarProjeto(int idProjeto, String novoNomeProjeto) {
		if (idProjeto == 0 || novoNomeProjeto == null)
			throw new IllegalArgumentException("Dado inserio Invalido");

		Projeto projeto = new Projeto();
		Projeto novo = new Projeto();
		projeto.setIdProjeto(idProjeto);
		novo.setNomeProjeto(novoNomeProjeto);

		projeto.setNomeProjeto(novoNomeProjeto);
		daoProjeto.atualizar(projeto);
	}

	public static void setExcluirProjeto(int idProjeto) {
		
		if (idProjeto == 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		Vector<CasoDeUso> list = daoCasoDeUso.buscarTodosCasoDeUso(idProjeto);
		if(list != null)
			for (CasoDeUso casoDeUso : list) {
				setExcluirCasoDeUso(idProjeto, casoDeUso.getIdCasoDeUso());
			}
		daoAtor.apagarAll(idProjeto);
		daoProjeto.apagar(idProjeto);
	}

	public static void setAtualizaDataProjeto(int idProjeto) {
		daoProjeto.atualizarDataModificacao(idProjeto);
	}

	// GET
	
	public static int getQtdCasoDeUso(int idProjeto) {
		return daoCasoDeUso.buscarQtdCasoDeUso(idProjeto);
	}

	public static View_InfoProjeto getInformacoesProjeto(int idProjeto) {

		if (idProjeto == 0)
			throw new IllegalArgumentException("Dado inserio Invalido");

		Projeto proj = new Projeto();
		proj.setIdProjeto(idProjeto);
		proj = daoProjeto.buscar(proj);

		View_InfoProjeto infoProj = new View_InfoProjeto();
		infoProj.setNomeDoProjeto(proj.getNomeProjeto());
		infoProj.setQtdAtores(daoAtor.buscarQtdAtores(idProjeto));
		infoProj.setQtdUC(daoCasoDeUso.buscarQtdCasoDeUso(idProjeto));
		infoProj.setQtdEscopo(daoCasoDeUso.buscarQtdEscopoCasoDeUso(idProjeto));
		infoProj.setDataCriacao(proj.getDataCriacao());
		infoProj.setDataModificacao(proj.getDataModificacao());

		return infoProj;
	}

	public static Vector<Projeto> getlistProjetos() {

		Vector<Projeto> listagem = daoProjeto.buscarTodos();
		return listagem;
	}
	
	public static ArrayList<View_ListCasoDeUso> getListCasoDeUsoDoProjeto(
			int idProjeto) {

		if (idProjeto == 0)
			throw new IllegalArgumentException("Dado inserio Invalido");

		Vector<CasoDeUso> listagem = daoCasoDeUso
				.buscarTodosCasoDeUso(idProjeto);

		if (listagem.size() != 0) {

			ArrayList<View_ListCasoDeUso> resultado = new ArrayList<View_ListCasoDeUso>();

			for (int i = 0; i < listagem.size(); i++) {

				int qtdExtencoes = daoExtensao.getQtdExtencoesCasoDeUso(listagem.get(i).getIdCasoDeUso());
				int qtdIncludes = daoExtensao.getQtdIncludesCasoDeUso(listagem.get(i).getIdCasoDeUso());

				View_ListCasoDeUso data = new View_ListCasoDeUso();
				data.setIdCasoDeUso(listagem.get(i).getIdCasoDeUso());
				data.setNomeCasoDeUso(listagem.get(i).getNomeCasoDeUso());
				data.setEscopo(listagem.get(i).getEscopo());
				data.setQtdExtencoes(qtdExtencoes);
				data.setQtdInclude(qtdIncludes);

				resultado.add(data);
			}
			return resultado;
		}
		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////
	// ATOR
	
	// SET
	
	public static void setCriarAtor(Ator ator) {

		if (ator.getIdProjeto() <= 0 || ator.getIdAtor() < 0 || 
				ator.getNomeAtor() == null || ator.getNomeAtor().equals(""))
				throw new IllegalArgumentException("Dado inserio Invalido");

		Vector<Ator> list = daoAtor.buscarTodos(ator.getIdProjeto());
		if(list != null)
			for (Ator tmp : list) {
				if(tmp.getNomeAtor().equals(ator.getNomeAtor()))
					throw new IllegalArgumentException("Dado inserio Invalido");
			}
		
		daoAtor.insere(ator);
	}

	public static void setEditarAtor(Ator ator) {

		if (ator.getIdProjeto() <= 0 || ator.getIdAtor() <= 0 || 
			ator.getNomeAtor() == null || ator.getNomeAtor().equals(""))
				throw new IllegalArgumentException("Dado inserio Invalido");
		daoAtor.atualizar(ator);
	}

	public static void setExcluirAtor(Ator ator) {

		if (ator.getIdProjeto() <= 0 || ator.getIdAtor() <= 0 || 
			ator.getNomeAtor() == null || ator.getNomeAtor().equals(""))
			throw new IllegalArgumentException("Dado inserio Invalido");

		daoAtor.apagar(ator);
	}
	
	// GET
	
	public static Ator getAtor(int idProjeto, String nome) {
		
		if(idProjeto <= 0 || nome == null)
			return null;
		
		return daoAtor.buscarAtor(idProjeto, nome);
	}
	
	public static String[] getListagemAtores(int idProjeto) {
		
		if (idProjeto <= 0)
			throw new IllegalArgumentException("Dado inserio Invalido");

		Vector<Ator> atorlist = daoAtor.buscarTodos(idProjeto);
		if (atorlist == null)
			return null;

		String[] resultAtorList = new String[atorlist.size()];
		for (int index = 0; index < atorlist.size(); index++) {
			resultAtorList[index] = new String();
			resultAtorList[index] = atorlist.get(index).getNomeAtor();
		}
		return resultAtorList;
	}

	public static Vector<Ator> getListAtor(int idProjeto) {
		
		if (idProjeto <= 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		return daoAtor.buscarTodos(idProjeto);
	}
	
	// //////////////////////////////////////////////////////////////////////////////
	// CASODEUSO
	
	// SET
		
 	public static int setCriarCasoDeUso(CasoDeUso casoDeUso) {

		if (casoDeUso == null || casoDeUso.getIdCasoDeUso() < 0 || 
				casoDeUso.getIdAtorPrimario() == 0 || 
				casoDeUso.getObjetivo() == null || casoDeUso.getEscopo() == null ||  casoDeUso.getNomeCasoDeUso() == null )
				throw new IllegalArgumentException("Dado inserio Invalido");
		
		daoCasoDeUso.insere(casoDeUso);

		int resul = daoCasoDeUso.buscarIDCasoDeUso(casoDeUso.getIdProjeto(), casoDeUso.getNomeCasoDeUso());
		return resul;
	}

	public static void setEditarCasoDeUso(CasoDeUso casoDeUso) {

		if (casoDeUso == null || casoDeUso.getIdCasoDeUso() <= 0 || 
			casoDeUso.getIdAtorPrimario() <= 0 || 
			casoDeUso.getObjetivo() == null || casoDeUso.getEscopo() == null ||  casoDeUso.getNomeCasoDeUso() == null )
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		daoCasoDeUso.atualizar(casoDeUso);
	}

	public static void setExcluirCasoDeUso(int idProjeto, int idCasoDeUso) { 
		
		if (idProjeto < 0 || idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		daoExtensao.apagarAll(idCasoDeUso);
		daoExtensao.apagarAllWithIdCasoUsoMaster(idCasoDeUso);
		daoFluxo.apagarAllWithIdCasoDeUso(idCasoDeUso);
		daoCasoDeUso.apagar(idCasoDeUso, idProjeto);
	}
	
	// GET
	
	public static CasoDeUso getCasoDeUso(int idProjeto, int idCasoDeUso) {

		if (idProjeto < 0 || idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");

		CasoDeUso cdu = daoCasoDeUso.buscarById(idCasoDeUso);
		if (cdu == null)
			return null;
		CasoDeUso dados = new CasoDeUso();
		dados.setIdProjeto(idProjeto);
		dados.setIdCasoDeUso(idCasoDeUso);
		dados.setNomeCasoDeUso(cdu.getNomeCasoDeUso());
		dados.setObjetivo(cdu.getObjetivo());
		dados.setEscopo(cdu.getEscopo());
		dados.setNivel(cdu.getNivel());
		dados.setAtorPrimario(daoAtor.buscarAtor(idProjeto,	cdu.getIdAtorPrimario()));
		dados.setAtorSuporte(daoAtor.buscarAtor(idProjeto, cdu.getIdAtorSuporte()));
		dados.setPreCondicao(cdu.getPreCondicao());
		dados.setPosCondicao(cdu.getPosCondicao());
		return dados;
	}

	public static Vector<CasoDeUso> getListCasosDeUsoIrmaos(int idCasoDeUso){
		/* Retorna a lista de casos de uso que s�o do mesmo projeto do caso de uso 
		 * caso a ID exista e caso o projeto possua outros casos de uso
		 */
		if (idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");

		CasoDeUso cdu = daoCasoDeUso.buscarById(idCasoDeUso);
		if (cdu == null)
			return null;
		Vector<CasoDeUso> vListCDU = daoCasoDeUso.buscarTodosCasoDeUso(cdu);
		
		return vListCDU;
	}
	
	// //////////////////////////////////////////////////////////////////////////////
	// FLUXO
	
	// SET
	
	public static void setAdicionaFluxo(Fluxo fluxo) {

		if (fluxo.getIdCasoDeUso() < 0 || fluxo.getIdFluxoMaster() < 0 || 
			fluxo.getPosicaoFluxo() <=0 || fluxo.getInformacaoFluxo() == null)
			throw new IllegalArgumentException("Dado inserio Invalido");

		Vector<Fluxo> fluxoList = null;
		if(fluxo.getIdFluxoMaster() == 0) {
			fluxoList = daoFluxo.getAllFluxoPrinciapl(fluxo.getIdCasoDeUso());
		} else {
			fluxoList =	daoFluxo.getAllFluxoExtensao(fluxo.getIdCasoDeUso(), fluxo.getIdFluxoMaster());
		}
		
		if (fluxoList != null) {
			for (int i = (fluxo.getPosicaoFluxo()-1); i < fluxoList.size(); i++) {
				Fluxo tmp = fluxoList.get(i);
				if (tmp != null) {
					tmp.setPosicao(tmp.getPosicaoFluxo() + 1);
					daoFluxo.atualizar(tmp);
				}
			}
		}
		daoFluxo.insere(fluxo);
	}
	
	public static void setEditarFluxo(Fluxo fluxo) {
		
		if (fluxo.getIdCasoDeUso() < 0 || fluxo.getIdFluxoMaster() < 0 || 
				fluxo.getPosicaoFluxo() <=0 || fluxo.getInformacaoFluxo() == null)
				throw new IllegalArgumentException("Dado inserio Invalido");
				
			daoFluxo.atualizar(fluxo);
	}
	
	public static void setExcluirFluxo(Fluxo fluxo) {

		if (fluxo == null || fluxo.getIdCasoDeUso() <= 0 || fluxo.getIdFluxo() <= 0)
			throw new IllegalArgumentException("Dado inserio Invalido");

		// apagar filhos
		Vector<Fluxo> listFlihos = null;
		listFlihos = listFluxoExtensao(fluxo.getIdCasoDeUso(),fluxo.getIdFluxo(),null);
		if(listFlihos!=null){
			for (Fluxo fluxoTMP : listFlihos) {
				daoFluxo.apagar(fluxoTMP);
				daoExtensao.apagarAllWithIdFluxo(fluxoTMP.getIdFluxo());
			}
		}
		
		// apagar voce
		daoFluxo.apagar(fluxo);
		daoExtensao.apagarAllWithIdFluxo(fluxo.getIdFluxo());
		
		// pega a tabela
		Vector<Fluxo> fluxoList = null;
		if(fluxo.getIdFluxoMaster() == 0) {// Fluxo Principal
			fluxoList = daoFluxo.getAllFluxoPrinciapl(fluxo.getIdCasoDeUso());
		} else { // Fluxo Etensao
			fluxoList =	daoFluxo.getAllFluxoExtensao(fluxo.getIdCasoDeUso(), fluxo.getIdFluxoMaster());
		}
		
		// atualizar os outros elementos da listagem, posterior ao excluido
		int posicao = 0;
		if((fluxo.getPosicaoFluxo()-1) >= 0)
			posicao = (fluxo.getPosicaoFluxo()-1);
		if ( posicao >= 0&& fluxoList != null) {
			for (int i = posicao; i < fluxoList.size(); i++) {// i = posicao, pois se apagar o ultimo, nao deixa entrar no for
				Fluxo tmp = fluxoList.get(i);
				tmp.setPosicao(tmp.getPosicaoFluxo() - 1);
				daoFluxo.atualizar(tmp);
			}
		}	
	}
	
	// GET

	public static int getSizeFluxoPrincipal(int idCasoDeUso) {
		
		if (idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		return daoFluxo.getQtdFluxoPrincipal(idCasoDeUso);
	}
	
	public static int getSizeFluxoExtensao(int idCasoDeUso) {
		
		if (idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		Vector<Fluxo> list = daoFluxo.getAllFluxoPrinciapl(idCasoDeUso);
		int result = 0;
		for(int i=0;i<list.size();i++) {
			Fluxo tmp = list.get(i);
			int qtd = daoFluxo.getQtdFluxoExtensao(idCasoDeUso, tmp.getIdFluxo());
			result = result + qtd;
		}
		return result;
	}
		
	public static boolean getHasFluxoExtensao(int idFluxo) {
		
		if (idFluxo < 0) 
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		boolean result = daoFluxo.hasFluxoExtensao(idFluxo);
		return result;
	}
	
	public static Fluxo getFluxo(int idFluxo) {
		
		if (idFluxo < 0) 
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		return daoFluxo.getFluxo(idFluxo);
	}
	
	public static String[] getListagemFluxoPrincipal(int idCasoUso) {

		if(idCasoUso <= 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		Vector<Fluxo> flux = daoFluxo.getAllFluxoPrinciapl(idCasoUso);
		if (flux == null || flux.size() <= 0)
			return null;

		String[] fluxoList = new String[flux.size()];
		for (int i = 0; i < flux.size(); i++) {
			fluxoList[i] = new String();
			fluxoList[i] = flux.get(i).getPosicaoFluxo() + ". " + flux.get(i).getInformacaoFluxo();
		}
		return fluxoList;
	}
	
	public static Vector<Fluxo> getListFluxoAntecessor(Fluxo fluxo) {//CARLOS

		if (fluxo == null || fluxo.getPosicaoFluxo() <= 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		Vector<Fluxo> result = new Vector<Fluxo>();
		result.add(fluxo);
		
		int idFlxMaster = fluxo.getIdFluxoMaster();
		while(idFlxMaster <= 0) {	
			Fluxo flxPai = getFluxo(idFlxMaster);
			if(flxPai != null) {
				result.add(1, flxPai);
				idFlxMaster = flxPai.getIdFluxoMaster();
			}
		}
		return result;
	}
	
	public static Vector<Fluxo> getListFluxoPrincipal(int idCasoUso) {
		
		if (idCasoUso <= 0) 
			throw new IllegalArgumentException("Dado inserio Invalido "+idCasoUso);
		
		return daoFluxo.getAllFluxoPrinciapl(idCasoUso);
	}
	
	public static Vector<Fluxo> getListFluxoExtensao(int idCasoDeUso, int idFluxo){
		
		if (idCasoDeUso < 0 || idFluxo < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
			
		return daoFluxo.getAllFluxoExtensao(idCasoDeUso,idFluxo);
	}
	
	public static Vector<Fluxo> getListAllFluxoExtensao(int idCasoDeUso) {
		Vector<Fluxo> resultado = null;
		Vector<Fluxo> vecFlxPric = daoFluxo.getAllFluxoPrinciapl(idCasoDeUso);
		if(vecFlxPric != null) {
			resultado = new Vector<Fluxo>();
			for (Fluxo fluxoBean : vecFlxPric) {
				Vector<Fluxo> vecTmp = listFluxoExtensao(idCasoDeUso, fluxoBean.getIdFluxo(),null);
				if(vecTmp != null)
					resultado.addAll(vecTmp);
			}
		}
		return resultado;
	}
	
	// //////////////////////////////////////////////////////////////////////////////
	// EXTENSAO
	
	// SET
	
	public static void setCriarExtensao(Extensao extensao) {
		if(extensao == null || extensao.getIdCasoDeUsoExtensao()<=0 || extensao.getIdCasoDeUsoMaster()<=0
				|| extensao.getIdFluxoPosicao() <0|| extensao.getTipoExtensao().equals(null) 
				|| extensao.getInformacaoExtensao().equals(""))
			throw new IllegalArgumentException("Dado inserio Invalido");

		daoExtensao.insere(extensao);
	}

	public static void setEditarExtensao(Extensao extensao) {
		
		if (extensao == null || extensao.getIdExtensao()<=0||extensao.getIdCasoDeUsoMaster()<=0||extensao.getIdCasoDeUsoExtensao()<=0
				||extensao.getIdFluxoPosicao()<=0||extensao.getIdExtensao()<=0||extensao.getTipoExtensao().equals("")||extensao.getInformacaoExtensao().equals(""))
				throw new IllegalArgumentException("Dado inserio Invalido");
		
		daoExtensao.atualizar(extensao);
	}

	public static void setExcluirExtensao(Extensao extensao) { 
		
		if (extensao == null || extensao.getIdExtensao() <= 0 )
				throw new IllegalArgumentException("Dado inserio Invalido");
		
		daoExtensao.apagar(extensao);
	}

	// GET	
	
	public static int getQtdExtensao(int idProjeto) {
		if (idProjeto < 0 ) {
			throw new IllegalArgumentException("Dado inserio Invalido");
		}
		Vector<CasoDeUso> cdus = daoCasoDeUso.buscarTodosCasoDeUso(idProjeto);
		int qtdExtProj=0;
		if(cdus!=null){
			for(CasoDeUso c : cdus){
				qtdExtProj = daoExtensao.getQtdIncludesCasoDeUso(c.getIdCasoDeUso()) + daoExtensao.getQtdExtencoesCasoDeUso(c.getIdCasoDeUso());
			}
		}
		return qtdExtProj;
	}

	public static int getQtdExtensaoInclude(int idCasoDeUso) {
		
		if (idCasoDeUso < 0 )
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		return daoExtensao.getQtdIncludesCasoDeUso(idCasoDeUso);
	}

	public static int getQtdExtensaoExtend(int idCasoDeUso) {
		if (idCasoDeUso < 0 ) {
			throw new IllegalArgumentException("Dado inserio Invalido");
		}
		return daoExtensao.getQtdExtencoesCasoDeUso(idCasoDeUso);
	}
	
	public static Extensao getExtensao(int idExtensao) {
		
		if(idExtensao <= 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		
		return daoExtensao.getExtensao(idExtensao);
	}
	
	public static Vector<Extensao> getListExtensao(int idCasoDeUso) {
		if (idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		Vector<Extensao> list = daoExtensao.getAllExtensao(idCasoDeUso);
		for (Extensao extensao : list) {
			Fluxo tmp = daoFluxo.getFluxo(extensao.getIdFluxoPosicao());
			extensao.setFluxo(tmp);
		}
		return list;
	}
	
	public static Vector<Extensao> getListExtensaoOfMaster(int idCasoDeUso) {
		if (idCasoDeUso < 0)
			throw new IllegalArgumentException("Dado inserio Invalido");
		Vector<Extensao> list = daoExtensao.getAllExtensaoOfMaster(idCasoDeUso);
		if(list != null) {
			for (Extensao extensao : list) {
				Fluxo tmp = daoFluxo.getFluxo(extensao.getIdFluxoPosicao());
				extensao.setFluxo(tmp);
			}
		}
		return list;
	}
	
	/* Methods Private */
	
	private static Vector<Fluxo> listFluxoExtensao(int idCasoDeUso, int idFluxo, Vector<Fluxo> vecFamilia) {
		
		if(vecFamilia == null) {																							// Voce nao sabe se e Pai
			Vector<Fluxo> vecFilhos = daoFluxo.getAllFluxoExtensao(idCasoDeUso, idFluxo);								// Busca saber se possui filhos
			if(vecFilhos == null)																							// Nao tem filho
				return null;																								// Voce nao e pai, RETORNA NULL
			else 																											// Voce e pai
				return listFluxoExtensao(idCasoDeUso, idFluxo, vecFilhos);													// Busca seus filhos, RETORNA FAMILIA
			
		} else { 																											// Voce sabe que e Pai
			Vector<Fluxo> family = new Vector<Fluxo>();																// Cria vetor familia
			for (int i = 0; i < vecFamilia.size(); i++) { 																	// Alinha familia para analise
				Fluxo filho = vecFamilia.get(i); 																		// Separa o filho
				family.add(filho);																							// Adiciona o filho a familia
				Vector<Fluxo> vecNetos = daoFluxo.getAllFluxoExtensao(idCasoDeUso, filho.getIdFluxo()); 				// Buscar saber se e Avo, pesquisando no filho adicionado
				if(vecNetos != null) { 																						// Tem netos
					Vector<Fluxo> vecFamiliaFilho = listFluxoExtensao(idCasoDeUso, filho.getIdFluxo(), vecNetos);		// Busca a familia do seu filho
					if(vecFamiliaFilho != null)																				// Voce encontra a familia do filho
						family.addAll(vecFamiliaFilho);																		// Adiciona a familia do filho a sua familia.
				} 
			}
			return family;																									// RETORNA A FAMILIA
		}	
	}
}