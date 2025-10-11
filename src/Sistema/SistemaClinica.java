package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import medicos.Medico;
import medicos.consultasMedicas;
import pacientes.Paciente;
import individuos.Persona;
import medicos.Medico;
import pacientes.Paciente;
import excepciones.*;
import java.util.ArrayList;
import java.time.LocalDate;

import hospedaje.Habitacion;

public class SistemaClinica implements iSistema {
	private Clinica clinica=null;
	
	public SistemaClinica(Clinica c) {
		this.clinica = c.getClinica(c.getNombre(),c.getDireccion(),c.getTelefono(),c.getTelefono());
	}

	public Clinica getClinica() {
		return this.clinica;
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
		try 
		{
			proximo = clinica.atiendePaciente();
			Factura factura = new Factura(proximo.getNombreyapellido());
			clinica.agregaFactura(factura);
			clinica.agregaPaciente(proximo);
			clinica.CreaConsulta(proximo);
		}
		catch(NoHayPacientesEnEsperaException e)
		{
			System.out.println(e);
		}
	}

	public void atiendePacienteMedico(Medico m, Paciente p)
	{
		consultasMedicas consulta = clinica.GetConsultaByPaciente(p);
		consulta.getMedicos().add(m);
		
		Factura factura = clinica.getFactura(p);
		factura.setMedicos(m);
	}
	
	
	public Factura egresaPaciente(Paciente p) {
		clinica.desvincularPacienteHabitacion(p);
		clinica.eliminarPaciente(p);
		return clinica.getFactura(p); 
	}
	
	public void ActividadMedico(Medico m, LocalDate desde, LocalDate hasta)
	{
		ArrayList<consultasMedicas> actividad = new ArrayList <>();
		try
		{
			actividad = clinica.getConsultas(m, desde, hasta);
			clinica.PrintConsultas(actividad);
		}
		catch(MedicoNoExisteException e)
		{
			System.out.println(e);
		}
	}

	//public void internaPaciente(Paciente p, Habitacion h) {
		//clinica.InternaPaciente(p,h);
	//}
}
