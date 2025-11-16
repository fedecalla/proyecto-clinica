package patrones.double_dispatch;

import modelo.pacientes.Paciente;

public class Ninio extends Paciente{

	public Ninio(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica) {
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica);
		this.rango = "Ninio";
		// TODO Auto-generated constructor stub
	}
	/**
	 * Metodo que resuelve en tiempo de ejecucion quien gana la sala de espera<br>
	 * @param Paciente
	 * @return el ganador de la sala de espera
	 */
	public Paciente getGanador(Paciente otro) {
		return otro.enfrentaNinio(this);
	}
	
	/**
	 * Metodos que determinan el ganador concreto de la sala de espera<br>
	 * Recibe un paciente y lo enfrenta con el actual<br>
	 */
	
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
