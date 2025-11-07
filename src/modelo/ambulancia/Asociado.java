package Modelo.ambulancia;

import Modelo.individuos.Persona;

public class Asociado extends Thread{ 
	private Persona persona;
	private Ambulancia ambulancia; 

	public Asociado(Persona p, Ambulancia a) {
		this.persona=p;
		this.ambulancia=a;
	}
	public Persona getPersona()
	{
		return this.persona;
	}
	
	/*public void run(){
		this.ambulancia.pedirAmbulancia(this);
		try {
			if (Math.random()>0.5) {
				Thread.sleep(2000); // espera 2 segundos
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.ambulancia.dejarAmbulancia();
	}*/
	
	@Override
	public void run() {
	    // Decidir al azar qué acción realizar
	    boolean quiereTraslado = Math.random() > 0.5;

	    if (quiereTraslado) {
	        this.ambulancia.pedirTraslado(this);
	    } else {
	        this.ambulancia.pedirAmbulancia(this);
	    }

	    try {
	        // Simular el tiempo de uso del recurso o viaje
	        if (Math.random() > 0.5) {
	            Thread.sleep(2000); // espera 2 segundos
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Liberar la ambulancia
	    this.ambulancia.dejarAmbulancia();
	}


	//falta hacer otro hilo llamador que se controle un boton habilitar/deshabilitar, hace que no se trabe si un hilo va a wait
	public String toString1() {
		return "Ambulancia traslada al asociado " + this.persona.getNombreyapellido() + ", dni: "+ this.persona.getDni();
	}
	
	public String toString2() {
		return "Ambulancia atiende en el domicilio al asociado " + this.persona.getNombreyapellido() + ", dni: "+ this.persona.getDni();
	}

}
