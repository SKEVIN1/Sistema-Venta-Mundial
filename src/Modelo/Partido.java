/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalDateTime;
/**
 *
 * @author kevinsica
 */
public class Partido {

    private int id;
    private String codigo_partido;
    private String equipo_local;
    private String equipo_visitante;
    private LocalDateTime fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private String fase;
    private String estado;
    private String descripcion;

   
    public Partido() {
    }

    
    public Partido(String codigoPartido, String equipoLocal, String equipoVisitante, LocalDateTime fecha, String estadio, String ciudad, int capacidad, String fase, String estado, String descripcion) {
        this.codigo_partido = codigoPartido;
        this.equipo_local = equipoLocal;
        this.equipo_visitante = equipoVisitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.fase = fase;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Partido(int id, String codigoPartido, String equipoLocal, String equipoVisitante, LocalDateTime fecha, String estadio, String ciudad, int capacidad, String fase, String estado, String descripcion) {
        this.id = id;
        this.codigo_partido = codigoPartido;
        this.equipo_local = equipoLocal;
        this.equipo_visitante = equipoVisitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.fase = fase;
        this.estado = estado;
        this.descripcion = descripcion;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoPartido() {
        return codigo_partido;
    }

    public void setCodigoPartido(String codigoPartido) {
        this.codigo_partido = codigoPartido;
    }

    public String getEquipoLocal() {
        return equipo_local;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipo_local = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipo_visitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipo_visitante = equipoVisitante;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Override
    public String toString() {
        return "Partido{" + "codigo=" + codigo_partido + ", " + equipo_local + " vs " + equipo_visitante + ", fase=" + fase + '}';
    }
}

