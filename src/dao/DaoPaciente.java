package dao;

import java.util.ArrayList;
import entidad.Pacientes;

public interface DaoPaciente {
	public boolean agregarPaciente(Pacientes paciente);
	public boolean bajaLogicaPaciente(Pacientes paciente);
	public boolean modificarPaciente(Pacientes paciente);
	public ArrayList<Pacientes> listarPaciente();
	public ArrayList<Pacientes> FiltrarNombre(Pacientes paciente);
	public ArrayList<Pacientes> FiltrarEstado(Pacientes paciente);
	public ArrayList<Pacientes> FiltrarNombreEstado(Pacientes paciente);
}
