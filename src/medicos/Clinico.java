package medicos;

public class Clinico extends Medico{

	public Clinico(String nom, String dni, String mat, String ciudad, String dom, String tel) {
		super(nom, dni, mat, ciudad, dom, tel);
	}

	@Override
	public double getHonorario() {
		return honorarioBasico*1.05;
	}

}
