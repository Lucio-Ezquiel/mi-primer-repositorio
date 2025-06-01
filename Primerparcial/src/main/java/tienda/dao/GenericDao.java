package tienda.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {
    void crear(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(ID id);
}
