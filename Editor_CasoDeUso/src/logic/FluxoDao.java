package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import model.Fluxo;

class FluxoDao {

	/* Local variables */

	private Connection con;
	private Statement comando;

	/* Private Methods */

	private void conectar() {
		try {
			con = ConFactory.conexao();
			comando = con.createStatement();
		} catch (ClassNotFoundException e) {
			imprimeErro("Erro ao carregar o driver",null);
		} catch (SQLException e) {
			imprimeErro("Erro ao conectar", e);
		}
	}

	private void fechar() {
		try {
			comando.close();
			con.close();
			ConFactory.nullConnection();
		} catch (SQLException e) {
			imprimeErro("Erro ao fechar conexão", e);
		}
	}

	private void imprimeErro(String msg,SQLException e) {
		System.err.println(msg);
		System.out.println(" Message: "+e.getMessage());
		System.out.println(" CAUSE: "+e.getCause());
		e.printStackTrace();
	}

	/* public Methods */

	public void insere(Fluxo fluxo) {
		conectar();
		String query;
		int num = 0;
		if(fluxo.getTipoAternativo()==true)
			num = 1;
		if(fluxo.getIdFluxoMaster() == 0) {
		query = "INSERT INTO Fluxo (idCasoDeUso,posicaoFluxo,informacaoFluxo,tipoAlternativo)VALUES("
				+ fluxo.getIdCasoDeUso()
				+ " , "
				+ fluxo.getPosicaoFluxo()
				+ " , '"
				+ fluxo.getInformacaoFluxo()
				+ "' , "
				+ num
				+ " );";
		} else {
		query = "INSERT INTO Fluxo (idCasoDeUso,idFluxoMaster,posicaoFluxo,informacaoFluxo,tipoAlternativo)VALUES("
				+ fluxo.getIdCasoDeUso()
				+ " , "
				+ fluxo.getIdFluxoMaster()
				+ " , "
				+ fluxo.getPosicaoFluxo()
				+ " , '"
				+ fluxo.getInformacaoFluxo()
				+ "' , "
				+ num
				+ " );";
		}
		//System.out.println("FluxoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao inserir Fluxo", e);
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public void atualizar(Fluxo Fluxo) {
		conectar();
		String plus = null;
		
		 if(Fluxo.getIdFluxoMaster() != 0)
			 plus = " idFluxoMaster  = " + Fluxo.getIdFluxoMaster() ;
		 else 
			 plus = " idFluxoMaster  = NULL";
		 
		String query = "UPDATE Fluxo SET " 
					 + plus
					 + ", posicaoFluxo = " + Fluxo.getPosicaoFluxo() 
					 + ", informacaoFluxo = '" + Fluxo.getInformacaoFluxo()
					 + "' WHERE idCasoDeUso = " + Fluxo.getIdCasoDeUso()
					 + "  AND idFluxo = " + Fluxo.getIdFluxo() + " ;";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public void apagar(Fluxo fluxo) {
		conectar();
		String query = "DELETE FROM Fluxo " + "WHERE idFluxo = "+ fluxo.getIdFluxo() + " ;";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			comando.executeUpdate(query);

		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Fluxos", e);
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
	
	public void apagarAllWithIdCasoDeUso(int idCasoDeUso){// TODO - YANG - TEST
		conectar();
		String query = "DELETE FROM Fluxo WHERE idCasoDeUso = "+ idCasoDeUso + " ;";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Fluxos", e);
			e.printStackTrace();
		} finally {
			fechar();
		}		
	}
	
	public void apagarAllWithIdFluxoMaster(int idFluxoMaster){// TODO - CARLOS - TEST
		conectar();
		String query = "DELETE FROM Fluxo WHERE idFluxoMaster = "+ idFluxoMaster +" ;";
		System.out.println("FluxoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Fluxos", e);
			e.printStackTrace();
		} finally {
			fechar();
		}		
	}

	// Fluxo
	
	public Fluxo getFluxo(int idFluxo) {
		conectar();
		String query = "SELECT * FROM Fluxo " 
					+ " WHERE idFluxo  = "+idFluxo+ ";";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			rs.next();
			Fluxo temp = new Fluxo();
			temp.setIdFluxo(rs.getInt("idFluxo"));
			temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
			temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
			temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
			temp.setPosicao(rs.getInt("posicaoFluxo"));
			temp.setInformacao(rs.getString("informacaoFluxo"));
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro em getFluxoExtensao", e);
			return null;
		}
	}
	
