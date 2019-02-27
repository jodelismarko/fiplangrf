package br.gov.mt.mti.fiplangrf.util.formatter;

import java.text.DecimalFormat;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldFormatter;

public class MoedaFormatter  implements FieldFormatter {
	@Override
	public Object getFormattedValue(Object value, String pattern) {
		if (value != null) {
			DecimalFormat sdf = new DecimalFormat("#,###,##0.00");

			return sdf.format(value);			
		}
		return null;
	}
}