package Modelo.excepciones;

/**
 * Clase excepcion ante especialidad desconocida
 */
public class NoExisteEspecialidadException extends Exception {
    public NoExisteEspecialidadException(String mensaje) {
        super(mensaje);
    }
}
