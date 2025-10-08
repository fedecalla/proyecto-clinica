package medicos;
import individuos.Persona;

public abstract class Medico extends Persona implements IMedico{
	protected String matricula, especialidad;
	
	protected static double honorarioBasico = 20000;
	
	public Medico(String nom, String dni, String ciudad, String dom, String tel, String mat) {
		super(dni, nom, tel, dom, ciudad);
		this.matricula=mat;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}



	public String getMatricula() {
		return matricula;
	}
	
	public abstract double getHonorario();

	@Override
	public String toString() {
		return super.toString()+" - (Medico) matricula: " + matricula + " - especialidad: " + especialidad + "";
	}
	
	

}
