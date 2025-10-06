package hospedaje;
import pacientes.Paciente;
import individuos.Persona;
import medicos.Medico;



/**
 * Clase HCompartida, hija de Habitacion
 * contiene un estatico de su valor
 */
public class HCompartida extends Habitacion{
	
	private Paciente persona;
	private static double CostoHabCompartida=50;
	private static int capacidad = 5;

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
	public void setPersona(Persona persona){
		super.pacientesEnHabitacion.add(persona);
		super.cantPacientes++;
	}

	/**
	 *Calculo del costo
	 */
	
	@Override
	public double getCosto(long cant_dias) {
		return costoAsignacion+(cant_dias*CostoHabCompartida);
	}

	@Override
	public boolean EstaLlena() {
		boolean resultado = false;
		if(super.cantPacientes == capacidad)
			resultado = true;
		return resultado;
		
	}
}
