package medicos;

public class Pediatra extends Medico{

	public Pediatra(String nom, String dni, String mat, String ciudad, String dom, String tel) {
		super(nom, dni, mat, ciudad, dom, tel);
		this.especialidad = "Pediatra";
	}

	@Override
	public double getHonorario() {
		return honorarioBasico*1.07;
	}

	

}
