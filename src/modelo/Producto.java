package modelo;

import java.util.Objects;

public class Producto {
    private static final String NULL_ERROR = "[ERROR] ESTE CAMPO NO PUEDE SER NULL";
    private static final String EMPTY_ERROR = "[ERROR] ESTE CAMPO NO PUEDE SER ESTAR VACIO";
    private static final String NRO_DE_SERIE_INVALIDO = "[ERROR] EL NUMERO DE SERIE DEBE SER MAYOR O IGUAL A 0";
    private static final String NOMBRE_INVALIDO = "[ERROR] EL NOMBRE TIENE QUE ESTAR COMPUESTO SOLO DE LETRAS";
    private static final String CATEGORIA_INVALIDA = "[ERROR] LA CATEGORIA TIENE QUE ESTAR COMPUESTA SOLO DE LETRAS";

    private String nombre = null;
    private String categoria = null;
    private int nroDeSerie = 0;

    public Producto(String nombre, String categoria, int nroDeSerie){
        setNombre(nombre);
        setCategoria(categoria);
        setNroDeSerie(nroDeSerie);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Producto)) {
            return false;
        }
        Producto temp = (Producto) obj;

        return this.nroDeSerie == temp.nroDeSerie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + this.nroDeSerie;
        hash = 53 * hash + Objects.hashCode(this.categoria);
        return hash;
    }

    @Override
    public String toString() {
        return  "\nNombre:" + this.nombre +
                "\n" + "Categoria: " + this.categoria +
                "\n" + "Nro. de serie:" + this.nroDeSerie;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        if(nombre == null){
            throw new IllegalArgumentException(NULL_ERROR);
        }
        nombre = nombre.trim();
        if (nombre.isEmpty()){
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
        if (!nombre.matches("[a-zA-Z]+")){
            throw new IllegalArgumentException(NOMBRE_INVALIDO);
        }
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if(categoria == null){
            throw new IllegalArgumentException(NULL_ERROR);
        }
        categoria = categoria.trim();
        if (categoria.isEmpty()){
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
        if (!categoria.matches("[a-zA-Z]+")){
            throw new IllegalArgumentException(CATEGORIA_INVALIDA);
        }

        this.categoria = categoria;
    }

    public int getNroDeSerie() {
        return nroDeSerie;
    }

    public void setNroDeSerie(int nroDeSerie) {
        if (nroDeSerie <= 0){
            throw new IllegalArgumentException(NRO_DE_SERIE_INVALIDO);
        }
        this.nroDeSerie = nroDeSerie;
    }
}
