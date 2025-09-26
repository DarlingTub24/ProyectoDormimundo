package objetos;

public class Libros implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String autor;
	boolean prestado = false;
	public Libros(String nombre, String autor,boolean prestado) {
		this.nombre=nombre;
		this.autor=autor;
		this.prestado=prestado;
	}
	public Libros(String nombre, String autor) {
		this.nombre=nombre;
		this.autor=autor;
	}
	public String getTitulo() {
		return nombre;
	}
	public void setTitulo(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public boolean isPrestado() {
		return prestado;
	}
	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}	
	public String toString() {
		return (nombre + " - " + autor);
	}
	
}
