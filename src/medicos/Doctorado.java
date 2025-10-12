package medicos;

/**
 * Clase doctorado, extiende de la clase Decorator Posgrado
 */

public class Doctorado extends DecoratorPosgrado{

	/**
	 * Constructor de clase
	 */
	public Doctorado(IMedico m) {
		super(m);
	}

	/**
	 * Calculo del honorario acumulado con la especialidad
	 */
	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.1;
	}

	@Override
	public String getNombreyapellido() {
		// TODO Auto-generated method stub
		return super.getNombreyapellido();
	}
	@Override
	public String getEspecialidad() {
		// TODO Auto-generated method stub
		return super.getEspecialidad();
	}

}
