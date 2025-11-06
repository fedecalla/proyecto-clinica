package modelo.ambulancia;

public class Ambulancia {
	
	private int atendiendo;
	
	public Ambulancia() {
		this.atendiendo=0;
	}
	
	
	public int getAtendiendo() {
		return atendiendo;
	}


	public void setAtendiendo(int atendiendo) {
		this.atendiendo = atendiendo;
	}
	
	public synchronized void pedirAmbulancia(Asociado a) {
		
		while(this.atendiendo==1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.atendiendo=1;
		
		a.toString();
		notifyAll();	
	}
	
	public synchronized void dejarAmbulancia () {
		this.atendiendo=0;
		notifyAll();
	}

}
