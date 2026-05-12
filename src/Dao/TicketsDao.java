/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexion.CreateConnection;
import Modelo.*;
import java.sql.*;
import java.time.LocalDateTime;
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
                 ticket.setCodigo_ticket(rs.getString("codigo_ticket"));
                ticket.setNumero_asiento(rs.getString("numero_asiento"));
                ticket.setFila(rs.getString("fila"));
                ticket.setSeccion(rs.getString("seccion"));
                ticket.setPrecio(rs.getDouble("precio"));
                ticket.setEstado(rs.getString("estado"));
                ticket.setFechaGeneracion( rs.getTimestamp("fecha_generacion") .toLocalDateTime()
                );

                lista.add(ticket);
            }

            ps.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println("Error al obtener tickets: " + e.getMessage());
        }

        return lista;
    }

    // =====================================================
    // GUARDAR TICKET
    // =====================================================
    public boolean guardar(Tickets ticket) {

        // VALIDAR ASIENTO DUPLICADO
        if (existeAsiento(ticket.getPartido_id(),
                ticket.getNumero_asiento())) {

            System.out.println("El asiento ya existe");
            return false;
        }
        
        sql = "Insert into ticket "
                + "(partido_id, numero_asiento, codigo_ticket,fila, seccion, precio, estado, fecha_generacion) "
                + "values(?,?,?,?,?,?,?,?)";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticket.getPartido_id());
            ps.setString(2, ticket.getNumero_asiento());
            ps.setString(3, ticket.getCodigo_ticket());
            ps.setString(4, ticket.getFila());
            ps.setString(5, ticket.getSeccion());
            ps.setDouble(6, ticket.getPrecio());
            ps.setString(7, ticket.getEstado());
            ps.setTimestamp( 8 ,Timestamp.valueOf(ticket.getFechaGeneracion())
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
    // VALIDAR ASIENTO DUPLICADO
    // =====================================================
    public boolean existeAsiento(int partido_id,
            String numero_asiento) {

        sql = "Select * from ticket "
                + "where partido_id=? "
                + "and numero_asiento=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, partido_id);
            ps.setString(2, numero_asiento);

            ResultSet rs = ps.executeQuery();

            return rs.next();

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
                + "codigo_ticket=?, "
                + "numero_asiento=?, "
                + "fila=?, "
                + "seccion=?, "
                + "precio=?, "
                + "estado=? "
                + "where id=?";

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticket.getPartido_id());
            ps.setString(2, ticket.getCodigo_ticket());
            ps.setString(3, ticket.getNumero_asiento());
            ps.setString(4, ticket.getFila());
            ps.setString(5, ticket.getSeccion());
            ps.setDouble(6, ticket.getPrecio());
            ps.setString(7, ticket.getEstado());
            ps.setInt(8, ticket.getId());

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
    // CONSULTAR TICKET 
    // =====================================================
    public Tickets consultar(int id) {

        sql = "SELECT * FROM ticket WHERE id=?";

        Tickets ticket = null;

        try (Connection conn = connF.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                ticket = new Tickets();

                ticket.setId(rs.getInt("id"));
                ticket.setPartido_id(rs.getInt("partido_id"));
                ticket.setCodigo_ticket(rs.getString("codigo_ticket"));
                ticket.setNumero_asiento(rs.getString("numero_asiento"));
                ticket.setFila(rs.getString("fila"));
                ticket.setSeccion(rs.getString("seccion"));
                ticket.setPrecio(rs.getDouble("precio"));
                ticket.setEstado(rs.getString("estado"));

                ticket.setFechaGeneracion( rs.getTimestamp("fecha_generacion").toLocalDateTime()
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return ticket;
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
                ticket.setCodigo_ticket(rs.getString("codigo_ticket"));
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
    // =====================================================
// GENERAR TICKETS AUTOMATICAMENTE
// =====================================================
public boolean generarTickets(int partido_id) {

    String sql = "Insert into ticket "
            + "(partido_id,codigo_ticket, numero_asiento, fila, seccion, precio, estado, fecha_generacion) "
            + "values(?,?,?,?,?,?,?,?)";

    try (Connection conn = connF.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

        // VIP
        for (int i = 1; i <= 20; i++) {

            String codigo = "TCK-" + partido_id+ "-A"+ i;
            
            ps.setInt(1, partido_id);
                ps.setString(2, codigo);
                ps.setString(3, "A" + i);
                ps.setString(4, "A");
                ps.setString(5, "VIP");
                ps.setDouble(6, 500);
                ps.setString(7, "DISPONIBLE");
                ps.setTimestamp( 8, Timestamp.valueOf(LocalDateTime.now())
            );

            ps.executeUpdate();
        }

        // PREFERENCIAL
        for (int i = 1; i <= 50; i++) {

            String codigo= "TCK-" + partido_id + "-B"+ i;
            
                ps.setInt(1, partido_id);
                ps.setString(2, codigo);
                ps.setString(3, "B" + i);
                ps.setString(4, "B");
                ps.setString(5, "PREFERENCIAL");
                ps.setDouble(6, 250);
                ps.setString(7, "DISPONIBLE");
                ps.setTimestamp(8,Timestamp.valueOf( LocalDateTime.now())
            );

            ps.executeUpdate();
        }

        // ============================================
        // GENERAL
        // ============================================
        for (int i = 1; i <= 100; i++) {
 
            String codigo = "TCK-" + partido_id+ "-C" + i;

                ps.setInt(1, partido_id);
                ps.setString(2, codigo);
                ps.setString(3, "C" + i);
                ps.setString(4, "C");
                ps.setString(5, "GENERAL");
                ps.setDouble(6, 100);
                ps.setString(7, "DISPONIBLE");
                ps.setTimestamp( 8, Timestamp.valueOf( LocalDateTime.now())
            );

            ps.executeUpdate();
        }

        ps.close();
        conn.close();

        return true;

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return false;
}
    
}

