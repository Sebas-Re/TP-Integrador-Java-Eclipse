package entidad;

import excepciones.FaltaArrobaException;
import excepciones.FaltaPuntoException;

public class Email {
	String Email;
	
	public Email()
	{
		this.Email = "Vacio";
	}
	public Email(String mail)
	{
		this.Email = mail;
	}
	
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	
	
	public static boolean validarMail (String mail) throws FaltaArrobaException, FaltaPuntoException
	{
		Boolean auxArroba = false;
		Boolean auxPunto = false;
		
		   for(int i=0; i< mail.length() ; i++)
		   {
			   if(mail.charAt(i) == '@')
				   auxArroba=true;
			   if(mail.charAt(i) == '.')
				   auxPunto=true;
		   }
		   
		   if(auxArroba == false)
		   {
			  
			   throw new FaltaArrobaException();
		   }
		   
		   if(auxPunto==false)
		   {
			   throw new FaltaPuntoException();
		   }
		  
		  
		   if(auxArroba && auxPunto)
		   {
			   return true;
		   }
		   return false;
	}
	
	
}
