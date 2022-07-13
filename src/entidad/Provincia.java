package entidad;

public class Provincia {
	private int idProv;
	private String nombreProv;
	private int idPaisProv;
	
	public Provincia()
	{
		this.idProv = -1;
		this.nombreProv = "sin nombre";
		this.idPaisProv = -1;
	}
	public Provincia(int idP, String nom, int idPP)
	{
		this.idProv = idP;
		this.nombreProv = nom;
		this.idPaisProv = idPP;
	}
	///get y set
	public int getIdProv() {
		return idProv;
	}
	public void setIdProv(int idProv) {
		this.idProv = idProv;
	}
	public String getNombreProv() {
		return nombreProv;
	}
	public void setNombreProv(String nombreProv) {
		this.nombreProv = nombreProv;
	}
	public int getIdPaisProv() {
		return idPaisProv;
	}
	public void setIdPaisProv(int idPaisProv) {
		this.idPaisProv = idPaisProv;
	}
	//to string
	@Override
	public String toString() {
		return "Provincia [idProv=" + idProv + ", nombreProv=" + nombreProv + ", idPaisProv=" + idPaisProv + "]";
	}
	
}
