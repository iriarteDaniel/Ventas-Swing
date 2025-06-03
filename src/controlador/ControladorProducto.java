package controlador;

import modelo.Cliente;
import modelo.Producto;
import repositorio.RepositorioProducto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ControladorProducto implements Controlador<Producto>{
    private static final RepositorioProducto repo = new RepositorioProducto();

    public void guardar(Producto prod){
        repo.guardar(prod);
    }

    public void eliminar(int id){
        repo.eliminar(id);
    }

    public HashMap<Integer, Producto> obtener(){
        return repo.obtener();
    }

    public Producto obtenerPorID(int nroDeSerie){
        return repo.obtenerPorID(nroDeSerie);
    }

    public void editar(Producto prod){
        Producto temp = repo.obtenerPorID(prod.getNroDeSerie());
        temp.setNombre(prod.getNombre());
        temp.setCategoria(prod.getCategoria());
    }

    public ArrayList<Producto> getSortedByNameList(boolean reversed){
        ArrayList<Producto> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Producto::getNombre).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Producto::getNombre));
        }

        return lista;
    }

    public ArrayList<Producto> getSortedByCategoriaList(boolean reversed){
        ArrayList<Producto> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Producto::getCategoria).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Producto::getCategoria));
        }

        return lista;
    }

    public ArrayList<Producto> getSortedByIDList(boolean reversed){
        ArrayList<Producto> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Producto::getNroDeSerie).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Producto::getNroDeSerie));
        }

        return lista;
    }


}
