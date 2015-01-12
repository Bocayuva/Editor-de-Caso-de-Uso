package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import model.Ator;

class AtorDao {

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

	private void imprimeErro(String msg,SQLException e) {
		System.err.println(msg);
		System.out.println(" Message: "+e);
		System.out.println(" CAUSE: "+e.getCause());
		e.printStackTrace();
	}

	/* public Methods */

	public void insere(Ator Ator) {
		conectar();
		String query = "INSERT INTO Ator (idProjeto,nomeAtor) VALUES ("
				+ Ator.getIdProjeto() + " , '" + Ator.getNomeAtor() + "' ) ;";
		// System.out.println("AtorDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao inserir Ator", e);
		} finally {
			fechar();
		}
	}

	public void atualizar(Ator Ator) {
		conectar();
		String query = "UPDATE Ator SET nomeAtor = '" + Ator.getNomeAtor()
				+ "' WHERE idProjeto =  " + Ator.getIdProjeto()
				+ " AND idAtor = " + Ator.getIdAtor() + " ;";
		// System.out.println("AtorDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

	public void apagar(Ator Ator) {
		conectar();
		String query = "DELETE FROM Ator WHERE idProjeto = "
				+ Ator.getIdProjeto() + " AND nomeAtor = '"
				+ Ator.getNomeAtor() + "' ;";
		// System.out.println("AtorDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Ators", e);
		} finally {
			fechar();
		}
	}

	public void apagarAll(int idProjeto) {
		conectar();
		String query = "DELETE FROM Ator WHERE idProjeto = " + idProjeto + ";";
		// System.out.println("AtorDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Ators", e);
		} finally {
			fechar();
		}
	}

	public int buscarQtdAtores(int idProjeto) {
		conectar();
		String query = "SELECT COUNT(*) FROM Ator WHERE idProjeto = "
				+ idProjeto + " ;";
		// System.out.println("AtorDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			int resultados = 0;
			while (rs.next()) {
				resultados = Integer.parseInt(rs.getString("count(*)"));
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Ator", e);
			return 0;
		}
	}

	public Ator buscarAtor(int idProjeto, String nomeAtor) {
		conectar();
		String query = "SELECT * FROM Ator WHERE idProjeto = " + idProjeto
				+ " AND nomeAtor = '" + nomeAtor + "' ;";
		// System.out.println("AtorDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			Ator resultados = new Ator();
			resultados.setIdProjeto(rs.getInt("idProjeto"));
			resultados.setIdAtor(rs.getInt("idAtor"));
			resultados.setNomeAtor(rs.getString("nomeAtor"));
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Ator Inexistente ou ja Existente", e);
			return null;
		}
	}

	public Ator buscarAtor(int idProjeto, int idAtor) {
		conectar();
		String query = "SELECT * FROM Ator WHERE idProjeto = " + idProjeto
				+ " AND idAtor = " + idAtor + " ;";
		// System.out.println("AtorDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			Ator resultados = new Ator();
			while (rs.next()) {
				resultados.setIdProjeto(rs.getInt("idProjeto"));
				resultados.setIdAtor(rs.getInt("idAtor"));
				resultados.setNomeAtor(rs.getString("nomeAtor"));
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Ator", e);
			return null;
		}

	}

	public Vector<Ator> buscarTodos(int idProjeto) {
		conectar();
		String query = "SELECT * FROM Ator WHERE idProjeto = " + idProjeto
				+ " ;";
		// System.out.println("AtorDao	| query: "+query);
		Vector<Ator> resultados = new Vector<Ator>();
		try {
			ResultSet rs = comando.executeQuery(query);
			while (rs.next()) {
				Ator temp = new Ator();
				// pega todos os atributos da Ator
				temp.setIdProjeto(rs.getInt("idProjeto"));
				temp.setIdAtor(rs.getInt("idAtor"));
				temp.setNomeAtor(rs.getString("nomeAtor"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Ators", e);
			return null;
		}
	}
}