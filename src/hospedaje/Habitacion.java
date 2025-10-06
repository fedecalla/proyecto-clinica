package hospedaje;
import pacientes.Paciente;
import individuos.Persona;
import medicos.Medico;
import java.util.ArrayList;
import excepciones.HabitacionCompletaException;



/**
 * Clase Habitacion
 * clase padre, contiene a los pacientes internados
 * contiene un estatico del valor de asignacion de un paciente
 */
public abstract class Habitacion {
	
	protected static double costoAsignacion=200;
	protected ArrayList<Persona> pacientesEnHabitacion; // si es habitacion compartida van a haber varios, y si es privada habra uno solo
	protected String tipo;
	protected int cantPacientes;
	
	/**
	*Constructor de habitacion
	*Se crea sin personas dentro
	*/
		
	public Habitacion() {
		this.pacientesEnHabitacion = new ArrayList<>();
		this.cantPacientes = 0;
	}
		
	/**
	*Metodo abstracto, calcula el costo de la estadia acorde al tipo de habitacion
	*/
	public abstract double getCosto(long cant_dias); 
		
		
	public ArrayList<Persona> getPersonas() {
		return this.pacientesEnHabitacion;
	}
		
		
		
	/**
	*asignacion de personas a la habitacion
	*<b>Pre:</b><br>
	*persona!=null<br>
	*b>Excepciones:</b><br>
	*que la habitacion este completa<br>
	*/
		
	public abstract void setPersona(Persona persona) throws HabitacionCompletaException; // se sobreescribe porque varia de si la habitacion es compartida o no


	public String getTipo() {
		return tipo;
	}
	
	public abstract boolean EstaLlena();
	

}
