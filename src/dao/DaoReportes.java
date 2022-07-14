package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entidad.Medicos;
import entidad.Pacientes;
import entidad.Reportes;

public interface DaoReportes
{
public ArrayList<Reportes> CargarReportes();
public ArrayList<Medicos> FiltrarReporteMedicos(String filtrarTipoEstado, String FechaInicial, String FechaFinal);
public ArrayList<Pacientes> FiltrarReportePacientes(String filtrarTipoEstado, String FechaInicial, String FechaFinal);
public ArrayList<Pacientes> DevolverResultSetPacientes(ResultSet rs, ArrayList<Pacientes> ls);
public ArrayList<Medicos> DevolverResultSetMedicos(ResultSet rs, ArrayList<Medicos> ls);
}
