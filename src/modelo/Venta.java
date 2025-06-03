package modelo;

import java.time.LocalDate;
import java.util.*;

public class Venta {
    private int id = 0;
    private Cliente cliente = null;
    private HashMap<Producto, Integer> productos = new HashMap();
    private LocalDate fecha = null;

    public Venta(Cliente cliente, HashMap<Producto, Integer> productos, int id){
        setId(id);
        setCliente(cliente);
        setProductos(productos);
        setFecha(LocalDate.now());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Venta)) {
            return false;
        }
        Venta temp = (Venta) obj;

        return this.id == temp.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cliente);
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.productos);
        hash = 53 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public String toString() {
        return  "\nID de la venta:" + this.id +
                "\n" + "Fecha: " + this.fecha +
                "\n" + "Cliente: \n" + this.cliente +
                "\n" + "Producto: \n" + this.productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("Menor o igual a 0");
        }
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Map getProductos() {
        return productos;
    }

    public void setProductos(HashMap<Producto, Integer> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
