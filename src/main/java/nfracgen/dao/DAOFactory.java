package nfracgen.dao;

public class DAOFactory {

	/**
	 * Instancia uma classe que implementa a interface SenhaServicoDAO. A classe a ser implementada
	 * est� definida em um arquivo de configura��es
	 * @return Inst�ncia criada
	 */
	public static PowerLawCoefficientsDAO getSenhaServicoDAO() {
		try {
			// Obt�m o nome da classe a ser instanciada
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