	public Vector<Fluxo> getListAllFluxo(int idCasoDeUso) {
		conectar();
		String query = " SELECT * FROM Fluxo "
				 	 + " WHERE idCasoDeUso = " + idCasoDeUso 
					 + " ORDER BY posicaoFluxo ASC;";
		//System.out.println("FluxoDao	| query: "+query);
		Vector<Fluxo> resultados = new Vector<Fluxo>();
		try {
			ResultSet rs = comando.executeQuery(query);
			while (rs.next()) {
				Fluxo temp = new Fluxo();
				temp.setIdFluxo(rs.getInt("idFluxo"));
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
				temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
				temp.setPosicao(rs.getInt("posicaoFluxo"));
				temp.setInformacao(rs.getString("informacaoFluxo"));
				resultados.add(temp);
			}
			if(resultados.size()==0)
				return null;
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro em getListAllFluxo", e);
			return null;
		}
	}
	
	// Fluxo Principal

	public int getQtdFluxoPrincipal(int idCasoDeUso) {
		conectar();
		String query = "SELECT COUNT(idFluxo) FROM Fluxo " 
					+ " WHERE idCasoDeUso = " + idCasoDeUso 
					+ " AND idFluxoMaster IS NULL ;";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			int resultados = 0;
			while (rs.next()) {
				resultados = Integer.parseInt(rs.getString(1));
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Qtd CasoDeUso", e);
			return 0;
		}
	}
	
