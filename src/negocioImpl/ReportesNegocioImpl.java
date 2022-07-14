package negocioImpl;

import java.util.ArrayList;

import daolmpl.DaoReportesImpl;
import entidad.Medicos;
import entidad.Pacientes;
import entidad.Reportes;
import negocio.ReportesNegocio;

public class ReportesNegocioImpl implements ReportesNegocio
{
	private DaoReportesImpl daoRep = new DaoReportesImpl();
	public ArrayList<Reportes> CargarReportes() {

		
		return daoRep.CargarReportes();
	}

	public ArrayList<Medicos> FiltrarReporteMedicos(String filtrarTipoEstado, String fechaInicial, String fechaFinal) {
		return daoRep.FiltrarReporteMedicos(filtrarTipoEstado, fechaInicial, fechaFinal);
	}

	@Override
	public ArrayList<Pacientes> FiltrarReportePacientes(String filtrarTipoEstado, String fechaInicial,String fechaFinal) {
		return daoRep.FiltrarReportePacientes(filtrarTipoEstado, fechaInicial, fechaFinal);
	}
	
}
