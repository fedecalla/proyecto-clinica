package modelo.ambulancia;

public class TrasladandoPaciente implements EstadoAmbulancia {

	public void solicitaAtencionaDomicilio(Ambulancia a) throws SolicitudNoAtendidaException{
		return;
	}

	public void solicitaTrasladoaClinica(Ambulancia a) throws SolicitudNoAtendidaException{
		throw new SolicitudNoAtendidaException("Ambulancia haciendo traslado");
	}

	public void retornoAutomatico(Ambulancia a) throws SolicitudNoAtendidaException{
		a.setEstado(new Disponible());
	}

	public void mantenimiento(Ambulancia as) throws SolicitudNoAtendidaException{
		throw new SolicitudNoAtendidaException("Ambulancia haciendo traslado");
	}

}
