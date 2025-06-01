package tienda.dao.impl;

import tienda.dao.ProductoDao;
import tienda.model.Producto;
import tienda.util.ConexionBaseDatos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDaoImpl implements ProductoDao {

    private static final Logger logger = LogManager.getLogger(ProductoDaoImpl.class);

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO Productos (nombre, descripcion, precio, stock, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setBigDecimal(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getCategoriaId());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setId(rs.getInt(1));
                    }
                }
                logger.info("Producto creado: {}", producto.getNombre());
            }
        } catch (SQLException e) {
            logger.error("Error al crear producto: {}", e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {
        String sql = "SELECT id, nombre, descripcion, precio, stock, categoria_id FROM Productos WHERE id = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getBigDecimal("precio"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setCategoriaId(rs.getInt("categoria_id"));
                    return Optional.of(producto);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al buscar producto por ID: {}", e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, precio, stock, categoria_id FROM Productos";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            logger.error("Error al listar productos: {}", e.getMessage(), e);
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE Productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setBigDecimal(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getCategoriaId());
            stmt.setInt(6, producto.getId());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                logger.info("Producto actualizado: ID {}", producto.getId());
            } else {
                logger.warn("No se encontró producto con ID {} para actualizar.", producto.getId());
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar producto: {}", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM Productos WHERE id = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                logger.info("Producto eliminado: ID {}", id);
            } else {
                logger.warn("No se encontró producto con ID {} para eliminar.", id);
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar producto: {}", e.getMessage(), e);
        }
    }
}
