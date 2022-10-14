/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.negocio;
import mx.itson.pastor.persistencia.ClienteDAO;


/**
 *
 * @author alumnog
 */
public class ClienteNegocio {
    
    public static boolean guardar(String nombre, String direccion, String telefono, String email){
        boolean resultado = false;
        
        try {
            resultado = ClienteDAO.guardar(nombre, direccion, telefono, email);        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
        
        return resultado;
    }
    
    
    
    
    

}
