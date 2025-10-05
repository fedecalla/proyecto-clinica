package hospedaje;
import individuos.Paciente;
import individuos.Persona;
import individuos.Medico;



/**
 * Clase HCompartida, hija de Habitacion
 * contiene un estatico de su valor
 */
public class HCompartida extends Habitacion{
	
	private Paciente persona;
	private static double CostoHabCompartida=50;

	/**
	 *Constructor de la clase
	 *puede contener dos personas
	 */
	public HCompartida() {
		super();
		this.persona=null;
		this.tipo="Compartida";
	}

	/**
	 *asignacion de personas a la habitacion
	 *<b>Pre:</b><br>
	 *persona!=null<br>
	 *b>Excepciones:</b><br>
	 *que la habitacion este completa<br>
	 */
	@Override 
	public void setPersona(Persona persona) {
		if (this.persona ==null) {
			this.persona = persona;
		}else if (this.persona ==null) {
			this.persona = persona;
		}else {
			throw new HabitacionCompletaException("Habitacion completa");
		}
	}

	/**
	 *Calculo del costo
	 */
	
	@Override
	public double getCosto(long cant_dias) {
		return costoAsignacion+(cant_dias*CostoHabCompartida);
	}
}
