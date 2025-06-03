package repositorio;

import modelo.Venta;

import java.util.HashMap;

public class RepositorioVentas implements Repositorio<Venta>{
    private static final String ID_INVALIDO = "[ERROR] NO SE ENCUENTRA VENTA CON EL ID ELEGIDO";

    private static HashMap<Integer, Venta> ventas = new HashMap();

    @Override
    public void guardar(Venta venta) {
        ventas.put(venta.getId(), venta);
    }

    @Override
    public void eliminar(int id) {
        if(!ventas.containsKey(id)){
            throw new IllegalArgumentException(ID_INVALIDO);
        }
        ventas.remove(id);
    }

    @Override
    public HashMap<Integer, Venta> obtener() {
        return ventas;
    }

    @Override
    public Venta obtenerPorID(int id) {
        if(!ventas.containsKey(id)){
            throw new IllegalArgumentException(ID_INVALIDO);
        }
        return ventas.get(id);
    }

    @Override
    public int obtenerCantidad() {
        return ventas.size();
    }

    public boolean contiene(int id){
        return ventas.containsKey(id);
    }
}
