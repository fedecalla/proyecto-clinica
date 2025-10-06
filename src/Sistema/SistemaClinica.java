package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import medicos.Medico;
import pacientes.Paciente;
import individuos.Persona;

import hospedaje.Habitacion;

public class SistemaClinica implements iSistema {
	private Clinica clinica;
	
	SistemaClinica(Clinica c){
		this.clinica=c;
	}
	
	public void registraMedico(Medico m) {
		clinica.agregaMedico(m);
	}

	public void registraPaciente(Persona p) {
		
		Factura factura = new Factura(p.getNombreyapellido());
		clinica.agregaPacienteACola(p);
		clinica.agregaFactura(factura);

		//y enviarlo a patio o sala de espera
	}

	public void ingresaPaciente(Persona p , String proposito) {
		
		if(proposito.toLowerCase() == "internacion")
			clinica.InternaPaciente(p);
		clinica.agregaPaciente(p);
	}

	public void atiendePaciente(Medico m, Persona p)
	{
		Factura factura = clinica.getFactura(p);
		factura.setMedicos(m);
	}
	public Factura egresaPaciente(Persona p) {
		clinica.eliminarPaciente(p);
		clinica.desvincularPacienteHabitacion(p);
		return clinica.getFactura(p); 
	}

	//public void internaPaciente(Paciente p, Habitacion h) {
		//clinica.internar(p,h);
	}

}
