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
        assert instancia != null : "La instancia de Ambulancia nunca debe ser null";
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
	
	/**
	 * Solicita la ambulancia para atender a un asociado.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>a</code> (el asociado) no debe ser {@code null}.</li>
	 *   <li>El hilo que invoca este método debe tener acceso sincronizado a la instancia de {@link Ambulancia}.</li>
	 *   <li>Si la ambulancia ya está atendiendo (atendiendo == 1), el hilo debe esperar hasta que quede disponible.</li>
	 * </ul>
	 *
	 * <p><b>Postcondiciones:</b></p>
	 * <ul>
	 *   <li>La ambulancia queda marcada como atendiendo (<code>atendiendo == 1</code>).</li>
	 *   <li>El estado de la ambulancia se actualiza a {@link AtendiendoPaciente}.</li>
	 *   <li>Se agrega una solicitud de atención al asociado (<code>a.getSolicitudes()</code>).</li>
	 *   <li>Se notifica a los observadores el cambio de estado y el log correspondiente.</li>
	 * </ul>
	 *
	 * @param a el {@link Asociado} que solicita la ambulancia.
	 */

	public synchronized void pedirAmbulancia(Asociado a) {
		assert a != null : "El asociado no puede ser null";
		while(this.atendiendo==1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.atendiendo=1;
		assert this.atendiendo == 1 : "La ambulancia debe estar marcada como atendiendo";
		this.setEstado(new AtendiendoPaciente());
		a.getSolicitudes().add(a.stringAtencion());
		setChanged();
		notifyObservers("LOG: "+a.toString2());
		notifyAll();	
		
	}
	
	public void eventoAtencionDomicilio(Asociado a) throws SolicitudNoAtendidaException {
		assert a != null : "El asociado no puede ser null";
		estado.solicitaAtencionaDomicilio(this);
		this.atendiendo=1;
		assert this.atendiendo == 1 : "La ambulancia debe quedar en estado atendiendo";
	}
	
	public synchronized void dejarAmbulancia () {
		this.atendiendo = 0;
		this.entaller = 0;
		this.setEstado(new Disponible());
	}

	/**
	 * Solicita la ambulancia para trasladar a un asociado.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>a</code> (el asociado) no debe ser {@code null}.</li>
	 *   <li>El hilo que invoca este método debe tener acceso sincronizado a la instancia de {@link Ambulancia}.</li>
	 *   <li>Si la ambulancia ya está atendiendo (<code>atendiendo == 1</code>), el hilo debe esperar hasta que quede disponible.</li>
	 * </ul>
	 *
	 * <p><b>Postcondiciones:</b></p>
	 * <ul>
	 *   <li>La ambulancia queda marcada como atendiendo (<code>atendiendo == 1</code>).</li>
	 *   <li>El estado de la ambulancia se actualiza a {@link TrasladandoPaciente}.</li>
	 *   <li>Se agrega una solicitud de traslado al asociado (<code>a.getSolicitudes()</code>).</li>
	 *   <li>Se notifica a los observadores el cambio de estado y el log correspondiente.</li>
	 * </ul>
	 *
	 * @param a el {@link Asociado} que solicita el traslado.
	 */

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
		a.getSolicitudes().add(a.stringTraslado());
		setChanged();
		notifyObservers("LOG: "+a.toString1());
		notifyAll();	
	}

	
	/**
	 * Solicita la ambulancia para realizar tareas de mantenimiento.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>llamador</code> no debe ser {@code null}.</li>
	 *   <li>El hilo que invoca este método debe tener acceso sincronizado a la instancia de {@link Ambulancia}.</li>
	 *   <li>Si la ambulancia ya está atendiendo (<code>atendiendo == 1</code>) o en taller (<code>entaller == 1</code>),
	 *       el hilo debe esperar hasta que quede disponible.</li>
	 * </ul>
	 *
	 * <p><b>Postcondiciones:</b></p>
	 * <ul>
	 *   <li>La ambulancia queda marcada como atendiendo (<code>atendiendo == 1</code>) y en taller (<code>entaller == 1</code>).</li>
	 *   <li>El estado de la ambulancia se actualiza a {@link EnTaller}.</li>
	 *   <li>Se notifica a los observadores el cambio de estado y el log correspondiente.</li>
	 * </ul>
	 *
	 * @param llamador el objeto que solicita el mantenimiento de la ambulancia.
	 */

	public synchronized void realizarMantenimiento(Llamador llamador) {
		while(this.atendiendo==1 || this.entaller==1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.atendiendo = 1;
		this.entaller = 1;
		this.setEstado(new EnTaller());
		setChanged();
		notifyObservers("LOG: "+llamador.toString());
		notifyAll();
	}

}
