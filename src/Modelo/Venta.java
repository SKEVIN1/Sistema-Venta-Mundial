/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author 
 */
public class Venta {

    private int id;                          // SERIAL PRIMARY KEY
    private java.sql.Timestamp fecha;        // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    private int clienteId;                   // FK hacia cliente(id)
    private int usuarioId;                   // FK hacia usuario(id)
    private double subtotal;                 // NUMERIC(10,2) >= 0
    private double ivaTotal;                 // NUMERIC(10,2) >= 0
    private double descuento;                // NUMERIC(10,2) DEFAULT 0
    private double total;                    // NUMERIC(10,2) >= 0
    private String metodoPago;               // EFECTIVO, TARJETA, TRANSFERENCIA
    private String moneda;                   // USD, EUR, MXN, GTQ
    private String estado;                   // COMPLETADA, CANCELADA

    public Venta() {
    }

    public Venta(int id, Timestamp fecha, int clienteId, int usuarioId, double subtotal, double ivaTotal, double descuento, double total, String metodoPago, String moneda, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.subtotal = subtotal;
        this.ivaTotal = ivaTotal;
        this.descuento = descuento;
        this.total = total;
        this.metodoPago = metodoPago;
        this.moneda = moneda;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(double ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



}



