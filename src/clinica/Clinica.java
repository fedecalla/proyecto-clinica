package clinica;

import individuos.Medico;
import individuos.Paciente;
import facturacion.Factura;
import hospedaje.Habitacion;
import java.util.ArrayList;


public class Clinica {

	private static Clinica singleton = null;
	private ArrayList <Medico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList <Factura> facturas;
	private ArrayList <Habitacion> habitaciones;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	
	private Clinica() {
		this.medicos = new ArrayList<>();
		this.pacientes = new ArrayList<>();
		this.facturas = new ArrayList<>();
		this.habitaciones = new ArrayList<>();
		this.nombre = null;
		this.direccion = null;
		this.ciudad = null;
		this.telefono = null;
		//FALTARIA INICIALIZAR SALA DE ESPERA Y PATIO PERO DEPENDE DE COMO SE IMPLEMENTEN.
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
	
}
