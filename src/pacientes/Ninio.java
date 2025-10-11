package pacientes;


public class Ninio extends Paciente{

	public Ninio(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica) {
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica);
		this.rango = "Ninio";
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
	
	@Override
	public String getRango() {
		return this.rango;
	}
}
