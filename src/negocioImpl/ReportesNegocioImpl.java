package negocioImpl;

import daolmpl.DaoReportesImpl;
import entidad.Reportes;

public class ReportesNegocioImpl
{

	public Reportes CargarReportes() {
		DaoReportesImpl daoRep = new DaoReportesImpl();
		
		return daoRep.CargarReportes();
	}
	
}
