package medicos;

public class Clinico extends Medico{

	public Clinico(String nom, String dni, String ciudad, String dom, String tel, String mat) {
		super(nom, dni, mat, ciudad, dom, tel);
		this.especialidad = "Clinico";
	}

	@Override
	public double getHonorario() {
		return honorarioBasico*1.05;
	}



}
