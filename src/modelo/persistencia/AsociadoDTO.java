package modelo.persistencia;

public class AsociadoDTO {
	 private String dni;
	    private String nombreyapellido;
	    private String telefono;
	    private String domicilio;
	    private String ciudad;

	    // Constructor vacío (útil para frameworks o para ir llenándolo a mano)
	    public AsociadoDTO() {
	    }

	    // Constructor completo
	    public AsociadoDTO(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad) {
	        this.dni = dni;
	        this.nombreyapellido = nombreyapellido;
	        this.telefono = telefono;
	        this.domicilio = domicilio;
	        this.ciudad = ciudad;
	    }

	    // Getters y setters
	    public String getDni() {
	        return dni;
	    }

	    public void setDni(String dni) {
	        this.dni = dni;
	    }

	    public String getNombreyapellido() {
	        return nombreyapellido;
	    }

	    public void setNombreyapellido(String nombreyapellido) {
	        this.nombreyapellido = nombreyapellido;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getDomicilio() {
	        return domicilio;
	    }

	    public void setDomicilio(String domicilio) {
	        this.domicilio = domicilio;
	    }

	    public String getCiudad() {
	        return ciudad;
	    }

	    public void setCiudad(String ciudad) {
	        this.ciudad = ciudad;
	    }

	    @Override
	    public String toString() {
	        return "AsociadoDTO{" +
	                "dni='" + dni + '\'' +
	                ", nombreyapellido='" + nombreyapellido + '\'' +
	                ", telefono='" + telefono + '\'' +
	                ", domicilio='" + domicilio + '\'' +
	                ", ciudad='" + ciudad + '\'' +
	                '}';
	    }
}

