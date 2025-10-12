package hospedaje;
import pacientes.Paciente;
import excepciones.HabitacionCompletaException;
import individuos.Persona;
import medicos.Medico;



/**
 * Clase HCompartida, hija de Habitacion
 * contiene un estatico de su valor
 */
public class HCompartida extends Habitacion{
	private static double CostoHabCompartida=50;
	private int capacidad;

	/**
	 *Constructor de la clase
	 *puede contener cinco personas
	 */
	public HCompartida() {
		super();
		this.tipo="Compartida";
		this.capacidad = 5;
	}

	/**
	 *asignacion de personas a la habitacion
	 *<b>Pre:</b><br>
	 *persona!=null<br>
	 *b>Excepciones:</b><br>
	 *que la habitacion este completa<br>
	 */


	/**
	 *Calculo del costo
	 */
	
	public double getCosto(long cant_dias) {
		return costoAsignacion+(cant_dias*CostoHabCompartida);
	}


}
