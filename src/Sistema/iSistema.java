package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import hospedaje.Habitacion;
import medicos.Medico;
import pacientes.Paciente;

public interface iSistema {

	public void registraMedico(Medico m);

	public void registraPaciente(Paciente p);

	public void ingresaPaciente();

	public void atiendePacienteMedico(Medico m, Paciente p);
	
	public Factura egresaPaciente(Paciente p);

	//public void internaPaciente(Paciente p, Habitacion h);
	
}
