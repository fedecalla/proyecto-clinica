package modelo.individuos;

public class Persona {
	protected String dni;
	protected String nombreyapellido;
	protected String telefono;
	protected String domicilio;
	protected String ciudad;
	
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
		return "(Persona) Dni: " + dni + ", nombreyapellido: " + nombreyapellido;
	}
	
	
	
	
}
