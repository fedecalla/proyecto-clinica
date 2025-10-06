package hospedaje;
import individuos.Paciente;
import individuos.Persona;
import individuos.Medico;



/**
 * Clase Habitacion
 * clase padre, contiene a los pacientes internados
 * contiene un estatico del valor de asignacion de un paciente
 */
public abstract class Habitacion {
	
	protected static double costoAsignacion=200;
	protected Paciente persona;
	protected String tipo;
	
	/**
	*Constructor de habitacion
	*Se crea sin personas dentro
	*/
		
	public Habitacion() {
		this.persona = null;
	}
		
	/**
	*Metodo abstracto, calcula el costo de la estadia acorde al tipo de habitacion
	*/
	public abstract double getCosto(long cant_dias); 
		
		
	public Persona getPersona() {
		return persona;
	}
		
		
		
	/**
	*asignacion de personas a la habitacion
	*<b>Pre:</b><br>
	*persona!=null<br>
	*b>Excepciones:</b><br>
	*que la habitacion este completa<br>
	*/
		
	public void setPersona(Paciente persona) throws HabitacionCompletaException {
		if(persona == null) {
			this.persona = persona;
		}
		else {
			throw new HabitacionCompletaException("Habitacion completa");
		}
	}


	public String getTipo(long cant_dias) {
		return tipo;
	}

}
