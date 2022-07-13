package excepciones;

public class FaltaArrobaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FaltaArrobaException()
	{
		
	}

	@Override
	public String getMessage() {
		
		return "ERROR, el Email no contiene arroba (@).";
	}
	
	
}
