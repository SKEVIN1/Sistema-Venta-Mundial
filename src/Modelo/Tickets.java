/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author arten
 */
public class Tickets {
    private int id_ticket;
    private int partido_id;
    private String codigo_ticket;
    private String numero_asiento;
    private String fila;
    private String seccion;
    private double precio;
    private String estado;
    private LocalDateTime fechaGeneracion;

    public Tickets() {
    }

    
    public Tickets(int id_ticket, int partido_id,  String codigo_ticket,String numero_asiento, String fila, String seccion, double precio, String estado, LocalDateTime fechaGeneracion) {
        this.id_ticket = id_ticket;
        this.partido_id = partido_id;
        this.codigo_ticket = codigo_ticket;
        this.numero_asiento = numero_asiento;
        this.fila = fila;
        this.seccion = seccion;
        this.precio = precio;
        this.estado = estado;
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getPartido_id() {
        return partido_id;
    }

    public void setPartido_id(int partido_id) {
        this.partido_id = partido_id;
    }

    public String getCodigo_ticket() {
        return codigo_ticket;
    }

    public void setCodigo_ticket(String codigo_ticket) {
        this.codigo_ticket = codigo_ticket;
    }

    public String getNumero_asiento() {
        return numero_asiento;
    }

    public void setNumero_asiento(String numero_asiento) {
        this.numero_asiento = numero_asiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    
    
    
    
}
