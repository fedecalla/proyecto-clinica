package modelo.excepciones;

public class NoHayPacientesEnEsperaException extends Exception{

	public NoHayPacientesEnEsperaException() {
		super("No hay pacientes esperando a ser atendidos");
	}
	
}
