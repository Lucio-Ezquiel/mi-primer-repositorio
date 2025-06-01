package tienda.dao.impl;

import tienda.dao.CategoriaDao;
import tienda.model.Categoria;
import tienda.util.ConexionBaseDatos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDaoImpl implements CategoriaDao {

    private static final Logger logger = LogManager.getLogger(CategoriaDaoImpl.class);

    @Override
    public void crear(Categoria categoria) {
        String sql = "INSERT INTO Categorias (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        categoria.setId(rs.getInt(1));
                    }
                }
                logger.info("Categoría creada: {}", categoria.getNombre());
            }
        } catch (SQLException e) {
            logger.error("Error al crear categoría: {}", e.getMessage(), e);
        }
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        String sql = "SELECT id, nombre, descripcion FROM Categorias WHERE id = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setDescripcion(rs.getString("descripcion"));
                    return Optional.of(categoria);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al buscar categoría por ID: {}", e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Categoria> listarTodos() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM Categorias";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            logger.error("Error al listar categorías: {}", e.getMessage(), e);
        }
        return categorias;
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE Categorias SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getId());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                logger.info("Categoría actualizada: ID {}", categoria.getId());
            } else {
                logger.warn("No se encontró categoría con ID {} para actualizar.", categoria.getId());
            }
        } catch (SQLException e) {
            logger.error("Error al actualizar categoría: {}", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM Categorias WHERE id = ?";
        try (Connection conn = ConexionBaseDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                logger.info("Categoría eliminada: ID {}", id);
            } else {
                logger.warn("No se encontró categoría con ID {} para eliminar.", id);
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar categoría: {}", e.getMessage(), e);
        }
    }
}
