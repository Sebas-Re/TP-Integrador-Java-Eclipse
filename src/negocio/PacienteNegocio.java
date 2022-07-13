package negocio;

import entidad.Pacientes;

import java.util.ArrayList;

public interface PacienteNegocio {
	public boolean agregarPaciente(Pacientes paciente);
	public boolean bajaLogicaPaciente(Pacientes paciente);
	public boolean modificarPaciente(Pacientes paciente);
	public ArrayList<Pacientes> FiltrarNombreEstado(Pacientes p);
	public ArrayList<Pacientes> listarPaciente();
}
