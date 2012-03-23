package no.ntnu.fp.storage;

import java.security.NoSuchAlgorithmException;

import no.ntnu.fp.model.Tools;

public class TestingLogin {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		
		KalSysLogin login = new KalSysLogin();
		
		boolean answer = false;
//		answer = login.insertUser("test", Tools.SHAHash("password"), "test@admin.no", "admin", 81549300);
		
		answer = login.authenticate("test", Tools.SHAHash("password"));
		
//		String answer = Tools.SHAHash("password");
		System.out.println(answer);
	}
	
}
