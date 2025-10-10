
package pacientes;
import individuos.Persona;

public abstract class Paciente extends Persona{
	private int historiaclinica;
	private int norden;
	protected String rango;
	
	
	public Paciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, int norden)
	{
		super(dni, nombreyapellido, telefono, domicilio, ciudad);
		this.historiaclinica = historiaclinica;
		this.norden = norden;
	}
	
	public String getNombre() {
		return this.nombreyapellido;
	}

	public int getHistoriaclinica() {
		return historiaclinica;
	}

	public int getNorden() {
		return norden;
	}

	public abstract String getRango();
	
	public abstract Paciente getGanador(Paciente otro);
	public abstract Paciente enfrentaNinio(Paciente otro);
	public abstract Paciente enfrentaJoven(Paciente otro);
	public abstract Paciente enfrentaMayor(Paciente otro);


}
