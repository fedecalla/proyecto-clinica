package medicos;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interfaz Medico
 */
public interface IMedico{
	double getHonorario();
	String getMatricula();
	public ArrayList<consultasMedicas> getConsultas();
	String getReporte(LocalDate desde, LocalDate hasta);
	String getNombreyapellido();
	String getEspecialidad();
}




