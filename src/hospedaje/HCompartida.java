package hospedaje;
import pacientes.Paciente;
import excepciones.HabitacionCompletaException;
import individuos.Persona;
import medicos.Medico;



/**
 * Clase HCompartida, hija de Habitacion
 * contiene un estatico de su costo
 */
public class HCompartida extends Habitacion{
	private static double CostoHabCompartida=50;
	private int capacidad;

	/**
	 *Constructor de la clase
	 *Designa su capacidad y tipo
	 */
	public HCompartida() {
		super();
		this.tipo="Compartida";
		this.capacidad = 5;
	}
	
	/**
	 *Calculo del costo
	 */
	
	public double getCosto(long cant_dias) {
		return costoAsignacion+(cant_dias*CostoHabCompartida);
	}


}
