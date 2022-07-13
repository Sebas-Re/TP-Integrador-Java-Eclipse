package entidad;

public class Pais {
	private int id;
	private String nombre;
	
	public Pais()
	{
		this.id = -1;
		this.nombre = "sin nombre";
	}
	public Pais(int ID, String nom)
	{
		this.id = ID;
		this.nombre = nom;
	}
	
	/// GET Y SET
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/// TO STRING
	
	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
