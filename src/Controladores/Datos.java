package Controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datos {

    private static String usuario;
    private static int puntos;

    public static String getUsuario() {
        return usuario;
    }

    public boolean traerDatos(String usuario) {

        try {
            Statement consultarDatos = ConexionBD.conexion.createStatement();
            ResultSet consulta = consultarDatos.executeQuery("select USUARIO from datos where USUARIO = '" + usuario + "'");

            if (consulta.next()) {
                Datos.usuario = consulta.getString(1);

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

    public boolean enviarDatos(String nuevoUsuario) {
        try {
            Statement capturarUsuario;
            capturarUsuario = ConexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            capturarUsuario.executeUpdate("insert into datos (USUARIO) values('" + nuevoUsuario + "')");

            capturarUsuario.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void enviarRegistro(String usuario, int puntos) {
        try {
            Statement capturarRegistro;
            capturarRegistro = ConexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            capturarRegistro.executeUpdate("insert into registros (usuario, puntos) values('" + usuario + "'," + "'" + puntos + "')");

            capturarRegistro.close();

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void traerRegistros(ArrayList usuarios, ArrayList puntos) {
        try {
            Statement traerRegistros = ConexionBD.conexion.createStatement();
            ResultSet consulta = traerRegistros.executeQuery("select usuario, puntos from datos");

            while(consulta.next()){
                usuarios.add(consulta.getString(1));
                puntos.add(consulta.getInt(2));
            }
            consulta.close();
            traerRegistros.close();

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
