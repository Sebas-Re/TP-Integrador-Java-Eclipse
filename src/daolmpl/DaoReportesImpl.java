package daolmpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import dao.DaoReportes;
import entidad.Reportes;

public class DaoReportesImpl implements DaoReportes{
	private static final String DiasMasConcurridos = "select `Dia_Turno` AS `Dias mas concurridos`, COUNT(`Dia_Turno`) AS `Frecuencia` from `bdclinica`.`turno` GROUP BY `Dia_Turno` ORDER BY COUNT(`Dia_Turno`) DESC LIMIT 3;";
	private static final String HorariosMasConcurridos = "select `Inicio_Turno` AS `Horarios mas concurridos`, COUNT(`Inicio_Turno`) AS `Frecuencia` from `bdclinica`.`turno` GROUP BY `Inicio_Turno` ORDER BY COUNT(`Inicio_Turno`) DESC LIMIT 3;";
	Conexion Conexion = new Conexion();
	
	
	@Override
	public ArrayList<Reportes> CargarReportes()
	{
		// TODO Acá tendrian que estar las dos consultas SQL para cargar en el objeto reporte y devolverlo;
		
		Connection conexion;
		Statement st;
		ResultSet rs;
		
		ArrayList<Reportes> vRep = new ArrayList<Reportes>(3);
		int Contador=0;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(DiasMasConcurridos);
			
			while(rs.next())
			{
				Reportes reporte = new Reportes();
				reporte.setDias_Mas_Concurridos(rs.getString("Dias mas concurridos"));
				reporte.setFrec_Dias_Mas_Concurridos(rs.getString("Frecuencia"));
				vRep.add(Contador,reporte);
				Contador++;
			 //ultimo guardado en posicion 2.
			}
			
			st = conexion.createStatement();
			rs = st.executeQuery(HorariosMasConcurridos);
			Contador=0;
			while (rs.next())
			{
				
				

				vRep.get(Contador).setHorarios_Mas_Concurridos(rs.getTime("Horarios mas concurridos"));
				vRep.get(Contador).setFrec_Horarios_Mas_Concurridos(rs.getString("Frecuencia"));
				
				Contador++;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		
		return vRep;
	}

}
