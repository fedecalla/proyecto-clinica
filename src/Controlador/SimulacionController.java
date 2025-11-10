package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import InterfazGrafica.VentanaSimulacion;
import modelo.simulacion;
import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Llamador;
import modelo.ambulancia.Operario;
import modelo.persistencia.AsociadoDAO;

public class SimulacionController implements ActionListener,Observer{
	
	private Ambulancia ambulancia;
	private simulacion modelo;
	private VentanaSimulacion vista;
	
	
	public SimulacionController(VentanaSimulacion vista) {
		super();
		this.ambulancia = Ambulancia.getAmbulancia();
		this.vista = vista;
		this.modelo = new simulacion(this.ambulancia, new AsociadoDAO(), new Operario(new Llamador(this.ambulancia)));
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		int cantidad_asociados;
		if(evento.getActionCommand().equalsIgnoreCase("comenzarsimulacion"))
		{
			cantidad_asociados = Integer.parseInt(vista.getTxtCantAsociados().getText());
			this.modelo.setCant(cantidad_asociados);
			this.modelo.getAmbulancia().addObserver(this);
			
			this.vista.getLblEstado().setText((this.ambulancia.getEstado().toString()).toUpperCase());
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
		else if (evento.getActionCommand().equalsIgnoreCase("SolicitarMantenimiento")) {
			this.modelo.getOperario().Llamar();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
	    String mensaje = (String) arg;

	    SwingUtilities.invokeLater(() -> {
	        if (mensaje.startsWith("ESTADO:")) {
	            String estado = mensaje.replace("ESTADO:", "").trim();
	            vista.getLblEstado().setText(estado);
	        } else if (mensaje.startsWith("LOG: ")) {
	            String log = mensaje.replace("LOG: ", "").trim();
	            vista.getAreaMovimientos().append(log + "\n");
	            vista.getAreaMovimientos().setCaretPosition(
	                vista.getAreaMovimientos().getDocument().getLength()
	            );
	        }
	    });
	}



}
