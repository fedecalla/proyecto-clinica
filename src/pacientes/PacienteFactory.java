package pacientes;

import excepciones.RangoNoExisteException;

/**
 * Clase correspondiente al patron factory para la creacion de pacientes determinados por edad.
 */
public class PacienteFactory{
	
	/**
	 * 
	 * @param dni
	 * @param nombreyapellido
	 * @param telefono
	 * @param domicilio
	 * @param ciudad
	 * @param historiaclinica
	 * @param norden
	 * @param rango
	 * 
	 * <h3>Pre-condiciones:</h3> la edad debe ser un numero positivo >0<br>
	 * Retorna una instancia de alguna clase clasificada por la edad del paciente.<br>
	 */
	public static Paciente crearPaciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, String rango) throws RangoNoExisteException 
	{
        if (rango.equalsIgnoreCase("ninio"))
        {
            return new Ninio( dni,nombreyapellido,telefono,domicilio,ciudad,historiaclinica);
        } 
        else if (rango.equalsIgnoreCase("joven"))
        {
            return new Joven( dni,nombreyapellido,telefono,domicilio,ciudad,historiaclinica);
        } 
        else if (rango.equalsIgnoreCase("mayor"))
        {
            return new Mayor( dni,nombreyapellido,telefono,domicilio,ciudad, historiaclinica);
        }
        else
        	throw new RangoNoExisteException("RANGO INGRESADO NO EXISTE");
}

}
