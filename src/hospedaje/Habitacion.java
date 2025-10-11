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
	protected String tipo;
	protected int capacidad;


		
	/**
	*Metodo abstracto, calcula el costo de la estadia acorde al tipo de habitacion
	*/
	public abstract double getCosto(long cant_dias); 
		
	public int getCapacidad()
	{
		return this.capacidad;
	}
	public void setCapacidad(int c)
	{
		this.capacidad = c;
	}
	public void setPersona(){
		this.capacidad -=1;
	}
	/**
	*asignacion de personas a la habitacion
	*<b>Pre:</b><br>
	*persona!=null<br>
	*b>Excepciones:</b><br>
	*que la habitacion este completa<br>
	*/


	public String getTipo() {
		return tipo;
	}
	
	

}
