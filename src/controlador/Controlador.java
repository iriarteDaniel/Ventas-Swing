package controlador;

import java.util.HashMap;

public interface Controlador<M> {
    public void guardar(M obj);

    public void eliminar(int id);

    public HashMap<Integer, M> obtener();

    public M obtenerPorID(int id);

    public void editar(M obj);

}
