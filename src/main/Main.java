package main;
import InterfazGrafica.VentanaPrincipal;
import modelo.clinica.Clinica;

public class Main {

	public static void main(String[] args) {

		Clinica c = Clinica.getClinica("Clínica Central", "Av. Rivadavia 1234", "Mar del Plata", "011-4567-8901");
		VentanaPrincipal ventanaP = new VentanaPrincipal("Clinica progra");
		ventanaP.setVisible(true);
	}

}

