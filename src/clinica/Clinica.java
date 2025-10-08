package clinica;

import medicos.Medico;

import pacientes.Paciente;
import facturacion.Factura;
import hospedaje.Habitacion;
import java.util.ArrayList;
import java.util.LinkedList;

import excepciones.HabitacionCompletaException;
import individuos.Persona;




public class Clinica {

	private static Clinica singleton = null;
	private ArrayList <Medico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList <Factura> facturas;
	private ArrayList <Habitacion> habitaciones;
	private SalaEspera salaEspera;
	//private Queue<Paciente> listaEspera;
	private ArrayList<Paciente> patio;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	private LinkedList <Paciente> colaDeEspera;
	
	private Clinica(String nombre, String direccion, String ciudad, String telefono) {
		this.medicos = new ArrayList<>();
		this.pacientes = new ArrayList<>();
		this.facturas = new ArrayList<>();
		this.habitaciones = new ArrayList<>();
		this.salaEspera  = new SalaEspera();
		//this.listaEspera = new ArrayDeque<>();
		this.patio = new ArrayList<>();
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.colaDeEspera = new LinkedList<>();
		//FALTARIA INICIALIZAR SALA DE ESPERA Y PATIO PERO DEPENDE DE COMO SE IMPLEMENTEN.
	}
	
	private Habitacion getHabitacion()
	{
		Habitacion habitacion; int i = 0;
		while(this.habitaciones.get(i)!=null && this.habitaciones.get(i).EstaLlena())
			i++;
		habitacion = this.habitaciones.get(i);
		return habitacion;
	}
	public static Clinica getClinica(String nombre, String direccion, String ciudad, String telefono ) {
		if(Clinica.singleton == null)
			Clinica.singleton = new Clinica(nombre,direccion, ciudad, telefono);
		return Clinica.singleton;
	}
	
	public void agregaMedico(Medico m) {
		this.medicos.add(m);
	}
	
	public void eliminaMedico(Medico medico) {
		this.medicos.remove(medico);
	}
	
	public void agregaFactura(Factura fac) {
		this.facturas.add(fac);
	}
	
	public void eliminaFactura(Factura fac) {
		this.facturas.remove(fac);
	}
	
	public void agregaHabitacion(Habitacion h) {
		this.habitaciones.add(h);
	}
	
	public void eliminaHabitacion(Habitacion h) {
		this.habitaciones.remove(h);
	}
	
	public void setNombre(String nombre) {
		if(nombre.matches("^[a-zA-Z]*$"))
			this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setDireccion(String direccion) {
		if(direccion != "" && direccion != null)
			this.direccion = direccion;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public void setTelefono(String tel) {
		if(tel != "" && tel.length() >= 7 && tel.length() <= 12 && tel.matches("\\d+"))
			this.telefono = tel;			
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public void setCiudad(String c) {
		if(c.matches("^[a-zA-Z]*$"))
			this.ciudad = c;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}


	private void setFechaSalida(Paciente paciente)
	{
		Factura factura = this.getFactura(paciente);
		factura.setFechaSalida();
	}
	
	
	
	
	
	//METODOS QUE INTERACTUAN EN EL Sistema	--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String s;
		s = ("Clinica: "+ nombre +" - Direccion: "+this.direccion + " - Telefono: "+ this.telefono+" - Ciudad: "+ this.ciudad +"\n"+"MEDICOS: \n" );
				for (int i=0;i<this.medicos.size(); i++) {
					s+=(this.medicos.get(i).toString());
					s+="\n";
				}
			
		return s ;
	}

	public void agregaPacienteACola(Paciente p) //podria tirar excepcion de cola llena
	{
		this.colaDeEspera.add(p);
	}
	public void agregaPaciente(Paciente paciente) {
		
		this.pacientes.add(paciente);
	}

	public Factura getFactura(Paciente paciente) { // precondicion:exista ya la factura
		Factura factura; int i=0;
		while(this.facturas.get(i).getNombrePaciente()!=paciente.getNombreyapellido())
			i++;
		factura = this.facturas.get(i);
		return factura;
	}
	
	public Factura getFactura(int numero) {
		Factura fac;
		int i = 0;
		while(this.facturas.get(i).getNum() != numero)
			i++;
		if(numero < this.facturas.size())
			fac = this.facturas.get(i);
		else
			fac = null;
		return fac;
			
	}
	
	
	public void InternaPaciente(Persona paciente) // podria tirar excepciones de tipo no hay habitaciones disponibles
	{
		Habitacion SalaDeInternacion = this.getHabitacion();
		try {
			if(SalaDeInternacion != null)
				SalaDeInternacion.setPersona(paciente);
		}
		catch(HabitacionCompletaException e) {
			System.out.println(e.getMessage());
		}
		//aca habria un else que tire una excepcion
	}
	
	
	public void eliminarPaciente(Paciente paciente)
	{
		this.setFechaSalida(paciente);
		int i=0;
		while(this.pacientes.get(i) != paciente)
			i++;
		if(this.pacientes.get(i) != null)
			this.pacientes.remove(i);
	}
	
	
	public void desvincularPacienteHabitacion(Persona paciente)
	{
		int i =0; int j;
		while(this.habitaciones.get(i) != null)
		{
			j =0; Habitacion sala = this.habitaciones.get(i);
			while(sala.getPersonas().get(j)!=paciente && sala.getPersonas().get(j)!=null )
				j++;
			if(sala.getPersonas().get(j)!=null)
			{
				sala.getPersonas().remove(j);
				break;
			}
		}
	}	
		
	public void ingresaPaciente(Paciente p) {
		if (!salaEspera.estaOcupada())
			salaEspera.ingresar(p);
		else {
			Paciente perdedor = salaEspera.ingresar(p);
			if (perdedor!=null)
				patio.add(perdedor);
		}
		colaDeEspera.add(p);
	}
	
	
	public Paciente atiendePaciente() {
		Paciente proximo =  colaDeEspera.getFirst();
		colaDeEspera.removeFirst();
		return proximo;
		/*Falta ver como manejar los pacientes en atencion
		 * 
		 */
	}
	/* egresaPaciente() deberia llamar a la factura para mostrar	
	 * 
	 */
}
