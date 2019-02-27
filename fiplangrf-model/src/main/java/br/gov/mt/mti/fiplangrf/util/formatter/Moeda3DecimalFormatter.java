package br.gov.mt.mti.fiplangrf.util.formatter;

import java.text.DecimalFormat;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldFormatter;

public class Moeda3DecimalFormatter  implements FieldFormatter {
	@Override
	public Object getFormattedValue(Object value, String pattern) {
		if (value != null) {
			DecimalFormat sdf = new DecimalFormat("#,###,###0.000");

			return sdf.format(value);			
		}
		return null;
	}
}