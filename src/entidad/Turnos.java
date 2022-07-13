package entidad;

public class Turnos {
	
	private int Cod_Turno;
	private int Cod_Horario_Turno;
	private Pacientes DatosPaciente;
	private Medicos DatosMedico;
	//private String DNI_Paciente_Turno;
	//private String DNI_Medico_Turno;
	private String Dia_Turno;
	private String Fecha_Turno;
	private String Inicio_Turno;
	private String Fin_Turno;
	private String Especialidad_Turno;
	private String Estado_Turno;	
	
	public Turnos() {
		this.DatosPaciente = new Pacientes();
		this.DatosMedico = new Medicos();
	}
	
	public String getInicio_Turno() {
		return Inicio_Turno;
	}
	public void setInicio_Turno(String inicio_Turno) {
		Inicio_Turno = inicio_Turno;
	}
	public String getFin_Turno() {
		return Fin_Turno;
	}
	public void setFin_Turno(String fin_Turno) {
		Fin_Turno = fin_Turno;
	}
	public String getFecha_Turno() {
		return Fecha_Turno;
	}
	public void setFecha_Turno(String fecha_Turno) {
		Fecha_Turno = fecha_Turno;
	}
	public String getEstado_Turno() {
		return Estado_Turno;
	}
	public void setEstado_Turno(String estado_Turno) {
		Estado_Turno = estado_Turno;
	}
	public String getEspecialidad_Turno() {
		return Especialidad_Turno;
	}
	public void setEspecialidad_Turno(String especialidad_Turno) {
		Especialidad_Turno = especialidad_Turno;
	}
	public String getDNI_Paciente_Turno() {
		return DatosPaciente.getDNI();
	}
	public void setDNI_Paciente_Turno(String dNI_Paciente_Turno) {
		DatosPaciente.setDNI(dNI_Paciente_Turno);
	}
	public String getDNI_Medico_Turno() {
		return DatosMedico.getDni_m();
	}
	public void setDNI_Medico_Turno(String dNI_Medico_Turno) {
		DatosMedico.setDni_m(dNI_Medico_Turno);
	}
	public String getDia_Turno() {
		return Dia_Turno;
	}
	public void setDia_Turno(String dia_Turno) {
		Dia_Turno = dia_Turno;
	}
	public int getCod_Turno() {
		return Cod_Turno;
	}
	public void setCod_Turno(int cod_Turno) {
		Cod_Turno = cod_Turno;
	}
	public int getCod_Horario_Turno() {
		return Cod_Horario_Turno;
	}
	public void setCod_Horario_Turno(int cod_Horario_Turno) {
		Cod_Horario_Turno = cod_Horario_Turno;
	}
	
}
