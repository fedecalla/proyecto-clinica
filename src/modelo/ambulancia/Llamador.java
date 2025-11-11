package modelo.ambulancia;

public class Llamador extends Thread{
	private Ambulancia ambulancia;
	private boolean activo;

	public Llamador(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
		this.activo = true;
	}


	public void activar() {
		this.activo = true;
	}


	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	// ------ ------ ------ ------ ------ ------ ------ ------ ------ ------

	public void run(){
		while (activo) {
			this.ambulancia.realizarMantenimiento(this);
			try {
				Thread.sleep(4000); // espera 4 segundos
				this.detener();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.ambulancia.dejarAmbulancia();

		}
	}
	
	public void detener() {
		this.activo = false;
	}


	@Override
	public String toString() {
		return "Ambulancia en mantenimiento";
	}


}
