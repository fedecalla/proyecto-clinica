package medicos;
import java.time.LocalDate;
public interface IMedico {
	double getHonorario();
	String getMatricula();
	String getReporte(LocalDate desde, LocalDate hasta);
}
