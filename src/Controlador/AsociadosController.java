package Controlador;

import InterfazGrafica.VentanaAsociados;
import Modelo.excepciones.AsociadoInvalidoException;
import Modelo.clinica.Clinica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class AsociadosController implements ActionListener{
	
	
	private VentanaAsociados vista;
	private Clinica modelo;

	public AsociadosController(VentanaAsociados vista, Clinica modelo)
	{
		this.vista = vista;
		this.modelo = modelo;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		
		if(evento.getActionCommand().equalsIgnoreCase("AgregarAsociado"))
		{
			String nombre, apellido, dni;
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
			//Elimina un asociado de la base de datos si es que existe
				
			// Paso 1: chequear que los campos hayan sido correctamente completados
			// paso 2: buscar al asociado de la base de datos
			// paso 3: Si Existe eliminarlo de la base de datos
			
			//paso 4: Llamar a algun metodo que informe que la operacion fue exitosa			
			
		}
		else if(evento.getActionCommand().equalsIgnoreCase("ListarAsociados"))
		{
			//Trae todos los asociados de la base de datos y los lista
				//llamaria a alguna funcion del paquete de persistencia para traer todos los elementos de la tabla asociados en un arrayList
			
			// llamar a alguna funcion de la vista que reciba un arrayList y muestre la informacion por pantalla en algun panel concreto
			
		}

	}
}
