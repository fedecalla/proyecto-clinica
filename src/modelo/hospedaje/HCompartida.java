package hospedaje;

/**
 * Clase HCompartida, hija de Habitacion<br>
 * contiene un estatico de su costo<br>
 */
public class HCompartida extends Habitacion{
	private static double CostoHabCompartida=50;
	private int capacidad;

	/**
	 *Constructor de la clase<br>
	 *Designa su capacidad y tipo<br>
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
