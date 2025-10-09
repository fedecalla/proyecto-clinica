
package pacientes;
import individuos.Persona;
import excepciones.RangoNoEncontradoException;

public abstract class Paciente extends Persona{
	private int historiaclinica;
	private int norden;
	private String rango;
	
	
	public Paciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, int norden) throws RangoNoEncontradoException{
		super(dni, nombreyapellido, telefono, domicilio, ciudad);
		this.historiaclinica = historiaclinica;
		this.norden = norden;
		this.rango = rango;	
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

	public String getRango() {
		return rango;
	}
	
	public abstract Paciente getGanador(Paciente otro);
	public abstract Paciente enfrentaNinio(Paciente otro);
	public abstract Paciente enfrentaJoven(Paciente otro);
	public abstract Paciente enfrentaMayor(Paciente otro);


}
