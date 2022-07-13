package daolmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DaoMedico;
import entidad.Pacientes;
import entidad.Turnos;

public class DaoMedicoImpl implements DaoMedico{
	private static final String traerTodosLosTurnos = "SELECT * FROM turno";
	private static final String traerPacientePorDNI = "SELECT * FROM paciente where DNI_Paciente=?";
	Conexion Conexion = new Conexion();
	@Override
	public ArrayList<Turnos> listarTurnos() {
		ArrayList<Turnos> ls = new ArrayList<Turnos>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTodosLosTurnos);
			
			ls= DevolverResultSet(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	public ArrayList<Turnos> DevolverResultSet(ResultSet rs, ArrayList<Turnos> ls) throws SQLException{
		while(rs.next())
		{
			Turnos t = new Turnos();
			
			t.setCod_Turno(rs.getInt("Cod_Turno"));
			t.setCod_Horario_Turno(rs.getInt("Cod_Horario_Turno"));
			t.setDNI_Paciente_Turno(rs.getString("DNI_Paciente_Turno"));
			t.setDNI_Medico_Turno(rs.getString("DNI_Medico_Turno"));
			t.setDia_Turno(rs.getString("Dia_Turno"));
			t.setFecha_Turno(rs.getString("Fecha_Turno"));
			t.setInicio_Turno(rs.getString("Inicio_Turno"));
			t.setFin_Turno(rs.getString("Fin_Turno"));
			t.setEspecialidad_Turno(rs.getString("Especialidad_Turno"));
			t.setEstado_Turno(rs.getString("Estado_Turno"));
			
			ls.add(t);
			
		}
		return ls;
	}
	
	public ArrayList<Pacientes> DevolverResultSetPacientes(ResultSet rs, ArrayList<Pacientes> ls) throws SQLException{
		while(rs.next())
		{
			Pacientes p = new Pacientes();
			
			p.setDNI(rs.getString("DNI_Paciente"));
			p.setNombre(rs.getString("Nombre_Paciente"));
			p.setApellido(rs.getString("Apellido_Paciente"));
			p.setSexo(rs.getString("Sexo_Paciente"));
			p.setFechaNacimiento(rs.getString("Fecha_Nacimiento_Paciente"));
			p.setNacionalidad(rs.getString("Nacionalidad_Paciente"));
			p.setProvincia(rs.getString("Provincia_Paciente"));
			p.setLocalidad(rs.getString("Localidad_Paciente"));
			p.setDireccion(rs.getString("Direccion_Paciente"));
			p.setCorreo(rs.getString("Correo_Paciente"));
			p.setTelefono(rs.getString("Telefono_Paciente"));
			p.setEstado(rs.getInt("Estado_Paciente"));
			ls.add(p);
			
		}
		return ls;
	}

	@Override
	public ArrayList<Pacientes> ListarPacientePorDNI(Pacientes p) {
		ArrayList<Pacientes> ls = new ArrayList<Pacientes>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(traerPacientePorDNI);
			Statement.setString(1, p.getDNI());
			
			rs = Statement.executeQuery();
			
			ls = DevolverResultSetPacientes(rs, ls);
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;	
	}


	
}
