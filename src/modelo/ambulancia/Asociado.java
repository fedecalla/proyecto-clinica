package modelo.ambulancia;

import java.util.ArrayList;
import java.util.Observer;

import modelo.individuos.Persona;

public class Asociado extends Thread {
	private ArrayList<Observer> observers;
	private Persona persona;
	private Ambulancia ambulancia;
	private Boolean activo;
	private ArrayList<String> solicitudes = new ArrayList<String>();
	private int cant_solicitudes;

	
	/**
	 * Crea una nueva instancia de {@link Asociado} vinculada a una {@link Persona} y a la {@link Ambulancia} única.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>p</code> (la persona asociada) no debe ser {@code null}.</li>
	 *   <li><code>a</code> (la ambulancia) no debe ser {@code null}.</li>
	 * </ul>
	 *
	 * <p><b>Postcondiciones:</b></p>
	 * <ul>
	 *   <li>El objeto {@link Asociado} queda inicializado con la persona y la ambulancia proporcionadas.</li>
	 *   <li>El atributo <code>activo</code> se establece en {@code true}.</li>
	 *   <li>La lista de solicitudes se inicializa vacía.</li>
	 *   <li>La cantidad de solicitudes (<code>cant_solicitudes</code>) se establece en 0.</li>
	 * </ul>
	 *
	 * @param p la {@link Persona} asociada al nuevo objeto.
	 * @param a la instancia de {@link Ambulancia} que se vincula al asociado.
	 */
	
	
	public Asociado(Persona p, Ambulancia a) {
		assert p != null : "La persona asociada no puede ser null";
	    assert a != null : "La ambulancia no puede ser null";
		this.persona = p;
		this.ambulancia = a;
		this.activo = true;
		this.solicitudes.clear(); //limpia las solicitudes
		this.cant_solicitudes = 0;
	}
	
	public void setCantSolicitudes(int cant) {
		assert cant >= 0 : "La cantidad de solicitudes no puede ser negativa";
		this.cant_solicitudes = cant;
	}
	
	public int getCantSolicitudes() {
		return this.cant_solicitudes;
	}

	public void detener() {
		this.activo = false;
	}
	
	public ArrayList<String> getSolicitudes() {
		return solicitudes;
	}
	
	public Persona getPersona() {
		return this.persona;
	}
	
	public String getNombre() {
		return this.persona.getNombreyapellido();
	}
	
	public String getDNI() {
		return this.persona.getDni();
	}
	
	public ArrayList<Observer> getObservers() {
		return this.observers;
	}
	
	// ------ ------ ------ ------ ------ ------ ------ ------

	
	/**
	 * Ejecuta el ciclo de atención de un asociado en un hilo independiente.
	 * 
	 * <p>Mientras el asociado esté activo y tenga solicitudes pendientes, 
	 * solicita la ambulancia para traslado o atención, simula el tiempo de uso 
	 * y luego libera la ambulancia.</p>
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>activo</code> debe ser {@code true} para que el ciclo se ejecute.</li>
	 *   <li><code>getCantSolicitudes()</code> debe ser mayor a 0 para que el hilo procese solicitudes.</li>
	 *   <li><code>ambulancia</code> no debe ser {@code null}.</li>
	 *   <li>El objeto {@link Asociado} debe estar correctamente inicializado con una {@link Persona} válida.</li>
	 * </ul>
	 *
	 * <p><b>Postcondiciones:</b></p>
	 * <ul>
	 *   <li>Por cada iteración, se solicita la ambulancia para traslado o atención.</li>
	 *   <li>Se simula un tiempo de espera de 2 segundos.</li>
	 *   <li>La cantidad de solicitudes del asociado se decrementa en 1.</li>
	 *   <li>La ambulancia se libera al finalizar cada solicitud.</li>
	 *   <li>Cuando <code>activo</code> es {@code false} o no quedan solicitudes, el ciclo termina.</li>
	 * </ul>
	 */
	
	
	@Override
	public void run() {
		while (activo && this.getCantSolicitudes() > 0 ) {
			assert this.ambulancia != null : "La ambulancia no puede ser null";
			// Decidir al azar qué acción realizar
			boolean quiereTraslado = Math.random() > 0.5;
			if (quiereTraslado) 
				this.ambulancia.pedirTraslado(this);
			else 
				this.ambulancia.pedirAmbulancia(this);
			
			try {
				// Simular el tiempo de uso del recurso o viaje
					Thread.sleep(2000); // espera 2 segundos
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally { 
				// Liberar la ambulancia
				this.cant_solicitudes -= 1; 
				this.ambulancia.dejarAmbulancia();
			}
		}
	}

	
	public String toString1() {
		return "- Trasladando asociado: \n  " + this.persona.getNombreyapellido() + " -  DNI: "
				+ this.persona.getDni();
	}

	public String toString2() {
		return "- Atendiendo asociado: \n  " + this.persona.getNombreyapellido() + " -  DNI: "
				+ this.persona.getDni();
	}
	
	public String stringTraslado() {
		return (" Fue trasladado por la Ambulancia \n");
	}
	
	public String stringAtencion() {
		return (" Recibio atencion de la Ambulancia \n");
	}
	
	@Override
	public String toString() {
		return this.persona.toString();
	}
	
}
