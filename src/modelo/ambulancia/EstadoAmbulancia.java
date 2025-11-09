package modelo.ambulancia;

public interface EstadoAmbulancia {
	void solicitaAtencionaDomicilio(Ambulancia a) throws SolicitudNoAtendidaException;
	void solicitaTrasladoaClinica(Ambulancia a) throws SolicitudNoAtendidaException;
	void retornoAutomatico(Ambulancia a) throws SolicitudNoAtendidaException;
	void mantenimiento(Ambulancia a) throws SolicitudNoAtendidaException;
}
