
package pacientes;
import clinica.PrioridadJoven;
import clinica.PrioridadMayor;
import clinica.PrioridadNiño;
import clinica.PrioridadSala;
import individuos.Persona;


public class Paciente extends Persona{
	private int historiaclinica;
	private int norden;
	private String rango;
	private PrioridadSala prioridad;
	
	
	public Paciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, int norden, String rango) {
		super(dni, nombreyapellido, telefono, domicilio, ciudad);
		this.historiaclinica = historiaclinica;
		this.norden = norden;
		this.rango = rango;
		switch (rango) {
		case "niño": this.prioridad = new PrioridadNiño();
		case "joven" : this.prioridad = new PrioridadJoven();
		case "mayor" : this.prioridad = new PrioridadMayor();
		}		
	}

	public int getHistoriaclinica() {
		return historiaclinica;
	}

	public int getNorden() {
		return norden;
	}

	public String getRango() {
		return rango;
	}
	
	
	public PrioridadSala getPrioridad() {
		return prioridad;
	}


}
