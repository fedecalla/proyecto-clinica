package medicos;

/**
 * Clase Fijo, extiende de la clase Decorator Contrato
 */

public class Fijo extends DecoratorContrato{

	/**
	 * Constructor de la clase
	 */
	public Fijo(IMedico m) {
		super(m);
	}
	
	/**
	 * Calculo del honorario acumulado con la especialidad y posgrado
	 */

	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.1;
	}

}
