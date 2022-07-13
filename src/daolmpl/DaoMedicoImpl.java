package daolmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DaoMedico;
import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;

public class DaoMedicoImpl implements DaoMedico{
	private static final String traerTodosLosTurnos = "SELECT * FROM turno";
	private static final String traerPacientePorDNI = "SELECT * FROM paciente where DNI_Paciente=?";
	private static final String traerTodosLosMedicos = "SELECT * FROM medico";
	private static final String FiltrarMedicosxNombre = "SELECT * FROM medico where Nombre_Medico like '%";
	private static final String FiltrarMedicosXNombreEstado = "SELECT * FROM medico where Estado_Medico = ? and Nombre_Medico like '%";
	private static final String FiltrarMedicosxEstado = "SELECT * FROM medico where Estado_Medico=?";
	private static final String modificarMedico = "UPDATE paciente set Nombre_Paciente = ?, Apellido_Paciente = ?,Sexo_Paciente = ?,Fecha_Nacimiento_Paciente = ?,Nacionalidad_Paciente = ?,Provincia_Paciente = ?,Localidad_Paciente = ?,Direccion_Paciente = ?,Correo_Paciente = ?,Telefono_Paciente = ?,Estado_Paciente = ? WHERE DNI_Paciente = ?";
	private static final String AgregarMedico = "INSERT INTO medico(Cod_Usuario_Medico,DNI_Medico,Nombre_Medico,Apellido_Medico,Sexo_Medico,Fecha_Nacimiento_Medico,Nacionalidad_Medico,Provincia_Medico,Localidad_Medico,Direccion_Medico,Correo_Medico,Telefono_Medico,Especialidad_Medico,Estado_Medico) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	DaoUsuarioImpl daoUsuario = new DaoUsuarioImpl();
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

	@Override
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
	
	@Override
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

	public ArrayList<Medicos> ListarMedico() {
		ArrayList<Medicos> ls = new ArrayList<Medicos>();
		Connection conexion;
		Statement st;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(traerTodosLosMedicos);
			
			while(rs.next())
			{
				Medicos M = new Medicos();
				
				M.setDni_m(rs.getString("DNI_Medico"));
				M.setNombre_m(rs.getString("Nombre_Medico"));
				M.setApellido_m(rs.getString("Apellido_Medico"));
				M.setSexo_m(rs.getString("Sexo_Medico"));
				M.setFechaNac_m(rs.getString("Fecha_Nacimiento_Medico"));
				M.setNacionalidad(rs.getString("Nacionalidad_Medico"));
				M.setProvincia_m(rs.getString("Provincia_Medico"));
				M.setLocalidad_m(rs.getString("Localidad_Medico"));
				M.setDireccion_m(rs.getString("Direccion_Medico"));
				M.setCorreoElectronico_m(rs.getString("Correo_Medico"));
				M.setTelefono_m(rs.getString("Telefono_Medico"));
				M.setEspecialidad(rs.getString("Especialidad_Medico"));
				M.setEstado(rs.getInt("Estado_Medico"));
				ls.add(M);
				
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}
	
	@Override
	public ArrayList<Medicos> FiltrarEstado(Medicos medicos) {

		ArrayList<Medicos> ls = new ArrayList<Medicos>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(FiltrarMedicosxEstado);
			Statement.setInt(1, medicos.getEstado());
			
			rs = Statement.executeQuery();
			
			ls= DevolverResultSetMedicos(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<Medicos> FiltrarNombre(Medicos medicos) {
		ArrayList<Medicos> ls = new ArrayList<Medicos>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(FiltrarMedicosxNombre + medicos.getNombre_m()+"%'");
			
			rs = Statement.executeQuery();
			
			ls= DevolverResultSetMedicos(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;	
	}
	
	@Override
	public ArrayList<Medicos> FiltrarNombreEstado(Medicos medicos) {
		ArrayList<Medicos> ls = new ArrayList<Medicos>();
		Connection conexion;
		PreparedStatement Statement;
		ResultSet rs;
		try 
		{
			conexion = Conexion.getSQLConexion();
			Statement = conexion.prepareStatement(FiltrarMedicosXNombreEstado+medicos.getNombre_m()+"%'");
			Statement.setInt(1, medicos.getEstado());

			
			rs = Statement.executeQuery();
			
			ls= DevolverResultSetMedicos(rs, ls);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public ArrayList<Medicos> DevolverResultSetMedicos(ResultSet rs, ArrayList<Medicos> ls) throws SQLException{
		while(rs.next())
		{
			Medicos m = new Medicos();
			
			m.setDni_m(rs.getString("DNI_Medico"));
			m.setNombre_m(rs.getString("Nombre_Medico"));
			m.setApellido_m(rs.getString("Apellido_Medico"));
			m.setSexo_m(rs.getString("Sexo_Medico"));
			m.setFechaNac_m(rs.getString("Fecha_Nacimiento_Medico"));
			m.setNacionalidad(rs.getString("Nacionalidad_Medico"));
			m.setProvincia_m(rs.getString("Provincia_Medico"));
			m.setLocalidad_m(rs.getString("Localidad_Medico"));
			m.setDireccion_m(rs.getString("Direccion_Medico"));
			m.setCorreoElectronico_m(rs.getString("Correo_Medico"));
			m.setTelefono_m(rs.getString("Telefono_Medico"));
			m.setEspecialidad(rs.getString("Especialidad_Medico"));
			m.setEstado(rs.getInt("Estado_Medico"));
			ls.add(m);
			
		}
		return ls;
	}
	
	@Override
	public boolean modificarMedico(Medicos medico) {
		PreparedStatement statement;
		Connection conexion = Conexion.getSQLConexion();
		boolean modificarExitoso = false;
		try
		{
			statement = conexion.prepareStatement(modificarMedico);
			statement.setString(1, medico.getNombre_m());
			statement.setString(2, medico.getApellido_m());
			statement.setString(3, medico.getSexo_m());
			statement.setString(4, medico.getFechaNac_m());
			statement.setString(5, medico.getNacionalidad());
			statement.setString(6, medico.getProvincia_m());
			statement.setString(7, medico.getLocalidad_m());
			statement.setString(8, medico.getDireccion_m());
			statement.setString(9, medico.getCorreoElectronico_m());
			statement.setString(10, medico.getTelefono_m());
			statement.setInt(11, medico.getEstado());
			statement.setString(12, medico.getDni_m());
			
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

	public int agregarMedico(Usuario us, Medicos Med)
	{
		int InsertsExitosos =0;
		if(daoUsuario.AgregarUsuario(us))
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
				statement.setInt(14, Med.getEstado());

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
	
}
