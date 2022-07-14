package daolmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;

public class DaoTurnosImpl {
	
	private static final String traerMedicos = "select DNI_Medico,Nombre_Medico, Apellido_Medico, Especialidad_Medico, Estado_Medico, Dia_Semana_Horario from medico as m inner join horario as h on h.Cod_Horario = Cod_Horario_Medico";
	private static final String traerTurnosXMedico = "select  Cod_Turno,concat_ws(' ',m.Nombre_Medico,m.Apellido_Medico) as medico ,DNI_Paciente_Turno, concat_ws(' ',p.Nombre_Paciente,p.Apellido_Paciente) as paciente, p.Sexo_Paciente,\r\n" + 
			"concat_ws(', ',p.Nacionalidad_Paciente,p.Provincia_Paciente, p.Localidad_Paciente, p.Direccion_Paciente) as direccion,p.Fecha_Nacimiento_Paciente,p.Correo_Paciente,p.Telefono_Paciente,\r\n" + 
			"Dia_Turno, concat_ws(' ','de',Inicio_Turno,'a',Fin_Turno) as horario, Especialidad_Medico as especialidad,Observacion, Estado_Turno from turno\r\n" + 
			"inner join medico as m on m.DNI_Medico = DNI_Medico_Turno \r\n" + 
			"inner join paciente as p on p.DNI_Paciente = DNI_Paciente_Turno\r\n" + 
			"where DNI_Medico_Turno =?";
	private static final String traerTurnos = "select  Cod_Turno,concat_ws(' ',m.Nombre_Medico,m.Apellido_Medico) as medico ,DNI_Paciente_Turno, concat_ws(' ',p.Nombre_Paciente,p.Apellido_Paciente) as paciente, p.Sexo_Paciente,\r\n" + 
			"concat_ws(', ',p.Nacionalidad_Paciente,p.Provincia_Paciente, p.Localidad_Paciente, p.Direccion_Paciente) as direccion,p.Fecha_Nacimiento_Paciente,p.Correo_Paciente,p.Telefono_Paciente,\r\n" + 
			"Dia_Turno, concat_ws(' ','de',Inicio_Turno,'a',Fin_Turno) as horario, Especialidad_Medico as especialidad,Observacion, Estado_Turno from turno\r\n" + 
			"inner join medico as m on m.DNI_Medico = DNI_Medico_Turno \r\n" + 
			"inner join paciente as p on p.DNI_Paciente = DNI_Paciente_Turno";
	private static final String traerDNIMedico= "select DNI_Medico from medico where Cod_Usuario_Medico=?";
	private static final String modificarTurno = "UPDATE turno set Observacion = ?,Estado_Turno= ? WHERE Cod_Turno= ?";
	private static final String NombreMedicos = "select concat_ws(' ',Nombre_Medico,Apellido_Medico) as nombre from medico";
	private static final String traerDiaTurno="select Dia_Turno from turno group by Dia_Turno";
	private static final String traerEspecialidades = "select Especialidad_Medico from medico";
	private static final String codigohorario = "select Cod_Horario_Medico from medico where DNI_Medico =?";
	private static final String agregarturno="INSERT INTO `bdclinica`.`turno` (`Cod_Horario_Turno`,`DNI_Medico_Turno`,`DNI_Paciente_Turno`,`Fecha_Turno`,`Dia_Turno`,`Inicio_Turno`,`Fin_Turno`,`Estado_Turno`) VALUES(?,?,?,?,?,?,?,?)";
	Conexion Conexion = new Conexion();
	
	public ArrayList<String> listarEspecialidad() {
		ArrayList<String> ls = new ArrayList<String>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerEspecialidades);
			
