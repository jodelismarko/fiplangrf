package br.gov.mt.mti.fiplangrf.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtil {
	
	
	public static String getMD5(String str) throws NoSuchAlgorithmException{
		 MessageDigest m=MessageDigest.getInstance("MD5");
	       m.update(str.getBytes(),0,str.length());	      
	       return new BigInteger(1,m.digest()).toString(16);
	}
	
	
	public static String getInternalKey() {
			//Pode mudar se quiser
	       return "vsdrgvedrg2435t6v";
	}
	
	
	public static String getInternalEncrypt(String str){
			
	       try {
			return CryptUtil.getMD5(str+CryptUtil.getInternalKey());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return null;
	}


}