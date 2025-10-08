package excepciones;

public class RangoNoEncontradoException extends Exception {
	
	public RangoNoEncontradoException(String rango) {
        super("Rango no existente: " + rango);
    }
}
