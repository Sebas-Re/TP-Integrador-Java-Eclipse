package entidad;

public class Usuario
{
	private int codUsuario;
	private int tipoUsuario;
	private String nombreUsuario;

	private Email eMailUsuario;
	private String contraseñaUsuario;
	
	
	public Usuario() {
		this.eMailUsuario = new Email();
		this.nombreUsuario = null;
	}
	
	
	public Usuario(int tipoUsuario, String eMailUsuario, String contraseñaUsuario) {
	//Codigo usuario no hace falta agregarlo puesto que es incremental y no va en la consulta de insert
		this.tipoUsuario = tipoUsuario;
		this.eMailUsuario.setEmail(eMailUsuario);
		this.contraseñaUsuario = contraseñaUsuario;
	}
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String geteMailUsuario() {
		return eMailUsuario.getEmail();
	}
	public void seteMailUsuario(String eMailUsuario) {
		
		if(Email.validarMail(eMailUsuario))
		{
			this.eMailUsuario.setEmail(eMailUsuario);
		}
		
	}
	public String getContraseñaUsuario() {
		return contraseñaUsuario;
	}
	public void setContraseñaUsuario(String contraseñaUsuario) {
		this.contraseñaUsuario = contraseñaUsuario;
		
		
	}
	@Override
	public String toString() {
		return "Usuario [codigo=" + codUsuario + ", tipoo=" + tipoUsuario + ", eMail=" + eMailUsuario
				+ "]";
	}
	
	
}
