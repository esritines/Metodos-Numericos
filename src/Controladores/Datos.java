package Controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datos {

    private static String usuario;
    private static ArrayList<Integer> preguntas = new ArrayList<>();
    private static Iterator<Integer> recorrer;

    public static String getUsuario() {
        return usuario;
    }

    public void vaciarPreguntas(){
        preguntas.clear();
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
            ResultSet consulta = traerRegistros.executeQuery("select usuario, puntos from registros order by puntos desc");

            for (int i = 0; i < 6; i++) {
                consulta.next();
                usuarios.add(consulta.getString(1));
                puntos.add(consulta.getInt(2));
            }

            consulta.close();
            traerRegistros.close();

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String traerPregunta(ArrayList respuestasTemp, ArrayList respuestasVerdadera, ArrayList textoPregunta) {

        String preguntaTemp = "";
        recorrer = preguntas.iterator();
        
        boolean valor;

        do {
            valor = false;
//            int n = (int) (Math.random() * 2) + 1;
            int n = 1;

            try {
                Statement consultarDatos = ConexionBD.conexion.createStatement();
                ResultSet consulta = consultarDatos.executeQuery("select pregunta, respuesta, verdadera, texto from preguntas where id = '" + n + "'");
              
                while (recorrer.hasNext()) {
                    if (n == recorrer.next()) {
                        valor = true;
                        break;
                    }
                }

                if (!valor) {
                    while (consulta.next()) {
                        preguntaTemp = consulta.getString(1);
                        respuestasTemp.add(consulta.getString(2));
                        respuestasVerdadera.add(consulta.getInt(3));
                        textoPregunta.add(consulta.getString(4));
                    }
                    preguntas.add(n);        
                }
            } catch (SQLException ex) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
        } while (valor);
        return preguntaTemp;
    }
}
