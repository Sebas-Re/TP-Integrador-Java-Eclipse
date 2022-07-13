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
	public boolean bajaLogicaMedico(Usuario us);
	//No hace falta pasar el dni del medico. ya con el codigo del usuario se puede hacer un inner join para encontrarlo
	public boolean modificarMedico(Usuario us); //Lo mismo que el anterior comentario
	public ArrayList<Medicos> listarMedicos();
}
