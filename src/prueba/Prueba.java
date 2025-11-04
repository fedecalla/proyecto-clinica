package prueba;
import clinica.Clinica;
import excepciones.NoExisteContratoException;
import excepciones.NoExisteEspecialidadException;
import excepciones.NoExistePosgradoException;
import facturacion.Factura;
import hospedaje.*;
import Sistema.SistemaClinica;
import pacientes.Paciente;
import patrones.PacienteFactory;
import medicos.*;

import java.time.LocalDate;
import java.util.*;

public class Prueba {

	public static void main(String[] args) {
		SistemaClinica sistema = new SistemaClinica(Clinica.getClinica("clinicaColon","colon 2431","Mar del Plata" ,"223144343"));
		
		//EL PACIENTE LLEGA A LA CLINICA Y SE REGSITRA EN EL SISTEMA. SE LE OTORGA UN NUMERO DE ORDEN.
		Paciente p1 = PacienteFactory.crearPaciente("45231123", "Tomás Gómez", "1123456789", "Calle Falsa 123", "Buenos Aires", 101, 8);

		Clinico m1 = new Clinico("3123" , "asnfdas", "123124", "a la vuelta", "mardel pa", "12312");
		IMedico m2 = new Magister(m1);
		
		Paciente p2 = PacienteFactory.crearPaciente("38900654", "Lucía Fernández", "1134567890", "Av. Corrientes 4520", "Buenos Aires", 102, 27);
		Paciente p3 = PacienteFactory.crearPaciente("20331555", "Carlos Pérez", "1145567788", "San Martín 320", "Rosario", 103, 68);
		Paciente p4 = PacienteFactory.crearPaciente("47122001", "Martina López", "1167788990", "Belgrano 555", "Córdoba", 104, 10);
		Paciente p5 = PacienteFactory.crearPaciente("41788321", "Sofía Morales", "1122334455", "Rivadavia 222", "La Plata", 105, 35);
		MedicoFactory factory = new MedicoFactory();
		
		try {
			
		    IMedico m4 = MedicoFactory.crearMedico("cirujano", "doctor", "fijo", "Juan", "123", "MDP", "Calle Falsa", "223...", "MAT001");
		    IMedico m5 = MedicoFactory.crearMedico("clinico", "magister", "temporal", "Ana", "456", "MDP", "Calle Real", "223...", "MAT002");
		    
		    sistema.registraPaciente(p1);
		    sistema.registraPaciente(p2);
			sistema.registraMedico(m4);
			sistema.registraMedico(m5);
			sistema.ingresaPaciente();
			sistema.medicoAtiendePaciente(m4, p1);
			sistema.ingresaPaciente();
			sistema.medicoAtiendePaciente(m5, p1);
			sistema.internaPaciente(p1, "privada");
			sistema.internaPaciente(p1, "compartida");
			//sistema.internaPaciente(p1, "intensiva");
			
			Factura f1 = sistema.egresaPaciente(p1);
			
			System.out.println(f1.toString());
			System.out.println("HONORARIOS: \n");
		    System.out.printf("Honorario de %s: %.2f%n", m4.getMatricula(), m4.getHonorario());
		    System.out.printf("Honorario de %s: %.2f%n", m5.getMatricula(), m5.getHonorario());

		} catch (NoExisteEspecialidadException e) {
		    System.out.println("Error: Especialidad no reconocida → " + e.getMessage());
		} catch (NoExistePosgradoException e) {
		    System.out.println("Error: Posgrado no reconocido → " + e.getMessage());
		} catch (NoExisteContratoException e) {
		    System.out.println("Error: Tipo de contrato no reconocido → " + e.getMessage());
		}
		

	}

}
