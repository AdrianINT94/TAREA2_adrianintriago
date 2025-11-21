package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import db.ConnectionManager;
import model.Credencial;

public class CredencialDAO {
	
		private static final String TABLE ="credencial";
		
		
		private Credencial mapRow(ResultSet rs) {
			try {
				Credencial credencial = new Credencial();
				credencial.setUsername(rs.getString("nombreusuario"));
				credencial.setPassword(rs.getString("password"));
				credencial.setRol(rs.getString("rol"));
				return credencial;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public void insert (Credencial credencial) {
			String sql ="INSERT INTO" +TABLE + "(nombreusuario, password, rol) VALUES (?,?,?)";
			try(Connection con =ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
				stmt.setString(1,credencial.getUsername());
				stmt.setString(2, credencial.getPassword());
				stmt.setString(3,credencial.getRol());
				stmt.executeUpdate();
			}catch(SQLException e) {e.printStackTrace();}
		}
		
		public void update (Credencial credencial) {
			String sql ="UPDATE " +TABLE +" SET password = ?, rol = ? WHERE nombreusuario = ?";
			try(Connection con = ConnectionManager.getConnection(); 
				PreparedStatement stmt =con.prepareStatement(sql) ){
				stmt.setString(1,credencial.getPassword());
				stmt.setString(2, credencial.getRol());
				stmt.setString(3,credencial.getUsername());
				stmt.executeUpdate();
			}catch(SQLException e) {e.printStackTrace();}
			
		}
		
		public void delete(String username) {
			String sql ="DELETE FROM " +TABLE + " WHERE  nombreusuario = ?";
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql) ){
				stmt.setString(1,username);
				stmt.executeUpdate();
				
			}catch(SQLException e) {e.printStackTrace(); }
		}
			
		public Credencial findByUsername(String username) {
			String sql= "SELECT * FROM "+ TABLE + "WHERE nombreusuario = ?";
			try(Connection con= ConnectionManager.getConnection();
					PreparedStatement stmt=con.prepareStatement(sql)){
				stmt.setString(1, username);
				ResultSet rs= stmt.executeQuery();
				if(rs.next()) {
					return mapRow(rs);
				}
			}catch(SQLException e) {e.printStackTrace();}
		
		return null;
}
		
		
		public Credencial findById(int id) {
			String sql ="SELECT * FROM " +TABLE +" WHERE id = ?";
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
				stmt.setInt(1, id);
				ResultSet rs =stmt.executeQuery();
				return rs.next() ? mapRow(rs):null;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
			
			public List<Credencial> findAll(){
				String sql ="SELECT * FROM" +TABLE;
				List<Credencial> credenciales= new ArrayList<>();
				try(Connection con =ConnectionManager.getConnection();
						PreparedStatement stmt =con.prepareStatement(sql);
						ResultSet rs=stmt.executeQuery() ){
					while(rs.next()) {
						Credencial credencial =mapRow(rs);
						if(credencial !=null) credenciales.add(credencial);
					}
				}catch(SQLException e) {e.printStackTrace();}
				return credenciales;
			}
		
			
		
}
