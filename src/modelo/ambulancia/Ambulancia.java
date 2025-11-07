package modelo.ambulancia;

public class Ambulancia {
	
	private int atendiendo = 0;
	private EstadoAmbulancia estado;
	//private List<Observer> observers = new ArrayList<>();
	
	
	public Ambulancia() {
		this.estado = new Disponible();
	}
	
	
	public int getAtendiendo() {
		return atendiendo;
	}


	public void setAtendiendo(int atendiendo) {
		this.atendiendo = atendiendo;
	}
	
	
	public EstadoAmbulancia getEstado() {
		return estado;
	}


	public synchronized void setEstado(EstadoAmbulancia nuevo) {
		this.estado = nuevo;
		notifyAll();
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
		a.toString1();
		notifyAll();	
	}
	
	public void eventoAtencionDomicilio(Asociado a) throws SolicitudNoAtendidaException {
		estado.solicitaAtencionaDomicilio(this);
		this.atendiendo=1;
	}
	
	public synchronized void dejarAmbulancia () {
		this.atendiendo=0;
		notifyAll();
	}


	public synchronized void pedirTraslado(Asociado a) {
		
		while(this.atendiendo==1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.atendiendo=1;
		a.toString2();
		notifyAll();	
	}


	public synchronized void realizarMantenimiento(Llamador llamador) {
		
		while(this.atendiendo==1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.atendiendo=1;
		llamador.toString();
		notifyAll();
	}

}
