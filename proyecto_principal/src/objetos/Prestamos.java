package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamos implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private Libros libro;
	private String fecha_Prestamo;
	private String fecha_Vencimiento;
	private String fecha_Devolucion;
	private transient DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		public Prestamos(Usuarios usuario, Libros libro, String fecha_Vencimiento) {
			super();
			this.usuario = usuario;
			formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.libro = libro;
			this.fecha_Prestamo = LocalDate.now().format(formato);
			this.setFecha_Vencimiento(fecha_Vencimiento);
			this.fecha_Devolucion = null;
			this.libro.setPrestado(true);
		}
		public void devolver() {
			formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			/*
			if (!LocalDate.now().isAfter(LocalDate.parse(fecha_Vencimiento,formato))) {
				this.fecha_Devolucion=LocalDate.now().format(formato);
				this.libro.setPrestado(false);
			} 
		*/
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
		public String getNombreUsuario(){
			return usuario.getNombre();
		}
		public void setUsuario(Usuarios usuario) {
			this.usuario = usuario;
		}
		public Libros getLibro() {
			return libro;
		}
		public String getInformacionLibro(){
			return libro.toString();
		}
		public void setLibro(Libros libro) {
			this.libro = libro;
		}
		public String getFecha_Vencimiento() {
			return fecha_Vencimiento;
		}
		public void setFecha_Vencimiento(String fecha_Vencimiento) {
			this.fecha_Vencimiento = fecha_Vencimiento;
		}
		public boolean isVencido() {
			DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			System.out.println("hola "+ fecha_Vencimiento);
			if (fecha_Vencimiento != null) {
				if (LocalDate.now().isAfter(LocalDate.parse(fecha_Vencimiento.trim(),formato2))) {
					System.out.println("no Esta vencido");
					return true;
				}
			}
			return false;
		}
		
		
		
}
