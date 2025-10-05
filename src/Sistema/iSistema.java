package Sistema;
import facturacion.Factura;
import hospedaje.Habitacion;
import individuos.Medico;
import individuos.Paciente;

public interface iSistema {

void registraMedico(Medico m);

void registraPaciente(Paciente p);

void ingresaPaciente(Paciente p);

void atiendePaciente(Medico m, Paciente p);

Factura egresaPaciente(Paciente p);

void internaPaciente(Paciente p, Habitacion h);

}
