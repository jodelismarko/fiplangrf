package br.gov.mt.mti.fiplangrf.common.util;

import org.apache.commons.lang.StringUtils;

public class Sanityzer {
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String sanityzeNumericString(String str){
		String sanitized = str.replaceAll("[^0-9]", "");
		if(StringUtils.isNumeric(sanitized)){
			sanitized = Long.valueOf(sanitized).toString();
			return sanitized;
		}
		return null;
	}
	
	public static String sanityze(String str){
		if(str == null)
			return null;
		str = str.trim();
		str =  removeMultipleSpaces( str);		
		return str;
	}
	public static String removeMultipleSpaces(String str){
		
		str = str.replace("  ", " ");	
		if(str.contains("  ")){
			return removeMultipleSpaces(str);
		}
		return str;
	}
}
