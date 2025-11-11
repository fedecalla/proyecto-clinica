package modelo.ambulancia;

public class Operario extends Thread{
	private Ambulancia ambulancia;
	private Llamador llamador;


	public Operario(Ambulancia a) {
		this.ambulancia = a;
	}
	

	public void Llamar() {
			if (this.ambulancia.getEnTaller()!=1)
				this.llamador = new Llamador(this.ambulancia);
				this.llamador.activar();
				this.llamador.start();			
	}

}
