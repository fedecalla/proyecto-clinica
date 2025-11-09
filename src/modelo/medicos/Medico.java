package modelo.medicos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import modelo.individuos.Persona;
	
public abstract class Medico extends Persona implements IMedico{
	protected String matricula, especialidad;
	protected ArrayList<consultasMedicas> consultas;
	
	protected static double honorarioBasico = 20000;
	
	public Medico(String nom, String dni, String ciudad, String dom, String tel, String mat) {
		super(dni, nom, tel, dom, ciudad);
		this.matricula=mat;
		this.consultas = new ArrayList<consultasMedicas>();	
		}

	
	public String getNombreyapellido() {
		return super.getNombreyapellido();
	}
	
	public String getEspecialidad() {
		return this.especialidad;
	}

	public String getMatricula() {
		return matricula;
	}
	
	public ArrayList<consultasMedicas> getConsultas()
	{
		return this.consultas;
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
	 * Funcionalidad que agrega consultas medicas realizadas por el medico. Se agrega una cada vez que se genera una factura de egreso de Paciente.<br>
	 */
	
	/*public void agregaConsulta(LocalDate fecha, Paciente paciente) {
		consultasMedicas c = new consultasMedicas(fecha,paciente);
		this.consultas.add(c);
	}*/
	

	
	/**
	 * Funcion que permite generar un reporte de consultas medicas realizadas por un medico, entre 2 fechas especificadas.<br>
	 * <b>Pre:</br><br>
	 * fecha desde menor que fecha hasta<br>
	 *
	 */
	public String getReporte(LocalDate desde, LocalDate hasta) {
		String rep = ("REPORTE DE MEDICO, MATRICULA: "+this.getMatricula()+"\n");
		int cont=0;
		int i=0;
		LocalDate actual;
		
		this.consultas.sort(Comparator.comparing(consultasMedicas::getFecha));
		
		if (this.consultas.size()>0) {
			actual = this.consultas.get(i).getFecha();
			
		while (i<this.consultas.size() && this.consultas.get(i).getFecha().compareTo(desde)<0)
			i= i+1;
		
		actual=this.consultas.get(i).getFecha();
		
			while (i<this.consultas.size() && actual.compareTo(hasta)<=0) {
				cont+=1;
				rep += (cont + "." + this.consultas.get(i).toString());
				if (cont>10)
					return rep;
				i= i+1;
			}
		}
		else 
			rep = "NO HAY CONSULTAS REALIZADAS";
		return rep;
	}

}
