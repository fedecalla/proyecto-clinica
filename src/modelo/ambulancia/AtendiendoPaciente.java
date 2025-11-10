package modelo.ambulancia;

public class AtendiendoPaciente implements EstadoAmbulancia{

	public void solicitaAtencionaDomicilio(Ambulancia a) throws SolicitudNoAtendidaException{

	}

	public void solicitaTrasladoaClinica(Ambulancia a) throws SolicitudNoAtendidaException{
		throw new SolicitudNoAtendidaException("Ambulancia atendiendo paciente");		
	}

	public void retornoAutomatico(Ambulancia a) throws SolicitudNoAtendidaException{
		a.setEstado(new RegresandoDeAtencion());
	}

	public void mantenimiento(Ambulancia a) throws SolicitudNoAtendidaException{
		// TODO Auto-generated method stub
		throw new SolicitudNoAtendidaException("Ambulancia atendiendo paciente");
	}

	@Override
	public String toString() {
		return "Atendiendo Paciente";
	}

}