	public int getIdFluxoPrincipal(int idCasoDeUso, int posicao){
		conectar();
		String query = "SELECT * FROM Fluxo " 
					+ " WHERE idCasoDeUso = " + idCasoDeUso 
					+ " AND idFluxoMaster IS NULL "
					+ " AND posicaoFluxo = " + posicao + ";";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			rs.next();
			Fluxo temp = null;
			temp = new Fluxo();
			temp.setIdFluxo(rs.getInt("idFluxo"));
			temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
			temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
			temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
			temp.setPosicao(rs.getInt("posicaoFluxo"));
			temp.setInformacao(rs.getString("informacaoFluxo"));
			return temp.getIdFluxo();
		} catch (SQLException e) {
			imprimeErro("Erro em buscarFluxoPrincipal - Fluxo", e);
			return 0;
		}
	}
	
	public Fluxo getFluxoPrincipal(int idCasoDeUso, int posicao) {
		conectar();
		String query = "SELECT * FROM Fluxo " 
					+ " WHERE idCasoDeUso = " + idCasoDeUso 
					+ " AND idFluxoMaster IS NULL "
					+ " AND posicaoFluxo = " + posicao + ";";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
				rs.next();
				Fluxo temp = null;
				temp = new Fluxo();
				temp.setIdFluxo(rs.getInt("idFluxo"));
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
				temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
				temp.setPosicao(rs.getInt("posicaoFluxo"));
				temp.setInformacao(rs.getString("informacaoFluxo"));
				return temp;

		} catch (SQLException e) {
			e = null;
			//imprimeErro("Erro em buscarFluxoPrincipal - Fluxo", e);
			return null;
		}
	}
	
	public Vector<Fluxo> getAllFluxoPrinciapl(int idCasoDeUso) {
		conectar();
		String query = " SELECT * FROM Fluxo "
					 + " WHERE idCasoDeUso = " + idCasoDeUso 
					 + " AND idFluxoMaster IS NULL "
					 + " ORDER BY posicaoFluxo ASC;";
		//System.out.println("FluxoDao	| query: "+query);
		Vector<Fluxo> resultados = new Vector<Fluxo>();
		try {
			ResultSet rs = comando.executeQuery(query);
			while (rs.next()) {
				Fluxo temp = new Fluxo();
				temp.setIdFluxo(rs.getInt("idFluxo"));
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
				temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
				temp.setPosicao(rs.getInt("posicaoFluxo"));
				temp.setInformacao(rs.getString("informacaoFluxo"));
				resultados.add(temp);
			}
			if(resultados.size()==0)
				return null;
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro buscarTodoFluxoPrinciapl", e);
			return null;
		}
	}
	
	// Fluxo Extensao
	
	public boolean hasFluxoExtensao(int idFluxo) {
		String query = "SELECT COUNT(*) FROM Fluxo WHERE idFluxoMaster = " + idFluxo + " ;";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			rs.next();
			if(rs.getInt(1)==0)
				return false;
			else
				return true;
		} catch (SQLException e) {
			imprimeErro("Erro ao getPossuiFluxoExtensao", e);
			return false;
		}
	}
	
	public int getQtdFluxoExtensao(int idCasoDeUso, int idFluxoMaster) {
		conectar();
		String query = "SELECT COUNT(*) FROM Fluxo " 
					+ " WHERE idCasoDeUso = " + idCasoDeUso 
					+ " AND idFluxoMaster = " + idFluxoMaster + " ;";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			imprimeErro("Erro ao getQtdFluxoExtensao", e);
			return 0;
		}
	}
	
	public Fluxo getFluxoExtensaoPorID(int idFluxo) {
		conectar();
		String query = "SELECT * FROM Fluxo " 
					+ " WHERE idFluxo  = "+idFluxo+ ";";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			rs.next();
			Fluxo temp = null;
			temp = new Fluxo();
			temp.setIdFluxo(rs.getInt("idFluxo"));
			temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
			temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
			temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
			temp.setPosicao(rs.getInt("posicaoFluxo"));
			temp.setInformacao(rs.getString("informacaoFluxo"));
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro em getFluxoExtensao", e);
			return null;
		}
	}
	
	public Fluxo getFluxoExtensao(int idCasoDeUso, int idFluxoMaster, int posicao) {
		conectar();
		String query = "SELECT * FROM Fluxo " 
				+ " WHERE idCasoDeUso = " + idCasoDeUso 
				+ " AND idFluxoMaster = " + idFluxoMaster 
				+ " AND posicaoFluxo = " + posicao + ";";
		//System.out.println("FluxoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			rs.next();
			Fluxo temp = new Fluxo();
			temp.setIdFluxo(rs.getInt("idFluxo"));
			temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
			temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
			temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
			temp.setPosicao(rs.getInt("posicaoFluxo"));
			temp.setInformacao(rs.getString("informacaoFluxo"));
			return temp;
		} catch (SQLException e) {
			imprimeErro("Erro em getFluxoExtensao", e);
			return null;
		}
	}
	
	public Vector<Fluxo> getAllFluxoExtensao(int idCasDeUso, int idFluxoMaster) {
		conectar();
		String query = " SELECT * FROM Fluxo "
				 	 + " WHERE idCasoDeUso = " + idCasDeUso 
					 + " AND idFluxoMaster = " + idFluxoMaster
					 + " ORDER BY posicaoFluxo ASC;";
		//System.out.println("FluxoDao	| query: "+query);
		Vector<Fluxo> resultados = new Vector<Fluxo>();
		try {
			ResultSet rs = comando.executeQuery(query);
			while (rs.next()) {
				Fluxo temp = new Fluxo();
				temp.setIdFluxo(rs.getInt("idFluxo"));
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setIdFluxoMaster(rs.getInt("idFluxoMaster"));
				temp.setTipoAternativo(rs.getBoolean("tipoAlternativo"));
				temp.setPosicao(rs.getInt("posicaoFluxo"));
				temp.setInformacao(rs.getString("informacaoFluxo"));
				resultados.add(temp);
			}
			if(resultados.size()==0)
				return null;
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro em getAllFluxoExtensao", e);
			return null;
		}
	}

}