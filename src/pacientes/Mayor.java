package pacientes;

class Mayor extends Paciente{
	public Mayor(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica, int norden) {
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica, norden);
		this.rango="Mayor";
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
	
	@Override
	public String getRango() {
		return this.rango;
	}
}
