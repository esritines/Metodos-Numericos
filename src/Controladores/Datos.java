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
    private static ArrayList<Integer> preguntasP = new ArrayList<>();
    private static ArrayList<Integer> preguntasT = new ArrayList<>();
    private static Iterator<Integer> recorrerP;
    private static Iterator<Integer> recorrerT;

    private static String pregunta;
    private static String respuesta;
    private static String texto;
    private static ArrayList<String> respuestas = new ArrayList<>();
    private static int aleatoria;

    public static int getAleatoria() {
        return aleatoria;
    }

    public static String getPregunta() {
        return pregunta;
    }

    public static String getRespuesta() {
        return respuesta;
    }

    public static String getTexto() {
        return texto;
    }

    public static ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public static String getUsuario() {
        return usuario;
    }

    public void vaciarPreguntas() {
        preguntasP.clear();
        preguntasT.clear();
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

    public void crearPregunta() {

        respuestas.clear();

        recorrerP = preguntasP.iterator();
        recorrerT = preguntasT.iterator();

        boolean valor;
        aleatoria = (int) (Math.random() * 3);

        do {
            valor = false;

            switch (aleatoria) {
                case 0:
                    valor = traerProblema();
                    break;
                case 1:
                    valor = traerProblema();
                    break;
                case 2:
                    valor = traerTeorica();
                    break;
            }
        } while (valor);
    }

    public boolean traerProblema() {
        int n = (int) (Math.random() * 25) + 1;

        try {
            Statement consultarDatos = ConexionBD.conexion.createStatement();
            ResultSet consulta = consultarDatos.executeQuery("select pregunta, respuesta, texto from preguntasP where id = '" + n + "'");

            while (recorrerP.hasNext()) {
                if (n == recorrerP.next()) {
                    return true;
                }
            }

            consulta.next();
            pregunta = consulta.getString(1);
            respuesta = consulta.getString(2);
            texto = consulta.getString(3);
            preguntasP.add(n);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    public boolean traerTeorica() {
        int n = (int) (Math.random() * 20) + 1;
        try {
            Statement consultarDatos = ConexionBD.conexion.createStatement();
            ResultSet consulta = consultarDatos.executeQuery("select pregunta, respuesta1, respuesta2, respuesta3, respuesta4 from preguntasT where id = '" + n + "'");

            while (recorrerT.hasNext()) {
                if (n == recorrerT.next()) {
                    return true;
                }
            }

            consulta.next();
            pregunta = consulta.getString(1);
            respuestas.add(consulta.getString(2));
            respuestas.add(consulta.getString(3));
            respuestas.add(consulta.getString(4));
            respuestas.add(consulta.getString(5));
            preguntasT.add(n);

            return false;

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }
}
