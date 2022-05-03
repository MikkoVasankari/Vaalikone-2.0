package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.kysymys;
import dao.Dao;
import java.sql.Connection;

public class Dao {
	private String url;
	private String name;
	private String pass;
	private Connection conn;
	
	public Dao(String url, String name, String pass) {
		this.url=url;
		this.name=name;
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
	            conn = DriverManager.getConnection(url, name, pass);
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public ArrayList<kysymys> readAllKysymys() {
		ArrayList<kysymys> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from kysymykset");
			while (RS.next()){
				kysymys kysymys = new kysymys();
				kysymys.setId(RS.getInt("KYSYMYS_ID"));
				kysymys.setKysymys(RS.getString("KYSYMYS"));
				list.add(kysymys);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	public ArrayList<kysymys> updateKysymys(kysymys f) {
		try {
			String sql="update kysymykset set KYSYMYS=? where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, f.getKysymys());
			pstmt.setInt(2, f.getId());
			pstmt.executeUpdate();
			return readAllKysymys();
		}
		catch(SQLException e) {
			return null;
		}
	}
	public ArrayList<kysymys> deleteKysymys(String id) {
		try {
			String sql="delete from kysymykset where kysymys_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllKysymys();
		}
		catch(SQLException e) {
			return null;
		}
	}

	public kysymys readKysymys(String id) {
		kysymys f=null;
		try {
			String sql="select * from kysymykset where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				f=new kysymys();
				f.setId(RS.getInt("KYSYMYS_ID"));
				f.setKysymys(RS.getString("KYSYMYS"));
			}
			return f;
		}
		catch(SQLException e) {
			return null;
		}
	}
	public ArrayList<kysymys> addKysymys(String id, String kysymys){
		try {
			
			String sql="INSERT INTO kysymykset VALUES(?, ?)";;

			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, kysymys);
			pstmt.executeUpdate();
			return readAllKysymys();
		}
		catch(SQLException e) {
			return null;
		}
	}
	
}
