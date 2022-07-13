package negocioImpl;

import java.util.ArrayList;

import daolmpl.DaoNacionalidadImpl;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {
	DaoNacionalidadImpl nacDao = new DaoNacionalidadImpl();
	
	@Override
	public ArrayList<Pais> traerPaises() {
		return nacDao.traerPaises();
	}

	@Override
	public ArrayList<Provincia> traerProvincias() {
		return nacDao.traerProvincias();
	}

	@Override
	public ArrayList<Localidad> traerLocalidades() {
		return nacDao.traerLocalidades();
	}

}
