package gestor;

import java.sql.SQLException;
import java.util.List;

import dao.ProductoDAO;
import modelo.Producto;

/**
 * Clase gestora del sistema: contiene la lógica del kiosco.
 *
 * Valida los datos y le pide al DAO que los guarde o los busque.
 * No sabe nada de SQL ni de JDBC: solo conoce la interfaz ProductoDAO.
 *
 * NO hace falta modificar esta clase.
 */
public class Kiosco {

    private ProductoDAO productoDAO;

    public Kiosco(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public List<Producto> listarProductos() throws SQLException {
        return productoDAO.listarTodos();
    }

    public Producto buscarProducto(int id) throws SQLException {
        return productoDAO.buscarPorId(id);
    }

    public List<Producto> buscarProductosPorNombre(String texto) throws SQLException {
        return productoDAO.buscarPorNombre(texto);
    }

    public List<Producto> listarProductosConStockBajo(int minimo) throws SQLException {
        return productoDAO.listarStockBajo(minimo);
    }

    public void agregarProducto(Producto producto) throws SQLException {
        validar(producto);
        productoDAO.insertar(producto);
    }

    public boolean modificarProducto(Producto producto) throws SQLException {
        validar(producto);
        return productoDAO.actualizar(producto);
    }

    public boolean eliminarProducto(int id) throws SQLException {
        return productoDAO.eliminar(id);
    }

    private void validar(Producto producto) {
        if (producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (producto.getCategoria().isBlank()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }
}
