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
}
