/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mx.itson.pastor.entidades.Cliente;
import mx.itson.pastor.entidades.Cuenta;
import mx.itson.pastor.enumerador.TipoMovimiento;

/**
 *
 * @author alumnog
 */
public class CuentaDAO {
    
        
    public static List<Cuenta> obtenerTodos(){
        List<Cuenta> cuentas = new ArrayList<>();
        
        try {
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT cu.id, cu.numero, cl.nombre, cl.direccion, cl.telefono, cl.email FROM cuenta cu INNER JOIN cliente cl ON cu.idCliente = cl.id");
            while(resultSet.next()){
                Cliente c = new Cliente();
                Cuenta cuenta = new Cuenta();
                
                cuenta.setId(resultSet.getInt(1));
                cuenta.setNumeroCuenta(resultSet.getString(2));
                
                c.setId(resultSet.getInt(3));
                c.setNombre(resultSet.getString(4));
                c.setDireccion(resultSet.getString(5));
                c.setTelefono(resultSet.getString(6));
                c.setEmail(resultSet.getString(7));
                
                cuenta.setCliente(c);
                
                cuentas.add(cuenta);

            }

        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }

        return cuentas;
    }
    
    public static boolean guardar(String nombre, String cuenta, String telefono, String email){
        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "INSERT INTO cuenta (nombre, cuenta, telefono, email) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, cuenta);
            statement.setString(3, telefono);
            statement.setString(4, email);
            statement.execute();
            
            resultado = statement.getUpdateCount() ==1;
            
        } catch (Exception ex) {
            System.err.print("Ocurrio un error en guardar cuenta: " + ex.getMessage());
        }
        return resultado;
    }
    public static boolean editar(int idCliente, String numeroCuenta, TipoMovimiento tipo){
        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "UPDATE cuenta SET idCliente = ?, numeroCuenta = ?, tipo = ? WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, idCliente);
            statement.setString(2, numeroCuenta);
            statement.setObject(3, tipo);
            statement.execute();
            
            resultado = statement.getUpdateCount() ==1;
            
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
        
    }
    
    public static boolean eliminar(int idCliente, String numeroCuenta, TipoMovimiento tipo){
        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "DELETE FROM cuenta WHERE id = ?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, idCliente);
            statement.setString(2, numeroCuenta);
            statement.setObject(3, tipo);
            statement.execute();
            
            resultado = statement.getUpdateCount() ==1;
            
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    public static boolean verificacionExistencia(String email){
        boolean existencia = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "SELECT * FROM cuenta WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            
            existencia = resultSet.next();
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }
        return existencia;
        
    }
    
    
    
}
