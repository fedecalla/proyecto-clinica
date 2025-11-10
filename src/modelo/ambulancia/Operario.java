package modelo.ambulancia;

public class Operario extends Thread{
	private Llamador llamador;

	public Operario(Llamador op) {
		this.llamador = op;
	}

	public void Llamar() {
		if (this.llamador.getAmbulancia().getEnTaller()!=1)
			this.llamador.start();			
	}

}
