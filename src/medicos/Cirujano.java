package medicos;

public class Cirujano extends Medico{

	public Cirujano(String nom, String dni, String mat, String ciudad, String dom, String tel) {
		super(nom, dni, mat, ciudad, dom, tel);
		this.especialidad = "Cirujano";
	}

	@Override
	public double getHonorario() {
		return honorarioBasico*1.1;
	}



}
