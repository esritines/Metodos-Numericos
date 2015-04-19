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

        ArrayList<String> listaUsuarios = new ArrayList<>();
        ArrayList<Integer> listaPuntos = new ArrayList<>();

        traerRegistros(listaUsuarios, listaPuntos);

        for (int i = 0; i < listaPuntos.size(); i++) {

            int temporal;
            if (puntos > listaPuntos.get(i)) {
                System.out.println("entre al if");
                try {
                    Statement consultarDatos = ConexionBD.conexion.createStatement();
                    ResultSet consulta = consultarDatos.executeQuery("SELECT MIN(puntos) FROM registros");

                    consulta.next();
                    temporal = consulta.getInt(1);

                    consulta.close();
                    consultarDatos.close();

                    Statement consultarPuntos = ConexionBD.conexion.createStatement();
                    ResultSet consulta1 = consultarPuntos.executeQuery("select id from registros where puntos = '" + temporal + "'");

                    consulta1.next();
                    int temporal2 = consulta1.getInt(1);

                    consulta1.close();
                    consultarPuntos.close();

                    Statement borrarRegistro = ConexionBD.conexion.createStatement();
                    borrarRegistro.executeUpdate("DELETE from registros where id = '" + temporal2 + "'");

                    borrarRegistro.close();

                } catch (SQLException ex) {
                    Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
        }

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
            ResultSet consulta = traerRegistros.executeQuery("select usuario, puntos from registros");

            while (consulta.next()) {
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