package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import InterfazGrafica.VentanaEvolucionAsociado;
import InterfazGrafica.VentanaSimulacion;
import modelo.simulacion;
import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado;

public class SimulacionController implements ActionListener,Observer{

	private Ambulancia ambulancia;
	private simulacion modelo;
	private JDialog vista;

	// Flags para saber el estado de las tareas
	private boolean simulando, manteniendo,deteniendo;
	private int simulados = 0; //cantidad de asociados simulados
	int solicitudesxasociado = 3; //valor default

	public SimulacionController(simulacion modelo) {
		super();
		this.modelo = modelo;
		this.ambulancia = this.modelo.getAmbulancia();
		this.simulando = this.manteniendo = this.deteniendo = false;
	}

	public simulacion getModelo() {
		return modelo;
	}

	public void setModelo(simulacion modelo) {
		this.modelo = modelo;
	}

	public JDialog getVista() {
		return vista;
	}

	public void setVista(JDialog vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getActionCommand().equalsIgnoreCase("comenzarsimulacion"))
		{
			VentanaSimulacion ventana_sim = (VentanaSimulacion) this.vista;
			String input = ventana_sim.getTxtCantAsociados().getText().trim();
			String input2 = ventana_sim.getTxtCantSolicitudes().getText().trim();
			try {
				this.solicitudesxasociado = Integer.parseInt(input2);
				this.simulados = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(vista, "Ingrese solo números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			

			// Setting de flags:
			this.simulando = true;
			this.deteniendo = this.manteniendo = false;
			ventana_sim.getBtnComenzar().setEnabled(false);
			ventana_sim.getBtnFinalizar().setEnabled(true);
			ventana_sim.getBtnMant().setEnabled(true);
			
			this.modelo.setSolicitudesxasociado(solicitudesxasociado);
			this.modelo.setCant(this.simulados);
			this.modelo.getAmbulancia().addObserver(this);

			ventana_sim.actualizarEstado(this.ambulancia.getEstado().toString());
			this.modelo.comenzar(); 
		}
		else if(evento.getActionCommand().equalsIgnoreCase("TerminaSimulacion"))
		{
			VentanaSimulacion ventana_sim = (VentanaSimulacion) this.vista;
			this.deteniendo = true;
			this.simulando = this.manteniendo = false;
			ventana_sim.getBtnComenzar().setEnabled(true);
			ventana_sim.getBtnFinalizar().setEnabled(false);
			ventana_sim.getBtnMant().setEnabled(false);

			this.modelo.detener();
		}
		else if (evento.getActionCommand().equalsIgnoreCase("SolicitarMantenimiento")) {

			VentanaSimulacion ventana_sim = (VentanaSimulacion) this.vista;
			this.manteniendo = true;
			this.simulando = this.deteniendo = false;
			ventana_sim.getBtnComenzar().setEnabled(false);
			ventana_sim.getBtnFinalizar().setEnabled(true);
			ventana_sim.getBtnMant().setEnabled(false);	

			this.modelo.getOperario().Llamar();
		}
		else if (evento.getActionCommand().equalsIgnoreCase("EvolucionPacientes")) {
			SimulacionController s = new SimulacionController(this.modelo);
			JDialog vea = new VentanaEvolucionAsociado("Evolucion Pacientes",s, this.simulados);
			s.setVista(vea);
			vea.setVisible(true);
		}
		
		else if (evento.getActionCommand().equalsIgnoreCase("mostrarSolicitudes")) {
			VentanaEvolucionAsociado ventana_evo = (VentanaEvolucionAsociado) this.vista;
			 JButton fuente = (JButton) evento.getSource();
			 int indice = (int) fuente.getClientProperty("id");

			 Asociado seleccionado = modelo.getAsociados().get(indice);
			 ArrayList<String> solicitudes = seleccionado.getSolicitudes();
			 ventana_evo.mostrarSolicitudes(solicitudes,seleccionado);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String mensaje = (String) arg;
		VentanaSimulacion ventana_sim = (VentanaSimulacion) this.vista;

		SwingUtilities.invokeLater(() -> {
			if (mensaje.startsWith("ESTADO:")) {
				String estado = mensaje.replace("ESTADO:", "").trim();
				if (estado.equalsIgnoreCase("En el taller")) {
					this.manteniendo = false;
				}
				
				ventana_sim.actualizarEstado(estado);
		
			} else if (mensaje.startsWith("LOG: ")) {
				String log = mensaje.replace("LOG: ", "").trim();
				ventana_sim.actualizarLog(log);
			}

			if (this.manteniendo) {
				ventana_sim.getBtnMant().setEnabled(false);
			} else
				if (this.modelo.enTaller()) 
					ventana_sim.getBtnMant().setEnabled(false);
				else
					if (this.deteniendo)
						ventana_sim.getBtnMant().setEnabled(false);
					else
						ventana_sim.getBtnMant().setEnabled(true);


			});
		}

	public List<Asociado> getAsociados() {
		return this.modelo.getAsociados();
	}
}
