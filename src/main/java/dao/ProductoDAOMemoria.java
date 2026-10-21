package dao;

import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

/**
 * Implementación del DAO que guarda los productos en memoria (un ArrayList).
 *
 * Funciona, pero tiene un gran problema: cuando el programa se cierra,
 * todos los cambios se pierden. Por eso necesitamos una base de datos.
 *
 * NO hace falta modificar esta clase. Sirve de referencia para ver qué
 * tiene que hacer cada método.
 */
public class ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> productos;
    private int proximoId;

    public ProductoDAOMemoria() {
        productos = new ArrayList<>();
        proximoId = 1;

        // Los mismos datos iniciales que carga el script sql/kiosco.sql
        insertar(new Producto("Alfajor de chocolate", "Golosinas", 1200, 40));
        insertar(new Producto("Chicle de menta", "Golosinas", 500, 100));
        insertar(new Producto("Agua mineral 500 ml", "Bebidas", 900, 30));
        insertar(new Producto("Gaseosa cola 1,5 L", "Bebidas", 2800, 24));
        insertar(new Producto("Jugo de naranja 1 L", "Bebidas", 1900, 12));
        insertar(new Producto("Papas fritas 150 g", "Snacks", 2500, 18));
        insertar(new Producto("Maní salado 200 g", "Snacks", 1700, 15));
        insertar(new Producto("Galletitas de agua", "Almacén", 1100, 25));
        insertar(new Producto("Yerba mate 1 kg", "Almacén", 4500, 10));
        insertar(new Producto("Pilas AA x4", "Varios", 3600, 8));
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    @Override
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void insertar(Producto producto) {
        producto.setId(proximoId);
        proximoId++;
        productos.add(producto);
    }

    @Override
    public boolean actualizar(Producto producto) {
        Producto existente = buscarPorId(producto.getId());
        if (existente == null) {
            return false;
        }
        existente.setNombre(producto.getNombre());
        existente.setCategoria(producto.getCategoria());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        return true;
    }

    @Override
    public boolean eliminar(int id) {
        Producto existente = buscarPorId(id);
        if (existente == null) {
            return false;
        }
        productos.remove(existente);
        return true;
    }

    @Override
    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public List<Producto> listarStockBajo(int minimo) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() < minimo) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
