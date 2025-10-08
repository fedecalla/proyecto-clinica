package medicos;

import java.time.LocalDate;

public class consultasMedicas {
	private LocalDate fecha;
	private String nombrePaciente;
	
	public consultasMedicas(LocalDate fecha, String nombrepaciente) {
		this.fecha=fecha;
		this.nombrePaciente=nombrepaciente;		
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}
	@Override
	public String toString() {
		return " Fecha:" + fecha + " - Paciente: " + nombrePaciente + "\n";
	}
	
}
