package individuos;

public abstract class Persona {
	private String dni;
	private String nombreyapellido;
	private String telefono;
	private String domicilio;
	private String ciudad;
	
	public Persona(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad) {
		super();
		this.dni = dni;
		this.nombreyapellido = nombreyapellido;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
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
	
	
}
