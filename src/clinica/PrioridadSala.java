package clinica;
import pacientes.Paciente;
public interface PrioridadSala {
	/* Modela el comportamiento de las clases tipo PrioridadSala (double dispatch)
	 *  M
	 */
	ResultadoSala disputar(Paciente nuevo, Paciente actual);
	ResultadoSala contraNiño(Paciente nuevo, Paciente actual);
	ResultadoSala contraJoven(Paciente nuevo, Paciente actual);
	ResultadoSala contraMayor(Paciente nuevo, Paciente actual);
}