			while(rs.next())
			{
				
				ls.add(rs.getString("Especialidad_Medico"));
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	public String CodigoHorario(String dni) {
		String ls = null;
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(codigohorario);
			st.setString(1, dni);
			rs = st.executeQuery();
			
			while(rs.next())
			{
				
				ls =rs.getString("Cod_Horario_Medico");
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<Medicos> ListarMedico() {
		ArrayList<Medicos> ls = new ArrayList<Medicos>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerMedicos);
		
			
			while(rs.next())
			{
				Medicos m = new Medicos();
				
				m.setDni_m(rs.getString("DNI_Medico"));
				m.setNombre_m(rs.getString("Nombre_Medico"));
				m.setApellido_m(rs.getString("Apellido_Medico"));
				m.setEspecialidad(rs.getString("Especialidad_Medico"));
				m.setDia(rs.getString("Dia_Semana_Horario"));
				m.setEstado(rs.getInt("Estado_Medico"));
				ls.add(m);
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	public boolean agregarTurno(Turnos t) {
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(agregarturno);
			statement.setInt(1, t.getCod_Horario_Turno());
			statement.setString(2, t.getDNI_Medico_Turno());
			statement.setString(3, t.getDNI_Paciente_Turno());
			statement.setString(4, t.getFecha_Turno());
			statement.setString(5, t.getDia_Turno());
			statement.setString(6, t.getInicio_Turno());
			statement.setString(7, t.getFin_Turno());
			statement.setString(8, t.getEstado_Turno());
	
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public ArrayList<String> DiaTurnos() {
		ArrayList<String> ls = new ArrayList<String>();
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(traerDiaTurno);
			rs = st.executeQuery();
		
			
			while(rs.next())
			{
				
				ls.add(rs.getString("Dia_Turno"));
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	public ArrayList<Turnos> ListarTurnosXMedicos(Usuario us) {
		ArrayList<Turnos> ls = new ArrayList<Turnos>();
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(traerTurnosXMedico);
			st.setString(1, us.getDni_Usuario());
			rs = st.executeQuery();
		
			
			while(rs.next())
			{
				Turnos t = new Turnos();
				t.setCod_Turno(rs.getInt("Cod_Turno"));
				t.setMedico_Nombre(rs.getString("medico"));
				t.setDia_Turno(rs.getString("Dia_Turno"));
				t.setInicio_Turno(rs.getString("horario"));
				t.setEspecialidad_Turno(rs.getString("especialidad"));
				t.setObservacines(rs.getString("Observacion"));
				t.setEstado_Turno(rs.getString("Estado_Turno"));
				//datos paciente
				t.setDNI_Paciente_Turno(rs.getString("DNI_Paciente_Turno"));
				t.setNombre_Paciente(rs.getString("paciente"));
				t.setSexo_Paciente(rs.getString("Sexo_Paciente"));
				t.setDireccion_Paciente(rs.getString("direccion"));
				t.setNac_Paciente(rs.getString("Fecha_Nacimiento_Paciente"));
				t.setCorreo_Paciente(rs.getString("Correo_Paciente"));
				t.setTelefono_Paciente(rs.getString("Telefono_Paciente"));
				
				ls.add(t);	
				
			}
				
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	
	public ArrayList<Turnos> ListarTurnos() {
		ArrayList<Turnos> ls = new ArrayList<Turnos>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTurnos);
		
			
			while(rs.next())
			{
				Turnos t = new Turnos();
				t.setCod_Turno(rs.getInt("Cod_Turno"));
				t.setMedico_Nombre(rs.getString("medico"));
				t.setDia_Turno(rs.getString("Dia_Turno"));
				t.setInicio_Turno(rs.getString("horario"));
				t.setEspecialidad_Turno(rs.getString("especialidad"));
				t.setObservacines(rs.getString("Observacion"));
				t.setEstado_Turno(rs.getString("Estado_Turno"));
				//datos paciente
				t.setDNI_Paciente_Turno(rs.getString("DNI_Paciente_Turno"));
				t.setNombre_Paciente(rs.getString("paciente"));
				t.setSexo_Paciente(rs.getString("Sexo_Paciente"));
				t.setDireccion_Paciente(rs.getString("direccion"));
				t.setNac_Paciente(rs.getString("Fecha_Nacimiento_Paciente"));
				t.setCorreo_Paciente(rs.getString("Correo_Paciente"));
				t.setTelefono_Paciente(rs.getString("Telefono_Paciente"));
				
				ls.add(t);	
				
			}
				
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	public boolean modificarTurno(Turnos turno) {
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		boolean modificarExitoso = false;
		try
		{
			statement = conexion.prepareStatement(modificarTurno);
			statement.setString(1, turno.getObservacines());
			statement.setString(2, turno.getEstado_Turno());
			statement.setInt(3, turno.getCod_Turno());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				modificarExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return modificarExitoso;
	}

	
}
