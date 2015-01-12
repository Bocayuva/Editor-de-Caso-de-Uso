package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import model.Projeto;

class ProjetoDao {

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
	
	public void insere(Projeto Projeto) {//CARLOS
		conectar();
		String query = "INSERT INTO Projeto (nomeProjeto, dataCriacao, dataModificacao)VALUES('"+ Projeto.getNomeProjeto() + "', date('now'), date('now'));";
		//System.out.println("ProjetoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao inserir Projeto", e);
		} finally {
			fechar();
		}
	}

	public void apagar(int idProjeto) {//CARLOS
		conectar();
		String query = "DELETE FROM Projeto WHERE idProjeto = " + idProjeto + ";";
		//System.out.println("ProjetoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar Projetos", e);
		} finally {
			fechar();
		}
	}

	public void atualizar(Projeto Projeto) {//CARLOS
		conectar();
		String query = "UPDATE Projeto SET nomeProjeto = '"+Projeto.getNomeProjeto()+"', dataModificacao = date('now') WHERE  idProjeto = "+Projeto.getIdProjeto()+" ;";
		//System.out.println("ProjetoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro atualizar", e);
		} finally {
			fechar();
		}
	}
	
	public void atualizarDataModificacao(int idProjeto) {//CARLOS
		conectar();
		String query = "UPDATE Projeto SET dataModificacao = date('now') WHERE  idProjeto = "+idProjeto+" ;";
		//System.out.println("ProjetoDao	| query: "+query);
		try {
			comando.executeUpdate(query);
		} catch (SQLException e) {
			imprimeErro("Erro atualizarDataModificacao(int idProjeto)", e);
		} finally {
			fechar();
		}
	}

	public Projeto buscar(Projeto Projeto) {//CARLOS
		conectar();
		String query;
		if(Projeto.getIdProjeto() != -1)
			query = "SELECT * FROM Projeto WHERE idProjeto = " + Projeto.getIdProjeto() + ";";
		else
			query = "SELECT * FROM Projeto WHERE nomeProjeto = '" + Projeto.getNomeProjeto() + "' ;";
		//System.out.println("ProjetoDao	| query: "+query);
		try {
			ResultSet rs = comando.executeQuery(query);
			Projeto temp = new Projeto();
			temp.setIdProjeto(rs.getInt("idProjeto"));
			temp.setNomeProjeto(rs.getString("nomeProjeto"));
			temp.setDataCriacao(rs.getDate("dataCriacao"));
			temp.setDataModificacao(rs.getDate("dataModificacao"));
			return temp;
		} catch (SQLException e) {
			return null;
		} finally {
			fechar();
		}
	}

	public Vector<Projeto> buscarTodos() {//CARLOS
		conectar();
		String query = "SELECT * FROM Projeto";
		//System.out.println("ProjetoDao	| query: "+query);
		try {
			Vector<Projeto> resultados = new Vector<Projeto>();
			ResultSet rs = comando.executeQuery(query);
			while (rs.next()) {
				Projeto temp = new Projeto();
				// pega todos os atributos da Projeto
				temp.setIdProjeto(rs.getInt("idProjeto"));
				temp.setNomeProjeto(rs.getString("nomeProjeto"));

				DateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date  date = null;
				try {
					date = formatter.parse(rs.getString("dataCriacao"));
					temp.setDataCriacao(date);
					date = null;
					date = formatter.parse(rs.getString("dataModificacao"));
					temp.setDataModificacao(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar Projetos", e);
			return null;
		} finally {
			fechar();
		}
	}
}