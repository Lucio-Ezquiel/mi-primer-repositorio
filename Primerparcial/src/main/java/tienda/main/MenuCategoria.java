package tienda.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tienda.dao.CategoriaDao;
import tienda.model.Categoria;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuCategoria {
    private static final Logger logger = LogManager.getLogger(AplicacionTienda.class);
    private static Scanner scanner;
    private static CategoriaDao categoriaDao;

    public static void menuCategorias(CategoriaDao dao, Scanner sc) {
        categoriaDao = dao;
        scanner = sc;

        int opcion;
        do {
            System.out.println("\n--- Gestión de Categorías ---");
            System.out.println("1. Crear Categoría");
            System.out.println("2. Listar Categorías");
            System.out.println("3. Buscar Categoría por ID");
            System.out.println("4. Actualizar Categoría");
            System.out.println("5. Eliminar Categoría");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    buscarCategoriaPorId();
                    break;
                case 4:
                    actualizarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    logger.warn("Opción de menú de categorías inválida: {}", opcion);
            }
        } while (opcion != 0);
    }

    private static void crearCategoria() {
        System.out.println("\n--- Crear Nueva Categoría ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            logger.warn("Intento de crear categoría con nombre vacío.");
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        Categoria nuevaCategoria = new Categoria(0, nombre, descripcion);
        categoriaDao.crear(nuevaCategoria);
        System.out.println("Categoría creada exitosamente.");
    }

    private static void listarCategorias() {
        System.out.println("\n--- Listado de Categorías ---");
        List<Categoria> categorias = categoriaDao.listarTodos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    private static void buscarCategoriaPorId() {
        System.out.println("\n--- Buscar Categoría por ID ---");
        System.out.print("Ingrese el ID de la categoría: ");
        int id = leerEntero();
        Optional<Categoria> categoria = categoriaDao.buscarPorId(id);
        categoria.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Categoría con ID " + id + " no encontrada.")
        );
    }

    private static void actualizarCategoria() {
        System.out.println("\n--- Actualizar Categoría ---");
        System.out.print("Ingrese el ID de la categoría a actualizar: ");
        int id = leerEntero();
        Optional<Categoria> categoriaExistente = categoriaDao.buscarPorId(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            System.out.println("Categoría actual: " + categoria);

            System.out.print("Nuevo Nombre (dejar vacío para mantener '" + categoria.getNombre() + "'): ");
            String nombre = scanner.nextLine();
            if (!nombre.trim().isEmpty()) {
                categoria.setNombre(nombre);
            }

            System.out.print("Nueva Descripción (dejar vacío para mantener '" + categoria.getDescripcion() + "'): ");
            String descripcion = scanner.nextLine();
            if (!descripcion.trim().isEmpty()) {
                categoria.setDescripcion(descripcion);
            }

            categoriaDao.actualizar(categoria);
            System.out.println("Categoría actualizada exitosamente.");
        } else {
            System.out.println("Categoría con ID " + id + " no encontrada.");
            logger.warn("Intento de actualizar categoría inexistente con ID: {}", id);
        }
    }

    private static void eliminarCategoria() {
        System.out.println("\n--- Eliminar Categoría ---");
        System.out.print("Ingrese el ID de la categoría a eliminar: ");
        int id = leerEntero();
        categoriaDao.eliminar(id);
        System.out.println("Solicitud de eliminación de categoría procesada.");
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
