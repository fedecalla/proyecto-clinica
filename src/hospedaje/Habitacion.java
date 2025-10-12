package hospedaje;

/**
 * Clase Habitacion
 * clase padre asbtracta de los tipos de habitacion concretas
 * Contiene su capacidad, costo y tipo
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
	
	public String getTipo() {
		return tipo;
	}
	
	

}
