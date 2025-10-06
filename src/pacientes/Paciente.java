package pacientes;
import individuos.Persona;

public class Paciente extends Persona{
	private int historiaclinica;
	private int norden;
	
	public Paciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, int norden) {
		super(dni, nombreyapellido, telefono, domicilio, ciudad);
		this.historiaclinica = historiaclinica;
		this.norden = norden;
	}

	public int getHistoriaclinica() {
		return historiaclinica;
	}

	public int getNorden() {
		return norden;
	}
	
	


}
