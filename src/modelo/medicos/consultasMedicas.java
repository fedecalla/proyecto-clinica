package Modelo.medicos;
import java.time.LocalDate;

import java.util.ArrayList;

import Modelo.pacientes.Paciente;

public class consultasMedicas {
	private static int cantidad = 0;
	private ArrayList <IMedico> medicos;
	private LocalDate fecha;
	private Paciente paciente;
	
	/**
	 * <b>pre: </b><br>
	 * fecha != null <br>
	 * paciente != null <br>
	 * @param fecha de consulta
	 * @param paciente paciente que hizo la consulta
	 */
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
