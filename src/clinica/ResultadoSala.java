package clinica;
import individuos.Paciente;
public class ResultadoSala {
	/* Muesta el resultado de la BATALLA de la sala de espera
	 * 
	 */
	public final Paciente ganador;
	public final Paciente perdedor;
	public ResultadoSala(Paciente ganador, Paciente perdedor) {
		this.ganador=ganador;
		this.perdedor=perdedor;
	}
}
