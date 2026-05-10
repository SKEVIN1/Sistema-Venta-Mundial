/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Modelo.Cliente;
import Dao.ClienteDao;
import java.time.LocalDateTime;
import java.util.Date;

import java.util.List;
/**
 *
 * @author PC
 */
public class ClienteController {
    private final ClienteDao dao = new ClienteDao();
    
    public List<Cliente> listarCliente (){
        return dao.listar();
    }
    
    public void insertarCliente (String nombre, String apellido, String telefono, String email, String direccion, String dpi, Date fecha_nacimiento) {
    Cliente cl = new Cliente (0,nombre, apellido, telefono, email, direccion, dpi, fecha_nacimiento);
    dao.insertar(cl);
    }
    public void actualizarCliente(int id, String nombre, String apellido, String telefono, String email, String direccion, String dpi, Date fecha_nacimiento) {
        Cliente cl = new Cliente(id, nombre, apellido, telefono, email, direccion, dpi, fecha_nacimiento);
        
        dao.actualizar(cl);
    }

    public void eliminarCliente(int id) {
        dao.eliminar(id);
    }
}
