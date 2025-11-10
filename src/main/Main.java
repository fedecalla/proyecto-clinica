package main;
import Controlador.AsociadosController;
import InterfazGrafica.VentanaPrincipal;
import modelo.clinica.Clinica;

public class Main {

	public static void main(String[] args) {
		
		Clinica c = Clinica.getClinica("sadasd", "sadsad123213", "mardel", "12344556");
		AsociadosController asociadosController = new AsociadosController();
		VentanaPrincipal ventanaP = new VentanaPrincipal("Clinica progra");
		ventanaP.setVisible(true);
		

	}

}
