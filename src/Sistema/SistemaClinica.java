package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import medicos.Medico;
import medicos.consultasMedicas;
import pacientes.Paciente;
import excepciones.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class SistemaClinica implements iSistema {
	public Clinica clinica=null;
	
	public SistemaClinica(Clinica c) {
		this.clinica = Clinica.getClinica(c.getNombre(),c.getDireccion(),c.getTelefono(),c.getTelefono());
	}

	
	public void registraMedico(Medico m) {
		clinica.agregaMedico(m);
	}

	public void registraPaciente(Paciente p) {
		clinica.ingresaPaciente(p);
	}

	public void ingresaPaciente() {
		Paciente proximo;
		Factura factura;
		try 
		{
			proximo = clinica.atiendePaciente();
			factura = new Factura(proximo.getNombreyapellido());
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
		
		consulta.getMedicos().add(m); //podria hacerse en un setter
		
		Factura factura = clinica.getFactura(p);
		factura.setMedicos(m);
	}
	
	
	public Factura egresaPaciente(Paciente p) {
		clinica.eliminarPaciente(p);
		clinica.desvincularPacienteHabitacion(p);
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
	
	public void internaPaciente(Paciente p, String Thabitacion) {
		try
		{
			clinica.InternaPaciente(p,Thabitacion);
		}
		catch(NoHayHabitacionDisponibleException e)
		{
			System.out.println(e);
		}
	}
}
