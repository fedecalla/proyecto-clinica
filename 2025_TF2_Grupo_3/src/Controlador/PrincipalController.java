package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import InterfazGrafica.VentanaAsociados;
import InterfazGrafica.VentanaPrincipal;
import InterfazGrafica.VentanaSimulacion;
import modelo.simulacion;
import modelo.clinica.Clinica;
import persistencia.ConexionManager;

public class PrincipalController implements ActionListener{
	private VentanaPrincipal vista;
	private simulacion modelo_simulacion;
	private Clinica modelo_asociados;
	private ConexionManager modelo_conexion;

	public PrincipalController(simulacion modelo_simulacion,Clinica modelo_asociados) {
		super();
		this.modelo_simulacion = modelo_simulacion;
		this.modelo_asociados = modelo_asociados;
	}
	/**
	 * @param vista que va a manejar este controlador<br>
	 * pre: vista != null<br>
	 * post: ensambla la vista con este controlador<br>
	 * 
	 */
	public void setVista(VentanaPrincipal vista) {
		this.vista = vista;
	}
	
	/**
	 * @param ActionEvent evento<br>
	 * 
	 * pre: evento existente <br>
	 * post: Realiza algun cambio en el modelo segun el evento<br>
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getActionCommand().equalsIgnoreCase("VentanaSimulacion"))
		{
			VentanaSimulacion vs = new VentanaSimulacion(this.vista,"SIMULACION", new SimulacionController(this.modelo_simulacion));
			vs.setVisible(true);
		}
		else if (evento.getActionCommand().equalsIgnoreCase("VentanaAsociados"))
		{
			VentanaAsociados va = new VentanaAsociados(this.vista,"SIMULACION", new AsociadosController(this.modelo_asociados));
			va.setVisible(true);
		}
		else if (evento.getActionCommand().equalsIgnoreCase("Inicializar"))
		{
			ConexionManager.inicializarEsquema();
			this.vista.popUp("La tabla se ha creado satisfactoriamente!");
		}
	}
}