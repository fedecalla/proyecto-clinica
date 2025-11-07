package Sistema;
import java.time.LocalDate;

import modelo.excepciones.MedicoNoExisteException;
import modelo.facturacion.Factura;
import modelo.medicos.IMedico;
import modelo.pacientes.Paciente;

public interface iSistema {

	public void registraMedico(IMedico m);

	public void registraPaciente(Paciente p);

	public void ingresaPaciente();

	public void medicoAtiendePaciente(IMedico m, Paciente p) throws MedicoNoExisteException;
	
	public Factura egresaPaciente(Paciente p);

	public String ActividadMedico(IMedico m, LocalDate desde, LocalDate hasta) throws MedicoNoExisteException;
	
	public void internaPaciente(Paciente p, String tHabitacion);
	
}
