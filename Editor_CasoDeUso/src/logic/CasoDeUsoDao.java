package logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import model.CasoDeUso;

class CasoDeUsoDao {

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
	
	// SET
	
	public void insere(CasoDeUso CasoDeUso) {//CARLOS
		conectar();
		String sql = "INSERT INTO CasoDeUso(idProjeto,nomeCasoDeUso,objetivo,escopo"
				+ ",nivel,idAtorPrimario,idAtorSuporte,preCondicao,posCondicao)VALUES("
				+ CasoDeUso.getIdProjeto()
				+ " ,'"
				+ CasoDeUso.getNomeCasoDeUso()
				+ "','"
				+ CasoDeUso.getObjetivo()
				+ "','"
				+ CasoDeUso.getEscopo()
				+ "','"
				+ CasoDeUso.getNivel()
				+ "', "
				+ CasoDeUso.getIdAtorPrimario()
				+ " , "
				+ CasoDeUso.getIdAtorSuporte()
				+ " ,'"
				+ CasoDeUso.getPreCondicao()
				+ "','"
				+ CasoDeUso.getPosCondicao() + "');";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			comando.executeUpdate(sql);
		} catch (SQLException e) {
			imprimeErro("Erro ao inserir CasoDeUso", e);
		} finally {
			fechar();
		}
	}

	public void atualizar(CasoDeUso CasoDeUso) {//CARLOS
		conectar();
		String sql = "UPDATE CasoDeUso SET " 
				+ "   objetivo = '" + CasoDeUso.getObjetivo() 
				+ "', nomeCasoDeUso = '" + CasoDeUso.getNomeCasoDeUso() 
				+ "', escopo = '" + CasoDeUso.getEscopo() 
				+ "', nivel = '" + CasoDeUso.getNivel() 
				+ "', idAtorPrimario =  "	+ CasoDeUso.getIdAtorPrimario() 
				+ " , idAtorSuporte =  "	+ CasoDeUso.getIdAtorSuporte() 
				+ " , preCondicao = '" + CasoDeUso.getPreCondicao() 
				+ "', posCondicao = '" + CasoDeUso.getPosCondicao() 
				+ "'  WHERE  idProjeto =  " + CasoDeUso.getIdProjeto() 
				+ "   AND  idCasoDeUso = " + CasoDeUso.getIdCasoDeUso() + " ;";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			comando.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
	
	public void apagar(int idCasoDeUso, int idProjeto) {//CARLOS
		conectar();
		String sql = "DELETE FROM CasoDeUso WHERE idCasoDeUso = "+idCasoDeUso+" AND idProjeto = "+idProjeto+" ;";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			comando.executeUpdate(sql);
		} catch (SQLException e) {
			imprimeErro("Erro ao apagar CasoDeUsos", e);
		} finally {
			fechar();
		}
	}

	public void apagarAll(int idProjeto) {//CARLOS
		conectar();
		String sql = "DELETE FROM CasoDeUso WHERE idProjeto = "+idProjeto+";";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			comando.executeUpdate(sql);
		} catch (SQLException e) {
			imprimeErro("apagarAll", e);
		} finally {
			fechar();
		}
	}
	
	// GET
	
	public Vector<CasoDeUso> buscarTodos() {//CARLOS
		conectar();
		String sql = "SELECT * FROM CasoDeUso";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		
		try {
			ResultSet rs = comando.executeQuery(sql);
			Vector<CasoDeUso> resultados = new Vector<CasoDeUso>();
			while (rs.next()) {
				CasoDeUso temp = new CasoDeUso();
				temp.setIdProjeto(rs.getInt("idProjeto"));
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setNomeCasoDeUso(rs.getString("nomeCasoDeUso"));
				temp.setObjetivo(rs.getString("objetivo"));
				temp.setEscopo(rs.getString("escopo"));
				temp.setNivel(rs.getString("nivel"));
				temp.setIdAtorPrimario(rs.getInt("idAtorPrimario"));
				temp.setIdAtorSuporte(rs.getInt("idAtorSuporte"));
				temp.setPreCondicao(rs.getString("preCondicao"));
				temp.setPosCondicao(rs.getString("posCondicao"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			return null;
		}
	}

	public Vector<CasoDeUso> buscarTodosCasoDeUso(int idProjeto) {//CARLOS
		conectar();
		String sql = "SELECT idCasoDeUso, nomeCasoDeUso, escopo FROM CasoDeUso WHERE idProjeto = "+idProjeto+";";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			ResultSet rs = comando.executeQuery(sql);
			Vector<CasoDeUso> resultados = new Vector<CasoDeUso>();
			while (rs.next()) {
				CasoDeUso temp = new CasoDeUso();
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setNomeCasoDeUso(rs.getString("nomeCasoDeUso"));
				temp.setEscopo(rs.getString("escopo"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Vector<CasoDeUso> buscarTodosCasoDeUso(CasoDeUso cdu) {//yang
		conectar();
		String sql = "SELECT * FROM CasoDeUso WHERE idCasoDeUso <> "+cdu.getIdCasoDeUso()+" AND idProjeto = "+cdu.getIdProjeto()+";";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			ResultSet rs = comando.executeQuery(sql);
			Vector<CasoDeUso> resultados = new Vector<CasoDeUso>();
			while (rs.next()) {
				CasoDeUso temp = new CasoDeUso();
				temp.setIdProjeto(rs.getInt("idProjeto"));
				temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
				temp.setNomeCasoDeUso(rs.getString("nomeCasoDeUso"));
				temp.setObjetivo(rs.getString("objetivo"));
				temp.setEscopo(rs.getString("escopo"));
				temp.setNivel(rs.getString("nivel"));
				temp.setIdAtorPrimario(rs.getInt("idAtorPrimario"));
				temp.setIdAtorSuporte(rs.getInt("idAtorSuporte"));
				temp.setPreCondicao(rs.getString("preCondicao"));
				temp.setPosCondicao(rs.getString("posCondicao"));
				resultados.add(temp);
			}
			return resultados;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public CasoDeUso buscarById(int idCasoDeUso) {//CARLOS
		conectar();
		String sql = "SELECT * FROM CasoDeUso WHERE idCasoDeUso = " + idCasoDeUso + ";";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			ResultSet rs = comando.executeQuery(sql);
			rs.next();
			CasoDeUso temp = new CasoDeUso();
			temp.setIdProjeto(rs.getInt("idProjeto"));
			temp.setIdCasoDeUso(rs.getInt("idCasoDeUso"));
			temp.setNomeCasoDeUso(rs.getString("nomeCasoDeUso"));
			temp.setObjetivo(rs.getString("objetivo"));
			temp.setEscopo(rs.getString("escopo"));
			temp.setNivel(rs.getString("nivel"));
			temp.setIdAtorPrimario(rs.getInt("idAtorPrimario"));
			temp.setIdAtorSuporte(rs.getInt("idAtorSuporte"));
			temp.setPreCondicao(rs.getString("preCondicao"));
			temp.setPosCondicao(rs.getString("posCondicao"));
			return temp;
		} catch (SQLException e) {
			return null;
		}
	}

	public int buscarIDCasoDeUso(int idProjeto, String nomeCasoDeUso) {//CARLOS
		conectar();
		String sql = "SELECT idCasoDeUso FROM CasoDeUso WHERE nomeCasoDeUso = '"
				+ nomeCasoDeUso + "' AND idProjeto = " + idProjeto + " ;";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			ResultSet rs = comando.executeQuery(sql);
			rs.next();
			return rs.getInt("idCasoDeUso");
		} catch (SQLException e) {
			return 0;
		}
	}

	public int buscarQtdCasoDeUso(int idProjeto) {//CARLOS
		conectar();
		String sql = "SELECT COUNT(*) FROM CasoDeUso WHERE idProjeto = "+ idProjeto + " ;";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			ResultSet rs = comando.executeQuery(sql);
			rs.next();
			return Integer.parseInt(rs.getString("count(*)"));
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int buscarQtdEscopoCasoDeUso(int idProjeto) {//CARLOS
		conectar();
		String sql = "SELECT DISTINCT COUNT(escopo) FROM CasoDeUso WHERE idProjeto = "+idProjeto+";";
		//System.out.println("CasoDeUsoDao	| sql: "+sql);
		try {
			ResultSet rs = comando.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			return 0;
		}
	}
}