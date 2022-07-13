package daolmpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoUsuario;
import entidad.Medicos;
import entidad.Pacientes;
import entidad.Usuario;

public class DaoUsuarioImpl implements DaoUsuario
{
	private static final String agregarPaciente = "INSERT INTO paciente(DNI_Paciente,Nombre_Paciente,Apellido_Paciente,Sexo_Paciente,Fecha_Nacimiento_Paciente,Nacionalidad_Paciente,Provincia_Paciente,Localidad_Paciente,Direccion_Paciente,Correo_Paciente, Telefono_Paciente, Estado_Paciente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String traerTodosPacientes = "SELECT * FROM paciente";
	
	private static final String AgregarUsuario = "INSERT INTO usuario(Nombre_Usuario,Contraseña_Usuario,tipoUsuario_Usuario) VALUES(?,?,?)";
	private static final String AgregarMedico = "INSERT INTO medico(Cod_Usuario_Medico,DNI_Medico,Nombre_Medico,Apellido_Medico,Sexo_Medico,Fecha_Nacimiento_Medico,Nacionalidad_Medico,Provincia_Medico,Localidad_Medico,Direccion_Medico,Correo_Medico,Telefono_Medico,Especialidad_Medico,Estado_Medico) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	Conexion Conexion = new Conexion();
	
	
	 // Buscaría en la base de datos el siguiente ID y lo devolvería
	public int retornarID()
	{
		//Esta funcion tendria que devolver el ultimo ID cargado (el cual sería el que tiene el cod mas grande) para aplicarselo luego
		//a los medicos y administradores
		
		
		return 0;
	}

	
	
	public Usuario validarUsuario( String Usuario, String Contraseña)
	{
		
	Statement st;
		ResultSet rs;
		Usuario us = new Usuario();
		try 
		{
			
			Connection conexion = Conexion.getSQLConexion();
			
			st = conexion.createStatement();
			rs = st.executeQuery("select `Nombre_Usuario`,`tipoUsuario_Usuario` from usuario where Nombre_Usuario = '"+Usuario+"' AND Contraseña_Usuario ='"+Contraseña+"'");
				
			
			while(rs.next())
			{
				
				us.setNombreUsuario(rs.getString("Nombre_Usuario"));
				us.setTipoUsuario(rs.getInt("tipoUsuario_Usuario"));
				return us;
				
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		us.setTipoUsuario(-1);
		return us;
		
	}

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
		// TODO Auto-generated method stub
		return false;
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
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	
	//De acá para abajo todo lo nuevo (medico y algo de usuario). Añadir a DaoUsuario despues
	
	public boolean AgregarUsuario(Usuario us)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		boolean isInsertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(AgregarUsuario);
			statement.setString(1, us.getNombreUsuario());
			statement.setString(2, us.getContraseñaUsuario());
			statement.setInt(3, us.getTipoUsuario());
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
	
	public int agregarMedico(Usuario us, Medicos Med)
	{
		int InsertsExitosos =0;
		if(AgregarUsuario(us))
		{
			
			PreparedStatement statement;
			Connection conexion = Conexion.getSQLConexion();
			
			InsertsExitosos++;
			
			try
			{
				statement = conexion.prepareStatement(AgregarMedico);
			//	statement.setInt(1, retornarID() ); Resultado de la funcion "retornarID", la cual devuelve el ID mas alto (es decir, el mas reciente)
				statement.setString(2, Med.getDni_m());
				statement.setString(3, Med.getNombre_m());
				statement.setString(4, Med.getApellido_m());
				statement.setString(5, Med.getSexo_m());
				statement.setString(6, Med.getFechaNac_m());
				statement.setString(7, Med.getNacionalidad());
				statement.setString(8, Med.getProvincia_m());
				statement.setString(9, Med.getLocalidad_m());
				statement.setString(10, Med.getDireccion_m());
				statement.setString(11, Med.getCorreoElectronico_m());
				statement.setString(12, Med.getTelefono_m());
				statement.setString(13, Med.getEspecialidad());
				statement.setBoolean(14, Med.isEstado());

				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					InsertsExitosos++;
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
			
			
		}
		else {
			//Si pasa por acá, falló al agregar el usuario
			return 0;
		}
		
	return InsertsExitosos;
	}
	
	public boolean bajaLogicaMedico(Usuario us)
	{
		
		return false;
	}
	
	public boolean modificarMedicos(Usuario us) {
		
		return false;
	}
	
	public ArrayList<Medicos> listarMedicos()
	{
		ArrayList<Medicos> ls = new ArrayList<Medicos>();
		
		Connection conexion;
		Statement st;
		ResultSet rs;
		
		
		return ls;
	}
}
