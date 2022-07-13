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
		return DaoMedico.agregarMedico(us, Med);
	}

	@Override
	public boolean modificarMedico(Medicos m) {
		return DaoMedico.modificarMedico(m);
	}

	@Override
	public ArrayList<Medicos> listarMedicos() {
		return DaoMedico.ListarMedico();
	}
	
	@Override
	public ArrayList<Medicos> FiltrarNombreEstado(Medicos m) {
		ArrayList<Medicos> lista = null;
		if(!m.getNombre_m().equals("")) {
			if(m.getEstado()==0||m.getEstado()==1) {
				lista=DaoMedico.FiltrarNombreEstado(m);
			}
			else {
				lista=DaoMedico.FiltrarNombre(m);
			}
			
		}
		else {
			if(m.getEstado()==0||m.getEstado()==1) {
				lista=DaoMedico.FiltrarEstado(m);
			}
			else
			lista=DaoMedico.ListarMedico();
		}
		return  lista;
	}
	
	
}
