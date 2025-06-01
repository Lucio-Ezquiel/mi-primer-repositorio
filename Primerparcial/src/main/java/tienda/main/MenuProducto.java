package tienda.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tienda.dao.CategoriaDao;
import tienda.dao.ProductoDao;
import tienda.model.Categoria;
import tienda.model.Producto;


import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuProducto {
    private static final Logger logger = LogManager.getLogger(AplicacionTienda.class);
    private static Scanner scanner;
    private static ProductoDao productoDao;
    private static CategoriaDao categoriaDao;

    public static void menuProductos(ProductoDao prodDao, CategoriaDao catDao, Scanner sc) {
        productoDao = prodDao;
        categoriaDao = catDao;
        scanner = sc;

        int opcion;
        do {
            System.out.println("\n--- Gestión de Productos ---");
            System.out.println("1. Crear Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Buscar Producto por ID");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProductoPorId();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    logger.warn("Opción de menú de productos inválida: {}", opcion);
            }
        } while (opcion != 0);
    }

    private static void crearProducto() {
        System.out.println("\n--- Crear Nuevo Producto ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            logger.warn("Intento de crear producto con nombre vacío.");
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        BigDecimal precio;
        while (true) {
            System.out.print("Precio: ");
            try {
                precio = new BigDecimal(scanner.nextLine());
                if (precio.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("El precio no puede ser negativo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para el precio. Por favor, ingrese un número válido.");
                logger.error("Error de formato al ingresar precio: {}", e.getMessage());
            }
        }

        int stock;
        while (true) {
            System.out.print("Stock: ");
            stock = leerEntero();
            if (stock < 0) {
                System.out.println("El stock no puede ser negativo.");
                continue;
            }
            break;
        }

        listarCategoriasParaSeleccion();
        System.out.print("ID de Categoría: ");
        int categoriaId = leerEntero();
        Optional<Categoria> categoriaExistente = categoriaDao.buscarPorId(categoriaId);
        if (categoriaExistente.isEmpty()) {
            System.out.println("La categoría con ID " + categoriaId + " no existe. No se puede crear el producto.");
            logger.warn("Intento de crear producto con categoría ID inexistente: {}", categoriaId);
            return;
        }

        Producto nuevoProducto = new Producto(0, nombre, descripcion, precio, stock, categoriaId);
        productoDao.crear(nuevoProducto);
        System.out.println("Producto creado exitosamente.");
    }

    private static void listarProductos() {
        System.out.println("\n--- Listado de Productos ---");
        List<Producto> productos = productoDao.listarTodos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void buscarProductoPorId() {
        System.out.println("\n--- Buscar Producto por ID ---");
        System.out.print("Ingrese el ID del producto: ");
        int id = leerEntero();
        Optional<Producto> producto = productoDao.buscarPorId(id);
        producto.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Producto con ID " + id + " no encontrado.")
        );
    }

    private static void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = leerEntero();
        Optional<Producto> productoExistente = productoDao.buscarPorId(id);

        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            System.out.println("Producto actual: " + producto);

            System.out.print("Nuevo Nombre (dejar vacío para mantener '" + producto.getNombre() + "'): ");
            String nombre = scanner.nextLine();
            if (!nombre.trim().isEmpty()) {
                producto.setNombre(nombre);
            }

            System.out.print("Nueva Descripción (dejar vacío para mantener '" + producto.getDescripcion() + "'): ");
            String descripcion = scanner.nextLine();
            if (!descripcion.trim().isEmpty()) {
                producto.setDescripcion(descripcion);
            }

            BigDecimal precio;
            while (true) {
                System.out.print("Nuevo Precio (dejar vacío para mantener " + producto.getPrecio() + "): ");
                String precioStr = scanner.nextLine();
                if (precioStr.trim().isEmpty()) {
                    precio = producto.getPrecio();
                    break;
                }
                try {
                    precio = new BigDecimal(precioStr);
                    if (precio.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("El precio no puede ser negativo.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida para el precio. Por favor, ingrese un número válido.");
                    logger.error("Error de formato al ingresar nuevo precio: {}", e.getMessage());
                }
            }
            producto.setPrecio(precio);

            int stock;
            while (true) {
                System.out.print("Nuevo Stock (dejar vacío para mantener " + producto.getStock() + "): ");
                String stockStr = scanner.nextLine();
                if (stockStr.trim().isEmpty()) {
                    stock = producto.getStock();
                    break;
                }
                try {
                    stock = Integer.parseInt(stockStr);
                    if (stock < 0) {
                        System.out.println("El stock no puede ser negativo.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida para el stock. Por favor, ingrese un número válido.");
                    logger.error("Error de formato al ingresar nuevo stock: {}", e.getMessage());
                }
            }
            producto.setStock(stock);

            listarCategoriasParaSeleccion();
            System.out.print("Nuevo ID de Categoría (dejar 0 para mantener " + producto.getCategoriaId() + "): ");
            int nuevaCategoriaId = leerEntero();
            if (nuevaCategoriaId != 0) {
                Optional<Categoria> categoriaNueva = categoriaDao.buscarPorId(nuevaCategoriaId);
                if (categoriaNueva.isPresent()) {
                    producto.setCategoriaId(nuevaCategoriaId);
                } else {
                    System.out.println("La categoría con ID " + nuevaCategoriaId + " no existe. Se mantendrá la categoría actual.");
                    logger.warn("Intento de actualizar producto con nueva categoría ID inexistente: {}", nuevaCategoriaId);
                }
            }

            productoDao.actualizar(producto);
            System.out.println("Producto actualizado exitosamente.");
        } else {
            System.out.println("Producto con ID " + id + " no encontrado.");
            logger.warn("Intento de actualizar producto inexistente con ID: {}", id);
        }
    }

    private static void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = leerEntero();
        productoDao.eliminar(id);
        System.out.println("Solicitud de eliminación de producto procesada.");
    }

    private static void listarCategoriasParaSeleccion() {
        System.out.println("\n--- Categorías Disponibles ---");
        List<Categoria> categorias = categoriaDao.listarTodos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas. Por favor, cree al menos una categoría.");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                logger.error("Error de formato al leer entero: {}", e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
