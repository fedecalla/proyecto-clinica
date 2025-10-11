package medicos;
import java.time.LocalDate;


/**
 * Interfaz Medico
 */
public interface IMedico {
	double getHonorario();
	String getMatricula();
	String getReporte(LocalDate desde, LocalDate hasta);
}




