package medicos;


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
	 *
	 */
	
	public IMedico crearMedico(String tipo, String posgrado, String contrato, String nom, String dni,  String mat, String ciudad, String dom, String tel) {
        IMedico base;
        switch (tipo.toLowerCase()) {
            case "cirujano": base = new Cirujano(nom, dni, mat, ciudad, dom, tel); break;
            case "clinico": base = new Clinico(nom, dni, mat, ciudad, dom, tel); break;
            case "pediatra": base = new Pediatra(nom, dni, mat, ciudad, dom, tel); break;
            default: throw new IllegalArgumentException("Especialidad desconocida");
        }

        if (posgrado.equalsIgnoreCase("doctor"))
        	base = new Doctorado(base);
        else if (posgrado.equalsIgnoreCase("magister")) 
        	base = new Magister(base);
        else if (posgrado!=null)
        	throw new IllegalArgumentException("Posgrado desconocido");

        if (contrato.equalsIgnoreCase("fijo")) 
        	base = new Fijo(base);
        else if (contrato.equalsIgnoreCase("temporal")) 
        	base = new Temporal(base);
        else if (contrato!=null)
        	throw new IllegalArgumentException("Contrato desconocido");

        return base;
    }
}

