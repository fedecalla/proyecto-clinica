package clinica;
import pacientes.Paciente;
public class SalaEspera {
	
	private Paciente actual;
	
	
	public Paciente getActual() {
		return actual;
	}
	/* Vacia la sala sin tener en cuenta quien estaba en ella
	 * 
	 */
	public void vaciar() {
		this.actual=null;
	}
	
	/* Vacia la sala y devuelve al paciente que estaba en ella
	 * 
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
	
	
	/* Resuelve el ingreso de pacientes a la sala de espera
	 * Precondicion ingresante!= null
	 */
	public Paciente ingresar (Paciente ingresante) {
		if (!estaOcupada()){
			actual = ingresante;
			return null;
		}
		Paciente ocupante = this.actual;
		ResultadoSala r = ingresante.getPrioridad().disputar(ingresante, ocupante);
		actual = r.ganador;
		return r.perdedor;
		
	}
	
}
