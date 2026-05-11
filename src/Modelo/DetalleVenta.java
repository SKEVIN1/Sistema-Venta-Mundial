/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 
 */
public class DetalleVenta {
    
    private int id;                         
    private int ventaId;                     // FK hacia venta(id)
    private int ticketId;                    // FK hacia ticket(id)
    private double precio;                   // NUMERIC(10,2)
    private double subtotal;                 // NUMERIC(10,2)
    private double iva;                      // NUMERIC(10,2)

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int ventaId, int ticketId, double precio, double subtotal, double iva) {
        this.id = id;
        this.ventaId = ventaId;
        this.ticketId = ticketId;
        this.precio = precio;
        this.subtotal = subtotal;
        this.iva = iva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }


}
