package negocioImpl;

import java.util.ArrayList;

import daolmpl.DaoReportesImpl;
import entidad.Reportes;
import negocio.ReportesNegocio;

public class ReportesNegocioImpl implements ReportesNegocio
{

	public ArrayList<Reportes> CargarReportes() {
		DaoReportesImpl daoRep = new DaoReportesImpl();
		
		return daoRep.CargarReportes();
	}
	
}
