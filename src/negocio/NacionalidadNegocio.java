package negocio;

import java.util.ArrayList;

import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public interface NacionalidadNegocio {
	public ArrayList<Pais> traerPaises();
	public ArrayList<Provincia> traerProvincias();
	public ArrayList<Localidad> traerLocalidades();
}
