package hospedaje;

/**
 * Clase HPrivada, hija de Habitacion
 * contiene un estatico de su costo
 */

public class HTerapiaIntensiva extends Habitacion{
	
	private static double CostoHabTerapiaIntensiva=150;

	/**
	 *Constructor de la clase
	 *Designa su capacidad y tipo
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
