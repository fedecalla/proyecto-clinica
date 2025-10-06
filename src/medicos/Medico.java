package medicos;

public abstract class Medico implements IMedico{
	protected String nombre, dni, matricula, ciudad, domicilio, telefono, especialidad;
	
	protected static double honorarioBasico = 20000;
	
	public Medico(String nom, String dni, String mat, String ciudad, String dom, String tel ) {
		this.nombre=nom;
		this.dni=dni;
		this.matricula=mat;
		this.ciudad=ciudad;
		this.domicilio=dom;
		this.telefono=tel;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public abstract double getHonorario();

}
