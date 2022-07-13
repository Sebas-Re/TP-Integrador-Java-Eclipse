package dao;

import java.util.ArrayList;

import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public interface DaoNacionalidad {
	public ArrayList<Pais> traerPaises();
	public ArrayList<Provincia> traerProvincias();
	public ArrayList<Localidad> traerLocalidades();
}
