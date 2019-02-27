package br.gov.mt.mti.fiplangrf.common.util;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

	
	public static String decodeString(String val, String from, String to) throws UnsupportedEncodingException{	

		byte[] bytes;
	
			bytes = val.getBytes(from);
			String s2 = new String(bytes, to);
			
			return s2;
		
	}
}