package br.com.fracgen.dao;

public class DAOFactory {

	/**
	 * Instancia uma classe que implementa a interface SenhaServicoDAO. A classe a ser implementada
	 * está definida em um arquivo de configurações
	 * @return Instância criada
	 */
	public static PowerLawCoefficientsDAO getSenhaServicoDAO() {
		try {
			// Obtém o nome da classe a ser instanciada
			String daoClass = DAOProperties.getDAOClassName();

			// Instancia o objeto e retorna
			return (PowerLawCoefficientsDAO) Class.forName(daoClass).newInstance();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// Se ocorreu algum erro, mostra a stacktrace e retorna null
			e.printStackTrace();
			return null;
		}
	}
}
