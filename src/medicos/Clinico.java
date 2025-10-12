package medicos;

/**
 * Clase clinico, extiende de la clase abstracta Medico
 * es una especialidad
 */

public class Clinico extends Medico{
	
	/**
	 * Constructor de la clase
	 */
	
	public Clinico(String nom, String dni, String ciudad, String dom, String tel, String mat) {
	    super(nom, dni, ciudad, dom, tel, mat);
	    this.especialidad = "Clinico";
	}


	/**
	 * Calculo su honorario
	 */
	@Override
	public double getHonorario() {
		return honorarioBasico*1.05;
	}



}
