package entidad;

import java.sql.Time;

public class Reportes 
{
	String Dias_Mas_Concurridos;
	String Frec_Dias_Mas_Concurridos;
	Time Horarios_Mas_Concurridos;
	String Frec_Horarios_Mas_Concurridos;
	
	
	
	
	public String getDias_Mas_Concurridos() {
		return Dias_Mas_Concurridos;
	}
	public void setDias_Mas_Concurridos(String dias_Mas_Concurridos) {
		Dias_Mas_Concurridos = dias_Mas_Concurridos;
	}
	public String getFrec_Dias_Mas_Concurridos() {
		return Frec_Dias_Mas_Concurridos;
	}
	public void setFrec_Dias_Mas_Concurridos(String frec_Dias_Mas_Concurridos) {
		Frec_Dias_Mas_Concurridos = frec_Dias_Mas_Concurridos;
	}
	public Time getHorarios_Mas_Concurridos() {
		return Horarios_Mas_Concurridos;
	}
	public void setHorarios_Mas_Concurridos(Time horarios_Mas_Concurridos) {
		Horarios_Mas_Concurridos = horarios_Mas_Concurridos;
	}
	public String getFrec_Horarios_Mas_Concurridos() {
		return Frec_Horarios_Mas_Concurridos;
	}
	public void setFrec_Horarios_Mas_Concurridos(String frec_Horarios_Mas_Concurridos) {
		Frec_Horarios_Mas_Concurridos = frec_Horarios_Mas_Concurridos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dias_Mas_Concurridos == null) ? 0 : Dias_Mas_Concurridos.hashCode());
		result = prime * result + ((Frec_Dias_Mas_Concurridos == null) ? 0 : Frec_Dias_Mas_Concurridos.hashCode());
		result = prime * result
				+ ((Frec_Horarios_Mas_Concurridos == null) ? 0 : Frec_Horarios_Mas_Concurridos.hashCode());
		result = prime * result + ((Horarios_Mas_Concurridos == null) ? 0 : Horarios_Mas_Concurridos.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reportes other = (Reportes) obj;
		if (Dias_Mas_Concurridos == null) {
			if (other.Dias_Mas_Concurridos != null)
				return false;
		} else if (!Dias_Mas_Concurridos.equals(other.Dias_Mas_Concurridos))
			return false;
		if (Frec_Dias_Mas_Concurridos == null) {
			if (other.Frec_Dias_Mas_Concurridos != null)
				return false;
		} else if (!Frec_Dias_Mas_Concurridos.equals(other.Frec_Dias_Mas_Concurridos))
			return false;
		if (Frec_Horarios_Mas_Concurridos == null) {
			if (other.Frec_Horarios_Mas_Concurridos != null)
				return false;
		} else if (!Frec_Horarios_Mas_Concurridos.equals(other.Frec_Horarios_Mas_Concurridos))
			return false;
		if (Horarios_Mas_Concurridos == null) {
			if (other.Horarios_Mas_Concurridos != null)
				return false;
		} else if (!Horarios_Mas_Concurridos.equals(other.Horarios_Mas_Concurridos))
			return false;
		return true;
	}
	

	
	
}
