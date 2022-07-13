package daolmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Pacientes;

public class DaoTurnosImpl {
	private static final String traerEspecialidades = "select Especialidad_Medico from medico";
	private static final String traerMedicos = "select Nombre_Medico, Apellido_Medico from medico where Especialidad_Medico =?";
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
	
	public ArrayList<String> ListarMedicos(String Especialidad) {
		ArrayList<String> ls = new ArrayList<String>();
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(traerEspecialidades);
			st.setString(1, Especialidad);
			rs = st.executeQuery();
		
			
			while(rs.next())
			{
				
				ls.add(rs.getString("Nombre_Medico")+" "+rs.getString("Apellido_Medico"));
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

}
