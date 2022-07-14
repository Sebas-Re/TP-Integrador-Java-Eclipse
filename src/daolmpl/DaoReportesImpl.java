package daolmpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.bind.ParseConversionEvent;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import dao.DaoReportes;
import entidad.Medicos;
import entidad.Pacientes;
import entidad.Reportes;

public class DaoReportesImpl implements DaoReportes{
	private static final String DiasMasConcurridos = "select `Dia_Turno` AS `Dias mas concurridos`, COUNT(`Dia_Turno`) AS `Frecuencia` from `bdclinica`.`turno` GROUP BY `Dia_Turno` ORDER BY COUNT(`Dia_Turno`) DESC LIMIT 3;";
	private static final String HorariosMasConcurridos = "select `Inicio_Turno` AS `Horarios mas concurridos`, COUNT(`Inicio_Turno`) AS `Frecuencia` from `bdclinica`.`turno` GROUP BY `Inicio_Turno` ORDER BY COUNT(`Inicio_Turno`) DESC LIMIT 3;";                                                             //Fecha inicial y final                  Estado
	private static final String FiltrarMedicos = "select `DNI_Medico`, `Nombre_Medico`,`Apellido_Medico`,`Correo_Medico`,`Telefono_Medico`,count(`DNI_Medico`) as `FRECUENCIA`, `Estado_Medico` from `bdclinica`.`medico` inner join `bdclinica`.`turno` on `DNI_Medico`= `DNI_Medico_Turno` where `bdclinica`.`turno`.`Fecha_Turno` between ? and ? and  `Estado_Medico` != ? GROUP BY `DNI_Medico` ORDER BY count(`DNI_Medico`) DESC"; 
	private static final String FiltrarPacientes = "select `DNI_Paciente`, `Nombre_Paciente`,`Apellido_Paciente`,`Correo_Paciente`,`Telefono_Paciente`,count(`DNI_Paciente`) as `FRECUENCIA`, `Estado_Paciente` from `bdclinica`.`paciente` inner join `bdclinica`.`turno` on `DNI_Paciente`= `DNI_Paciente_Turno` where `bdclinica`.`turno`.`Fecha_Turno` between ? and ? and `Estado_Paciente` != ? GROUP BY `DNI_Paciente` ORDER BY count(`DNI_Paciente`) DESC";
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


	public ArrayList<Medicos> FiltrarReporteMedicos(String filtrarTipoEstado, String FechaInicial, String FechaFinal) {
		/*
		//filtrarTipoPersona, Valores:
		1 - Medico
		2 - Paciente
		
		filtrarTipoEstado, valores:
		0 - Todos
		1 - Baja
		2 - Alta
		*/
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		
		ArrayList<Medicos> vRepMedicos = new ArrayList<Medicos>();
		
		Date FechaInicial1;
		Date FechaFinal1;
		try {
		FechaInicial1 = Date.valueOf(FechaInicial);
		FechaFinal1 = Date.valueOf(FechaFinal);
		

			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(FiltrarMedicos);
			st.setDate(1, FechaInicial1);
			st.setDate(2, FechaFinal1);
			st.setInt(3, Integer.parseInt(filtrarTipoEstado));
			
			rs = st.executeQuery();
			vRepMedicos= DevolverResultSetMedicos(rs, vRepMedicos);
			
			return vRepMedicos;
					
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	

	public ArrayList<Pacientes> FiltrarReportePacientes(String filtrarTipoEstado, String FechaInicial, String FechaFinal) {
		/*
		//filtrarTipoPersona, Valores:
		1 - Medico
		2 - Paciente
		
		filtrarTipoEstado, valores:
		0 - Todos
		1 - Baja
		2 - Alta
		*/
		Connection conexion;
		PreparedStatement st;
		ResultSet rs;
		
		ArrayList<Pacientes> vRepPacientes = new ArrayList<Pacientes>();
		Date FechaInicial1;
		Date FechaFinal1;

		try {
		FechaInicial1 = Date.valueOf(FechaInicial);
		FechaFinal1 = Date.valueOf(FechaFinal);
		
			conexion = Conexion.getSQLConexion();
			st = conexion.prepareStatement(FiltrarPacientes);
			st.setDate(1, FechaInicial1);
			st.setDate(2, FechaFinal1);
			st.setInt(3, Integer.parseInt(filtrarTipoEstado));
			
			rs = st.executeQuery();
			vRepPacientes= DevolverResultSetPacientes(rs, vRepPacientes);
			
			return vRepPacientes;
					
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	

	public ArrayList<Medicos> DevolverResultSetMedicos(ResultSet rs, ArrayList<Medicos> ls){
		try {
			while(rs.next())
			{
				Medicos med = new Medicos();
				
				med.setDni_m(rs.getString("DNI_Medico"));
				med.setNombre_m(rs.getString("Nombre_Medico"));
				med.setApellido_m(rs.getString("Apellido_Medico"));
				med.setCorreoElectronico_m(rs.getString("Correo_Medico"));
				med.setTelefono_m(rs.getString("Telefono_Medico"));
				med.setTurnosActivos(rs.getString("FRECUENCIA"));
				med.setEstado(rs.getInt("Estado_Medico"));
				ls.add(med);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<Pacientes> DevolverResultSetPacientes(ResultSet rs, ArrayList<Pacientes> ls){
		try {
			while(rs.next())
			{
				Pacientes p = new Pacientes();
				
				p.setDNI(rs.getString("DNI_Paciente"));
				p.setNombre(rs.getString("Nombre_Paciente"));
				p.setApellido(rs.getString("Apellido_Paciente"));
				p.setCorreo(rs.getString("Correo_Paciente"));
				p.setTelefono(rs.getString("Telefono_Paciente"));
				p.setTurnosActivos(rs.getString("FRECUENCIA"));
				p.setEstado(rs.getInt("Estado_Paciente"));
				ls.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	
	
	
	
	}
	}
