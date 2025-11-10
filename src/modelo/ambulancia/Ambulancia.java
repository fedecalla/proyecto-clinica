package modelo.ambulancia;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Ambulancia extends Observable{
	private static Ambulancia instancia;
	private int atendiendo = 0;
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
		notifyObservers(nuevo.toString());
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
		System.out.println(a.toString1());
		notifyObservers();
		notifyAll();	
		
	}
	
	public void eventoAtencionDomicilio(Asociado a) throws SolicitudNoAtendidaException {
		estado.solicitaAtencionaDomicilio(this);
		this.atendiendo=1;
	}
	
	public synchronized void dejarAmbulancia () {
		this.atendiendo=0;
		notifyObservers();
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
		System.out.println(a.toString2());
		notifyObservers();
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
		notifyObservers();
		notifyAll();
	}

}
