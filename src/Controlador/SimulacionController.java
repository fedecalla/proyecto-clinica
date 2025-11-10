package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import InterfazGrafica.VentanaSimulacion;
import modelo.simulacion;
import modelo.ambulancia.Ambulancia;
import modelo.persistencia.AsociadoDAO;

public class SimulacionController implements ActionListener,Observer{
	
	private Ambulancia ambulancia;
	private simulacion modelo;
	private VentanaSimulacion vista;
	
	
	public SimulacionController(VentanaSimulacion vista) {
		super();
		this.ambulancia = Ambulancia.getAmbulancia();
		this.vista = vista;
		this.modelo = new simulacion(this.ambulancia, new AsociadoDAO());
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		int cantidad_asociados;
		if(evento.getActionCommand().equalsIgnoreCase("comenzarsimulacion"))
		{
			cantidad_asociados = Integer.parseInt(vista.getTxtCantAsociados().getText());
			this.modelo.setCant(cantidad_asociados);
			this.modelo.getAmbulancia().addObserver(this);
			this.modelo.run();  //arranca los hilos y comienza la simulacion de los asociados
			
			//pasa a la interfaz grafica todos los objetos que tiene que mostrar
			//cada vez que update es llamado muestra el cambio en la interfaz grafica
			
		}
		else if(evento.getActionCommand().equalsIgnoreCase("TerminaSimulacion"))
		{
			
			this.modelo.detener();
			//espera a que terminen de ejecutarse los hilos
			
			//una vez que los hilos terminan la solicitud pendiente les corta el ciclo de peticiones
			
			//una vez que todos los hilos terminaron sus ciclos de peticiones le avisa a la vista para que cierre la venta y haga un popup de simulacion terminada con exito

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String estado = (String) arg;
		this.vista.getLblEstado().setText(estado);
		//si update es llamado entonces es porque algun observado cambio su estado
		
		//chequea el cambio de estado, lo modifica en el arrayList y lo manda devuelta a la interfaz grafica para que muestre el cambio
	}

}
