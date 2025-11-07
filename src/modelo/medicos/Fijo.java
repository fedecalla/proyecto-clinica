package Modelo.medicos;

import java.util.ArrayList;


import patrones.decorator.DecoratorContrato;

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
