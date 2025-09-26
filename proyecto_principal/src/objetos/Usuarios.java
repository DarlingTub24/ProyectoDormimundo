package objetos;

public class Usuarios implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String id;
	
	public Usuarios(String nombre, String id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return id + " - " + nombre;
	}
}
