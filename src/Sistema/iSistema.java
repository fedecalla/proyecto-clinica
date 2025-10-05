package Sistema;
import facturacion.Factura;
import hospedaje.Habitacion;

public interface iSistema {

void registraMedico(Medico m);
void registraMedico(Medico m);

void registraPaciente(Paciente p);
void registraPaciente(Paciente p);

void ingresaPaciente(Paciente p);
void ingresaPaciente(Paciente p);

void atiendePaciente(Medico m, Paciente p);
void atiendePaciente(Medico m, Paciente p);

Factura egresaPaciente(Paciente p);

void internaPaciente(Paciente p, Habitacion h);

Factura egresaPaciente(Paciente p, int cantdias);
}
