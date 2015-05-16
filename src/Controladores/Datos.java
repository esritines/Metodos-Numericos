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
    private static String texto;
    private static ArrayList<String> respuestas = new ArrayList<>();
    

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

    public static boolean insertarPregunta(String pregunta, String array[]) {
        int verdadera, id;
        try {
            Statement capturarRegistro;
            
            capturarRegistro = ConexionBD.conexion.createStatement();
            ResultSet consulta = capturarRegistro.executeQuery("select MAX(id) from preguntas");
            
            consulta.next();
            
            id = consulta.getInt(1) + 1;

            consulta.close();
            
            capturarRegistro = ConexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            for (int i = 0; i < 4; i++) {
                verdadera = 0;
                if(i == 0){
                    verdadera = 1;
                }
                capturarRegistro.executeUpdate("insert into preguntas (id, pregunta, verdadera, respuesta) "
                + "values('" + id + "','" + pregunta + "','" + verdadera + "','" + array[i] + "')");
            }

            capturarRegistro.close();
            
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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

    public String traerPregunta() {

        String preguntaTemp = "";
        recorrerP = preguntasP.iterator();
        recorrerT = preguntasT.iterator();

        boolean valor;

        do {
            valor = false;
            int n = (int) (Math.random() * 2) + 1;

            try {
                Statement consultarDatos = ConexionBD.conexion.createStatement();
//                ResultSet consulta = consultarDatos.executeQuery
//                ("select pregunta, respuesta, verdadera, texto from preguntas where id = '" + n + "'");    //metodos numericos
                ResultSet consulta = consultarDatos.executeQuery
                ("select pregunta, respuesta, verdadera from preguntas where id = '" + n + "'");     //interaccion humano
                
                while (recorrerP.hasNext()) {
                    if (n == recorrerP.next()) {
                        valor = true;
                        break;
                    }
                }

                if (!valor) {
                    while (consulta.next()) {
                        preguntaTemp = consulta.getString(1);
                        respuestas.add(consulta.getString(2));
                        texto = consulta.getString(3);
                    }
                    preguntasP.add(n);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
        } while (valor);
        return preguntaTemp;
    }
}
