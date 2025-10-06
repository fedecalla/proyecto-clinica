package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import individuos.Medico;
import individuos.Paciente;

import hospedaje.Habitacion;

public class SistemaClinica implements iSistema {
	private Clinica clinica;
	
	SistemaClinica(Clinica c){
		this.clinica=c;
	}
	
	public void registraMedico(Medico m) {
		clinica.agregaMedico(m);
	}

	public void registraPaciente(Paciente p) {
		clinica.agregaPaciente(p);
	}

	public void ingresaPaciente(Paciente p) {
		clinica.ingresaPaciente(p);
	}

	public void atiendePaciente(Medico m, Paciente p);

	public Factura egresaPaciente(Paciente p) {
		return clinica.getFactura(p); 
	}

	public void internaPaciente(Paciente p, Habitacion h) {
		clinica.internar(p,h);
	}

}
