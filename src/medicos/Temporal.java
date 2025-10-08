package medicos;

/**
 * Clase Temporal, extiende de la clase Decorator Contrato
 */

public class Temporal extends DecoratorContrato{

	/**
	 * Constructor de la clase
	 */
	public Temporal(IMedico m) {
		super(m);
	}

	
	/**
	 * Calculo del honorario acumulado con la especialidad y posgrado
	 */
	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.05;
	}

}
