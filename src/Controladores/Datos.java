
package Controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Datos {
    
    private static String usuario;

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Datos.usuario = usuario;
    }
    
    public boolean traerDatos(String usuario) {
        
        try {
            Statement consultarDatos = ConexionBD.conexion.createStatement();
            ResultSet consulta = consultarDatos.executeQuery("select USUARIO from datos where USUARIO = '" + usuario + "'");

            if (consulta.next()) {
                this.usuario = consulta.getString(1);

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
            PreparedStatement consultarDatos = ConexionBD.conexion.prepareStatement("update DATOS set usuario = '" + usuario + "' where usuario = '" + usuario + "'");

            consultarDatos.executeUpdate();
            consultarDatos.close();

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
