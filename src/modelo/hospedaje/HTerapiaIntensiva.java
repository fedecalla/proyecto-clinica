package Modelo.hospedaje;

/**
 * Clase HPrivada, hija de Habitacion,<br>
 * contiene un estatico de su costo<br>
 */

public class HTerapiaIntensiva extends Habitacion{
	
	private static double CostoHabTerapiaIntensiva=150;

	/**
	 *Constructor de la clase<br>
	 *Designa su capacidad y tipo<br>
	 */
	public HTerapiaIntensiva() {
		super();
		this.tipo="Terapia Intensiva";
		super.capacidad = 1;
	}

	/**
	 *Calculo del costo
	 */
	@Override
	public double getCosto(long cant_dias) {
		return costoAsignacion+ Math.pow(CostoHabTerapiaIntensiva, cant_dias);
	}

}
