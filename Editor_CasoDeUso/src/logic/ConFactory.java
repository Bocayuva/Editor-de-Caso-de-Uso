package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


class ConFactory {
	
	private static Connection con =  null;
		
	public static Connection conexao() throws ClassNotFoundException,SQLException {
		if(con == null){
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:EUCdataBase.db";
			con = DriverManager.getConnection(dbURL);
			createTables();
		}
		return con;
	}
	public static void nullConnection() {
		con = null;
	}
	
	private static void createTables() throws SQLException {
		Statement comando = con.createStatement();
		String query = " CREATE TABLE if not exists 'Ator' ('idProjeto' INTEGER REFERENCES 'Projeto' ('idProjeto') ON DELETE CASCADE ON UPDATE CASCADE, 'idAtor' INTEGER PRIMARY KEY, 'nomeAtor' VARCHAR (20) NOT NULL);"
				+ " CREATE TABLE if not exists 'CasoDeUso' ('idProjeto' INTEGER REFERENCES 'Projeto' ('idProjeto') ON DELETE CASCADE ON UPDATE CASCADE, 'idCasoDeUso' INTEGER PRIMARY KEY AUTOINCREMENT, 'nomeCasoDeUso' VARCHAR (50) NOT NULL, 'objetivo' VARCHAR (50) NOT NULL, 'escopo' VARCHAR (50) NOT NULL, 'nivel' VARCHAR (50) NOT NULL, 'idAtorPrimario' INTEGER REFERENCES 'Ator' ('idAtor') ON DELETE CASCADE ON UPDATE CASCADE, 'idAtorSuporte' INTEGER REFERENCES 'Ator' ('idAtor') ON DELETE CASCADE ON UPDATE CASCADE, 'preCondicao' VARCHAR (50), 'posCondicao' VARCHAR (50));"
				+ " CREATE TABLE if not exists 'Extensao' ('idExtensao' INTEGER PRIMARY KEY AUTOINCREMENT, 'idCasoDeUso' INTEGER REFERENCES 'CasoDeUso' ('idCasoDeUso') ON DELETE CASCADE ON UPDATE CASCADE, 'idCasoDeUsoMaster' INTEGER REFERENCES 'CasoDeUso' ('idCasoDeUso') ON DELETE CASCADE ON UPDATE CASCADE, 'tipoExtensao' TEXT NOT NULL, 'informacaoExtensao' TEXT NOT NULL, 'idFluxoPosicao' INTEGER REFERENCES 'Fluxo' ('idFluxo') ON DELETE CASCADE ON UPDATE CASCADE);"
				+ " CREATE TABLE if not exists 'Fluxo' ('idFluxo' INTEGER PRIMARY KEY AUTOINCREMENT, 'idCasoDeUso' INTEGER NOT NULL REFERENCES 'CasoDeUso' ('idCasoDeUso') ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE, 'idFluxoMaster' INTEGER REFERENCES 'Fluxo' ('idFluxo') ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE, 'posicaoFluxo' INTEGER NOT NULL, 'informacaoFluxo' VARCHAR (50) NOT NULL, 'tipoAlternativo' BOOLEAN); "
				+ " CREATE TABLE if not exists 'Projeto' ('idProjeto' INTEGER PRIMARY KEY AUTOINCREMENT, 'nomeProjeto' VARCHAR (20) NOT NULL UNIQUE, 'dataCriacao' DATE NOT NULL, 'dataModificacao' DATE NOT NULL);";
		comando.executeUpdate(query);
		comando.close();
	}
}