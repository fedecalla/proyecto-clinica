package facturacion;
import medicos.Medico;
import java.util.ArrayList;

import hospedaje.Habitacion;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Clase factura
 * numero auto-incremental
 * informa toda actividad del paciente dentro de la clinica y sus costos
 */

public class Factura {
	
	private static int contador=0;
	private ArrayList<Medico> medicos;
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
		ingreso.format(fmt);
		this.ingreso=LocalDate.now();
		this.habitacion=null;
		this.total=0;
		this.nombrePaciente=nom;
		
	}
	
	/**
	 *getMedico
	 */
	
	
	public ArrayList<Medico> getMedicos() {
		return medicos;
	}
	/**
	 *setMedico
	 */

	public void setMedicos(Medico m) {
		medicos.add(m);
	}


	/**
	 *setHabitacion
	 */
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	/**
	 *Comienza el proceso de egreso del paciente de la clinica 
	 */

	public void Facturacion () {
		egreso.format(fmt);
		egreso=LocalDate.now();
		cant_dias=ChronoUnit.DAYS.between(ingreso, egreso);
		this.toString();
	}

	@Override
	/**
	 *Impresion de la factura
	 */
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
		    			aux=medicos.get(i);
		    			total+=aux.getHonorario()*1.2;
		    			texto+="Nombre medico :" + aux.getNombre() + "\t	Especialidad: "+ aux.getEspecialidad()+ "\t	Subtotal: %0.2f"+ (aux.getHonorario()*1.2)+ "\n";
		    		}
		    		
		    		texto+="\n";
		    		
		    		texto+="\t \t Total: %0.2f" + this.total + "\n";
		    		
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

