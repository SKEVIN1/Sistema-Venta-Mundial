/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Modelo.Partido;
import Dao.PartidoDao;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author PC
 */
public class PartidoController {
    
    private final PartidoDao dao = new PartidoDao();

    public List<Partido> listarPartidos() {
        return dao.listar();
    }

    public void insertarPartido(String codigo, String local, String visitante, LocalDateTime fecha, String estadio, String ciudad, int capacidad, String fase, String estado, String desc) {
        Partido p = new Partido(0, codigo, local, visitante, fecha, estadio, ciudad, capacidad, fase, estado, desc);
        dao.insertar(p);
    }

    public void actualizarPartido(int id, String codigo, String local, String visitante, LocalDateTime fecha, String estadio, String ciudad, int capacidad, String fase, String estado, String descripcion) {
        Partido p = new Partido(id, codigo, local, visitante, fecha, estadio, ciudad, capacidad, fase, estado, descripcion);
        dao.actualizar(p);
    }

    public void eliminarPartido(int id) {
        dao.eliminar(id);
    }
}

