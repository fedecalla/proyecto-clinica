package Sistema;
import clinica.Clinica;
import facturacion.Factura;
import medicos.consultasMedicas;
import medicos.*;
import pacientes.Paciente;
import excepciones.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class SistemaClinica implements iSistema {
	public Clinica clinica=null;
	
	public SistemaClinica(Clinica c) {
		this.clinica = Clinica.getClinica(c.getNombre(),c.getDireccion(),c.getTelefono(),c.getTelefono());
	}

	
	public void registraMedico(IMedico m) {
		clinica.agregaMedico(m);
	}

	/**
	 * @param Paciente
	 * Metodo de la clinica que agrega al paciente en la cola de Espera, y disputa la sala de espera privada
	 */
	public void registraPaciente(Paciente p) {
		clinica.registraPaciente(p);
	}

	/**
	 * Metodo que toma al primer paciente de la cola de espera, y lo atiende, generando su factura y su consulta.
	 */
	public void ingresaPaciente() {
		Paciente proximo;
		Factura factura;
		try 
		{
			proximo = clinica.atiendePaciente();
			factura = new Factura(proximo.getNombre());
			clinica.agregaFactura(factura);
			clinica.agregaPaciente(proximo);
			clinica.CreaConsulta(proximo);
		}
		catch(NoHayPacientesEnEsperaException e)
		{
			System.out.println(e);
		}
	}

	public void medicoAtiendePaciente(IMedico m, Paciente p) throws MedicoNoExisteException 
	/*
	 * <b>pre: </b><b>
	 * medico m != null <br
	 * paciente != null <br>
	 * 
	 */
	{
		if (!this.clinica.MedicoInClinica(m))
		{
			throw new MedicoNoExisteException(m.getMatricula());
		}
		
		consultasMedicas consulta = clinica.GetConsultaByPaciente(p);
		consulta.getMedicos().add(m);
		m.getConsultas().add(consulta);
		Factura factura = clinica.getFactura(p);
		factura.setMedicos(m);
		
	}
	
	/**
	 * <b>pre: </b><br>
	 * paciente p != null<br>
	 * @return factura a pagar del paciente
	 */
	public Factura egresaPaciente(Paciente p) {
		clinica.eliminarPaciente(p);
		clinica.desvincularPacienteHabitacion(p);
		return clinica.getFactura(p); 
	}

	
	/**
	 * 
	 * @param m medico a saber la actividad
	 * @param desde fecha de inicio de la actividad
	 * @param hasta fecha de fin de la actividad
	 * <b>pre: </b><br>
	 * m != null<br>
	 * hasta mayor que desde<br>
	 */
	public String ActividadMedico(IMedico m, LocalDate desde, LocalDate hasta)throws MedicoNoExisteException
	{
		String reporte="";
		//ArrayList<consultasMedicas> actividad = new ArrayList <>();
		if (!this.clinica.MedicoInClinica(m)){
			throw new MedicoNoExisteException(m.getMatricula());
		}
			reporte=m.getReporte(desde,hasta);
		return reporte;
	}
	/**
	 * <b>pre: </b><br>
	 * p != null<br>
	 * Thabitacion != null<br>
	 * Thabitacion no vacio <br>
	 * 
	 */
	public void internaPaciente(Paciente p, String Thabitacion) {
		try
		{
			clinica.InternaPaciente(p,Thabitacion);
		}
		catch(NoHayHabitacionDisponibleException e)
		{
			System.out.println(e);
		}
	}
}
