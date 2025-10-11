package medicos;
import java.time.LocalDate;


/**
 * Interfaz Medico
 */
public interface IMedico {
	double getHonorario();
	String getMatricula();
	String getReporte(LocalDate desde, LocalDate hasta);

	public String getCiudad();
	public String getDni();
	public String getNombreYapellido();
	public String getTelefono();
	public String GetDomicilio();
}




