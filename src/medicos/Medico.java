package medicos;
import individuos.Persona;
import java.util.ArrayList;
import java.lang.String;
import java.time.LocalDate;
	
public abstract class Medico extends Persona implements IMedico{
	protected String matricula, especialidad;
	protected ArrayList<consultasMedicas> consultas;
	
	protected static double honorarioBasico = 20000;
	
	public Medico(String nom, String dni, String ciudad, String dom, String tel, String mat) {
		super(dni, nom, tel, dom, ciudad);
		this.matricula=mat;
		this.consultas = new ArrayList<consultasMedicas>();	
		}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public String getMatricula() {
		return matricula;
	}
	
	public abstract double getHonorario();

	@Override
	public String toString() {
		return super.toString()+" - (Medico) matricula: " + matricula + " - especialidad: " + especialidad + "";
	}
	/**
	 * 
	 * @param fecha
	 * @param paciente
	 * Funcionalidad que agrega consultas medicas realizadas por el medico. Se agrega una cada vez que se genera una factura de egreso de Paciente.
	 */
	
	/*public void agregaConsulta(LocalDate fecha, Paciente paciente) {
		consultasMedicas c = new consultasMedicas(fecha,paciente);
		this.consultas.add(c);
	}*/
	

	
	/**
	 * Funcion que permite generar un reporte de consultas medicas realizadas por un medico, entre 2 fechas especificadas.
	 * <b>Pre:</br><br>
	 * fecha desde menor que fecha hasta<br>
	 *
	 */
	public String getReporte(LocalDate desde, LocalDate hasta) {
		//formato de las fechas: AAAA-MM-DD
		String rep = "";
		int cont;
		int i=0;
		while (i<this.consultas.size() && this.consultas.get(i).getFecha().isBefore(desde))
			i++;
		if (!(this.consultas.get(i).getFecha().isBefore(desde)) && !(this.consultas.get(i).getFecha().isAfter(hasta))) {
			cont=0;
			while (this.consultas.get(i).getFecha().isBefore(hasta)) {
				cont++;
				rep += (cont + this.consultas.get(i).toString());
			}
		}
		else {
			rep = ("Matricula: " + this.matricula + " - No ha realizado ninguna consulta en ese periodo");
		}
		return rep;
	}
	

}
