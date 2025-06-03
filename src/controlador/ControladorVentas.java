package controlador;

import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import repositorio.RepositorioVentas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorVentas implements Controlador<Venta> {
    private static final RepositorioVentas repoVentas = new RepositorioVentas();
    private static final ControladorCliente controlCliente = new ControladorCliente();
    private static final ControladorProducto controlProducto = new ControladorProducto();


    @Override
    public void guardar(Venta obj) {
        repoVentas.guardar(obj);
    }

    public void guardarVenta(int idCliente, HashMap<Integer, Integer> idProductos,  int idVenta){
        HashMap<Producto, Integer> productos = new HashMap<>();
        idProductos.forEach((id, cant) -> productos.put(controlProducto.obtenerPorID(id), cant));
        Cliente cliente = controlCliente.obtenerPorID(idCliente);

        guardar(new Venta(cliente, productos, idVenta));
    }

    @Override
    public void eliminar(int id) {
        if (!repoVentas.contiene(id)){
            throw new IllegalArgumentException();
        }
        repoVentas.eliminar(id);
    }

    @Override
    public HashMap<Integer, Venta> obtener() {
        return repoVentas.obtener();
    }

    @Override
    public Venta obtenerPorID(int id) {
        return repoVentas.obtenerPorID(id);
    }

    @Override
    public void editar(Venta obj) {
        //
    }

    public ArrayList<Venta> listarPorFecha(LocalDate inicio, LocalDate fin){
        ArrayList<Venta> ventas = new ArrayList();

        repoVentas.obtener().forEach((clave, valor) -> {
            if(valor.getFecha().isAfter(inicio) && valor.getFecha().isBefore(fin)){
                ventas.add(valor);
            }
        });

        return ventas;
    }
}
