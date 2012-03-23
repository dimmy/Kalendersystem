package no.ntnu.fp.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools {
	

		 public static String SHAHash(String code) throws NoSuchAlgorithmException {
			 code = code + "yeah";
		     MessageDigest md = MessageDigest.getInstance("SHA-256");
		  md.update(code.getBytes());
		  
		  byte byteData[] = md.digest();
		  
		  //convert the byte to hex format method 1
		      StringBuffer sb = new StringBuffer();
		      for (int i = 0; i < byteData.length; i++) {
		       sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		      }
		  
		   return sb.toString();
		 }
		 
		 public static void main(String[] args) throws NoSuchAlgorithmException{
		  System.out.println(SHAHash("crffr"));
		 }
	}

