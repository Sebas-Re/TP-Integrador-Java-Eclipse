package negocioImpl;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import daolmpl.DaoTurnosImpl;
import daolmpl.DaoUsuarioImpl;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;

public class NegocioTurnosImpl {
	DaoUsuarioImpl daous = new DaoUsuarioImpl();
	DaoTurnosImpl daotur = new DaoTurnosImpl();
	
	public Usuario TraerUsuario (String nombre, String contra) {
		return daous.validarUsuario(nombre, contra);
	}
	
	public Usuario TraerDNIMedico (Usuario us) {
		return daous.TraerDNIMedico(us);
	}

	public ArrayList<Turnos> ListarTurnosXMedico(Usuario us){
		return daotur.ListarTurnosXMedicos(us);
	}
	
	public boolean modificarTurno(Turnos turno) {
		return daotur.modificarTurno(turno);
	}

	
}
