package repositorio;

import modelo.Producto;

import java.util.HashMap;

public class RepositorioProducto implements Repositorio<Producto>{
    private static final String NUM_SERIE_INVALIDO = "[ERROR] NO SE ENCUENTRA PRODUCTO CON EL NUMERO DE SERIE ELEGIDO";

    private static final HashMap<Integer, Producto> productos = new HashMap();

    public HashMap<Integer, Producto> obtener(){
        return productos;
    }

    public int obtenerCantidad(){
        return productos.size();
    }

    public Producto obtenerPorID(int nroDeSerie){
        if(!productos.containsKey(nroDeSerie)){
            throw new IllegalArgumentException(NUM_SERIE_INVALIDO);
        }
        return productos.get(nroDeSerie);
    }

    public boolean contiene(int nroDeSerie){
        return productos.containsKey(nroDeSerie);
    }

    @Override
    public void guardar(Producto prod) {
        productos.put(prod.getNroDeSerie(), prod);
    }

    @Override
    public void eliminar(int nroDeSerie) {
        if(!productos.containsKey(nroDeSerie)){
            throw new IllegalArgumentException(NUM_SERIE_INVALIDO);
        }
        productos.remove(nroDeSerie);
    }
}
