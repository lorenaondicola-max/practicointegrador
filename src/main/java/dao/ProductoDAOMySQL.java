package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import modelo.Producto;

/**
 * Implementación del DAO que guarda los productos en MySQL usando JDBC.
 *
 * Tabla "productos":
 *   id INT (AUTO_INCREMENT, PK) | nombre VARCHAR(100) | categoria VARCHAR(50)
 *   precio DECIMAL(10,2)        | stock INT
 *
 * Reglas para todos los métodos:
 *  - Pedir la conexión con Conexion.obtenerConexion().
 *  - Usar PreparedStatement con ? para los valores (nunca concatenar).
 *  - Abrir Connection, PreparedStatement y ResultSet con try-with-resources.
 *  - No atrapar la SQLException: el método la declara con throws y el
 *    menú se encarga de mostrar el error.
 *
 * Las líneas "return ..." que ya están escritas sirven solo para que el
 * proyecto compile. Reemplazalas cuando completes cada método.
 */
public class ProductoDAOMySQL implements ProductoDAO {

    @Override
    public List<Producto> listarTodos() throws SQLException {
        // TODO Ejercicio 3: SELECT de todos los productos ordenados por id.
        //  Recorrer el ResultSet con while (rs.next()) y agregar cada fila
        //  a una lista usando el método mapear() de más abajo.
        String sql = "SELECT * FROM productos";

        List<Producto> resultado = new ArrayList<>();

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    resultado.add(mapear(rs));
                }
            }
        }
        return resultado;
    }

    @Override
    public Producto buscarPorId(int id) throws SQLException {
        // TODO Ejercicio 4: SELECT de un producto filtrando por id.
        //  Si el ResultSet tiene una fila, devolver el producto; si no, null.
        String sql = "SELECT * FROM productos WHERE id = ?";

        Producto productoBuscado = null;

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    productoBuscado = mapear(rs);
                }
            }
        }
        return productoBuscado;
    }

    @Override
    public void insertar(Producto producto) throws SQLException {
        // TODO Ejercicio 5: INSERT de un producto nuevo.
        //  No incluyas la columna id: la genera MySQL (AUTO_INCREMENT).

        String sql = "INSERT INTO productos (nombre, categoria, precio, stock)" + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps =  con.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            int filas = ps.executeUpdate();
        }
    }

    @Override
    public boolean actualizar(Producto producto) throws SQLException {
        // TODO Ejercicio 6: UPDATE de nombre, categoría, precio y stock.
        //  ¡Con WHERE! Devolver true si executeUpdate() modificó alguna fila.

        String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        // TODO Ejercicio 7: DELETE del producto con el id indicado.
        //  ¡Con WHERE! Devolver true si executeUpdate() eliminó alguna fila.

        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String texto) throws SQLException {
        // TODO Opcional A: SELECT de los productos cuyo nombre contiene el texto (LIKE).
        List<Producto> listaBuscarPorNombre = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? ";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, "%" + texto + "%");
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    listaBuscarPorNombre.add(mapear(rs));
                }
            }
        }
        return listaBuscarPorNombre;
    }

    @Override
    public List<Producto> listarStockBajo(int minimo) throws SQLException {
        // TODO Opcional B: SELECT de los productos con stock menor al mínimo.

        List<Producto> listaStockBajo = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE stock < ?";

        try(Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, minimo);

            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    listaStockBajo.add(mapear(rs));
                }
            }
        }
        return listaStockBajo;
    }

    /**
     * Convierte la fila actual del ResultSet en un objeto Producto.
     * Lo vas a reutilizar en todos los métodos que hacen SELECT.
     */
    private Producto mapear(ResultSet rs) throws SQLException {
        // TODO Ejercicio 3: leer cada columna con rs.getInt("id"), rs.getString("nombre"), etc.
        //  y crear el Producto con el constructor que recibe el id.

        return new Producto(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"));
    }
}
