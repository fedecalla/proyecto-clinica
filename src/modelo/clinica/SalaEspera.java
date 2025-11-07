package modelo.clinica;

import modelo.pacientes.Paciente;
public class SalaEspera {
	
	private Paciente actual;
	
	
	public Paciente getActual() {
		return actual;
	}

	public void vaciar() {
		this.actual=null;
	}
	
	/**
	 * @return devuelve el paciente de la sala de espera
	 */
	public Paciente retirar() {
		Paciente retirado = actual;
		this.actual=null;
		return retirado;
	}
	/* Devuelve si esta ocupada la sala
	 * 
	 */
	public boolean estaOcupada() {
		return actual!=null;
	}
	
	
	/**
	 * @param ingresante
	 *  Resuelve el ingreso de pacientes a la sala de espera
	 *  <b>pre: </b><br>
	 * ingresante!= null<br>
	 * @return paciente que gana la sala de espera
	 */
	public Paciente ingresar (Paciente ingresante) {
		if (!estaOcupada()){
			actual = ingresante;
			return null;
		}
		Paciente ocupante = this.actual;
		actual = ocupante.getGanador(ingresante);
		if (actual.equals(ocupante)) {
			return ingresante;
		}
		else
			return ocupante;
		
	}
	
}
