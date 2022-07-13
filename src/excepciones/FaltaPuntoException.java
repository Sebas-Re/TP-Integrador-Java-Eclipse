package excepciones;

public class FaltaPuntoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public FaltaPuntoException()
	{
		
	}

	@Override
	public String getMessage() {

		return "ERROR, el Email no contiene punto (.)";
	}
	
	
	
}