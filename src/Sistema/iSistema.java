package Sistema;
import facturacion.Factura;
import medicos.IMedico;
import pacientes.Paciente;

public interface iSistema {

	public void registraMedico(IMedico m);

	public void registraPaciente(Paciente p);

	public void ingresaPaciente();

	public void medicoAtiendePaciente(IMedico m, Paciente p);
	
	public Factura egresaPaciente(Paciente p);

	public void internaPaciente(Paciente p, String tHabitacion);
	
}
