package medicos;

/**
 * Clase clinico, extiende de la clase abstracta Medico<br>
 * es una especialidad<br>
 */
public class Pediatra extends Medico {
	
	/**
	 * Constructor de la clase
	 */

	public Pediatra(String nom, String dni, String mat, String ciudad, String dom, String tel) {
		super(nom, dni, mat, ciudad, dom, tel);
		this.especialidad = "Pediatra";
	}
	
	/**
	 * Calculo su honorario
	 */

	@Override
	public double getHonorario() {
		return honorarioBasico*1.07;
	}
	
	
	

}
