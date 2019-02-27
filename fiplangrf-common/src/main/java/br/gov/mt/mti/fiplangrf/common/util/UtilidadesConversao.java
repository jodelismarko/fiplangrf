package br.gov.mt.mti.fiplangrf.common.util;

public final class UtilidadesConversao {

	/**
	 * Transforma um char em seu correspondente inteiro
	 */
	public static final int charToInt(final char chNum) throws Exception {
		switch (chNum) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		default:
			throw new Exception("O caracter " + chNum
					+ " não é um número inteiro de [0 a 9]!");
		}
	}
}