package nfracgen.util;

/**
 * Strings utilities
 */
public class StringUtils {

	/**
	 * Verifica se a String � vazia ou composta apenas por espa�os em branco
	 * @param str String a ser verificada
	 * @return true se vazia; false caso contr�rio
	 */
	public static final boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}

		return str.trim().length() == 0;
	}

	/**
	 * Retorn o separador de linha usado pelo sistema operacional
	 * @return
	 */
	public static String newLine() {
		return System.getProperty("line.separator");
	}
}
