/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexion.CreateConnection;
import Modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author arten
 */
public class TicketsDao {
  CreateConnection connF = new CreateConnection();
    String sql;

    // =====================================================
    // OBTENER TODOS LOS TICKETS
    // =====================================================
    public List<Tickets> obtenerTickets() {

        List<Tickets> lista = new ArrayList<>();

        sql = "Select * From ticket order by id";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Tickets ticket = new Tickets();

                ticket.setId(rs.getInt("id"));
                ticket.setPartido_id(rs.getInt("partido_id"));
                ticket.setNumero_asiento(rs.getString("numero_asiento"));
                ticket.setFila(rs.getString("fila"));
                ticket.setSeccion(rs.getString("seccion"));
                ticket.setPrecio(rs.getDouble("precio"));
                ticket.setEstado(rs.getString("estado"));

                ticket.setFechaGeneracion(
                        rs.getTimestamp("fecha_generacion")
                                .toLocalDateTime()
                );

                lista.add(ticket);
            }

            ps.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println("Error al obtener tickets: "
                    + e.getMessage());
        }

        return lista;
    }

    // =====================================================
    // GUARDAR TICKET
    // =====================================================
    public boolean guardar(Tickets ticket) {

        sql = "Insert into ticket "
                + "(partido_id, numero_asiento, fila, seccion, precio, estado, fecha_generacion) "
                + "values(?,?,?,?,?,?,?)";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticket.getPartido_id());
            ps.setString(2, ticket.getNumero_asiento());
            ps.setString(3, ticket.getFila());
            ps.setString(4, ticket.getSeccion());
            ps.setDouble(5, ticket.getPrecio());
            ps.setString(6, ticket.getEstado());

            ps.setTimestamp(
                    7,
                    Timestamp.valueOf(ticket.getFechaGeneracion())
            );

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =====================================================
    // CAMBIAR ESTADO DEL TICKET
    // =====================================================
    public boolean eliminar(int id, String estado) {

        sql = "Update ticket Set estado=? where id=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =====================================================
    // ACTUALIZAR TICKET
    // =====================================================
    public boolean actualizar(Tickets ticket) {

        sql = "Update ticket Set "
                + "partido_id=?, "
                + "numero_asiento=?, "
                + "fila=?, "
                + "seccion=?, "
                + "precio=?, "
                + "estado=? "
                + "where id=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticket.getPartido_id());
            ps.setString(2, ticket.getNumero_asiento());
            ps.setString(3, ticket.getFila());
            ps.setString(4, ticket.getSeccion());
            ps.setDouble(5, ticket.getPrecio());
            ps.setString(6, ticket.getEstado());
            ps.setInt(7, ticket.getId());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =====================================================
    // CONSULTAR TICKET POR ID
    // =====================================================
    public boolean consultar(int id) {

        sql = "Select * from ticket where id=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeQuery();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =====================================================
    // CONSULTAR DISPONIBILIDAD POR PARTIDO
    // =====================================================
    public List<Tickets> consultarDisponibilidad(int partido_id) {

        List<Tickets> lista = new ArrayList<>();

        sql = "Select * from ticket "
                + "where partido_id=? "
                + "and estado='DISPONIBLE'";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, partido_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tickets ticket = new Tickets();

                ticket.setId(rs.getInt("id"));
                ticket.setPartido_id(rs.getInt("partido_id"));
                ticket.setNumero_asiento(rs.getString("numero_asiento"));
                ticket.setFila(rs.getString("fila"));
                ticket.setSeccion(rs.getString("seccion"));
                ticket.setPrecio(rs.getDouble("precio"));
                ticket.setEstado(rs.getString("estado"));

                ticket.setFechaGeneracion(
                        rs.getTimestamp("fecha_generacion")
                                .toLocalDateTime()
                );

                lista.add(ticket);
            }

            ps.close();
            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    // =====================================================
    // RESERVAR TICKET
    // =====================================================
    public boolean reservarTicket(int id) {

        sql = "Update ticket Set estado='RESERVADO' where id=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =====================================================
    // VENDER TICKET
    // =====================================================
    public boolean venderTicket(int id) {

        sql = "Update ticket Set estado='VENDIDO' where id=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}

