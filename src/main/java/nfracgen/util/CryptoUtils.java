package nfracgen.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utilit�rios para criptografia
 */
public class CryptoUtils {

	/**
	 * Criptografa dados usando o algoritmo AES.
	 * @param keyBytes Bytes da chave
	 * @param dataBytes Bytes dos dados
	 * @return Bytes criptografados
	 * @throws Exception
	 */
	public static byte[] encryptAES(byte[] keyBytes, byte[] dataBytes) throws Exception {
		return handleAES(keyBytes, dataBytes, Cipher.ENCRYPT_MODE);
	}

	/**
	 * Descriptografa dados usando o algoritmo AES.
	 * @param keyBytes Bytes da chave
	 * @param dataBytes Bytes dos dados
	 * @return Bytes descriptografados
	 * @throws Exception
	 */
	public static byte[] decryptAES(byte[] keyBytes, byte[] dataBytes) throws Exception {
		return handleAES(keyBytes, dataBytes, Cipher.DECRYPT_MODE);
	}

	/**
	 * @param keyBytes Bytes da chave
	 * @param dataBytes Bytes dos dados
	 * @param mode Modo: criptografia ou descriptografia. Aceita Cipher.ENCRYPT_MODE ou Cipher.DECRYPT_MODE
	 * @return Bytes criptografados ou descriptografados
	 * @throws Exception
	 */
	private static byte[] handleAES(byte[] keyBytes, byte[] dataBytes, int mode) throws Exception {
		// Verifica validade da chave
		if (keyBytes == null || keyBytes.length != 16) {
			throw new Exception("A chave � inv�lida");
		}

		// Verifica validade dos dados
		if (dataBytes == null) {
			throw new Exception("N�o existem dados");
		}

		// Cria um objeto que representa a chave AES
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

		// Instancia o algoritmo AES
		Cipher cipher = Cipher.getInstance("AES");

		// Inicializa o algoritmo
		cipher.init(mode, key);

		// Executa o processo de criptografia ou descriptografia
		return cipher.doFinal(dataBytes);
	}
}
