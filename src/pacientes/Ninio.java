package pacientes;

import excepciones.RangoNoEncontradoException;

public class Ninio extends Paciente{

	public Ninio(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica, int norden, String rango) throws RangoNoEncontradoException {
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica, norden);
		// TODO Auto-generated constructor stub
	}
	public Paciente getGanador(Paciente otro) {
		return otro.enfrentaNinio(this);
	}
	public Paciente enfrentaNinio(Paciente otro) {
		return this;
	}
	public Paciente enfrentaJoven(Paciente otro) {
		return this;
	}
	public Paciente enfrentaMayor(Paciente otro) {
		return otro;
	}
}
