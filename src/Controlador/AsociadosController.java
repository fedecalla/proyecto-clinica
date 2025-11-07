package Controlador;

import Vista.VentanaSistema;
import Modelo.clinica.Clinica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class AsociadosController implements ActionListener{
	
	
	private VentanaSistema vista;
	private Clinica modelo;

	public AsociadosController(VentanaSistema vista , Clinica modelo)
	{
		this.vista = vista;
		this.modelo = modelo;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		
		if(evento.getActionCommand().equalsIgnoreCase("AgregarAsociado"))
		{
			//Agrega un asociado a la base de datos y lo agrega al Arralist de Asociados
			
			//paso 1: crea un objeto asociado y chequea que todos los campos hayan sido correctamente completados
				//si no se pudo crear tira alguna excepcion atrapada y que haga un popup informando el error
			
			//paso2:  llama a un metodo que lo agregue a la base de datos
			//paso3: Agrega al objeto al arrayList de Asociados
			
			//paso4: llama a algun metodo de la vista que informa que la operacion fue exitosa
			
			
			
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
