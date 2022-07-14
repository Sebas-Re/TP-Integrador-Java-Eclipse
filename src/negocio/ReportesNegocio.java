package negocio;

import java.util.ArrayList;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Reportes;

public interface ReportesNegocio
{
	public ArrayList<Reportes> CargarReportes();
	ArrayList<Medicos> FiltrarReporteMedicos(String filtrarTipoEstado, String fechaInicial, String fechaFinal);
	ArrayList<Pacientes> FiltrarReportePacientes(String filtrarTipoEstado, String fechaInicial, String fechaFinal);
}
