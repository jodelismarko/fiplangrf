package br.gov.mt.mti.fiplangrf.common.util;

public class DigitoVerificadorUtil {

	/**
	 * Retorna o digito verificador calculado pelo mod 11 conforme regras de negócio.
	 */
	public static final int calculaDigitoVerificadorMOD11(final String snum)
			throws Exception {
        int result = 0;
        int idxMod11 = 2;
        for (int i = 1; i <= snum.length(); i++) {
            result += ((idxMod11++) * UtilidadesConversao.charToInt(snum.charAt(snum.length() - i)));
        }
        // O somatório é dividido por 11. Queremos o resto desta divisão.
        int resto = result % 11;
        
        // Para restos 0, 1, ou 10, o dígito verificador é 0.
        if (resto <=1) return 0;
        
        // Subtraímos o resto de 11.
        return 11 - resto;
	}
	
	/**
     * Retorna o digito verificador calculado para o CNPJ.
     */
    public static final int calculaDigitoVerificadorCNPJ(final String snum)
            throws Exception {
        int result = 0;
        int idxMod11 = 2;
        for (int i = 1; i <= snum.length(); i++) {
            result += ((idxMod11++) * UtilidadesConversao.charToInt(snum.charAt(snum.length() - i)));
            if (idxMod11 > 9) idxMod11 = 2;
        }
        // O somatório é dividido por 11. Queremos o resto desta divisão.
        int resto = result % 11;
        
        // Para restos 0, 1, ou 10, o dígito verificador é 0.
        if (resto <=1) return 0;
        
        // Subtraímos o resto de 11.
        return 11 - resto;
    }
}
