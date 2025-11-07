package Modelo.hospedaje;

/**
 * Clase Habitacion<br>
 * clase padre asbtracta de los tipos de habitacion concretas<br>
 * Contiene su capacidad, costo y tipo<br>
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
