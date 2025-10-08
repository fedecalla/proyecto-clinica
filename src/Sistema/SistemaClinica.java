package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import medicos.Medico;
import pacientes.Paciente;
import individuos.Persona;
import medicos.Medico;
import pacientes.Paciente;

import hospedaje.Habitacion;

public class SistemaClinica implements iSistema {
	private Clinica clinica=null;
	
	public SistemaClinica(Clinica c) {
		this.clinica = c.getClinica(c.getNombre(),c.getDireccion(),c.getTelefono(),c.getTelefono());
	}

	
	public void registraMedico(Medico m) {
		clinica.agregaMedico(m);
	}

	public void registraPaciente(Paciente p) {
		clinica.agregaPacienteACola(p);
		clinica.ingresaPaciente(p);
	}

	public void ingresaPaciente() {
		Paciente proximo;
		proximo = clinica.atiendePaciente();
		Factura factura = new Factura(proximo.getNombreyapellido());
		clinica.agregaFactura(factura);
		
		//aca de alguna manera tendriamos que saber el proposito del paciente e internarlo si es necesario
		
		clinica.agregaPaciente(proximo);
	}

	public void atiendePacienteMedico(Medico m, Paciente p)
	{
		Factura factura = clinica.getFactura(p);
		factura.setMedicos(m);
	}
	
	
	public Factura egresaPaciente(Paciente p) {
		clinica.eliminarPaciente(p);
		clinica.desvincularPacienteHabitacion(p);
		return clinica.getFactura(p); 
	}

	//public void internaPaciente(Paciente p, Habitacion h) {
		//clinica.InternaPaciente(p,h);
	//}
}
