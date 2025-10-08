package medicos;

/**
 * Clase decoradora de Medico
 */

public abstract class DecoratorContrato extends DecoratorPosgrado{

	/**
	 * Constructor de clase
	 */
	public DecoratorContrato(IMedico m) {
		super(m);
	}

}
