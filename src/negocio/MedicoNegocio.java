package negocio;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;

import java.util.ArrayList;

public interface MedicoNegocio {
	public ArrayList<Turnos> listarTurnos();
	public ArrayList<Pacientes> ListarPacientePorDNI(Pacientes p);
	public int agregarMedico(Usuario us, Medicos Med);
	//No hace falta pasar el dni del medico. ya con el codigo del usuario se puede hacer un inner join para encontrarlo
	public boolean modificarMedico(Medicos m); //Lo mismo que el anterior comentario
	public ArrayList<Medicos> listarMedicos();
	public ArrayList<Medicos> FiltrarNombreEstado(Medicos m);

}
