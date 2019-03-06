package br.gov.mt.mti.fiplangrf.common.util.formatter;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Formatter {

	public static String formatter(String mascara, String valor) {

		MaskFormatter mask;
		try {
			mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String formatter(String mascara, Integer valor) {

		MaskFormatter mask;
		try {
			mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(valor);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
