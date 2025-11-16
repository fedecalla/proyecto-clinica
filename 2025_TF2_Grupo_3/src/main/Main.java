package main;
import Controlador.PrincipalController;
import InterfazGrafica.VentanaPrincipal;
import modelo.simulacion;
import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Operario;
import modelo.clinica.Clinica;

public class Main {

	public static void main(String[] args) {
		
		Clinica c = Clinica.getClinica("Clínica Central", "Av. Rivadavia 1234", "Mar del Plata", "011-4567-8901");
		//CREACION DE LOS MODELOS A UTILIZAR:
		Ambulancia a = Ambulancia.getAmbulancia();
		simulacion s = new simulacion(a,new Operario(a));
		
		//CREACION DE LOS CONTROLADORES A UTILIZAR PASANDO COMO PARAMETRO A SU/s MODELO
		PrincipalController principalcontroller = new PrincipalController(s,c);

		//CREACION DE LAS VISTAS A UTILIZAR PASANDO COMO PARAMETRO A SU/s CONTROLADOR
		VentanaPrincipal ventanaP = new VentanaPrincipal(principalcontroller,"SISTEMA CLINICA");
		ventanaP.setVisible(true);
	}

}

