package dao;

import java.util.ArrayList;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Usuario;

public interface DaoUsuario
{
	public int retornarID();
	public Usuario validarUsuario(String Contraseña, String Usuario);
	public boolean AgregarUsuario(Usuario us);
	
	public boolean agregarPaciente(Pacientes paciente);
	public boolean modificarPaciente(Pacientes paciente);
	public boolean bajaLogicaPaciente(Pacientes paciente);
	public ArrayList<Pacientes> listarPaciente();
	
	public int agregarMedico(Usuario us, Medicos Med);
	public boolean modificarMedicos(Usuario us);
	public boolean bajaLogicaMedico(Usuario us);
	public ArrayList<Medicos> listarMedicos();
}
