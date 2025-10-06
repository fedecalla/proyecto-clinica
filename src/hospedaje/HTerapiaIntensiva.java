package hospedaje;

import excepciones.HabitacionCompletaException;
import individuos.Persona;

/**
 * Clase HPrivada, hija de Habitacion
 * contiene un estatico de su valor
 */

public class HTerapiaIntensiva extends Habitacion{
	
	private static double CostoHabTerapiaIntensiva=150;

	/**
	 *Constructor de la clase
	 */
	public HTerapiaIntensiva() {
		super();
		this.tipo="Terapia Intensiva";
	}

	/**
	 *Calculo del costo
	 */
	@Override
	public double getCosto(long cant_dias) {
		return costoAsignacion+ Math.pow(CostoHabTerapiaIntensiva, cant_dias);
	}

	@Override
	public void setPersona(Persona persona) throws HabitacionCompletaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean EstaLlena() {
		// TODO Auto-generated method stub
		return false;
	}

}
