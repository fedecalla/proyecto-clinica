package excepciones;

/**
 * Clase excepcion ante habitacion completa
 */
public class HabitacionCompletaException extends Exception {
    public HabitacionCompletaException(String mensaje) {
        super(mensaje);
    }
}
