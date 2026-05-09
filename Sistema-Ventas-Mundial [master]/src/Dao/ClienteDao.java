/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Cliente;
import Conexion.CreateConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kevinsica
 */
public class ClienteDao {
    private final  CreateConnection con = new CreateConnection();
    
    
    public boolean insertar(Cliente cliente) {
       
        String sql = "INSERT INTO cliente (nombre, apellido, telefono, email, direccion, dpi, fecha_nacimiento) VALUES (?,?,?,?,?,?,?,)";
        try(Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getDpi());
            ps.setObject(7, cliente.getFecha_nacimiento()); 
 
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
  
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                c.setDireccion(rs.getString("direccion"));
                c.setDpi(rs.getString("dpi"));
                c.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                c.setFecha_registro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

   
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, apellido=?, telefono=?, email=?, direccion=?, dpi=? WHERE id=?";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getDpi());
            ps.setInt(7, cliente.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

   
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection cn = con.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
  
}

