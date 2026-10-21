package app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import gestor.Kiosco;
import modelo.Producto;

/**
 * Menú de consola del sistema de kiosco.
 *
 * El menú solo muestra y pide datos: todo lo demás se lo pide a la clase
 * Kiosco. No sabe si los productos están en memoria o en MySQL.
 *
 * NO hace falta modificar esta clase.
 */
public class Menu {

    private Kiosco kiosco;
    private Scanner scanner;

    public Menu(Kiosco kiosco) {
        this.kiosco = kiosco;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            System.out.print("Elegí una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            try {
                switch (opcion) {
                    case 1:
                        listarProductos();
                        break;
                    case 2:
                        buscarPorId();
                        break;
                    case 3:
                        buscarPorNombre();
                        break;
                    case 4:
                        listarStockBajo();
                        break;
                    case 5:
                        agregarProducto();
                        break;
                    case 6:
                        modificarProducto();
                        break;
                    case 7:
                        eliminarProducto();
                        break;
                    case 0:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Datos inválidos: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println();
        System.out.println("===== SISTEMA DE KIOSCO =====");
        System.out.println("1. Listar productos");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Buscar productos por nombre");
        System.out.println("4. Listar productos con stock bajo");
        System.out.println("5. Agregar producto");
        System.out.println("6. Modificar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("0. Salir");
    }

    private void listarProductos() throws SQLException {
        System.out.println("--- LISTADO DE PRODUCTOS ---");
        mostrarLista(kiosco.listarProductos());
    }

    private void buscarPorId() throws SQLException {
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = kiosco.buscarProducto(id);
        if (producto == null) {
            System.out.println("No existe un producto con ID " + id + ".");
        } else {
            System.out.println(producto);
        }
    }

    private void buscarPorNombre() throws SQLException {
        System.out.print("Texto a buscar en el nombre: ");
        String texto = scanner.nextLine();
        mostrarLista(kiosco.buscarProductosPorNombre(texto));
    }

    private void listarStockBajo() throws SQLException {
        System.out.print("Mostrar productos con stock menor a: ");
        int minimo = scanner.nextInt();
        scanner.nextLine();
        mostrarLista(kiosco.listarProductosConStockBajo(minimo));
    }

    private void agregarProducto() throws SQLException {
        System.out.println("--- NUEVO PRODUCTO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Producto nuevo = new Producto(nombre, categoria, precio, stock);
        kiosco.agregarProducto(nuevo);
        System.out.println("Producto agregado.");
    }

    private void modificarProducto() throws SQLException {
        System.out.print("ID del producto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producto producto = kiosco.buscarProducto(id);
        if (producto == null) {
            System.out.println("No existe un producto con ID " + id + ".");
            return;
        }

        System.out.println("Datos actuales: " + producto);
        System.out.print("Nuevo nombre: ");
        producto.setNombre(scanner.nextLine());
        System.out.print("Nueva categoría: ");
        producto.setCategoria(scanner.nextLine());
        System.out.print("Nuevo precio: ");
        producto.setPrecio(scanner.nextDouble());
        System.out.print("Nuevo stock: ");
        producto.setStock(scanner.nextInt());
        scanner.nextLine();

        if (kiosco.modificarProducto(producto)) {
            System.out.println("Producto modificado.");
        } else {
            System.out.println("No se pudo modificar el producto.");
        }
    }

    private void eliminarProducto() throws SQLException {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (kiosco.eliminarProducto(id)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No existe un producto con ID " + id + ".");
        }
    }

    private void mostrarLista(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }
        for (Producto p : productos) {
            System.out.println(p);
        }
        System.out.println("Total: " + productos.size() + " producto/s.");
    }
}
