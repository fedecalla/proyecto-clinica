package medicos;

import java.util.ArrayList;

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
		return super.getNombreyapellido();
	}
	@Override
	public String getEspecialidad() {
		return super.getEspecialidad();
	}
	
	public ArrayList<consultasMedicas> getConsultas()
	{
		return super.encapsulado.getConsultas();
	}

}
