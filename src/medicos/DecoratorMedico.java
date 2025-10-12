package medicos;
import java.time.LocalDate;



/**
 * Primera clase decoradora de Medico, extiende y encapsula IMedico
 */
public abstract class DecoratorMedico implements IMedico{
	
	protected IMedico encapsulado;
	
	/**
	 * Constructor de la clase
	 */
	public DecoratorMedico(IMedico m) {
		this.encapsulado=m;
	}

	public String getNombreyapellido() {
		return this.encapsulado.getNombreyapellido();
	}

	public String getEspecialidad() {
		return this.encapsulado.getEspecialidad();
	}
	

	/**
	 * Get Matricula
	 */
	public String getMatricula() {
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
	
*/	
	/**
	 * Clase abstracta encargada de calcular honorarios
	 */
	
	public abstract double getHonorario();
	
	/**
	 * Reporat actividad del medico en un determinado periodo
	 *<b>Pre:</b><br>
	 *desde>0<br>
	 *hasta>desde<br>
	 */

	public String getReporte(LocalDate desde, LocalDate hasta) {
		return this.encapsulado.getReporte(desde, hasta);
	}

}
