package clinica;

import medicos.Medico;
import pacientes.Paciente;
import facturacion.Factura;
import hospedaje.Habitacion;
import java.util.ArrayList;
import java.util.LinkedList;
import individuos.Persona;


public class Clinica {

	private static Clinica singleton = null;
	private ArrayList <Medico> medicos;
	private ArrayList <Persona> pacientes;
	private ArrayList <Factura> facturas;
	private ArrayList <Habitacion> habitaciones;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	private LinkedList <Persona> colaDeEspera;
	
	private Clinica() {
		this.medicos = new ArrayList<>();
		this.pacientes = new ArrayList<>();
		this.facturas = new ArrayList<>();
		this.habitaciones = new ArrayList<>();
		this.nombre = null;
		this.direccion = null;
		this.ciudad = null;
		this.telefono = null;
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
	public Clinica getClinica() {
		if(Clinica.singleton == null)
			Clinica.singleton = new Clinica();
		return Clinica.singleton;
	}
	
	public void agregaMedico(Medico medico) {
		this.medicos.add(medico);
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
	private void setFechaSalida(Persona paciente)
	{
		Factura factura = this.getFactura(paciente);
		factura.setFechaSalida();
	}
	
	
	
	//METODOS QUE INTERACTUAN EN EL Sistema	--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void agregaPacienteACola(Persona p) //podria tirar excepcion de cola llena
	{
		this.colaDeEspera.add(p);
	}
	public void agregaPaciente(Persona paciente) {
		
		this.pacientes.add(paciente);
	}

	public Factura getFactura(Persona paciente) { // precondicion:exista ya la factura
		Factura factura; int i=0;
		while(this.facturas.get(i).getNombrePaciente()!=paciente.getNombreyapellido())
			i++;
		factura = this.facturas.get(i);
		return factura;
	}
	public void InternaPaciente(Persona paciente) // podria tirar excepciones de tipo no hay habitaciones disponibles
	{
		Habitacion SalaDeInternacion = this.getHabitacion();
		if(SalaDeInternacion != null)
			SalaDeInternacion.setPersona(paciente);
		else
			//tira excepcion porque no encontro habitacion disponible
	}
	public void eliminarPaciente(Persona paciente)
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
		int i =0;
		while(this.habitaciones.get(i) != null)
		{
			int j =0; Habitacion sala = this.habitaciones.get(i);
			while(sala.getPersonas().get(j)!=paciente && sala.getPersonas().get(j)!=null )
				j++;
			if(sala.getPersonas().get(j)!=null)
			{
				sala.getPersonas().remove(j);
				break;
			}
		}
		
	}
	
}
