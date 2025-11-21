package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Espectaculo;
import model.Numero;

public class EspectaculoDAO {

		private static final String TABLE ="espectaculo";
		private CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
		private NumeroDAO numeroDAO =new NumeroDAO();
		
		private Espectaculo mapRow(ResultSet rs) {
			try {
				Espectaculo e = new Espectaculo();
				e.setId(rs.getInt("id"));
				e.setNombre(rs.getString("nombre"));
				e.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
				e.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
				e.setCoordinador(coordinadorDAO.findById(rs.getInt("coordinador")));
				e.setNumeros(getNumeros(e.getId()));
				return e;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		public void insert (Espectaculo e) {
			String sql="INSERT INTO espectaculo(nombre,fecha_inicio,fecha_fin,coordinador)VALUES(?,?,?,?)";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
					stmt.setInt(1, e.getId());
					stmt.setString(2, e.getNombre());
					stmt.setDate(3,Date.valueOf(e.getFechaInicio()));
					stmt.setDate(4, Date.valueOf(e.getFechaFin()));
					stmt.setInt(5, e.getCoordinador().getId());
					
					stmt.executeUpdate();
					saveNumeros(e);
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}
		}
		
		public void update (Espectaculo e) {
			
			String sql="UPDATE espectaculo Set nombre=?, fecha_inicio=?, fecha_fin=?, coordinador=? WHERE id=?";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
				
				stmt.setString(1,e.getNombre());
				stmt.setDate(2, Date.valueOf(e.getFechaInicio()));
				stmt.setDate(3, Date.valueOf(e.getFechaFin()));
				stmt.setInt(4, e.getCoordinador().getId());
				stmt.setInt(5,e.getId());
				
				stmt.executeUpdate();
				
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		public void delete(int id) {
			
			deleteNumeros(id);
			
			String sql="DELETE FROM espectaculo WHERE id=?";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
				
				stmt.setInt(1,id);
				stmt.executeUpdate();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		public Espectaculo findById(int id) {
			String sql ="SELECT *FROM espectaculo WHERE id=?";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
				
				stmt.setInt(1, id);
				ResultSet rs= stmt.executeQuery();
				
				if(rs.next()) return mapRow(rs);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return null;
		}
		
		public List<Espectaculo> findAll(){
			
			List<Espectaculo> lista = new ArrayList<>();
			String sql ="SELECT * FROM espectaculo";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt=con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery()){
				
				while (rs.next()) {
					Espectaculo e =mapRow(rs);
					if(e !=null) lista.add(e);
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return lista;
		}
		
		private List<Numero> getNumeros(int idEspectaculo) {

		    List<Numero> lista = new ArrayList<>();
		    String sql = "SELECT numero_id FROM espectaculo_numero WHERE espectaculo_id=?";

		    try (Connection con = ConnectionManager.getConnection();
		         PreparedStatement stmt = con.prepareStatement(sql)) {

		        stmt.setInt(1, idEspectaculo);
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            int idNumero = rs.getInt("numero_id");
		            lista.add(numeroDAO.findById(idNumero));
		        }

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }

		    return lista;
		}
		
		private void saveNumeros(Espectaculo e) {

		    String sql = "INSERT INTO espectaculo_numero (espectaculo_id, numero_id) VALUES (?, ?)";

		    try (Connection con = ConnectionManager.getConnection();
		         PreparedStatement stmt = con.prepareStatement(sql)) {

		        for (Numero n : e.getNumeros()) {
		            stmt.setInt(1, e.getId());
		            stmt.setInt(2, n.getId());
		            stmt.executeUpdate();
		        }

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
		private void deleteNumeros(int idEspectaculo) {
			
			String sql="DELETE FROM espectaculo_numero WHERE espectaculo_id=?";
			
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement stmt =con.prepareStatement(sql)){
				
				stmt.setInt(1,idEspectaculo);
				stmt.executeUpdate();
				
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
}
		
