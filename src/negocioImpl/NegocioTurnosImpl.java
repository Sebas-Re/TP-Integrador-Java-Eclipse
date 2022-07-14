package negocioImpl;

import java.util.ArrayList;

import daolmpl.DaoTurnosImpl;
import daolmpl.DaoUsuarioImpl;
import entidad.Medicos;
import entidad.Turnos;
import entidad.Usuario;

public class NegocioTurnosImpl {
	DaoUsuarioImpl daous = new DaoUsuarioImpl();
	DaoTurnosImpl daotur = new DaoTurnosImpl();
	
	public Usuario TraerUsuario (String nombre, String contra) {
		return daous.validarUsuario(nombre, contra);
	}
	
	public String  CodigoHorario(String dni) {
		return daotur.CodigoHorario(dni);
	}
	
	public Usuario TraerDNIMedico (Usuario us) {
		return daous.TraerDNIMedico(us);
	}

	public ArrayList<Turnos> ListarTurnosXMedico(Usuario us){
		return daotur.ListarTurnosXMedicos(us);
	}
	
	public ArrayList<Turnos> ListarTurnos(){
		return daotur.ListarTurnos();
	}
	
	public ArrayList<String> DiaTurnos(){
		return daotur.DiaTurnos();
	}
	public ArrayList<Medicos> ListarMedicos(){
		return daotur.ListarMedico();
	}
	public ArrayList<String> listarEspecialidad(){
		return daotur.listarEspecialidad();
	}
	
	public boolean AgregarTurno(Turnos turno) {
		return daotur.agregarTurno(turno);
	}
	
	public boolean modificarTurno(Turnos turno) {
		return daotur.modificarTurno(turno);
	}

	
}
