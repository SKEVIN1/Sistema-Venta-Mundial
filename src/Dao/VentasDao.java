/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexion.CreateConnection;
import Modelo.DetalleVenta;
import Modelo.Venta;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author arten
 */
public class VentasDao {
     CreateConnection connF = new CreateConnection();
    String sql;

    // =====================================================
    // GUARDAR VENTA COMPLETA
    // =====================================================
    public boolean realizarVenta(Venta venta,
            List<DetalleVenta> detalles) {

        Connection conn = null;

        try {

            conn = connF.getConnection();

            conn.setAutoCommit(false);

            // =============================================
            // INSERTAR VENTA
            // =============================================
            sql = "INSERT INTO venta "
                    + "(fecha, cliente_id, usuario_id, subtotal, iva_total, descuento, total, metodo_pago, moneda, estado) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement psVenta
                    = conn.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psVenta.setTimestamp(
                    1,
                    Timestamp.valueOf(
                            LocalDateTime.now()
                    )
            );

            psVenta.setInt(2, venta.getClienteId());
            psVenta.setInt(3, venta.getUsuarioId());

            psVenta.setDouble(4, venta.getSubtotal());
            psVenta.setDouble(5, venta.getIvaTotal());
            psVenta.setDouble(6, venta.getDescuento());
            psVenta.setDouble(7, venta.getTotal());

            psVenta.setString(8, venta.getMetodoPago());
            psVenta.setString(9, venta.getMoneda());
            psVenta.setString(10, venta.getEstado());

            psVenta.executeUpdate();

            // =============================================
            // OBTENER ID VENTA
            // =============================================
            ResultSet rs
                    = psVenta.getGeneratedKeys();

            int venta_id = 0;

            if (rs.next()) {

                venta_id = rs.getInt(1);
            }

            // =============================================
            // INSERTAR DETALLES
            // =============================================
            for (DetalleVenta d : detalles) {

                sql = "INSERT INTO detalle_venta "
                        + "(venta_id, ticket_id, precio, subtotal, iva) "
                        + "VALUES(?,?,?,?,?)";

                PreparedStatement psDetalle
                        = conn.prepareStatement(sql);

                psDetalle.setInt(1, venta_id);
                psDetalle.setInt(2, d.getTicketId());

                psDetalle.setDouble(3, d.getPrecio());
                psDetalle.setDouble(4, d.getSubtotal());
                psDetalle.setDouble(5, d.getIva());

                psDetalle.executeUpdate();

                // =========================================
                // CAMBIAR TICKET A VENDIDO
                // =========================================
                sql = "UPDATE ticket "
                        + "SET estado='VENDIDO' "
                        + "WHERE id=?";

                PreparedStatement psTicket
                        = conn.prepareStatement(sql);

                psTicket.setInt(1, d.getId());

                psTicket.executeUpdate();
            }

            conn.commit();

            return true;

        } catch (SQLException e) {

            try {

                if (conn != null) {

                    conn.rollback();
                }

            } catch (SQLException ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();
        }

        return false;
    }

    // =====================================================
    // CALCULAR SUBTOTAL
    // =====================================================
    public double calcularSubtotal(
            List<DetalleVenta> detalles) {

        double subtotal = 0;

        for (DetalleVenta d : detalles) {

            subtotal += d.getPrecio();
        }

        return subtotal;
    }

    // =====================================================
    // CALCULAR IVA
    // =====================================================
    public double calcularIVA(double subtotal) {

        return subtotal * 0.12;
    }

    // =====================================================
    // CALCULAR DESCUENTO
    // =====================================================
    public double calcularDescuento(
            int cantidad,
            double total) {

        double descuento = 0;

        if (cantidad >= 10) {

            descuento = total * 0.07;

        } else if (cantidad > 5) {

            descuento = total * 0.05;
        }

        return descuento;
    }

    // =====================================================
    // CALCULAR TOTAL
    // =====================================================
    public double calcularTotal(
            double subtotal,
            double iva,
            double descuento) {

        return subtotal + iva - descuento;
    }
}
