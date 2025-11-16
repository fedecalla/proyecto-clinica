package modelo.individuos;

public class Persona {
	protected String dni;
	protected String nombreyapellido;
	protected String telefono;
	protected String domicilio;
	protected String ciudad;
	
	/**
	 * Crea una nueva instancia de Persona con los datos básicos.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>dni</code> no debe ser {@code null} ni vacío.</li>
	 *   <li><code>nombreyapellido</code> no debe ser {@code null} ni vacío.</li>
	 *   <li><code>telefono</code> no debe ser {@code null} ni vacío.</li>
	 *   <li><code>domicilio</code> no debe ser {@code null} ni vacío.</li>
	 *   <li><code>ciudad</code> no debe ser {@code null} ni vacío.</li>
	 * </ul>
	 *
	 * @param dni Documento Nacional de Identidad de la persona.
	 * @param nombreyapellido Nombre completo de la persona.
	 * @param telefono Número de teléfono de contacto.
	 * @param domicilio Dirección de domicilio.
	 * @param ciudad Ciudad de residencia.
	 */
	
	public Persona(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad) {
		super();
		this.dni = dni;
		this.nombreyapellido = nombreyapellido;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
	}
	public Persona(String dni, String nombreyapellido)
	{
		this.dni = dni;
		this.nombreyapellido = nombreyapellido;
	}

	public String getDni() {
		return dni;
	}


	public String getNombreyapellido() {
		return nombreyapellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public String getCiudad() {
		return ciudad;
	}

	@Override
	public String toString() {
		return "Dni: " + dni + ", Nombre: " + nombreyapellido;
	}
	
	
	
	
}
