package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.LoginDao;


public class LoginDao {
	
	
	String sql = "select * from login where name=? and pass=?";
	
	public LoginDao() {
		
	}
	
	
	public boolean check(String name, String pass)
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
			PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, name);
	        st.setString(2, pass);
	 
	        ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return false;
		
	}
	
	

}
