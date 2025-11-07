
package Modelo.pacientes;
import Modelo.hospedaje.Habitacion;
import Modelo.individuos.Persona;

/*
 * Clase abstracta padre de las clases concretas de paciente.
 * 
 */

public abstract class Paciente extends Persona{
	private int historiaclinica;
	protected static int siguiente = 0;
	private int norden;
	protected String rango;
	private Habitacion habitacion;
	
	public Paciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica)
	{
		super(dni, nombreyapellido, telefono, domicilio, ciudad);
		this.historiaclinica = historiaclinica;
		this.habitacion = null;
		this.norden = Paciente.siguiente;
		Paciente.siguiente = Paciente.siguiente + 1;
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
	public void setHabitacion(Habitacion h)
	{
		this.habitacion = h;
	}
	
	public Habitacion getHabitacion()
	{
		return this.habitacion;
	}
	public abstract String getRango();
	
	public String getTipoHabitacion()
	{
		return this.habitacion.getTipo();
	}

	/*
	 * Define los metodos del double dispatch para que sean extendidos en las clases hijas
	 * 
	 */
	public abstract Paciente getGanador(Paciente otro);
	public abstract Paciente enfrentaNinio(Paciente otro);
	public abstract Paciente enfrentaJoven(Paciente otro);
	public abstract Paciente enfrentaMayor(Paciente otro);


}
