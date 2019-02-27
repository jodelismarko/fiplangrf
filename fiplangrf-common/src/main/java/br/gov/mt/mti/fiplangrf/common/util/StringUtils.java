package br.gov.mt.mti.fiplangrf.common.util;

public class StringUtils extends br.gov.mt.cepromat.ceprofw.common.util.StringUtils {

	public static String formataCNAE(Long valor) {

		// Se for null, sai com formato vazio.
		if (valor == null)
			return "";

		// É negativo? (Não pode, mas quem sabe...)
		if (valor.intValue() < 0)
			valor = Long.valueOf(valor.longValue() * (-1));

		// Trabalhamos com string.
		String formatado = valor.toString();

		// O comprimento é maior do que um CEP pode ser? (Também não pode,
		// mas...)
		if (formatado.length() > 7)
			return "";

		// Se não tem comprimento suficiente (7), coloca zeros à  esquerda.
		formatado = leftPad(formatado, 7, '0');
		// Agora está com tamanho suficiente! Colocamos a máscara.
		return formatado.substring(0, 2) + "." + formatado.substring(2, 4) + "-" + formatado.substring(4, 5) + "-"
				+ formatado.substring(5, 7);
	}

	public static Long sanityzeNumericString(String str) {
		String sanitized = str.replaceAll("[^0-9]", "");
		if (StringUtils.isNumeric(sanitized)) {
			return new Long(sanitized);
		}
		return null;
	}

	public static String formataNumrNaturezaJuridica(Long valor) {
		
		// Se for null, sai com formato vazio.
		if (valor == null)
			return "";

		// É negativo? (Não pode, mas quem sabe...)
		if (valor.intValue() < 0)
			valor = Long.valueOf(valor.longValue() * (-1));

		// Trabalhamos com string.
		String formatado = valor.toString();

		// O comprimento é maior do que um CEP pode ser? (Também não pode,
		// mas...)
		if (formatado.length() > 4)
			return "";

		// Se não tem comprimento suficiente (4), coloca zeros à  esquerda.
		formatado = leftPad(formatado, 4, '0');
		// Agora está com tamanho suficiente! Colocamos a máscara.
		return formatado.substring(0, 3) + "-" + formatado.substring(3, 4);
	}
}
