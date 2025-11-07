package Modelo.ambulancia;

public class Disponible implements EstadoAmbulancia{

	
	public void solicitaAtencionaDomicilio(Ambulancia a) throws SolicitudNoAtendidaException{
		a.setEstado(new AtendiendoPaciente());
	}
	public void solicitaTrasladoaClinica(Ambulancia a) throws SolicitudNoAtendidaException{
		// TODO Auto-generated method stub
		a.setEstado(new TrasladandoPaciente());
	}

	public void retornoAutomatico(Ambulancia a) throws SolicitudNoAtendidaException{
		return;
	}

	public void mantenimiento(Ambulancia a) throws SolicitudNoAtendidaException{
		a.setEstado(new EnTaller());
	}

}
