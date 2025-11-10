package Controlador;

import InterfazGrafica.VentanaAsociados;
import modelo.clinica.*;
import modelo.excepciones.AsociadoInvalidoException;
import modelo.individuos.Persona;
import modelo.clinica.Clinica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class AsociadosController implements ActionListener{
	
	
	private VentanaAsociados vista;
	private Clinica modelo;
	
	public AsociadosController(VentanaAsociados vista) {
		super();
		this.vista = vista;
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
			String nombre = null, apellido = null, dni=null;
			try {
				this.vista.getDatosAsociado(nombre, apellido, dni);
				this.modelo.nuevoAsociado(nombre, apellido, dni);
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
				this.vista.getDniAsociado(dni);
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
			ArrayList<Persona> asociados = new ArrayList<>();
			asociados = this.modelo.getAllAsociados();
			//this.vista.ListarAsociados(asociados);
			
		}

	}
}
