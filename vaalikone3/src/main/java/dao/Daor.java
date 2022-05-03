package dao;
import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Ehdokas;


import java.sql.Connection;

public class Daor {
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	public Daor(String url, String user, String pass) {
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
	

	public ArrayList<Ehdokas> readAllEhdokkaat() {
		ArrayList<Ehdokas> list=new ArrayList<Ehdokas>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from ehdokkaat");
			while (RS.next()){
				Ehdokas f=new Ehdokas();
				f.setId(RS.getInt("EHDOKAS_ID"));
				f.setEtunimi(RS.getString("ETUNIMI"));
				f.setKotipaikkakunta(RS.getString("KOTIPAIKKAKUNTA"));
				f.setIka(RS.getInt("IKA"));
				f.setAmmatti(RS.getString("AMMATTI"));
				f.setKommentti(RS.getString("KOMMENTTI"));
				f.setEhdokas_num(RS.getString("EHDOKAS_NUM"));
				list.add(f);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	
	
}