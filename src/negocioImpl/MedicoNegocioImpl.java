package negocioImpl;

import java.util.ArrayList;

import daolmpl.DaoMedicoImpl;
import daolmpl.DaoUsuarioImpl;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import entidad.Medicos;
import entidad.Pacientes;
import entidad.Turnos;
import entidad.Usuario;

public class MedicoNegocioImpl implements MedicoNegocio {
	DaoUsuarioImpl UserDao = new DaoUsuarioImpl();
	DaoMedicoImpl DaoMedico = new DaoMedicoImpl();

	@Override
	public ArrayList<Turnos> listarTurnos() {
		return DaoMedico.listarTurnos();
	}
	@Override
	public ArrayList<Pacientes> ListarPacientePorDNI(Pacientes p) {
		return DaoMedico.ListarPacientePorDNI(p);
	}
	
	@Override
	public int agregarMedico(Usuario us, Medicos Med) {
		return UserDao.agregarMedico(us, Med);
	}

	@Override
	public boolean bajaLogicaMedico(Usuario us) {
		return UserDao.bajaLogicaMedico(us);
	}

	@Override
	public boolean modificarMedico(Usuario us) {
		return UserDao.modificarMedicos(us);
	}

	@Override
	public ArrayList<Medicos> listarMedicos() {
		return UserDao.listarMedicos();
	}
	
	
}
