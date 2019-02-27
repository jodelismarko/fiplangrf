package br.gov.mt.mti.fiplangrf.common.util;

import org.apache.commons.lang.StringUtils;

public class Validacao {

	public static final boolean validarCPFCNPJ(final String documento) {
		String limpo = FormataNumero.onlyNumericDigits(documento);
		
		if(limpo.length()==11){
			return cpf(documento);
		} else if(limpo.length()==14){
			return cnpj(documento);
		} else{
			return false;
		}
		
	}
	
	public static final boolean cpf(final String cpfTeste) {

		String limpo = FormataNumero.onlyNumericDigits(cpfTeste);

		if ((limpo.length() > 11) || (limpo.length() == 0) || (!limpo.matches("[0-9]*")) || Long.valueOf(limpo)==0) {
			return false;
		}

		limpo = StringUtils.leftPad(limpo, 11, "0");
		String digito1 = "";
		String digito2 = "";
		try {
			digito1 += DigitoVerificadorUtil.calculaDigitoVerificadorMOD11(limpo.substring(0, 9));
			digito2 += DigitoVerificadorUtil.calculaDigitoVerificadorMOD11(limpo.substring(0, 10));

			if ((!limpo.substring(9, 10).equals(digito1)) || (!limpo.substring(10, 11).equals(digito2)))
				return false;

		} catch (Exception e) {
			System.out.println("Validação: Erro no cálculo do dígito verificador: "+ e.getMessage());
			e.printStackTrace();
		}

		return true;
	}
	
	public static final boolean cnpj(final String cnpjTeste) {

		String limpo = FormataNumero.onlyNumericDigits(cnpjTeste);

		if ((limpo.length() > 14) || (limpo.length() == 0) || (!limpo.matches("[0-9]*")) || Long.valueOf(limpo)==0) {
			return false;
		}

		limpo = StringUtils.leftPad(limpo, 14, "0");
		String digito1 = "";
		String digito2 = "";
		try {
			digito1 += DigitoVerificadorUtil.calculaDigitoVerificadorCNPJ(limpo.substring(0, 12));
			digito2 += DigitoVerificadorUtil.calculaDigitoVerificadorCNPJ(limpo.substring(0, 13));

			if ((!limpo.substring(12, 13).equals(digito1)) || (!limpo.substring(13, 14).equals(digito2)))
				return false;

		} catch (Exception e) {
			System.out.println("Validação: Erro no cálculo do dígito verificador: "+ e.getMessage());
			e.printStackTrace();
		}

		return true;
	}
}
