package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Producto;

/**
 * DAO = Data Access Object (objeto de acceso a datos).
 *
 * Esta interfaz define QUÉ operaciones se pueden hacer con los productos,
 * pero no dice CÓMO se guardan. La clase Kiosco solo conoce esta interfaz,
 * por eso se puede cambiar dónde se guardan los datos (memoria o MySQL)
 * sin tocar el resto del programa.
 *
 * Implementaciones:
 *  - ProductoDAOMemoria: guarda los productos en un ArrayList (ya hecha).
 *  - ProductoDAOMySQL:   guarda los productos en MySQL usando JDBC.
 */
public interface ProductoDAO {

    /**
     * Devuelve todos los productos ordenados por id.
     * Si no hay productos, devuelve una lista vacía (nunca null).
     */
    List<Producto> listarTodos() throws SQLException;

    /**
     * Busca un producto por su id.
     * Devuelve el producto encontrado, o null si no existe.
     */
    Producto buscarPorId(int id) throws SQLException;

    /**
     * Agrega un producto nuevo. El id lo genera la base de datos
     * (AUTO_INCREMENT), así que no se guarda el id que traiga el objeto.
     */
    void insertar(Producto producto) throws SQLException;

    /**
     * Modifica nombre, categoría, precio y stock del producto cuyo id
     * coincide con producto.getId().
     * Devuelve true si se modificó el producto, false si no existía.
     */
    boolean actualizar(Producto producto) throws SQLException;

    /**
     * Elimina el producto con el id indicado.
     * Devuelve true si se eliminó, false si no existía.
     */
    boolean eliminar(int id) throws SQLException;

    /**
     * (Opcional A) Devuelve los productos cuyo nombre CONTIENE el texto
     * indicado. Ejemplo: "agua" encuentra "Agua mineral 500 ml" y
     * "Galletitas de agua".
     */
    List<Producto> buscarPorNombre(String texto) throws SQLException;

    /**
     * (Opcional B) Devuelve los productos con stock MENOR al mínimo
     * indicado, ordenados por id.
     */
    List<Producto> listarStockBajo(int minimo) throws SQLException;
}
