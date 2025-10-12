package excepciones;

public class MedicoNoExisteException extends Exception{

	public MedicoNoExisteException() {
		super("Medico no registrado en la clinica");
	}
	
	public MedicoNoExisteException(String matricula) {
		super(matricula + " Medico no registrado en la clinica");
	}
	
}
