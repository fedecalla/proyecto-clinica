package excepciones;

public class NotFoundMedicoException extends Exception {
	
	private String error;
	public NotFoundMedicoException(String error)
	{
		this.error = error;
	}
	
	@Override
	public toString()
	{
		return "error: "+this.error;
	}
	
	

}
