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
	
	private static final String AgregarUsuario = "INSERT INTO usuario(Nombre_Usuario,Contraseña_Usuario,tipoUsuario_Usuario) VALUES(?,?,?)";
	private static final String TraerUsuario = "select Nombre_Usuario, tipoUsuario_Usuario, Cod_Usuario from usuario where Nombre_Usuario ='";
	private static final String TraerDNIMedico = "select Nombre_Usuario, tipoUsuario_Usuario, Cod_Usuario, DNI_Medico from usuario inner join medico as m on m.Cod_Usuario_Medico = Cod_Usuario where Cod_Usuario=?";
	
	
	
	Conexion Conexion = new Conexion();
	
	
//	 // Buscaría en la base de datos el siguiente ID y lo devolvería
//	public int retornarID()
//	{
//		//Esta funcion tendria que devolver el ultimo ID cargado (el cual sería el que tiene el cod mas grande) para aplicarselo luego
//		//a los medicos y administradores
//		
//		
//		return 0;
//	}

	
	
	public Usuario validarUsuario( String Usuario, String Contraseña)
	{
		
	Statement st;
		ResultSet rs;
		Usuario us = new Usuario();
		try 
		{
			
			Connection conexion = Conexion.getSQLConexion();
			
			st = conexion.createStatement();
			rs = st.executeQuery(TraerUsuario+Usuario+"' and Contraseña_Usuario='"+ Contraseña+"'");
				
			
			while(rs.next())
			{
				
				us.setNombreUsuario(rs.getString("Nombre_Usuario"));
				us.setTipoUsuario(rs.getInt("tipoUsuario_Usuario"));
				us.setCodUsuario(rs.getInt("Cod_Usuario"));
				
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
	
	public Usuario TraerDNIMedico(Usuario us) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		ResultSet rs;
		Usuario user = new Usuario();
		
		try
		{
			statement = conexion.prepareStatement(TraerDNIMedico);
			statement.setInt(1, us.getCodUsuario());
			rs = statement.executeQuery();
			while(rs.next()) {
				user.setNombreUsuario(rs.getString("Nombre_Usuario"));
				user.setTipoUsuario(rs.getInt("tipoUsuario_Usuario"));
				user.setCodUsuario(rs.getInt("Cod_Usuario"));
				user.setDni_Usuario(rs.getString("DNI_Medico"));
				
			}
			
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return user;
	}
	



}
