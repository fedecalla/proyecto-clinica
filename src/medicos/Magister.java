package medicos;

/**
 * Clase magister, extiende de la clase Decorator Posgrado
 */
public class Magister extends DecoratorPosgrado{

	/**
	 * Constructor de clase
	 */
	public Magister(IMedico m) {
		super(m);
	}
	/**
	 * Calculo del honorario acumulado con la especialidad
	 */

	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.05;
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
