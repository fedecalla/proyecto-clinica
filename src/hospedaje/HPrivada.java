package hospedaje;


/**
 * Clase HPrivada, hija de Habitacion
 * contiene un estatico de su costo
 */
public class HPrivada extends Habitacion{
	
	private static double CostoHabPrivada=100;
	private double costo;
	
	/**
	 *Constructor de la clase
	 *Designa su tipo y capacidad
	 */

	public HPrivada() {
		super();
		this.tipo="Privada";
		super.capacidad = 1;
	}

	/**
	 *Calculo del costo
	 */
	@Override
	public double getCosto(long cant_dias) {
		this.costo=0;
		
		if(cant_dias<2) {
			this.costo= costoAsignacion+CostoHabPrivada;
		}else if(cant_dias<6) {
			this.costo= costoAsignacion+(cant_dias*CostoHabPrivada*1.3);
		}else {
			this.costo= costoAsignacion+(cant_dias*CostoHabPrivada*2);
		}
		
		return this.costo;
	}

}
