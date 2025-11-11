package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
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

	// Flags para saber el estado de las tareas
	private boolean simulando, manteniendo,deteniendo;


	public SimulacionController(VentanaSimulacion vista) {
		super();
		this.ambulancia = Ambulancia.getAmbulancia();
		this.vista = vista;
		this.modelo = new simulacion(this.ambulancia, new AsociadoDAO(), new Operario(this.ambulancia));
		this.simulando = this.manteniendo = this.deteniendo = false;

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		int cantidad_asociados;
		if(evento.getActionCommand().equalsIgnoreCase("comenzarsimulacion"))
		{

			String input = vista.getTxtCantAsociados().getText().trim();
			try {
				cantidad_asociados = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(vista, "Ingrese solo números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Setting de flags:
			this.simulando = true;
			this.deteniendo = this.manteniendo = false;
			this.vista.getBtnComenzar().setEnabled(false);
			this.vista.getBtnFinalizar().setEnabled(true);
			this.vista.getBtnMant().setEnabled(true);

			this.modelo.setCant(cantidad_asociados);
			this.modelo.getAmbulancia().addObserver(this);

			this.vista.getLblEstado().setText((this.ambulancia.getEstado().toString()).toUpperCase());
			this.modelo.comenzar();  //arranca los hilos y comienza la simulacion de los asociados

			//pasa a la interfaz grafica todos los objetos que tiene que mostrar
			//cada vez que update es llamado muestra el cambio en la interfaz grafica

		}
		else if(evento.getActionCommand().equalsIgnoreCase("TerminaSimulacion"))
		{
			// Setting de flags:
			this.deteniendo = true;
			this.simulando = this.manteniendo = false;
			this.vista.getBtnComenzar().setEnabled(true);
			this.vista.getBtnFinalizar().setEnabled(false);
			this.vista.getBtnMant().setEnabled(false);

			this.modelo.detener();
			//espera a que terminen de ejecutarse los hilos

			//una vez que los hilos terminan la solicitud pendiente les corta el ciclo de peticiones

			//una vez que todos los hilos terminaron sus ciclos de peticiones le avisa a la vista para que cierre la venta y haga un popup de simulacion terminada con exito

		}
		else if (evento.getActionCommand().equalsIgnoreCase("SolicitarMantenimiento")) {

			this.manteniendo = true;
			this.simulando = this.deteniendo = false;
			this.vista.getBtnComenzar().setEnabled(false);
			this.vista.getBtnFinalizar().setEnabled(true);
			this.vista.getBtnMant().setEnabled(false);	

			this.modelo.getOperario().Llamar();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String mensaje = (String) arg;

		SwingUtilities.invokeLater(() -> {
			if (mensaje.startsWith("ESTADO:")) {
				String estado = mensaje.replace("ESTADO:", "").trim();
				if (estado.equalsIgnoreCase("En el taller")) {
					this.manteniendo = false;
				}
				vista.getLblEstado().setText(estado);			
			} else if (mensaje.startsWith("LOG: ")) {
				String log = mensaje.replace("LOG: ", "").trim();
				vista.getAreaMovimientos().append(log + "\n");
				vista.getAreaMovimientos().setCaretPosition(
						vista.getAreaMovimientos().getDocument().getLength()
						);
			}

			if (this.manteniendo) {
				this.vista.getBtnMant().setEnabled(false);
			} else
				if (this.modelo.enTaller()) 
					this.vista.getBtnMant().setEnabled(false);
				else
					if (this.deteniendo)
						this.vista.getBtnMant().setEnabled(false);
					else
						this.vista.getBtnMant().setEnabled(true);


			});
		}

	
}
