/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.negocio;

import java.util.Date;
import mx.itson.pastor.enumerador.TipoMovimiento;
import mx.itson.pastor.persistencia.MovimientoDAO;

/**
 *
 * @author Mocchi
 */
public class MovimientoNegocio {
    public static boolean guardar(String concepto, Date fecha, int cargo, int abono, int subtotal, TipoMovimiento tipo){
        boolean resultado = false;
        
        try {
            resultado = MovimientoDAO.guardar(concepto, fecha, cargo, abono, subtotal, tipo);        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        } 
        return resultado;
    }
    
    public static boolean editar(String concepto, Date fecha, int cargo, int abono, int subtotal, TipoMovimiento tipo){
        boolean resultado = false;     
        try {
            resultado = MovimientoDAO.editar(concepto, fecha, cargo, abono, subtotal, tipo);        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
        return resultado;
    }
    public static boolean eliminar(String concepto, Date fecha, int cargo, int abono, int subtotal, TipoMovimiento tipo){
        boolean resultado = false;     
        try {
            resultado = MovimientoDAO.eliminar(concepto, fecha, cargo, abono, subtotal, tipo);        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
        return resultado;
    }
    
}
