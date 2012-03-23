package no.ntnu.fp.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import no.ntnu.fp.model.Tools;


public class KalSysLogin extends DatabaseConnection{

	public boolean authenticate(String username, String password){
		
		PreparedStatement pst = null;
		this.initializeDB();
		try {
			pst = conn.prepareStatement("select * from user where username=? AND password=?");
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rs = this.makeSingleQuery(pst);
			int count = 0;
			while(rs.next()){
				count++;
			}
			if(count == 1){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	public boolean insertUser(String username, String password, String email, String name, int phone){
		PreparedStatement pst = null;
		this.initializeDB();
		try {
			pst = conn.prepareStatement("insert into user values(?, ?, ?, ?, ? ) ");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, name);
			pst.setInt(5, phone);
			
			
			int rs = this.makeUpdate(pst);
			
			if(rs == 0) return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	
	
	
}
