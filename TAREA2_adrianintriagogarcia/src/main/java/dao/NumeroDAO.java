package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import model.Numero;

public class NumeroDAO {

    private static final String TABLE = "numero";

    //mapa
    private Numero mapRow(ResultSet rs) {
        try {
            return new Numero(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("duracion")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //insert
    public void insert(Numero n) {
        String sql = "INSERT INTO " + TABLE + " (nombre, duracion) VALUES (?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, n.getNombre());
            stmt.setString(2, n.getDuracion());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update
    public void update(Numero n) {
        String sql = "UPDATE " + TABLE + " SET nombre = ?, duracion = ? WHERE id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, n.getNombre());
            stmt.setString(2, n.getDuracion());
            stmt.setInt(3, n.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   //borrar
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

    //localizar elemento por id 
    public Numero findById(int id) {
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

    
    public List<Numero> findAll() {
        List<Numero> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Numero numero = mapRow(rs);
                if (numero != null) {
                    lista.add(numero);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}