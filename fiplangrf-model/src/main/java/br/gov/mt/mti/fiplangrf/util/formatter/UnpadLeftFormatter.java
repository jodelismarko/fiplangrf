package br.gov.mt.mti.fiplangrf.util.formatter;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldFormatterCriteria;

public class UnpadLeftFormatter  implements FieldFormatterCriteria {
	
	@Override
	public Object getFormattedValue(Object value) {
		if (value != null) {
			if(value instanceof String) {
				return ((String) value).replaceFirst("^0+(?!$)", "");
			}						
		}
		return null;
	}
}