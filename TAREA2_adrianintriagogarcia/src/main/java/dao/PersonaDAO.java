package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Persona;

public class PersonaDAO {

		private Persona mapRow(ResultSet rs) {
			try {
				return new Persona(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("nacionalidad")
						);
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public List<Persona> findAll(){
			List<Persona> list = new ArrayList<>();
			String sql ="SELECT * FROM persona";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery()){
				
				while(rs.next()) {
					Persona p =mapRow(rs);
					if(p !=null) list.add(p);
				}
			}catch(SQLException e ) {e.printStackTrace();}
			
			return list;
		}
		
		public Persona findById(int id) {
			String sql = "SELECT *FROM persona WHERE id=?";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt= con.prepareStatement(sql)){
				
				stmt.setInt (1,id);
				ResultSet rs= stmt.executeQuery();
				
				if(rs.next()) return mapRow(rs);
				
			}catch(SQLException e) {e.printStackTrace();}
			
			return null;
		}
		public void insert(Persona p) {
			String sql = "INSERT INTO persona (id,nombre,email,nacionalidad)VALUES (?,?,?,?)";
			
			try(Connection con =ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
				
				stmt.setInt(1,p.getId());
				stmt.setString(2, p.getNombre());
				stmt.setString(3, p.getEmail());
				stmt.setString(4,p.getNacionalidad());
				stmt.executeUpdate();
			}catch(SQLException e) { e.printStackTrace();}
			
		}
			public void update(Persona p) {
			    String sql = "UPDATE persona SET nombre=?, email=?, nacionalidad=? WHERE id=?";
			    
			    try (Connection con = ConnectionManager.getConnection();
			         PreparedStatement stmt = con.prepareStatement(sql)) {

			        stmt.setString(1, p.getNombre());
			        stmt.setString(2, p.getEmail());
			        stmt.setString(3, p.getNacionalidad());
			        stmt.setInt(4, p.getId());
			        stmt.executeUpdate();

			    } catch (SQLException e) {
			        e.printStackTrace();
			
					
		}
			}
		
		public void delete(int id) {
			String sql="DELETE FROM persona WHERE id=?";
			
			try(Connection con =ConnectionManager.getConnection();
					PreparedStatement stmt=con.prepareStatement(sql)){
				
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}catch(SQLException e) {e.printStackTrace();}
		}
		
}
