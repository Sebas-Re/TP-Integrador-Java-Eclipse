package entidad;

public class Localidad {
	private int idLoc;
	private int idProvLoc;
	private String nombreLoc;
	
	public Localidad()
	{
		
	}
	public Localidad(int id, int idProv, String nom)
	{
		this.idLoc = id;
		this.idProvLoc = idProv;
		this.nombreLoc = nom;
	}
	/// get y set
	public int getIdLoc() {
		return idLoc;
	}
	public void setIdLoc(int idLoc) {
		this.idLoc = idLoc;
	}
	public int getIdProvLoc() {
		return idProvLoc;
	}
	public void setIdProvLoc(int idProvLoc) {
		this.idProvLoc = idProvLoc;
	}
	public String getNombreLoc() {
		return nombreLoc;
	}
	public void setNombreLoc(String nombreLoc) {
		this.nombreLoc = nombreLoc;
	}
	///to string
	@Override
	public String toString() {
		return "Localidad [idLoc=" + idLoc + ", idProvLoc=" + idProvLoc + ", nombreLoc=" + nombreLoc + "]";
	}
	
}
