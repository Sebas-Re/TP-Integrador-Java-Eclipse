package entidad;

public class Pacientes {
	
	private String DNI;
	private String Nombre;
	private String Apellido; 
	private String Sexo;
	private String FechaNacimiento;
	private String Nacionalidad;
	private String Provincia;
	private String Localidad;
	private String Direccion;
	private Email Correo;
	private String Telefono; 
	private String TurnosActivos;
	private int Estado;
	
	public Pacientes()
	{
		this.DNI = "";
		this.Nombre = "";
		this.Apellido = "";
		this.Sexo = "";
		// this.FechaNacimiento = 10-20-2020;
		this.Nacionalidad = "";
		this.Provincia = "";
		this.Localidad = "";
		this.Direccion = "";
		this.Correo = new Email();
		this.Telefono = "";
		this.Estado = -1;
		
	}
	public Pacientes(String dni, String nombre, String apellido, String sexo, String fecha_nacimiento,String nacionalidad,String provincia,String localidad,String direccion,String correo,String telefono, int estado)
	{
		this.DNI = dni;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Sexo = sexo;
		this.FechaNacimiento = fecha_nacimiento; 
		this.Nacionalidad = nacionalidad;
		this.Provincia = provincia;
		this.Localidad = localidad;
		this.Direccion = direccion;
		this.Correo.setEmail(correo); 
		this.Telefono = telefono;
		this.Estado = estado;
	}
	//
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	public String getFechaNacimiento() {
		return FechaNacimiento;
	}
	public void setFechaNacimiento(String string) {
		FechaNacimiento = string;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getCorreo() {
		return Correo.getEmail();
	}
	public void setCorreo(String correo) {
		if(Email.validarMail(correo)) {
			this.Correo.setEmail(correo);
		}
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	//
	@Override
	public String toString() {
		return "Pacientes [DNI=" + DNI + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Sexo=" + Sexo
				+ ", FechaNacimiento=" + FechaNacimiento + ", Nacionalidad=" + Nacionalidad + ", Provincia=" + Provincia
				+ ", Localidad=" + Localidad + ", Direccion=" + Direccion + ", Correo=" + Correo + ", Telefono="
				+ Telefono + ", Estado=" + Estado + "]";
	}
	public String getTurnosActivos() {
		return TurnosActivos;
	}
	public void setTurnosActivos(String turnosActivos) {
		TurnosActivos = turnosActivos;
	}
	
}
