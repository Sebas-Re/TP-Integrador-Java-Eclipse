package daolmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DaoPaciente;
import entidad.Pacientes;

public class DaoPacienteImpl implements DaoPaciente{
	private static final String agregarPaciente = "INSERT INTO paciente(DNI_Paciente,Nombre_Paciente,Apellido_Paciente,Sexo_Paciente,Fecha_Nacimiento_Paciente,Nacionalidad_Paciente,Provincia_Paciente,Localidad_Paciente,Direccion_Paciente,Correo_Paciente, Telefono_Paciente, Estado_Paciente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String modificarPaciente = "UPDATE paciente set Nombre_Paciente = ?, Apellido_Paciente = ?,Sexo_Paciente = ?,Fecha_Nacimiento_Paciente = ?,Nacionalidad_Paciente = ?,Provincia_Paciente = ?,Localidad_Paciente = ?,Direccion_Paciente = ?,Correo_Paciente = ?,Telefono_Paciente = ?,Estado_Paciente = ? WHERE DNI_Paciente = ?";
	private static final String traerTodosPacientes = "SELECT * FROM paciente";
	private static final String traerTodosPacientesxDNI = "SELECT * FROM paciente where DNI_Paciente=?";
	private static final String FiltrarPacietesxNombre = "SELECT * FROM paciente where Nombre_Paciente like '%";
	private static final String FiltrarPacietesxNombreEstado = "SELECT * FROM paciente where Estado_Paciente = ? and Nombre_Paciente like '%";
	private static final String FiltrarPacietesxEstado = "SELECT * FROM paciente where Estado_Paciente=?";
	Conexion Conexion = new Conexion();
	
	@Override
	public boolean agregarPaciente(Pacientes paciente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(agregarPaciente);
			statement.setString(1, paciente.getDNI());
			statement.setString(2, paciente.getNombre());
			statement.setString(3, paciente.getApellido());
			statement.setString(4, paciente.getSexo());
			statement.setString(5, paciente.getFechaNacimiento());
			statement.setString(6, paciente.getNacionalidad());
			statement.setString(7, paciente.getProvincia());
			statement.setString(8, paciente.getLocalidad());
			statement.setString(9, paciente.getDireccion());
			statement.setString(10, paciente.getCorreo());
			statement.setString(11, paciente.getTelefono());
			statement.setInt(12, paciente.getEstado());
			
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

	@Override
	public boolean bajaLogicaPaciente(Pacientes paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarPaciente(Pacientes paciente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		boolean modificarExitoso = false;
		try
		{
			statement = conexion.prepareStatement(modificarPaciente);
			statement.setString(1, paciente.getNombre());
			statement.setString(2, paciente.getApellido());
			statement.setString(3, paciente.getSexo());
			statement.setString(4, paciente.getFechaNacimiento());
			statement.setString(5, paciente.getNacionalidad());
			statement.setString(6, paciente.getProvincia());
			statement.setString(7, paciente.getLocalidad());
			statement.setString(8, paciente.getDireccion());
			statement.setString(9, paciente.getCorreo());
			statement.setString(10, paciente.getTelefono());
			statement.setInt(11, paciente.getEstado());
			statement.setString(12, paciente.getDNI());
			
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
	
	public ArrayList<Pacientes> listarPacientexDNI(Pacientes p) {
		ArrayList<Pacientes> ls = new ArrayList<Pacientes>();
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(traerTodosPacientesxDNI);
			st.setString(1, p.getDNI());
			rs = st.executeQuery();
			
			ls= DevolverResultSet(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	@Override
	public ArrayList<Pacientes> FiltrarEstado(Pacientes paciente) {

		ArrayList<Pacientes> ls = new ArrayList<Pacientes>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(FiltrarPacietesxEstado);
			Statement.setInt(1, paciente.getEstado());
			
			rs = Statement.executeQuery();
			
			ls= DevolverResultSet(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<Pacientes> FiltrarNombre(Pacientes paciente) {
		ArrayList<Pacientes> ls = new ArrayList<Pacientes>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(FiltrarPacietesxNombre + paciente.getNombre()+"%'");
			
			rs = Statement.executeQuery();
			
			ls= DevolverResultSet(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;	
	}
	
	@Override
	public ArrayList<Pacientes> listarPaciente() {
		ArrayList<Pacientes> ls = new ArrayList<Pacientes>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTodosPacientes);
			
			ls= DevolverResultSet(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<Pacientes> FiltrarNombreEstado(Pacientes paciente) {
		ArrayList<Pacientes> ls = new ArrayList<Pacientes>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(FiltrarPacietesxNombreEstado+paciente.getNombre()+"%'");
			Statement.setInt(1, paciente.getEstado());

			
			rs = Statement.executeQuery();
			
			ls= DevolverResultSet(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<Pacientes> DevolverResultSet(ResultSet rs, ArrayList<Pacientes> ls) throws SQLException{
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
}
