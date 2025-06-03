package controlador;

import modelo.Cliente;
import repositorio.RepositorioCliente;

import java.awt.List;
import java.util.*;

public class ControladorCliente implements Controlador<Cliente> {
    private static RepositorioCliente repo = new RepositorioCliente();

    public void guardar(Cliente cliente){
        repo.guardar(cliente);
    }

    public Cliente obtenerPorID(int id){
        return repo.obtenerPorID(id);
    }

    public HashMap<Integer, Cliente> obtener(){
        return repo.obtener();
    }

    public void editar(Cliente cliente){
        Cliente temp = repo.obtenerPorID(cliente.getNumDocumento());
        temp.setNombre(cliente.getNombre());
        temp.setEdad(cliente.getEdad());
        temp.setEmail(cliente.getEmail());
    }

    public void eliminar(int numDocumento){
        repo.eliminar(numDocumento);
    }

    public ArrayList<Cliente> getSortedByNameList(boolean reversed){
        ArrayList<Cliente> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Cliente::getNombre).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Cliente::getNombre));
        }

        return lista;
    }

    public ArrayList<Cliente> getSortedByEmailList(boolean reversed){
        ArrayList<Cliente> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Cliente::getEmail).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Cliente::getEmail));
        }

        return lista;
    }

    public ArrayList<Cliente> getSortedByEdadList(boolean reversed){
        ArrayList<Cliente> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Cliente::getEdad).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Cliente::getEdad));
        }

        return lista;
    }

    public ArrayList<Cliente> getSortedByCiList(boolean reversed){
        ArrayList<Cliente> lista = new ArrayList<>(repo.obtener().values());
        if(reversed){
            Collections.sort(lista, Comparator.comparing(Cliente::getNumDocumento).reversed());
        } else {
            Collections.sort(lista, Comparator.comparing(Cliente::getNumDocumento));
        }

        return lista;
    }
}
