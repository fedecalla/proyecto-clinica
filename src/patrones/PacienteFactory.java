package patrones;

import pacientes.Joven;
import pacientes.Mayor;
import pacientes.Ninio;
import pacientes.Paciente;

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
	 * @param edad <br><br>
	 * 
	 * <h3>Pre-condiciones:</h3> la edad debe ser un numero positivo >0
	 * 
	 * Retorna una instancia de alguna clase clasificada por la edad del paciente.
	 */
	public static Paciente crearPaciente(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad, int historiaclinica, int edad) {
	        if (edad < 13)
	        {
	            return new Ninio( dni,nombreyapellido,telefono,domicilio,ciudad,historiaclinica);
	        } 
	        else if (edad < 60)
	        {
	            return new Joven( dni,nombreyapellido,telefono,domicilio,ciudad,historiaclinica);
	        } 
	        else
	        {
	            return new Mayor( dni,nombreyapellido,telefono,domicilio,ciudad, historiaclinica);
	    }
	        
	}

}
