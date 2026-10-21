package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import config.Conexion;

/**
 * Programa para comprobar que la conexión a MySQL funciona (Ejercicio 1).
 * Ejecutalo con el botón "Run" de IntelliJ.
 *
 * NO hace falta modificar esta clase.
 */
public class ProbarConexion {

    public static void main(String[] args) {
        System.out.println("Intentando conectar a MySQL...");

        try (Connection con = Conexion.obtenerConexion()) {
            if (con == null) {
                System.out.println("obtenerConexion() devolvió null: todavía no completaste el Ejercicio 1.");
                return;
            }
            DatabaseMetaData md = con.getMetaData();
            System.out.println("¡Conexión exitosa!");
            System.out.println("Servidor: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
        } catch (SQLException e) {
            System.out.println("No se pudo conectar: " + e.getMessage());
        }
    }
}
