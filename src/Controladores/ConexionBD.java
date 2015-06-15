package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    public static Connection conexion;

    public static void conectar() {
        try {
            String BaseDeDatos = "jdbc:ucanaccess://./src/Base de Datos/puzzle.accdb";
            conexion = DriverManager.getConnection(BaseDeDatos, "", "");
            if (ConexionBD.conexion != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (Exception e) {
        }
    }
}
