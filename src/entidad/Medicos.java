package entidad;

public class Medicos extends Usuario
{
	private String CodUsuarioMedico;
	private String dni_m;
	private String nombre_m;
	private String apellido_m;
	private String sexo_m;
	private String fechaNac_m;
	private String Nacionalidad;
	private String provincia_m;
	private String localidad_m;
	private String direccion_m;
	private Email correoElectronico_m;
	private String telefono_m;
	private String especialidad;
	private String dia;
	private String horarioAtencion;
	private String codigoDeTurno;
	private int CodHorarioMedico;
	private String TurnosActivos;
	private int Estado;

	
	public Medicos() {
		correoElectronico_m = new Email();
	}
	

	
	public Medicos(String dni_m, String nombre_m, String apellido_m, String sexo_m, String fechaNac_m, String Nacionalidad,
			String direccion_m, String localidad_m, String provincia_m, String correoElectronico_m, String telefono_m,
			String especialidad, String dia, String horarioAtencion, String TurnosActivos,String cod, String CodUsuMed, int CodHorMed, int Estado) {
		super();
		this.dni_m = dni_m;
		this.nombre_m = nombre_m;
		this.apellido_m = apellido_m;
		this.sexo_m = sexo_m;
		this.fechaNac_m = fechaNac_m;
		this.Nacionalidad = Nacionalidad;
		this.direccion_m = direccion_m;
		this.localidad_m = localidad_m;
		this.provincia_m = provincia_m;
		this.correoElectronico_m.setEmail(correoElectronico_m);
		this.telefono_m = telefono_m;
		this.especialidad = especialidad;
		this.dia = dia;
		this.horarioAtencion = horarioAtencion;
		this.codigoDeTurno = cod;
		this.CodUsuarioMedico = CodUsuMed;
		this.TurnosActivos = TurnosActivos;
		this.CodHorarioMedico = CodHorMed;
		this.setEstado(Estado);
	}
	
	
	
	public String getDni_m() {
		return dni_m;
	}
	public void setDni_m(String dni_m) {
		this.dni_m = dni_m;
	}
	public String getNombre_m() {
		return nombre_m;
	}
	public void setNombre_m(String nombre_m) {
		this.nombre_m = nombre_m;
	}
	public String getApellido_m() {
		return apellido_m;
	}
	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}
	public String getSexo_m() {
		return sexo_m;
	}
	public void setSexo_m(String sexo_m) {
		this.sexo_m = sexo_m;
	}
	public String getFechaNac_m() {
		return fechaNac_m;
	}
	public void setFechaNac_m(String fechaNac_m) {
		this.fechaNac_m = fechaNac_m;
	}
	public String getDireccion_m() {
		return direccion_m;
	}
	public void setDireccion_m(String direccion_m) {
		this.direccion_m = direccion_m;
	}
	public String getLocalidad_m() {
		return localidad_m;
	}
	public void setLocalidad_m(String localidad_m) {
		this.localidad_m = localidad_m;
	}
	public String getProvincia_m() {
		return provincia_m;
	}
	public void setProvincia_m(String provincia_m) {
		this.provincia_m = provincia_m;
	}
	public String getCorreoElectronico_m() {
		return correoElectronico_m.getEmail();
	}
	public void setCorreoElectronico_m(String correoElectronico) {
		if(Email.validarMail(correoElectronico))
		{
			this.correoElectronico_m.setEmail(correoElectronico);
		}
		
		
	}
	public String getTelefono_m() {
		return telefono_m;
	}
	public void setTelefono_m(String telefono_m) {
		this.telefono_m = telefono_m;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHorarioAtencion() {
		return horarioAtencion;
	}
	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}



	public int getEstado() {
		return Estado;
	}



	public void setEstado(int estado) {
		Estado = estado;
	}



	public String getNacionalidad() {
		return Nacionalidad;
	}



	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}



	public String getTurnosActivos() {
		return TurnosActivos;
	}



	public void setTurnosActivos(String turnosActivos) {
		TurnosActivos = turnosActivos;
	}



	public String getCodigoDeTurno() {
		return codigoDeTurno;
	}



	public void setCodigoDeTurno(String codigoDeTurno) {
		this.codigoDeTurno = codigoDeTurno;
	}



	public String getCodUsuarioMedico() {
		return CodUsuarioMedico;
	}



	public void setCodUsuarioMedico(String codUsuarioMedico) {
		CodUsuarioMedico = codUsuarioMedico;
	}



	public int getCodHorarioMedico() {
		return CodHorarioMedico;
	}



	public void setCodHorarioMedico(int codHorarioMedico) {
		CodHorarioMedico = codHorarioMedico;
	}

	
	
	
	
}
