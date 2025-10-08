package excepciones;

public class MedicoNoExisteException extends Exception{

	public MedicoNoExisteException() {
		super("Medico no registrado en la clinica");
	}
	
}
