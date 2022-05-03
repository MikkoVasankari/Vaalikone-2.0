package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.kysymys;

public class kyselydao {
	private String url;
	private String user;
	private String passwd;
	private Connection connection;

	public kyselydao(String url, String user, String passwd) {
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

	public ArrayList<kysymys> readAllKysymykset() {
		ArrayList<kysymys> lista = new ArrayList<kysymys>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet RS = stmt.executeQuery("select * from kysymykset");
			while (RS.next()) {
				kysymys k = new kysymys();
				k.setId(RS.getInt("KYSYMYS_ID"));
				k.setKysymys(RS.getString("KYSYMYS"));
				lista.add(k);
			}
			return lista;
		} catch (SQLException e) {
			return null;
		}
	}

}
