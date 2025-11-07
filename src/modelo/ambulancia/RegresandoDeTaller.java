package Modelo.ambulancia;

public class RegresandoDeTaller implements EstadoAmbulancia{

	public void solicitaAtencionaDomicilio(Ambulancia a) throws SolicitudNoAtendidaException{
				
	}

	@Override
	public void solicitaTrasladoaClinica(Ambulancia a) throws SolicitudNoAtendidaException{
		// TODO Auto-generated method stub
		throw new SolicitudNoAtendidaException("Ambulancia regresando del taller");
	}

	@Override
	public void retornoAutomatico(Ambulancia a) throws SolicitudNoAtendidaException{
		a.setEstado(new Disponible());
	}

	@Override
	public void mantenimiento(Ambulancia a) throws SolicitudNoAtendidaException{
		throw new SolicitudNoAtendidaException("Ambulancia regresando del taller");
	}
	
}
