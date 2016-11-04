package br.com.fracgen.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.fracgen.dao.DAOProperties;

public class ConnectionFactory {

	/**
	 * Abre uma nova conexão com o banco de dados
	 * @return Nova conexão
	 * @throws SQLException
	 */
	public static Connection openConnection() throws SQLException {
		// Cria a URL de conexão com base nas configurações do arquivo
		String url = String.format("jdbc:mysql://%s:%d/%s", DAOProperties.getDBDAOServerName(), DAOProperties.getDBDAOPort(), DAOProperties.getDBDAODBName());

		// Cria uma nova conexão e retorna
		return DriverManager.getConnection (url, DAOProperties.getDBDAOUserName(), DAOProperties.getDBDAOPassword());
	}
}
