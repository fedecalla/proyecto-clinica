package medicos;

import pacientes.Paciente;
import java.time.LocalDate;
import java.util.ArrayList;

public class consultasMedicas {
	private static int cantidad = 0;
	private ArrayList <IMedico> medicos;
	private LocalDate fecha;
	private Paciente paciente;
	
	public consultasMedicas(LocalDate fecha, Paciente paciente) {
		this.medicos = new ArrayList<IMedico>();
		this.fecha=fecha;
		this.paciente=paciente;
		consultasMedicas.cantidad+= 1;
	}


	public LocalDate getFecha() {
		return fecha;
	}
	
	public ArrayList<IMedico> getMedicos(){
		return this.medicos;
	}
	
	public static int getcantidad(){
		return consultasMedicas.cantidad;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}
	@Override
	public String toString() {
		return " Fecha: " + fecha + " - Paciente: " + this.paciente.getNombre() + "\n";
	}
}
