package Modelo.ambulancia;

public class EnTaller implements EstadoAmbulancia{

	@Override
	public void solicitaAtencionaDomicilio(Ambulancia a) throws SolicitudNoAtendidaException{
		
	}

	@Override
	public void solicitaTrasladoaClinica(Ambulancia a) throws SolicitudNoAtendidaException{
		// TODO Auto-generated method stub
		throw new SolicitudNoAtendidaException("Ambulancia en taller");
	}

	@Override
	public void retornoAutomatico(Ambulancia a) throws SolicitudNoAtendidaException{
		
	}

	@Override
	public void mantenimiento(Ambulancia a) throws SolicitudNoAtendidaException{
		a.setEstado(new RegresandoDeTaller());
	}

}
