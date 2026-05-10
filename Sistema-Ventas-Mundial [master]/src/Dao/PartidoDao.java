/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Partido;
import Conexion.CreateConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PC
 */
public class PartidoDao {
    private final CreateConnection con = new CreateConnection();

    public boolean insertar(Partido partido) {
        String sql = "INSERT INTO partido (codigo_partido, equipo_local, equipo_visitante, fecha, estadio, ciudad, capacidad, fase, estado, descripcion) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, partido.getCodigoPartido());
            ps.setString(2, partido.getEquipoLocal());
            ps.setString(3, partido.getEquipoVisitante());
            ps.setObject(4, partido.getFecha());
            ps.setString(5, partido.getEstadio());
            ps.setString(6, partido.getCiudad());
            ps.setInt(7, partido.getCapacidad());
            ps.setString(8, partido.getFase());
            ps.setString(9, partido.getEstado());
            ps.setString(10, partido.getDescripcion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar partido: " + e.getMessage());
            return false;
        }
    }

    public List<Partido> listar() {
        List<Partido> lista = new ArrayList<>();
        String sql = "SELECT * FROM partido";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Partido p = new Partido();
                p.setId(rs.getInt("id"));
                p.setCodigoPartido(rs.getString("codigo_partido"));
                p.setEquipoLocal(rs.getString("equipo_local"));
                p.setEquipoVisitante(rs.getString("equipo_visitante"));
                p.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                p.setEstadio(rs.getString("estadio"));
                p.setCiudad(rs.getString("ciudad"));
                p.setCapacidad(rs.getInt("capacidad"));
                p.setFase(rs.getString("fase"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar partidos: " + e.getMessage());
        }
        return lista;
    }
    public boolean actualizar(Partido partido) {
        String sql = "UPDATE partido SET codigo_partido=?, equipo_local=?, equipo_visitante=?, fecha=?, estadio=?, ciudad=?, capacidad=?, fase=?, estado=?, descripcion=? WHERE id=?";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, partido.getCodigoPartido());
            ps.setString(2, partido.getEquipoLocal());
            ps.setString(3, partido.getEquipoVisitante());
            ps.setObject(4, partido.getFecha());
            ps.setString(5, partido.getEstadio());
            ps.setString(6, partido.getCiudad());
            ps.setInt(7, partido.getCapacidad());
            ps.setString(8, partido.getFase());
            ps.setString(9, partido.getEstado());
            ps.setString(10, partido.getDescripcion());
            ps.setInt(11, partido.getId()); // El ID es la llave para saber cuál actualizar

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar partido: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM partido WHERE id = ?";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar partido: " + e.getMessage());
            return false;
        }
    }
    
}

