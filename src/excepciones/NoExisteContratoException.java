package excepciones;

/**
 * Clase excepcion ante contrato desconocido
 */
public class NoExisteContratoException extends Exception {
    public NoExisteContratoException(String mensaje) {
        super(mensaje);
    }
}
