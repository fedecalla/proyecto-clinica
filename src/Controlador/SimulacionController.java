package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

public class SimulacionController implements Observer,ActionListener{
	
	private ArrayList<Observable> observados;
	
	

	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase("comenzarsimulacion"))
		{
			//lee la cantidad de asociados con lo que quiere simular y los trae de la clinica para guardarlos en el arrayLIst de observados
				//de esta manera cada observado tiene que declarar a su a este controlador como su observador

			//Lee la cantidad de peticiones que se realiza por asociado
			
			//arranca los hilos y comienza la simulacion de los asociados
			//pasa a la interfaz grafica todos los objetos que tiene que mostrar
			//cada vez que update es llamado muestra el cambio en la interfaz grafica
			
		}
		else if(evento.getActionCommand().equalsIgnoreCase("TerminaSimulacion"))
		{
			//espera a que terminen de ejecutarse los hilos
			
			//una vez que los hilos terminan la solicitud pendiente les corta el ciclo de peticiones
			
			//una vez que todos los hilos terminaron sus ciclos de peticiones le avisa a la vista para que cierre la venta y haga un popup de simulacion terminada con exito

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		//si update es llamado entonces es porque algun observado cambio su estado
		
		//chequea el cambio de estado, lo modifica en el arrayList y lo manda devuelta a la interfaz grafica para que muestre el cambio
	}

}
