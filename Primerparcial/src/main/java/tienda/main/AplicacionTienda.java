package tienda.main;

import tienda.dao.CategoriaDao;
import tienda.dao.ProductoDao;
import tienda.dao.impl.CategoriaDaoImpl;
import tienda.dao.impl.ProductoDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AplicacionTienda {

    private static final Logger logger = LogManager.getLogger(AplicacionTienda.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductoDao productoDao = new ProductoDaoImpl();
    private static final CategoriaDao categoriaDao = new CategoriaDaoImpl();

    public static void main(String[] args) {
        logger.info("Iniciando aplicación de Tienda Electrónica.");
        mostrarMenuPrincipal();
        logger.info("Aplicación de Tienda Electrónica finalizada.");
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Categorías");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    MenuProducto.menuProductos(productoDao, categoriaDao, scanner);
                    break;
                case 2:
                    MenuCategoria.menuCategorias(categoriaDao, scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    logger.warn("Opción de menú principal inválida: {}", opcion);
            }
        } while (opcion != 0);
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
