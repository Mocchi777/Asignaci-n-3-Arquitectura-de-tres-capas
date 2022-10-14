/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.negocio;

import mx.itson.pastor.enumerador.TipoMovimiento;
import mx.itson.pastor.persistencia.CuentaDAO;

/**
 *
 * @author Mocchi
 */
public class CuentaNegocio {
    
    public static boolean guardar(String nombre, String direccion, String telefono, String email){
        boolean resultado = false;
        
        try {
            resultado = CuentaDAO.guardar(nombre, direccion, telefono, email);        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
        
        return resultado;
    }

    
    public static boolean editar(int idCliente, String numeroCuenta, TipoMovimiento tipo){
        boolean resultado = false;
        
        try {
            resultado = CuentaDAO.editar(idCliente, numeroCuenta, tipo);
            
        } catch (Exception ex) {
            System.out.print("Ocurrio un error:" + ex.getMessage());
        }
        
        return resultado;
    }
    
    public static boolean eliminar(int idCliente, String numeroCuenta, TipoMovimiento tipo){
        boolean resultado = false;
        
        try {
            resultado = CuentaDAO.eliminar(idCliente, numeroCuenta, tipo);
            
        } catch (Exception ex) {
            System.out.print("Ocurrio un error" + ex.getMessage());
        }
        
        return resultado;
    }
    
    
    
    
}
