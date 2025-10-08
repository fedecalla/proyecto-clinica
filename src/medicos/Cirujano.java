package medicos;


/**
 * Clase cirujano, extiende de la clase abstracta Medico
 * es una especialidad
 */
public class Cirujano extends Medico{
	
	/**
	 * Constructor de la clase
	 */

	public Cirujano(String nom, String dni, String mat, String ciudad, String dom, String tel) {
		super(nom, dni, mat, ciudad, dom, tel);
		this.especialidad = "Cirujano";
	}

	/**
	 * Calculo su honorario
	 */
	@Override
	public double getHonorario() {
		return honorarioBasico*1.1;
	}



}
