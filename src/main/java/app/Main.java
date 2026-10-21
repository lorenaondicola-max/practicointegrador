package app;

import dao.ProductoDAO;
import dao.ProductoDAOMySQL;
import gestor.Kiosco;

/**
 * Punto de entrada del sistema de kiosco.
 */
public class Main {

    public static void main(String[] args) {
        // Por ahora el sistema guarda los datos en memoria: funciona, pero al
        // cerrar el programa se pierden todos los cambios.
        //
        // TODO Ejercicio 2: reemplazá ProductoDAOMemoria por ProductoDAOMySQL
        //  (y corregí el import). ¡Es el único cambio que hace falta acá!
        //ProductoDAO productoDAO = new ProductoDAOMemoria();

        ProductoDAO productoDAO = new ProductoDAOMySQL();

        Kiosco kiosco = new Kiosco(productoDAO);
        Menu menu = new Menu(kiosco);
        menu.iniciar();
    }
}
