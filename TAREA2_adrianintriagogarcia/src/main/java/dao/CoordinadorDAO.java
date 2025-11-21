package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Coordinador;

public class CoordinadorDAO {
	 	
		private static final String TABLE="coordinador";
		
		private Coordinador mapRow(ResultSet rs) {
			try {
				Coordinador coordinador =new Coordinador();
				coordinador.setId(rs.getInt("id"));
				coordinador.setNombre(rs.getString("nombre"));
				coordinador.setEmail(rs.getString("email"));
				coordinador.setNacionalidad(rs.getString("nacionalidad"));
				coordinador.setSenior(rs.getBoolean("senior"));
				coordinador.setFechaSenior(rs.getObject("fecha_senior",LocalDate.class));
				return coordinador;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public void insert (Coordinador c) {
			String sql="INSERT INTO" +TABLE +"(id,nombre,email,nacionalidad,senior,fecha_senior) VALUE(?,?,?,?,?,?)";
			try(Connection con =ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
				stmt.setInt(1, c.getId());
	            stmt.setString(2, c.getNombre());
	            stmt.setString(3, c.getEmail());
	            stmt.setString(4, c.getNacionalidad());
	            stmt.setBoolean(5, c.isSenior());
	            stmt.setDate(6, java.sql.Date.valueOf(c.getFechaSenior()));
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
			}
		}
		
		 public void update(Coordinador c) {
		        String sql = "UPDATE " + TABLE + " SET nombre = ?, email = ?, nacionalidad = ?, senior = ?, fecha_senior = ? WHERE id = ?";
		        try (Connection con = ConnectionManager.getConnection();
		             PreparedStatement stmt = con.prepareStatement(sql)) {
		            stmt.setString(1, c.getNombre());
		            stmt.setString(2, c.getEmail());
		            stmt.setString(3, c.getNacionalidad());
		            stmt.setBoolean(4, c.isSenior());
		            stmt.setDate(5, java.sql.Date.valueOf(c.getFechaSenior()));
		            stmt.setInt(6, c.getId());
		            stmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    
		    public void delete(int id) {
		        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
		        try (Connection con = ConnectionManager.getConnection();
		             PreparedStatement stmt = con.prepareStatement(sql)) {
		            stmt.setInt(1, id);
		            stmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    
		    public Coordinador findById(int id) {
		        String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
		        try (Connection con = ConnectionManager.getConnection();
		             PreparedStatement stmt = con.prepareStatement(sql)) {
		            stmt.setInt(1, id);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		                return mapRow(rs);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return null;
		    }

		    
		    public List<Coordinador> findAll() {
		        String sql = "SELECT * FROM " + TABLE;
		        List<Coordinador> lista = new ArrayList<>();
		        try (Connection con = ConnectionManager.getConnection();
		             PreparedStatement stmt = con.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                Coordinador coordinador = mapRow(rs);
		                if (coordinador != null) {
		                    lista.add(coordinador);
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return lista;
		    }
		}
		
	

