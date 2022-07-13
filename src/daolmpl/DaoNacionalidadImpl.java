package daolmpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DaoNacionalidad;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public class DaoNacionalidadImpl implements DaoNacionalidad{

	private static final String traerTodosPaises = "SELECT * FROM pais";
	private static final String traerTodosProvincias = "SELECT * FROM provincia";
	private static final String traerTodosLocalidad = "SELECT * FROM localidad";
	Conexion Conexion = new Conexion();
	
	@Override
	public ArrayList<Pais> traerPaises() {
		ArrayList<Pais> ls = new ArrayList<Pais>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTodosPaises);
			
			ls= DevolverResultSetPais(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	@Override
	public ArrayList<Provincia> traerProvincias() {
		ArrayList<Provincia> ls = new ArrayList<Provincia>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTodosProvincias);
			
			ls= DevolverResultSetProv(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<Localidad> traerLocalidades() {
		ArrayList<Localidad> ls = new ArrayList<Localidad>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTodosLocalidad);
			
			ls= DevolverResultSetLoc(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<Pais> DevolverResultSetPais(ResultSet rs, ArrayList<Pais> ls) throws SQLException{
		while(rs.next())
		{
			Pais p = new Pais();
			
			p.setId(rs.getInt("idPais"));
			p.setNombre(rs.getString("nombrePais"));
			ls.add(p);
			
		}
		return ls;
	}
	public ArrayList<Provincia> DevolverResultSetProv(ResultSet rs, ArrayList<Provincia> ls) throws SQLException{
		while(rs.next())
		{
			Provincia prov = new Provincia();
			
			prov.setIdProv(rs.getInt("idProv"));
			prov.setNombreProv(rs.getString("nombreProv"));
			prov.setIdPaisProv(rs.getInt("idPaisProv"));
			ls.add(prov);
			
		}
		return ls;
	}
	public ArrayList<Localidad> DevolverResultSetLoc(ResultSet rs, ArrayList<Localidad> ls) throws SQLException{
		while(rs.next())
		{
			Localidad loc = new Localidad();
			
			loc.setIdLoc(rs.getInt("idLoc"));
			loc.setIdProvLoc(rs.getInt("idProvLoc"));
			loc.setNombreLoc(rs.getString("nombreLoc"));
			
			ls.add(loc);
			
		}
		return ls;
	}
}
