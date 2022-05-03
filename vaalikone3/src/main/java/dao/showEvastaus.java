package dao;

import java.sql.DriverManager;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import data.ehdokasVastaukset;
import data.Ehdokas;


import java.sql.Connection;

public class showEvastaus {
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	public showEvastaus(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	            conn = DriverManager.getConnection(url, user, pass);
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	

	public ArrayList<ehdokasVastaukset> readAllVastaukset() {
		ArrayList<ehdokasVastaukset> list=new ArrayList<ehdokasVastaukset>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from vastaukset");
			while (RS.next()){
				ehdokasVastaukset f=new ehdokasVastaukset();
				f.setEhdokas_id(RS.getInt("EHDOKAS_ID"));
				f.setKysymys_id(RS.getInt("KYSYMYS_ID"));
				f.setVastaus(RS.getString("VASTAUS"));
				f.setKommentti(RS.getString("KOMMENTTI"));
				f.setEhdokas_num(RS.getInt("EHDOKAS_NUM"));

				list.add(f);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
}	
	

