package patrones.factory;

import modelo.excepciones.NoExisteContratoException;
import modelo.excepciones.NoExisteEspecialidadException;
import modelo.excepciones.NoExistePosgradoException;
import modelo.medicos.Cirujano;
import modelo.medicos.Clinico;
import modelo.medicos.Fijo;
import modelo.medicos.IMedico;
import modelo.medicos.Magister;
import modelo.medicos.Pediatra;
import modelo.medicos.Temporal;
import patrones.decorator.Doctorado;

/**
 * Clase Factory de medico, permite la creacion de un medico solo con su especialidad
 */
public class MedicoFactory {
	
	/**
	 *Metodo Factory
	 *<b>Pre:</b><br>
	 *nombre != null <br>
	 *dni != null <br>
	 *matricula != null <br>
	 *ciudad != null <br>
	 *domicilio != null <br>
	 *telefono != null <br>
	 *<b>Excepciones:</b><br>
	 * @exception NoExisteEspecialidadException
	 *@exception NoExistePosgradoException
	 *@exception NoExisteContratoException
	 */
	
	public static IMedico crearMedico(String tipo, String posgrado, String contrato, String nom, String dni,  String mat, String ciudad, String dom, String tel) throws NoExisteEspecialidadException, NoExistePosgradoException, NoExisteContratoException {
        IMedico base;
        switch (tipo.toLowerCase()) {
            case "cirujano": base = new Cirujano(nom, dni, mat, ciudad, dom, tel); break;
            case "clinico": base = new Clinico(nom, dni, mat, ciudad, dom, tel); break;
            case "pediatra": base = new Pediatra(nom, dni, mat, ciudad, dom, tel); break;
            default: throw new NoExisteEspecialidadException("Especialidad mal ingresada");
        }

        if (posgrado.equalsIgnoreCase("doctor"))
        	base = new Doctorado(base);
        else if (posgrado.equalsIgnoreCase("magister")) 
        	base = new Magister(base);
        else if (posgrado!=null)
        	throw new NoExistePosgradoException("Posgrado mal ingresado");

        if (contrato.equalsIgnoreCase("fijo")) 
        	base = new Fijo(base);
        else if (contrato.equalsIgnoreCase("temporal")) 
        	base = new Temporal(base);
        else if (contrato!=null)
        	throw new NoExisteContratoException("Contrato mal ingresado");

        return base;
    }
}

