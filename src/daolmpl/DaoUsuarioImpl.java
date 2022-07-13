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
	



}
