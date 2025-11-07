package modelo.ambulancia;

public class Llamador extends Thread{
	private Ambulancia ambulancia;
	
	public void run(){
		this.ambulancia.realizarMantenimiento(this);
		try {
			Thread.sleep(4000); // espera 4 segundos
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.ambulancia.dejarAmbulancia();
		
		//aca deberia avisar al boton, observer 
	}

	@Override
	public String toString() {
		return "Ambulancia en mantenimiento";
	}
	
	
}
