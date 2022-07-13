package negocioImpl;

import java.util.ArrayList;

import daolmpl.DaoPacienteImpl;
import negocio.PacienteNegocio;
import entidad.Pacientes;

public class PacienteNegocioImpl implements PacienteNegocio {
	DaoPacienteImpl PacienteDao = new DaoPacienteImpl();

	@Override
	public boolean agregarPaciente(Pacientes paciente) {
		return PacienteDao.agregarPaciente(paciente);
	}

	@Override
	public boolean bajaLogicaPaciente(Pacientes paciente) {
		return PacienteDao.bajaLogicaPaciente(paciente);
	}

	@Override
	public boolean modificarPaciente(Pacientes paciente) {
		return PacienteDao.modificarPaciente(paciente);
	}

	@Override
	public ArrayList<Pacientes> listarPaciente() {
		return PacienteDao.listarPaciente();
	}
	
	@Override
	public ArrayList<Pacientes> FiltrarNombreEstado(Pacientes p) {
		ArrayList<Pacientes> lista = null;
		if(!p.getNombre().equals("")) {
			if(p.getEstado()==0||p.getEstado()==1) {
				lista=PacienteDao.FiltrarNombreEstado(p);
			}
			else {
				lista=PacienteDao.FiltrarNombre(p);
			}
			
		}
		else {
			if(p.getEstado()==0||p.getEstado()==1) {
				lista=PacienteDao.FiltrarEstado(p);
			}
		}
		return  lista;
	}

	
	
}
