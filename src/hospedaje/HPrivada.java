package hospedaje;

import excepciones.HabitacionCompletaException;
import individuos.Persona;

/**
 * Clase HPrivada, hija de Habitacion
 * contiene un estatico de su valor
 */
public class HPrivada extends Habitacion{
	
	private static double CostoHabPrivada=100;
	private double costo;
	
	/**
	 *Constructor de la clase
	 */

	public HPrivada() {
		super();
		this.tipo="Privada";
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

	@Override
	public void setPersona(Persona persona) throws HabitacionCompletaException {
		if(!this.EstaLlena())
		{
			super.pacientesEnHabitacion.add(persona);
			super.cantPacientes++;
		}
		else
			throw new HabitacionCompletaException("no se puede agregar a la habitacion porque esta la misma esta completa");	
	}

	@Override
	public boolean EstaLlena() {
		boolean resultado = false;
		if(super.cantPacientes == 1)
			resultado = true;
		return resultado;
	}

}
