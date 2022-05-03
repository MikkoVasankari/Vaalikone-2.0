package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.ehdokasVastaukset;

public class ehdokasVastauksetDAO {
	private String url;
	private String user;
	private String passwd;
	private Connection connection;
	
	
	public ehdokasVastauksetDAO(String url, String user, String passwd) {
		this.url = url;
		this.user = user;
		this.passwd = passwd;
	}
	
	public boolean getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					throw new SQLException(e);
				}
				connection = DriverManager.getConnection(url, user, passwd);
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public ArrayList<ehdokasVastaukset> readAllVastaukset() {
		ArrayList<ehdokasVastaukset> lista = new ArrayList<ehdokasVastaukset>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet RS = stmt.executeQuery("select * from vastaukset");
			while (RS.next()) {
				ehdokasVastaukset eV = new ehdokasVastaukset();
				eV.setEhdokas_id(RS.getInt("EHDOKAS_ID"));
				eV.setKysymys_id(RS.getInt("KYSYMYS_ID"));
				eV.setVastaus(RS.getString("VASTAUS"));
			
				lista.add(eV);
			}
			return lista;
		} catch (SQLException e) {
			return null;
		}
	}
	
}
