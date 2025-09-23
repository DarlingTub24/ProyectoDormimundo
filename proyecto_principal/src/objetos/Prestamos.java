package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamos {
		private Usuarios usuario;
		private Libros libro;
		private String fecha_Prestamo;
		private String fecha_Devolucion;
		private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		public Prestamos(Usuarios usuario, Libros libro) {
			super();
			this.usuario = usuario;
			this.libro = libro;
			this.fecha_Prestamo = LocalDate.now().format(formato);
			this.fecha_Devolucion = null;
			this.libro.setPrestado(true);
		}
		public void devolver() {
			this.fecha_Devolucion=LocalDate.now().format(formato);
			this.libro.setPrestado(false);
		}
		public String getFecha_Prestamo() {
			return fecha_Prestamo;
		}
		public void setFecha_Prestamo(String fecha_Prestamo) {
			this.fecha_Prestamo = fecha_Prestamo;
		}
		public String getFecha_Devolucion() {
			return fecha_Devolucion;
		}
		public void setFecha_Devolucion(String fecha_Devolucion) {
			this.fecha_Devolucion = fecha_Devolucion;
		}
		public Usuarios getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuarios usuario) {
			this.usuario = usuario;
		}
		public Libros getLibro() {
			return libro;
		}
		public void setLibro(Libros libro) {
			this.libro = libro;
		}
		
		
		
}
