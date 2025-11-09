package modelo.ambulancia;

public class Operario extends Thread{
	private Llamador llamador;
	
	public void Llamar() {
		do {
			//accesibilidad boton();
			Llamador llamador=new Llamador(); 
			llamador.start();
		}while(true);									//un do algo y activar el boton para que se presione?
	}
	
}
