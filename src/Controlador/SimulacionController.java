package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import InterfazGrafica.VentanaPrincipal;
import modelo.simulacion;
import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado;
import modelo.individuos.Persona;

public class SimulacionController implements ActionListener,Observer{
	
	private Ambulancia ambulancia;
	private simulacion modelo;
	private VentanaPrincipal vista;
	

	

	@Override
	public void actionPerformed(ActionEvent evento) {
		int cantidad_asociados;
		if(evento.getActionCommand().equalsIgnoreCase("comenzarsimulacion"))
		{
			cantidad_asociados = Integer.parseInt(vista.lblEstado.getText()); //Lee la cantidad de peticiones que se realiza por asociado
																			  //de alguna forma habria que extraer 5 asociados de la base de datos y gurdarlos en:
																			  //ArrayList<Asociado> asociados = new ArrayList<Asociado>();

			ArrayList<Asociado> asociados = new ArrayList<Asociado>();
			Persona p1 = new Persona("40235123", "Juan Pérez", "3516543210", "Av. Colón 1234", "Córdoba");
			Persona p2 = new Persona("38999876", "María López", "3417896543", "San Martín 450", "Rosario");
			Persona p3 = new Persona("41567890", "Carlos González", "2614567890", "Belgrano 980", "Mendoza");
			Persona p4 = new Persona("42765432", "Lucía Fernández", "1132456789", "Av. Corrientes 1500", "Buenos Aires");

			asociados.add(new Asociado(p1,ambulancia));
			asociados.add(new Asociado(p2,ambulancia));
			asociados.add(new Asociado(p3,ambulancia));
			asociados.add(new Asociado(p4,ambulancia));

			this.ambulancia.addObserver(this);
			this.modelo = new simulacion(new ArrayList<Asociado>(),ambulancia);

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
		this.vista.lblEstado.setText(estado);
		//si update es llamado entonces es porque algun observado cambio su estado
		
		//chequea el cambio de estado, lo modifica en el arrayList y lo manda devuelta a la interfaz grafica para que muestre el cambio
	}

}
