package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import InterfazGrafica.VentanaAsociados;
import modelo.ambulancia.Asociado;
import modelo.clinica.Clinica;
import modelo.excepciones.AsociadoInvalidoException;




public class AsociadosController implements ActionListener{
	
	
	private VentanaAsociados vista;
	private Clinica modelo;
	
	public AsociadosController(Clinica modelo) {
		super();
		this.modelo = modelo;
	}
	
	public void setVista(VentanaAsociados vista)
	{
		this.vista = vista;
	}
	public void setModelo(Clinica modelo)
	{
		this.modelo = modelo;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		
		if(evento.getActionCommand().equalsIgnoreCase("AgregarAsociado"))
		{
			String nombre = null, apellido = null, dni=null, telefono = null, domicilio = null, ciudad = null;
			try {
				nombre = this.vista.getNombreAsociado();
				apellido = this.vista.getApellidoAsociado();
				dni = this.vista.getDniAsociado();
				telefono = this.vista.getTelefonoAsociado();
				domicilio = this.vista.getDomicilioAsociado();
				ciudad = this.vista.getCiudadAsociado();
				this.modelo.nuevoAsociado(nombre, apellido, dni, telefono, domicilio, ciudad);
				this.vista.popUp("asociado agregado con exito");
			}
			catch(AsociadoInvalidoException e) {
				this.vista.popUp(e.getMessage());
			}
		}
		else if(evento.getActionCommand().equalsIgnoreCase("EliminarAsociado"))
		{
			String dni = null;
			try {
				dni = this.vista.getDniAsociado();
				System.out.println(dni);
				this.modelo.removeAsociado(dni);
				this.vista.popUp("asociado eliminado con exito");
			}
			catch(AsociadoInvalidoException e)
			{
				this.vista.popUp(e.getMessage());
			}
			
		}
		else if(evento.getActionCommand().equalsIgnoreCase("ListarAsociados"))
		{
			ArrayList<Asociado> asociados = null;
			asociados = this.modelo.getAllAsociados();
			this.vista.Mostrar_Asociados(asociados);
		}

	}
}
