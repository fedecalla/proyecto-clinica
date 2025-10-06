package clinica;

import pacientes.Paciente;

public class PrioridadNiño implements PrioridadSala{
	/*Desarrolla el double dispatch del niño
	 * 
	 */
	@Override
	public ResultadoSala disputar(Paciente nuevo, Paciente actual) {
		switch (actual.getRango().toLowerCase()) {
		case "niño": return contraNiño(nuevo,actual);
		case "joven" : return contraJoven(nuevo,actual);
		case "mayor" : return contraMayor(nuevo,actual);
		}
		return null;
	}

	@Override
	public ResultadoSala contraNiño(Paciente nuevo, Paciente actual) {
		// TODO Auto-generated method stub
		return new ResultadoSala(actual,nuevo);
	}

	@Override
	public ResultadoSala contraJoven(Paciente nuevo, Paciente actual) {
		// TODO Auto-generated method stub
		return new ResultadoSala(nuevo,actual);
	}

	@Override
	public ResultadoSala contraMayor(Paciente nuevo, Paciente actual) {
		// TODO Auto-generated method stub
		return new ResultadoSala(actual,nuevo);
	}
	

}
