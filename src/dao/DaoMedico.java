package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;

public interface DaoMedico {
	public ArrayList<Turnos> listarTurnos();
	public ArrayList<Medicos> ListarMedico();
	public ArrayList<Pacientes> ListarPacientePorDNI(Pacientes p);
	public boolean modificarMedico(Medicos medico);
	public int agregarMedico(Usuario us, Medicos Med);
	public ArrayList<Medicos> FiltrarNombreEstado(Medicos medicos);
	public ArrayList<Medicos> FiltrarEstado(Medicos medicos);
	public ArrayList<Medicos> FiltrarNombre(Medicos medicos);
	public ArrayList<Medicos> DevolverResultSetMedicos(ResultSet rs, ArrayList<Medicos> ls) throws SQLException;
	public ArrayList<Turnos> DevolverResultSet(ResultSet rs, ArrayList<Turnos> ls) throws SQLException;
	public ArrayList<Pacientes> DevolverResultSetPacientes(ResultSet rs, ArrayList<Pacientes> ls) throws SQLException;
	
	
}
