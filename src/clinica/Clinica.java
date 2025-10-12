package clinica;

import medicos.Medico;

import pacientes.Paciente;
import facturacion.Factura;
import medicos.consultasMedicas;
import hospedaje.*;
import java.util.*;
import java.time.LocalDate;
import excepciones.*;




public class Clinica {

	private static Clinica singleton = null;
	private ArrayList <Medico> medicos;
	private ArrayList <Paciente> pacientes;
	private ArrayList <Factura> facturas;
	private Stack <Habitacion> privadas;
	private Stack <Habitacion> compartidas;
	private Stack <Habitacion> TerapiaIntensiva;
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
		this.medicos = new ArrayList<>();
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
		this.TerapiaIntensiva = new Stack<Habitacion>();
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
			this.TerapiaIntensiva.push(new HTerapiaIntensiva());
		}
	}
	
	public void agregaMedico(Medico m) {
		this.medicos.add(m);
	}
	
	public void agregaPrivada(Habitacion privada) {
		this.privadas.push(privada);
	}
	public void agregaCompartida(Habitacion compartida) {
		this.privadas.push(compartida);
	}
	
	public void agregaTIntensiva(Habitacion intensiva) {
		this.privadas.push(intensiva);
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
	
	//-------
	
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
	
	
	
	/*
	 * Metodo que retira a un paciente de su habitacion(dado de alta) y en caso de que previamente estuviera llena se devuelve la habitacion a la pila de habitaciones disponibles(de su tipo)
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
			case "intensiva": this.TerapiaIntensiva.push(habitacion);
								break;
								
			}
		}
		paciente.setHabitacion(null);
	}	
	
	/*
	 * Ingresa al paciente y lo ubica en el lugar de espera correspondiente
	 */
		
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
	
	/*
	 * Llama al proximo paciente a atender y libera su lugar de espera correspondiente
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
	
	public void atenderPaciente(Medico m, Paciente p)
	{
		consultasMedicas consulta = this.GetConsultaByPaciente(p);
		consulta.getMedicos().add(m);
		
		Factura factura = this.getFactura(p);
		factura.setMedicos(m);
	}

	
	public void CreaConsulta(Paciente paciente)
	{
		LocalDate fecha = LocalDate.now();
		consultasMedicas consulta  = new consultasMedicas(fecha,paciente,null);
		this.consultas.add(consulta);
	}
	
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
	
	/*
	 * Retorna si un medico esta registrado en la clinica
	 */
	
	private boolean MedicoInClinica(Medico medico)
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
		
	
	/*
	 * PRECONDICION: FECHA_INICIO < FECHA_FIN
	 */
	public ArrayList <consultasMedicas> getConsultas(Medico medico, LocalDate fecha_inicio, LocalDate fecha_fin) throws MedicoNoExisteException{
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
	
	/*
	 * Retorna una habitacion con lugar disponible para un paciente
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
				else
					resultado = this.privadas.pop();
				
				resultado.setCapacidad(resultado.getCapacidad()-1);
				break;
			}	
			case "terapiaIntensiva" :
			{
				if(this.TerapiaIntensiva.isEmpty())
					resultado = null;
				else
				{
					resultado = this.TerapiaIntensiva.pop();
					resultado.setCapacidad(resultado.getCapacidad()-1);
				}
				break;
			}
		}
	
		return resultado;
	}
	
	/* 
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



