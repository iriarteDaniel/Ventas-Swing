package modelo;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private String nombre = null;
    private int edad = 0;
    private int numDocumento = 0;
    private String email = null;
    private static final String NULL_ERROR = "[ERROR] ESTE CAMPO NO PUEDE SER NULL";
    private static final String EMPTY_ERROR = "[ERROR] ESTE CAMPO NO PUEDE SER ESTAR VACIO";
    private static final String NOMBRE_INVALIDO = "[ERROR] NOMBRE INVALIDO, EL NOMBRE SOLO PUEDE ESTAR COMPUESTO DE LETRAS";
    private static final String EDAD_INVALIDA = "[ERROR] EDAD INVALIDA, LA EDAD NO PUEDE SER MENOR O IGUAL A 0";
    private static final String EMAIL_INVALIDO = "[ERROR] EMAIL INVALIDO, UN EMAIL NO PUEDE TENER CARACTERES QUE NO SEAN LETRAS, NUMEROS, PUNTOS, GUION-BAJOS NI ARROBAS ";
    private static final String NUM_DOC_INVALIDO = "[ERROR] INVALID NUM_DOC, NUM_DOC MUST NOT BE LESS OR EQUAL TO 0";
    private static final String EXTENCION_INVALIDA = "[ERROR] EXTENSION DE EMAIL INVALIDA, LA EXTENSION DEL EMAIL DEBE ESTAR EN LA LISTA: \"com\", \"org\", \"net\", \"int\", \"edu\", \"gov\", \"mil\"";
    private static final String DOMINIO_INVALIDO = "[ERROR] DOMINIO DE EMAIL INVALIDO, EL EMAIL DEBE TENER UN DOMINIO SOLO COMPUESTO POR LETRAS, NUMEROS, PUNTOS Y GUIONES, Y TENER UN EXTENSION VALIDA";

    public Cliente(String nombre, String email, int edad, int numDocumento){
        setNombre(nombre);
        setEmail(email);
        setEdad(edad);
        setNumDocumento(numDocumento);
    }
    public Cliente(String nombre, String email) {
        this(nombre, email, 18, 1000);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cliente)) {
            return false;
        }
        Cliente temp = (Cliente) obj;

        return this.numDocumento == temp.numDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + this.edad;
        hash = 53 * hash + this.numDocumento;
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public String toString() {
        return  "Nombre: " + this.nombre +
                "\n" + "CI Nro: " + this.numDocumento +
                "\n" + "Edad: " + this.edad +
                "\n" + "Email: " + this.email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad <= 0){
            throw new IllegalArgumentException(EDAD_INVALIDA);
        }
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    private boolean isValidText(String texto){
        return texto.matches("[a-zA-Z]*");
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
        nombre = nombre.trim();
        if (nombre.isEmpty()){
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
        if (!isValidText(nombre)){
            throw new IllegalArgumentException(NOMBRE_INVALIDO);
        }
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    private static boolean isValidExtension(String extension){
        String[] validExtensions = {"com", "org", "net", "int", "edu", "gov", "mil"};
        for(String validExtension : validExtensions){
            if (extension.equals(validExtension)){
                return true;
            }                                                                     // End If
        }
        throw new IllegalArgumentException(EXTENCION_INVALIDA);
    }

    private static boolean isValidDomain(String domain) {
        String extension = domain.substring(domain.indexOf(".")+1);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(domain);
        if( matcher.matches() && isValidExtension(extension)){
            return true;
        }
        throw new IllegalArgumentException(DOMINIO_INVALIDO);
    }

    private static boolean isValidEmail(String email) {
        if (email.isEmpty()){
            throw new IllegalArgumentException(EMPTY_ERROR);
        }                                                                         // End If
        String domain = email.substring(email.indexOf("@")+1);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._@-]+$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() && isValidDomain(domain)) {
            return true;
        }
        throw new IllegalArgumentException(EMAIL_INVALIDO);
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
        email = email.trim();
        if (email.isEmpty()){
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
        if(isValidEmail(email)){
            this.email = email;
        }
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        if (numDocumento <= 0){
            throw new IllegalArgumentException(NUM_DOC_INVALIDO);
        }
        this.numDocumento = numDocumento;
    }
}
