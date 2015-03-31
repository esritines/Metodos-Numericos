
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Datos {
    
    private static String contraseña;
    private static String usuario;
    
    public boolean traerDatos(String contraseña, String usuario) {
        
        try {
            Statement consultarDatos = ConexionBD.conexion.createStatement();
            ResultSet consulta = consultarDatos.executeQuery("select CONTRASEÑA, USUARIO from datos where CONTRASEÑA = '" + contraseña + "' and USUARIO = '" + usuario + "'");

            if (consulta.next()) {
                this.contraseña = consulta.getString(1);
                this.usuario = consulta.getString(2);

                consulta.close();
                consultarDatos.close();

                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void enviarDatos() {
        try {
            PreparedStatement consultarDatos = ConexionBD.conexion.prepareStatement("update DATOS set CONTRASEÑA = '" + contraseña + "' where usuario = '" + usuario + "'");

            consultarDatos.executeUpdate();
            consultarDatos.close();

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
