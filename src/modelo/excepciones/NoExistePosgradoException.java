package modelo.excepciones;

/**
 * Clase excepcion ante posgrado desconocido
 */
public class NoExistePosgradoException extends Exception {
    public NoExistePosgradoException(String mensaje) {
        super(mensaje);
    }
}
