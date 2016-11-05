package nfracgen.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nfracgen.dao.DAOProperties;

public class ConnectionFactory {

	/**
	 * Abre uma nova conex�o com o banco de dados
	 * @return Nova conex�o
	 * @throws SQLException
	 */
	public static Connection openConnection() throws SQLException {
		// Cria a URL de conex�o com base nas configura��es do arquivo
		String url = String.format("jdbc:mysql://%s:%d/%s", DAOProperties.getDBDAOServerName(), DAOProperties.getDBDAOPort(), DAOProperties.getDBDAODBName());

		// Cria uma nova conex�o e retorna
		return DriverManager.getConnection (url, DAOProperties.getDBDAOUserName(), DAOProperties.getDBDAOPassword());
	}
}
