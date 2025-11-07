package main;
import Modelo.clinica.*;
import InterfazGrafica.VentanaPrincipal;

import java.awt.event.ActionListener;

import Controlador.AsociadosController;

public class Main {

	public static void main(String[] args) {
		
		Clinica c = Clinica.getClinica("sadasd", "sadsad123213", "mardel", "12344556");
		AsociadosController asociadosController = new AsociadosController();
		VentanaPrincipal ventanaP = new VentanaPrincipal(asociadosController);
		

	}

}
