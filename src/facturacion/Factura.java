package facturacion;
import medicos.IMedico;
import medicos.Medico;
import java.util.ArrayList;

import hospedaje.Habitacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Clase factura
 * numero auto-incremental
 * informa toda actividad del paciente dentro de la clinica y sus costos
 */

public class Factura {
	
	private static int contador=0;
	private ArrayList<IMedico> medicos;
	private Medico aux;
	private LocalDate ingreso, egreso;
	private int nro, i;
	private double total;
	private Habitacion habitacion;
	private long cant_dias;
	private String texto, nombrePaciente;
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	/**
	 *Constructor de la clase factura
	 *<b>Pre:</b><br>
	 *nombre != null <br>
	 */
	
	public Factura (String nom) {
		contador++;
		this.nro=contador;
		medicos=new ArrayList<>();
		this.ingreso=LocalDate.now();
		this.ingreso.format(fmt);
		this.habitacion=null;
		this.total=0;
		this.nombrePaciente=nom;
		
	}
	
	//******************************GETTERS Y SETTERS************************************
	
	public int getNum() {
		return this.nro;
	}
	
	public ArrayList<IMedico> getMedicos() {
		return medicos;
	}

	public void setMedicos(IMedico m) {
		medicos.add(m);
	}



	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	/**
	 *Comienza el proceso de egreso del paciente de la clinica 
	 *
	 */

	public void Facturacion () {
		egreso.format(fmt);
		egreso=LocalDate.now();
		cant_dias=ChronoUnit.DAYS.between(ingreso, egreso);
		this.toString();
	}

	@Override
	public String toString() {
		     texto= "Factura Nº:" + nro + "\n" +
		    	   "Nombre del paciente:" + this.nombrePaciente + "\n" +
		    	   "Fecha de ingreso:" + ingreso + "\n" +
		           "Fecha de egreso:" + egreso + "\n" +
		           "Cantidad de dias:" + cant_dias + "\n";
		    
		    		if(habitacion != null) {
		    			texto += "Habitacion tipo:" + this.habitacion.getTipo() + "\t Costo: "+ this.habitacion.getCosto(cant_dias)+ "\n" +"\n" ;
		    		}
		    		
		    		texto+="Consultas medicas:"+ "\n" +"\n";
		    		
		    		for(i=0;i<medicos.size();i++) {
		    			IMedico aux = medicos.get(i);
		    			total+=medicos.get(i).getHonorario()*1.2;
		    			texto+="Nombre medico :" + aux.getNombreyapellido() + "\t	Especialidad: "+ aux.getEspecialidad()+ "\t	Subtotal: "+(aux.getHonorario()*1.2)+ "\n";
		    		}
		    		texto+="\n";
		    		
		    		texto += String.format("\t \t \t \t \t \t \t \t Total: %.2f\n", this.total);
		    		
		    return texto;
		}
	
	public String getNombrePaciente()
	{
		return this.nombrePaciente;
	}
	
	public void setFechaSalida()
	{
		this.egreso = LocalDate.now();
	}
}

