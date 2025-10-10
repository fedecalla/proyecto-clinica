package pacientes;

public class Joven extends Paciente{
	public Joven(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica, int norden){
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica, norden);
		this.rango = "Joven";
	}
	public Paciente getGanador(Paciente otro) {
		return otro.enfrentaJoven(this);
	}
	public Paciente enfrentaNinio(Paciente otro) {
		return otro;
	}
	public Paciente enfrentaJoven(Paciente otro) {
		return this;
	}
	public Paciente enfrentaMayor(Paciente otro) {
		return this;
	}
	
	@Override
	public String getRango() {
		return this.rango;
	}
}
