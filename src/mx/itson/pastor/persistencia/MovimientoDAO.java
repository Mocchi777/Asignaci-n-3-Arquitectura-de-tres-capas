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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mx.itson.pastor.entidades.Movimiento;
import mx.itson.pastor.enumerador.TipoMovimiento;

/**
 *
 * @author casa
 */
public class MovimientoDAO {
    
     public static List<Movimiento> obtenerTodos(){
        List<Movimiento> movimientos = new ArrayList<>();
        
        try {
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, concepto, fecha, cargo, abono, subtotal, tipo FROM movimiento");
            while(resultSet.next()){
                Movimiento m = new Movimiento();

                m.setId(resultSet.getInt(1));
                m.setConcepto(resultSet.getString(2));
                m.setFecha(resultSet.getDate(3));
                m.setCargo(resultSet.getInt(4));
                m.setAbono(resultSet.getInt(5));
                m.setSubtotal(resultSet.getInt(6));
                m.setTipo(TipoMovimiento.CARGO);
                movimientos.add(m);

            }

        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }

        return movimientos;
        }
     
     
    
     public static boolean verificacionExistencia(String cargo){
        boolean existencia = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "SELECT * FROM movimiento WHERE cargo = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, cargo);
            ResultSet resultSet = statement.executeQuery();
            
            existencia = resultSet.next();
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }
        return existencia;
        
    }
     
     
    public static boolean guardar(String concepto, Date fecha, int cargo, int abono, int subtotal, TipoMovimiento tipo){
        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "INSERT INTO movimiento (concepto, fecha, cargo, abono, subtotal, tipo) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, concepto);
            statement.setDate(2, (java.sql.Date) fecha);
            statement.setInt(3, cargo);
            statement.setInt(4, abono);
            statement.setInt(5, subtotal);
 
            
            statement.execute();
            
            resultado = statement.getUpdateCount() ==1;
            
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public static boolean editar(String concepto, Date fecha, int cargo, int abono, int subtotal, TipoMovimiento tipo){
        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "UPDATE movimiento SET concepto= ?, fecha= ?, cargo= ?, abono= ?, subtotal= ?, tipo= ? WHERE id= ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, concepto);
            statement.setDate(2, (java.sql.Date) fecha);
            statement.setInt(3, cargo);
            statement.setInt(4, abono);
            statement.setInt(5, subtotal);
           // statement.setInt(6, tipo);
            
            statement.execute();
            
            resultado = statement.getUpdateCount() ==1;
            
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public static boolean eliminar(String concepto, Date fecha, int cargo, int abono, int subtotal, TipoMovimiento tipo){
        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "DELETE FROM movimiento WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, concepto);
            statement.setDate(2, (java.sql.Date) fecha);
            statement.setInt(3, cargo);
            statement.setInt(4, abono);
            statement.setInt(5, subtotal);
            //statement.setString(6, tipo);
            
            statement.execute();
            
            resultado = statement.getUpdateCount() ==1;
            
        } catch (Exception ex) {
            System.err.print("Ocurrio un error: " + ex.getMessage());
        }

        return resultado;
    }
}
