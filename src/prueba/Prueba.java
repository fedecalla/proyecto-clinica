package prueba;
import clinica.Clinica;
import Sistema.SistemaClinica;

public class Prueba {

	public static void main(String[] args) {
		
		Clinica clinica = Clinica.getClinica("clinicaColon","colon 2431","Mar del Plata" ,"223144343");
		SistemaClinica sistema = new SistemaClinica(clinica);

	}

}
