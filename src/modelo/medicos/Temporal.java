package Modelo.medicos;

import java.util.ArrayList;

import patrones.decorator.DecoratorContrato;

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
	
	public ArrayList<consultasMedicas> getConsultas()
	{
		return super.encapsulado.getConsultas();
	}
}
