package modelo.ambulancia;

import java.util.ArrayList;
import java.util.Observer;

import modelo.individuos.Persona;

public class Asociado extends Thread {
	private ArrayList<Observer> observers;
	private Persona persona;
	private Ambulancia ambulancia;
	private Boolean activo;
	private ArrayList<String> solicitudes = new ArrayList<String>();
	private int cant_solicitudes;

	public Asociado(Persona p, Ambulancia a) {
		this.persona = p;
		this.ambulancia = a;
		this.activo = true;
		this.solicitudes.clear(); //limpia las solicitudes
		this.cant_solicitudes = 0;
	}
	
	public void setCantSolicitudes(int cant) {
		this.cant_solicitudes = cant;
	}
	
	public int getCantSolicitudes() {
		return this.cant_solicitudes;
	}

	public void detener() {
		this.activo = false;
	}
	
	public ArrayList<String> getSolicitudes() {
		return solicitudes;
	}
	
	public Persona getPersona() {
		return this.persona;
	}
	
	public String getNombre() {
		return this.persona.getNombreyapellido();
	}
	
	public String getDNI() {
		return this.persona.getDni();
	}
	
	public ArrayList<Observer> getObservers() {
		return this.observers;
	}
	
	// ------ ------ ------ ------ ------ ------ ------ ------

	@Override
	public void run() {
		while (activo && this.getCantSolicitudes() > 0 ) {
			// Decidir al azar qué acción realizar
			boolean quiereTraslado = Math.random() > 0.5;
			if (quiereTraslado) 
				this.ambulancia.pedirTraslado(this);
			else 
				this.ambulancia.pedirAmbulancia(this);
			
			try {
				// Simular el tiempo de uso del recurso o viaje
					Thread.sleep(2000); // espera 2 segundos
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally { 
				// Liberar la ambulancia
				this.cant_solicitudes -= 1; 
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
	
	public String stringTraslado() {
		return (" Fue trasladado por la Ambulancia \n");
	}
	
	public String stringAtencion() {
		return (" Recibio atencion de la Ambulancia \n");
	}
	
	@Override
	public String toString() {
		return this.persona.toString();
	}
	
}
