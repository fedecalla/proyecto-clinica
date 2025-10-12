package pacientes;


/* Clase que modela el comportamiento de los pacientes jovenes y su double dispatch
 * 
 */

public class Joven extends Paciente{
	/*Utiliza el constructor de la clase padre y le agrega su rango etario
	 * 
	 */
	public Joven(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad,
			int historiaclinica){
		super(dni, nombreyapellido, telefono, domicilio, ciudad, historiaclinica);
		this.rango = "Joven";
	}
	
	/*
	 * Metodo que resuelve en tiempo de ejecucion quien gana la sala de espera
	 * @param Paciente
	 * Precondicion Paciente!=null
	 * Retorna el ganador de la sala de espera
	 */
	public Paciente getGanador(Paciente otro) {
		return otro.enfrentaJoven(this);
	}
	
	/*
	 * Metodos que determinan el ganador concreto de la sala de espera
	 */
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
