package modelo.ambulancia;

public class Llamador extends Thread{
	private Ambulancia ambulancia;
	private boolean activo;

	public Llamador(Ambulancia ambulancia) {
		super();
		assert ambulancia != null : "La ambulancia no puede ser null";
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
			assert this.activo : "El asociado debería seguir activo durante el mantenimiento";
			try {
				Thread.sleep(4000); // espera 4 segundos
				this.detener();
				assert !this.activo : "El asociado debe quedar inactivo después de detenerse";
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.ambulancia.dejarAmbulancia();
			assert this.ambulancia.getEstado() != null : "La ambulancia debe tener un estado válido al ser liberada";

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
