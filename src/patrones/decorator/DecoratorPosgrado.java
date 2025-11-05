package medicos;
/**
 * Clase decoradora de Medico
 */
public abstract class DecoratorPosgrado extends DecoratorMedico{

	/**
	 * Constructor de clase
	 */
	public DecoratorPosgrado(IMedico m) {
		super(m);
	}

}
