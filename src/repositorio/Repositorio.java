package repositorio;

import java.util.HashMap;

public interface Repositorio<M> {

    public void guardar(M obj);

    public void eliminar(int id);

    public HashMap<Integer, M> obtener();

    public M obtenerPorID(int id);

    public int obtenerCantidad();

    public boolean contiene(int id);
}