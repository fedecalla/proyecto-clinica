package modelo.ambulancia;

import modelo.individuos.Persona;

public class Asociado extends Thread{ 
	private Persona persona;
	private Ambulancia ambulancia; 

	public Asociado(Persona p, Ambulancia a) {
		this.persona=p;
		this.ambulancia=a;
	}
	
	public void run(){
		this.ambulancia.pedirAmbulancia(this);
		try {
			Thread.sleep(2000); // espera 2 segundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.ambulancia.dejarAmbulancia();
	}

	//falta random para transladar y hacer otro hilo llamador que se controle un boton habilitar/deshabilitar, hace que no se trabe si un hilo va a wait
	@Override
	public String toString() {
		return "Ambulancia atendiendo al asociado " + this.persona.getNombreyapellido() + ", dni: "+ this.persona.getDni();
	}

}
