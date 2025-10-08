
package pacientes;
import clinica.PrioridadJoven;
import clinica.PrioridadMayor;
import clinica.PrioridadNiño;
import clinica.PrioridadSala;
import individuos.Persona;
import excepciones.RangoNoEncontradoException;

public class Paciente extends Persona{
	private int historiaclinica;
	private int norden;
	private String rango;
	private PrioridadSala prioridad;
	
	
	public Paciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, int norden, String rango) throws RangoNoEncontradoException{
		super(dni, nombreyapellido, telefono, domicilio, ciudad);
		this.historiaclinica = historiaclinica;
		this.norden = norden;
		this.rango = rango;
		switch (rango.toLowerCase()) {
		case "niño": this.prioridad = new PrioridadNiño();
					 break;	
		case "joven" : this.prioridad = new PrioridadJoven();
						break;
		case "mayor" : this.prioridad = new PrioridadMayor();
					 break;	
		default: throw new RangoNoEncontradoException(rango);
		}		
	}
	
	public String getNombre() {
		return this.nombreyapellido;
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
