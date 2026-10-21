package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Centraliza los datos de conexión a la base de datos.
 * Cada vez que el DAO necesita hablar con MySQL, pide una conexión acá.
 */
public class Conexion {

    // TODO Ejercicio 1: completá los datos de conexión de TU instalación de MySQL.
    //  - URL: "jdbc:mysql://servidor:puerto/base" (servidor localhost, puerto 3306, base kiosco)
    //  - USUARIO y CLAVE: los que usás para entrar a MySQL Workbench
    private static final String URL = "jdbc:mysql://localhost:3306/kiosco";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    public static Connection obtenerConexion() throws SQLException {
        // TODO Ejercicio 1: devolver una conexión nueva usando DriverManager
        //  con los tres datos de arriba.
        return DriverManager.getConnection(URL,USUARIO,CLAVE);
    }
}
