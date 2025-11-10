package modelo.ambulancia;

import java.util.ArrayList;
import java.util.Observer;

import modelo.individuos.Persona;

public class Asociado extends Thread {
	private ArrayList<Observer> observers;
	private Persona persona;
	private Ambulancia ambulancia;
	private Boolean activo;

	public Asociado(Persona p, Ambulancia a) {
		this.persona = p;
		this.ambulancia = a;
		this.activo = true;
	}

	public void detener() {
		this.activo = false;
	}


	/*
	 * public void run(){
	 * this.ambulancia.pedirAmbulancia(this);
	 * try {
	 * if (Math.random()>0.5) {
	 * Thread.sleep(2000); // espera 2 segundos
	 * 
	 * }
	 * 
	 * } catch (InterruptedException e) {
	 * e.printStackTrace();
	 * }
	 * this.ambulancia.dejarAmbulancia();
	 * }
	 */

	@Override
	public void run() {
		while (activo) {
			// Decidir al azar qué acción realizar
			boolean quiereTraslado = Math.random() > 0.5;
			if (quiereTraslado) 
				this.ambulancia.pedirTraslado(this);
			else 
				this.ambulancia.pedirAmbulancia(this);
			
			try {
				// Simular el tiempo de uso del recurso o viaje
				if (Math.random() > 0.5) {
					Thread.sleep(2000); // espera 2 segundos
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally { 
				// Liberar la ambulancia
				this.ambulancia.dejarAmbulancia();
			}
		}
	}

	// falta hacer otro hilo llamador que se controle un boton
	// habilitar/deshabilitar, hace que no se trabe si un hilo va a wait
	public String toString1() {
		return "- Trasladando asociado: \n  " + this.persona.getNombreyapellido() + " -  DNI: "
				+ this.persona.getDni();
	}

	public String toString2() {
		return "- Atendiendo asociado: \n  " + this.persona.getNombreyapellido() + " -  DNI: "
				+ this.persona.getDni();
	}
	
	@Override
	public String toString() {
		return this.persona.toString();
	}
	
	
	public Persona getPersona() {
		return this.persona;
	}

}
