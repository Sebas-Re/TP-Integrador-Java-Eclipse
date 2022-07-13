package dao;

import java.util.ArrayList;

import entidad.Pacientes;
import entidad.Turnos;

public interface DaoMedico {
	public ArrayList<Turnos> listarTurnos();
	public ArrayList<Pacientes> ListarPacientePorDNI(Pacientes p);
}
