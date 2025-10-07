package medicos;

public abstract class DecoratorMedico implements IMedico{
	
	protected IMedico encapsulado;
	
	public DecoratorMedico(IMedico m) {
		this.encapsulado=m;
	}

/*	public String getNombre() {
		return this.encapsulado.getNombre();
	}

	public String getDni() {
		return this.encapsulado.getDni();
	}

*/	public String getMatricula() {
		return this.encapsulado.getMatricula();
	}

/*	public String getCiudad() {
		return this.encapsulado.getCiudad();
	}

	public String getDomicilio() {
		return this.encapsulado.getDomicilio();
	}

	public String getTelefono() {
		return this.encapsulado.getTelefono();
	}
	
*/	public abstract double getHonorario();

}
