package clinica;

import java.time.LocalDate;
import java.util.*;

import excepciones.MedicoNoExisteException;
import excepciones.NoHayHabitacionDisponibleException;
import excepciones.NoHayPacientesEnEsperaException;
import facturacion.Factura;
import hospedaje.HCompartida;
import hospedaje.HPrivada;
import hospedaje.HTerapiaIntensiva;
import hospedaje.Habitacion;
import medicos.IMedico;
import medicos.consultasMedicas;
import pacientes.Paciente;




public class Clinica {

	private static Clinica singleton = null;
	private ArrayList <IMedico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList <Factura> facturas;
	private Stack <Habitacion> privadas;
	private Stack <Habitacion> compartidas;
	private Stack <Habitacion> intensivas;
	private ArrayList <consultasMedicas> consultas;
	private SalaEspera salaEspera;
	private ArrayList<Paciente> patio;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	private LinkedList <Paciente> colaDeEspera;
	private static int cantHabitaciones=10;
	
	//constructor
	private Clinica(String nombre, String direccion, String ciudad, String telefono) {
		this.medicos = new ArrayList<IMedico>();
		this.pacientes = new ArrayList<>();
		this.facturas = new ArrayList<>();
		this.salaEspera  = new SalaEspera();
		this.patio = new ArrayList<>();
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.colaDeEspera = new LinkedList<>();
		this.consultas = new ArrayList<>();
		this.privadas = new Stack<Habitacion>();
		this.compartidas = new Stack<Habitacion>();
		this.intensivas = new Stack<Habitacion>();
		inicializarHabitaciones();
	}
	
	public static Clinica getClinica(String nombre, String direccion, String ciudad, String telefono ) {
		if(Clinica.singleton == null)
			Clinica.singleton = new Clinica(nombre,direccion, ciudad, telefono);
		return Clinica.singleton;
	}
	
	/*
	 * Metodo que crea una cantidad de habitaciones de cada tipo de la clinica, se instancia con el constructor de Clinica
	 * 
	 */
	private void inicializarHabitaciones() {
		for (int i=0; i<cantHabitaciones; i++) {
			this.compartidas.push(new HCompartida());
			this.privadas.push(new HPrivada());
			this.intensivas.push(new HTerapiaIntensiva());
		}
	}
	
	public void agregaMedico(IMedico m) {
		this.medicos.add(m);
	}
/*	
	public void agregaPrivada(Habitacion privada) {
		this.privadas.push(privada);
	}
	public void agregaCompartida(Habitacion compartida) {
		this.privadas.push(compartida);
	}
	
	public void agregaTIntensiva(Habitacion intensiva) {
		this.privadas.push(intensiva);
	}
	
	public void agregaCompartida(Habitacion h) {
		this.compartidas.push(h);
	}
	
	public void agregaIntensiva(Habitacion h) {
		this.intensivas.push(h);
	}
*/
	public void eliminaMedico(IMedico medico) {
		this.medicos.remove(medico);
	}
	
	public void agregaFactura(Factura fac) {
		this.facturas.add(fac);
	}
	
