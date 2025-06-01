package tienda.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBaseDatos {

    private static final Logger logger = LogManager.getLogger(ConexionBaseDatos.class);
    private static final String URL = "jdbc:h2:./tienda_db/tienda";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            inicializarBaseDatos();
        } catch (ClassNotFoundException e) {
            logger.fatal("No se pudo cargar el driver de H2: {}", e.getMessage(), e);
            throw new RuntimeException("Error al cargar el driver de H2", e);
        }
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void inicializarBaseDatos() {
        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS Categorias (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nombre VARCHAR(255) NOT NULL UNIQUE," +
                    "descripcion VARCHAR(255)" +
                    ");");
            logger.info("Tabla 'Categorias' verificada/creada.");

            stmt.execute("CREATE TABLE IF NOT EXISTS Productos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nombre VARCHAR(255) NOT NULL," +
                    "descripcion VARCHAR(255)," +
                    "precio DECIMAL(10, 2) NOT NULL," +
                    "stock INT NOT NULL," +
                    "categoria_id INT," +
                    "FOREIGN KEY (categoria_id) REFERENCES Categorias(id) ON DELETE SET NULL" +
                    ");");
            logger.info("Tabla 'Productos' verificada/creada.");

        } catch (SQLException e) {
            logger.fatal("Error al inicializar la base de datos: {}", e.getMessage(), e);
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }
}
