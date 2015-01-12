package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import model.Extensao;

class ExtensaoDao {

	/* Local variables */

	private Connection con;
	private Statement comando;

	/* Private Methods */

	private void conectar() {
		try {
			con = ConFactory.conexao();
			comando = con.createStatement();
		} catch (ClassNotFoundException e) {
			imprimeErro("Erro ao carregar o driver", null);
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

	private void imprimeErro(String msg, SQLException e) {
		System.err.println(msg);
		System.out.println(" Message: " + e);
		System.out.println(" CAUSE: " + e.getCause());
		e.printStackTrace();
	}

	/* public Methods */

	// SET
	public void insere(Extensao Extensao) {
		conectar();
		String query = "INSERT INTO Extensao (idCasoDeUso, idCasoDeUsoMaster, idFluxoPosicao,informacaoExtensao, tipoExtensao) VALUES ("
				+ Extensao.getIdCasoDeUsoExtensao()
				+ ","
				+ Extensao.getIdCasoDeUsoMaster()
				+ ","
				+ Extensao.getIdFluxoPosicao()
				+ ",'"
				+ Extensao.getInformacaoExtensao()
				+ "','"
				+ Extensao.getTipoExtensao()+ "');";
				// System.out.println("ExtensaoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao insere", e);
		} finally {
			fechar();
		}
	}

	public void atualizar(Extensao Extensao) {
		conectar();
		String query = "UPDATE Extensao SET "
				+"idCasoDeUso = "	+ Extensao.getIdCasoDeUsoExtensao() 
				+ " , idFluxoPosicao = "	+ Extensao.getIdFluxoPosicao() 
				+ " , idCasoDeUsoMaster = "	+ Extensao.getIdCasoDeUsoMaster() 
				+ " , tipoExtensao = '"	+ Extensao.getTipoExtensao() 
				+ "', informacaoExtensao = '"+ Extensao.getInformacaoExtensao() 
				+ "' WHERE idExtensao = "+ Extensao.getIdExtensao() + " ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao atualizar", e);
		} finally {
			fechar();
		}
	}

	public void apagar(Extensao extensao) {
		conectar();
		String query = null;
		if (extensao.getIdExtensao() <= 0)
			query = "DELETE FROM Extensao " + " WHERE idExtensao = "
					+ extensao.getIdExtensao() + " ;";
		else
			query = "DELETE FROM Extensao WHERE idExtensao = "
					+ extensao.getIdExtensao() + " AND idCasoDeUso = "
					+ extensao.getIdCasoDeUsoExtensao() + " ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao apagar", e);
		} finally {
			fechar();
		}
	}

	public void apagarAll(int idCasoDeUso) {
		conectar();
		String query = "DELETE FROM Extensao WHERE idCasoDeUso = " + idCasoDeUso + " ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao apagarAll", e);
		} finally {
			fechar();
		}
	}

	public void apagarAllWithIdCasoUsoMaster(int idCasoDeUsoMaster) {
		conectar();
		String query = "DELETE FROM Extensao WHERE idCasoDeUsoMaster = " + idCasoDeUsoMaster + " ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao apagarAll", e);
		} finally {
			fechar();
		}
	}
	
	public void apagarAllWithIdFluxo(int idFluxoPosicao) {
		conectar();
		String query = "DELETE FROM Extensao WHERE idFluxoPosicao = " + idFluxoPosicao + " ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao apagarAll", e);
		} finally {
			fechar();
		}
	}
	
	// GET
	public int getQtdExtencoesCasoDeUso(int idCasoDeUso) {
		conectar();
		String query = "SELECT COUNT(*) " + "FROM Extensao "
				+ " WHERE idCasoDeUso = " + idCasoDeUso
				+ " AND tipoExtensao = 'Extend' ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			int resultados = 0;
			while (rs.next()) {
				resultados = rs.getInt("count(*)");
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao getQtdExtencoesCasoDeUso", e);
			return 0;
		}
	}

	public int getQtdIncludesCasoDeUso(int idCasoDeUso) {
		conectar();
		String query = "SELECT COUNT(*) " + "FROM Extensao "
				+ " WHERE idCasoDeUso = " + idCasoDeUso
				+ " AND tipoExtensao  = 'Include' ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			int resultados = 0;
			while (rs.next()) {
				resultados = rs.getInt("count(*)");
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao getQtdIncludesCasoDeUso", e);
			return 0;
		}
	}

	public Extensao getExtensao(int idExtensao) {
		conectar();
		String query = "SELECT * FROM Extensao WHERE idExtensao  = " + idExtensao + " ;";
		// System.out.println("ExtensaoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			Extensao temp = new Extensao();
			// pega todos os atributos da Extensao
			temp.setIdExtensao(rs.getInt("idExtensao"));
			temp.setIdCasoDeUsoExtensao(rs.getInt("idCasoDeUso"));
			temp.setIdCasoDeUsoMaster(rs.getInt("idCasoDeUsoMaster"));
			temp.setIdFluxoPosicao(rs.getInt("idFluxoPosicao"));
			temp.setTipoExtensao(rs.getString("tipoExtensao"));
			temp.setInformacaoExtensao(rs.getString("informacaoExtensao"));
			return temp;
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao getExtensao", e);
			return null;
		}
	}

	public Vector<Extensao> getAllExtensao(int idCasoDeUso) {
		conectar();
		String query = "SELECT * FROM Extensao WHERE idCasoDeUso = " + idCasoDeUso + " ;";
		System.out.println("ExtensaoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			Vector<Extensao> resultados = new Vector<Extensao>();
			while (rs.next()) {
				Extensao temp = new Extensao();
				temp.setIdExtensao(rs.getInt("idExtensao"));
				temp.setIdCasoDeUsoExtensao(rs.getInt("idCasoDeUso"));
				temp.setIdCasoDeUsoMaster(rs.getInt("idCasoDeUsoMaster"));
				temp.setIdFluxoPosicao(rs.getInt("idFluxoPosicao"));
				temp.setTipoExtensao(rs.getString("tipoExtensao"));
				temp.setInformacaoExtensao(rs.getString("informacaoExtensao"));
				resultados.add(temp);
			}
			if (resultados.size() == 0)
				return null;
			return resultados;
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao getAllExtensao", e);
			return null;
		}
	}
	
	public Vector<Extensao> getAllExtensaoOfMaster(int idCasoDeUsoMaster) {
		conectar();
		String query = "SELECT * FROM Extensao WHERE idCasoDeUsoMaster = " + idCasoDeUsoMaster + " ;";
		//System.out.println("ExtensaoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			Vector<Extensao> resultados = new Vector<Extensao>();
			while (rs.next()) {
				Extensao temp = new Extensao();
				temp.setIdExtensao(rs.getInt("idExtensao"));
				temp.setIdCasoDeUsoExtensao(rs.getInt("idCasoDeUso"));
				temp.setIdCasoDeUsoMaster(rs.getInt("idCasoDeUsoMaster"));
				temp.setIdFluxoPosicao(rs.getInt("idFluxoPosicao"));
				temp.setTipoExtensao(rs.getString("tipoExtensao"));
				temp.setInformacaoExtensao(rs.getString("informacaoExtensao"));
				resultados.add(temp);
			}
			if (resultados.size() == 0)
				return null;
			return resultados;
		} catch (SQLException e) {
			imprimeErro("ExtensaoDAO		| Erro na funcao getAllExtensao", e);
			return null;
		}
	}
}