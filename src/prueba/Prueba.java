package prueba;
import clinica.Clinica;
import Sistema.SistemaClinica;
import pacientes.Paciente;
import medicos.Clinico;
import pacientes.PacienteFactory;

public class Prueba {

	public static void main(String[] args) {
		
		Clinico m = new Clinico("222222","medic","161616","direccion0", "mdp", "Matriculanazi");
		
		Paciente p1 = PacienteFactory.crearPaciente("45231123", "Tomás Gómez", "1123456789", "Calle Falsa 123", "Buenos Aires", 101, 8);
		Paciente p2 = PacienteFactory.crearPaciente("38900654", "Lucía Fernández", "1134567890", "Av. Corrientes 4520", "Buenos Aires", 102, 27);
		Paciente p3 = PacienteFactory.crearPaciente("20331555", "Carlos Pérez", "1145567788", "San Martín 320", "Rosario", 103, 68);
		Paciente p4 = PacienteFactory.crearPaciente("47122001", "Martina López", "1167788990", "Belgrano 555", "Córdoba", 104, 10);
		Paciente p5 = PacienteFactory.crearPaciente("41788321", "Sofía Morales", "1122334455", "Rivadavia 222", "La Plata", 105, 35);

		SistemaClinica sistema = new SistemaClinica(Clinica.getClinica("clinicaColon","colon 2431","Mar del Plata" ,"223144343"));
		
		sistema.getClinica().agregaPaciente(p1);
		sistema.getClinica().agregaPaciente(p2);
		
		System.out.print(sistema.getClinica().getPacientes().get(1).getNombre());
		
		
		
		/* 
		 * El paciente llega a la ventana de atención al cliente de la clínica y se le otorga un número de orden. 
		 * Luego es derivado a la Sala de Espera Privada o al Patio, según corresponda. 
		 * A partir de ese momento el Paciente es atendido cuando lo llamen.
		 * 
		 * */
		
		
/*		sistema.ingresaPaciente(p1);
		sistema.registraMedico(m);
		System.out.println(p1.toString());System.out.println(p2.toString());System.out.println(p3.toString());
		System.out.println(m.toString());
*/
	}

}