	public void eliminaFactura(Factura fac) {
		this.facturas.remove(fac);
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
	
	public SalaEspera getSaladeEspera() {
		return this.salaEspera;
	}
	
	public ArrayList <Paciente> getPacientes(){
		return this.pacientes;
	}
	
	
	
	
	// METODOS QUE INTERACTUAN EN EL SISTEMA	--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	
	/**
	 * 
	 * @param paciente
	 * Metodo que agrega al paciente al vector de pacientes de la Clinica.
	 */
	public void agregaPaciente(Paciente paciente) {
		this.pacientes.add(paciente);
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
	
	
	
	
	
	//-------

	/**
	 * 
	 * @param paciente
	 * <b>pre: </b><br>
	 * paciente != null <br>
	 * @return retorna una factura
	 */
	public Factura getFactura(Paciente paciente) { 
		Factura factura; int i=0;
		while(this.facturas.get(i).getNombrePaciente()!=paciente.getNombreyapellido())
			i++;
		factura = this.facturas.get(i);
		return factura;
	}
	/**
	 * 
	 * @param numero de factura
	 * <b>pre: </b><br>
	 * numero positivo <br>
	 * @return retorna una factura
	 */
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
	
	
	
	/*
	 * Metodo que retira a un paciente de su habitacion(dado de alta) y en caso de que previamente estuviera llena se devuelve la habitacion a la pila de habitaciones disponibles(de su tipo)
	 */
	
	/**
	 * 
	 * @param paciente
	 * si un paciente tiene una habitacion vinculada, se la desvincula y se agrega la habitacion a la pila de habitaciones correspondiente<br>
	 * <b>pre: </b><br>
	 * paciente != null<br>
	 */
	public void desvincularPacienteHabitacion(Paciente paciente)
	{
		Habitacion habitacion = paciente.getHabitacion();
		String tipo = habitacion.getTipo();
		if(habitacion.getCapacidad() == 0) {
			habitacion.setCapacidad(1);
			switch (tipo.toLowerCase())
			{
			case "compartida": this.compartidas.push(habitacion);
								break;
			case "privada": this.privadas.push(habitacion);
								break;
			case "intensiva": this.intensivas.push(habitacion);
								break;
								
			}
		}
		paciente.setHabitacion(null);
	}	
	
	/*
	 * Ingresa al paciente y lo ubica en el lugar de espera correspondiente
	 */
	
	/**
	 * disputa la sala de espera y agrega el paciente a la cola
	 * 
	 * @param paciente p a registrar
	 * <b>pre: </b> <br>
	 * paciente p != null<br>
	 * 
	 */
		
	public void registraPaciente(Paciente p) {
		if (!salaEspera.estaOcupada())
			salaEspera.ingresar(p);
		else {
			Paciente perdedor = salaEspera.ingresar(p);
			if (perdedor!=null)
				patio.add(perdedor);
		}
		colaDeEspera.add(p);
	}
	
	/**
	 * 
	 * @throws NoHayPacientesEnEsperaException
	 * @return Proximo paciente a atender segun la cola de Espera.
	 */
	public Paciente atiendePaciente() throws NoHayPacientesEnEsperaException{
		if (colaDeEspera.isEmpty())
			throw new NoHayPacientesEnEsperaException();
		Paciente proximo =  colaDeEspera.getFirst();
		colaDeEspera.removeFirst();
		
		if (proximo.equals(this.salaEspera.getActual()))
			this.salaEspera.vaciar();
		else
			this.patio.remove(proximo);
		return proximo; 
	}
	/**
	 * 
	 * @param medico "m" medico que va a atender al paciente
	 * @param paciente "p" paciente que va a ser atendido
	 * 
	 * <b>pre: </b><br>
	 * medico m!= null <br>
	 * paciente p!= null <br>
	 */
	public void atenderPaciente(IMedico m, Paciente p)
	{
		consultasMedicas consulta = this.GetConsultaByPaciente(p);
		consulta.getMedicos().add(m);
		
		Factura factura = this.getFactura(p);
		factura.setMedicos(m);
	}

	/**
	 * crea una consulta en el sistema
	 * @param paciente al que se le crea una consulta en el sistema
	 * <b>pre: </b><br>
	 * paciente != null <br>
	 * 
	 */
	public void CreaConsulta(Paciente paciente)
	{
		LocalDate fecha = LocalDate.now();
		consultasMedicas consulta  = new consultasMedicas(fecha,paciente);
			this.consultas.add(consulta);
	}
	
	/**
	 * 
	 * @param paciente paciente del que se busca la consulta
	 *  <b>pre: </b><br>
	 *  paciente != null <br>
	 *  
	 * @return la consulta que hizo el paciente
	 */
	public consultasMedicas GetConsultaByPaciente(Paciente paciente)
	{
		int i = 0;
		consultasMedicas consulta = null;
		while(i<consultasMedicas.getcantidad() && !this.consultas.get(i).getPaciente().equals(paciente))
			i++;
		if(i<consultasMedicas.getcantidad())
			consulta = this.consultas.get(i);
		
		return consulta;
		
	}
	
	
	/**
	 * 
	 * @param medico a verificar si esta en la clinica
	 *  <b>pre: </b><br>
	 *  medico!=null<br>
	 * @return un booleano que indica si el medico esta en la clinica
	 */
	
	private boolean MedicoInClinica(IMedico medico)
	{
		boolean resultado=false; int i=0;
		while(!this.medicos.get(i).equals(medico))
		{
			i++;
			if(this.medicos.get(i).equals(medico))
				resultado = true;
		}
		return resultado;
	}
		
	

	/**
	 * 
	 * @param medico al que se busca su actividad
	 * @param fecha_inicio tope inferior de la actividad del medico
	 * @param fecha_fin tope superior de la actividad del medico
	 * @return todas las consultas en las que estuvo el medico
	 *  <b>pre: </b><br>
	 *  medico != null <br>
	 *  fecha_inicio menor que fecha_fin <br>
	 *  fecha_inicio != null <br>
	 *  fecha_fin != null <br>
	 * @throws MedicoNoExisteException si el medico no existe
	 */
	public ArrayList <consultasMedicas> getConsultas(IMedico medico, LocalDate fecha_inicio, LocalDate fecha_fin) throws MedicoNoExisteException
	{
		if(MedicoInClinica(medico))
		{	
			ArrayList <consultasMedicas> result = new ArrayList <>();
			int i = 0;
			while(this.consultas.get(i).getFecha().compareTo(fecha_inicio) < 0 && i< consultasMedicas.getcantidad())
				i++;
			while(this.consultas.get(i).getFecha().compareTo(fecha_fin) <= 0 && i<consultasMedicas.getcantidad()) {
				if(this.consultas.get(i).getMedicos().contains(medico)) {
					result.add(this.consultas.get(i));
				}
			}
			
			return result;
		}
		else
			throw new MedicoNoExisteException();
	}
	
	/**
	 * 
	 * @param tipo de habitacion que busca
	 *  <b>pre: </b><br>
	 *  tipo != null;
	 * @return devuelve una habitacion no llena del tipo solicitado, null si no existe el tipo
	 */
	private Habitacion getHabitacionNollena(String tipo)
	{
		Habitacion resultado=null;
		switch(tipo)
		{
			case "compartida" :
			{
					if(this.compartidas.isEmpty())
						resultado = null;
					else
					{
						resultado = this.compartidas.pop();
						resultado.setPersona();
						if(resultado.getCapacidad() != 0)
							this.compartidas.push(resultado);
					}
					break;
			}
			case "privada" :
			{
				if(this.privadas.isEmpty())
					resultado = null;
				else {
					resultado = this.privadas.pop();
					resultado.setCapacidad(resultado.getCapacidad()-1);
					break;
				}
					
			}	
			case "terapiaIntensiva" :
			{
				if(this.intensivas.isEmpty())
					resultado = null;
				else
				{
					resultado = this.intensivas.pop();
					resultado.setCapacidad(resultado.getCapacidad()-1);
				}
				break;
			}
		}
	
		return resultado;
	}
	
	/** 
	 * Asigna una habitacion del tipo pedido a un paciente
	 * @param paciente (!=null)
	 * @param tipo de habitacion ("privada" o "compartida" o " Terapia intensiva")
	 * @throws NoHayHabitacionDisponibleException si no hay ninguna habitacion con capacidad
	 */
	
	
	public void InternaPaciente(Paciente p, String tipoHabitacion) throws NoHayHabitacionDisponibleException
	{
		
		Habitacion habitacion = getHabitacionNollena(tipoHabitacion);
		if(habitacion != null)
			p.setHabitacion(habitacion);
		else
			throw new NoHayHabitacionDisponibleException("Todas las habitaciones estan llenas");
	}
	
	 /**
	  * 
	  * @param consultas que se quieren pasar a string
	  *  <b>pre: </b><br>
	  *  consultas no vacia <br>
	  *  consultas != null <br>
	  * @return String con los datos de todas las consultas del arrayList
	  */
	public String PrintConsultas(ArrayList<consultasMedicas> consultas)
	{	
		String informe = "";
		Iterator<consultasMedicas> printeable = consultas.iterator();
		
		while(printeable.hasNext())
		{
			consultasMedicas actividad = printeable.next();
			informe += actividad;
		}
		return informe;
	}
	
	
}



