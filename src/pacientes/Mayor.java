package pacientes;

import excepciones.RangoNoEncontradoException;

public class Mayor extends Paciente{
	public Mayor(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica, int norden) throws RangoNoEncontradoException {
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica, norden);
		// TODO Auto-generated constructor stub
	}
	public Paciente getGanador(Paciente otro) {
		return otro.enfrentaMayor(this);
	}
	public Paciente enfrentaNinio(Paciente otro) {
		return this;
	}
	public Paciente enfrentaJoven(Paciente otro) {
		return otro;
	}
	public Paciente enfrentaMayor(Paciente otro) {
		return this;
	}
}
