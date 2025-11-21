package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import db.ConnectionManager;
import model.Artista;
import model.Especialidad;

public class ArtistaDAO {

		private static final String TABLE="artista";
		
		
	private String toEspecialidadesString(List<Especialidad> especialidades) {
		 if (especialidades == null || especialidades.isEmpty()) return "";
	        return especialidades.stream().map(Especialidad::name).collect(Collectors.joining(","));
		
	}
	
	private List<Especialidad> toEspecialidadesList(String especialidadesStr){
		List<Especialidad> especialidades = new ArrayList<>();
		if (especialidadesStr ==null || especialidadesStr.isEmpty()) return especialidades;
		Arrays.stream(especialidadesStr.split(",")).forEach(e -> especialidades.add(Especialidad.valueOf(e)));
		return especialidades;
	}


	public void insert(Artista artista) {
	String sql ="INSERT INTO " + TABLE + "(nombre,emailnacionalidad,apodo,especialidades) VALUES (?,?,?,?,?)";
	try(Connection con = ConnectionManager.getConnection(); PreparedStatement stmt =con.prepareStatement(sql)){
	stmt.setString (1,artista.getNombre());
	stmt.setString (2,artista.getEmail());
	stmt.setString(3,artista.getNacionalidad());
	stmt.setString(4,artista.getApodo());
	stmt.setString(5, toEspecialidadesString(artista.getEspecialidades()));
	stmt.executeUpdate();
	
	}catch(SQLException e) {e.printStackTrace();}
	
}


	public void update (Artista artista) {
		String sql="UPDATE" +TABLE+ "SET nombre=?, email=?,nacionalidad=?, apodo=?, especialidades=? WHERE id=?";
		try(Connection con =ConnectionManager.getConnection();PreparedStatement stmt= con.prepareStatement(sql)) {
			stmt.setString(1,artista.getNombre());
			stmt.setString(2, artista.getEmail());
			stmt.setString(3,artista.getNacionalidad());
			stmt.setString(4,artista.getApodo());
			stmt.setString(5, toEspecialidadesString(artista.getEspecialidades()));
			stmt.setInt(6,artista.getId());
			stmt.executeUpdate();
		
		
	}catch (SQLException e) {e.printStackTrace();}
	
}

		public void delete(int id) {
				String sql="DELETE FROM" +TABLE+ "WHERE id=?";
				try(Connection con = ConnectionManager.getConnection(); PreparedStatement stmt =con.prepareStatement(sql)){
					stmt.setInt(1, id);
					stmt.executeUpdate();
		
	}catch(SQLException e) {e.printStackTrace();}
}
	
	 private Artista mapRow(ResultSet rs) {
	        try {
	            Artista artista = new Artista();
	            artista.setId(rs.getInt("id"));
	            artista.setNombre(rs.getString("nombre"));
	            artista.setEmail(rs.getString("email"));
	            artista.setNacionalidad(rs.getString("nacionalidad"));
	            artista.setApodo(rs.getString("apodo"));
	            artista.setEspecialidades(toEspecialidadesList(rs.getString("especialidades")));
	            return artista;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	
}

	 	public Artista findById(int id) {
	 			String sql ="SELECT * FROM" +TABLE + "WHERE id=?";
	 				try(Connection con =ConnectionManager.getConnection();PreparedStatement stmt=con.prepareStatement(sql)){
	 					stmt.setInt(1, id);
	 					ResultSet rs=stmt.executeQuery();
	 					return rs.next() ? mapRow(rs) :null;
	
	 				}catch(SQLException e) {e.printStackTrace();return null;}
	 	}

	 	public List<Artista> findAll(){
	 		String sql="SELECT *FROM "+TABLE;
	 		List<Artista> artistas  = new ArrayList<>();
	 		try(Connection con=ConnectionManager.getConnection();PreparedStatement stmt =con.prepareStatement(sql);ResultSet rs=stmt.executeQuery()){
	 			while(rs.next()) {
	 				Artista artista = mapRow(rs);
	 				if(artista !=null) artistas.add(artista);
			
		}
	 		}catch(SQLException e) {e.printStackTrace();}
	 		return artistas;
	 	}

	}
