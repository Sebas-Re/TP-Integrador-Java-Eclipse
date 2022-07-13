package daolmpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DaoReportes;
import entidad.Reportes;

public class DaoReportesImpl implements DaoReportes{
	private static final String DiasMasConcurridos = "select `Dia_Turno` AS `Dias mas concurridos`, COUNT(`Dia_Turno`) AS `Frecuencia` from `bdclinica`.`turno` GROUP BY `Dia_Turno` ORDER BY COUNT(`Dia_Turno`) DESC LIMIT 3;";
	private static final String HorariosMasConcurridos = "select `Inicio_Turno` AS `Horarios mas concurridos`, COUNT(`Inicio_Turno`) AS `Frecuencia` from `bdclinica`.`turno` GROUP BY `Inicio_Turno` ORDER BY COUNT(`Inicio_Turno`) DESC LIMIT 3;";
	Conexion Conexion = new Conexion();
	
	
	@Override
	public Reportes CargarReportes(Reportes reporte)
	{
		// TODO Acá tendrian que estar las dos consultas SQL para cargar en el objeto reporte y devolverlo;
		
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(DiasMasConcurridos);
			
			while(rs.next())
			{
				
				reporte.setDias_Mas_Concurridos(rs.getString("Dias mas concurridos"));
				reporte.setFrec_Dias_Mas_Concurridos(rs.getString("Frecuencia"));
				
			}
			
			st = conexion.createStatement();
			rs = st.executeQuery(HorariosMasConcurridos);
			
			while (rs.next())
			{
				/*Horario es tipo date, asi que no se bien como pasarlo. Hay que revisarlo cuando tengamos tiempo */
				
				//reporte.setHorarios_Mas_Concurridos();
				reporte.setFrec_Horarios_Mas_Concurridos(rs.getString("Frecuencia"));
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		
		return reporte;
	}

}
