package repositorio;

import modelo.Cliente;

import java.util.HashMap;

public class RepositorioCliente implements Repositorio<Cliente>{
    private static final String CI_INVALIDO = "[ERROR] NO SE ENCUENTRA CLIENTE CON EL CI ELEGIDO";
    private static HashMap<Integer, Cliente> clientes = new HashMap();


    public int obtenerCantidad(){
        return this.clientes.size();
    }

    public boolean contiene(int id){
        return clientes.containsKey(id);
    }

    public HashMap<Integer, Cliente> obtener() {
        return RepositorioCliente.clientes;
    }

    public Cliente obtenerPorID(int ci){
        if(!clientes.containsKey(ci)){
            throw new IllegalArgumentException(CI_INVALIDO);
        }
        return clientes.get(ci);
    }

    public void guardar(Cliente cliente) {
        this.clientes.put(cliente.getNumDocumento(), cliente);
    }

    public void eliminar(int ci) {
        if(!clientes.containsKey(ci)){
            throw new IllegalArgumentException(CI_INVALIDO);
        }
        this.clientes.remove(ci);
    }

}