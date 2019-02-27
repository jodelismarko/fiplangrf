package br.gov.mt.mti.fiplangrf.util.formatter;

import java.text.SimpleDateFormat;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldFormatter;

public class DateTimeFormatter implements FieldFormatter {
		
	@Override
	public Object getFormattedValue(Object value, String pattern) {
		if (value != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy - HH:mm:ss");
			return sdf.format(value);			
		}
		
		return null;
	}

}
