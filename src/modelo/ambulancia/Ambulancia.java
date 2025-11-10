package modelo.ambulancia;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Ambulancia extends Observable{
	private static Ambulancia instancia;
	private int atendiendo = 0;
	private int entaller = 0;
	private EstadoAmbulancia estado;
	private ArrayList<Observer> observers = new ArrayList<>();
	
	
	private Ambulancia() {
		this.estado = new Disponible();
	}
	
	public static Ambulancia getAmbulancia() {
        if (instancia == null) {
            instancia = new Ambulancia();
        }
        return instancia;
    }
	
	public int getEnTaller() {
		return this.entaller;
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
		setChanged();
		notifyObservers("ESTADO: "+nuevo.toString());
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
		this.setEstado(new AtendiendoPaciente());
		setChanged();
		notifyObservers("LOG: "+a.toString1());
		notifyAll();	
		
	}
	
	public void eventoAtencionDomicilio(Asociado a) throws SolicitudNoAtendidaException {
		estado.solicitaAtencionaDomicilio(this);
		this.atendiendo=1;
	}
	
	public synchronized void dejarAmbulancia () {
		this.atendiendo = 0;
		this.entaller = 0;
		this.setEstado(new Disponible());
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
		this.setEstado(new TrasladandoPaciente());
		setChanged();
		notifyObservers("LOG: "+a.toString2());
		notifyAll();	
	}


	public synchronized void realizarMantenimiento(Llamador llamador) {
		while(this.atendiendo==1 || this.entaller==1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.setEstado(new EnTaller());
		this.atendiendo = 1;
		this.entaller = 1;
		setChanged();
		notifyObservers("LOG: "+llamador.toString());
		notifyAll();
	}

}
