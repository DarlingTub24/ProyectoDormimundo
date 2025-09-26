package gUILayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Libros;

public class UILibros_agregar extends JFrame{
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_agregar;
	private JLabel titulo,autor;
	private Principal principal;

	public UILibros_agregar(Principal principal) {
		this.principal = principal;
		setTitle("Agregar Libros");
		setSize(700,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		InitComponents();
		getContentPane().add(panel);
	}
<<<<<<< HEAD
	private void InitComponents() {
=======
	 private void InitComponents() {

>>>>>>> c3aa97ac307968fd667bf3a3eff24f80b3e85e0b
		panel = new JPanel(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

			//PARTE DEL Titulo:
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx=1.0;
			gbc.weighty=1.0;
			gbc.fill= GridBagConstraints.NONE;
			titulo = new JLabel("Titulo:");
			titulo.setFont(new Font("Arial", Font.PLAIN, 24));
			panel.add(titulo,gbc);
			//Insertar JTextField
			JTextField txt_titulo = new JTextField(10);
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			txt_titulo.setFont(new Font("Arial", Font.PLAIN, 24));
			panel.add(txt_titulo,gbc);
			
			//PARTE DEL Autor:
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill= GridBagConstraints.NONE;
			autor = new JLabel("Autor:");
			autor.setFont(new Font("Arial", Font.PLAIN, 24));
			panel.add(autor,gbc);
			//Insertar JTextField
			JTextField txt_autor = new JTextField(10);
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			txt_autor.setFont(new Font("Arial", Font.PLAIN, 24));
			panel.add(txt_autor,gbc);
			
			//PARTE DEL Boton Agregar
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth=2;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			boton_agregar = new JButton("AGREGAR");
			boton_agregar.setFont(new Font("Arial", Font.BOLD, 24));
			boton_agregar.addActionListener(e -> agregarArrayList(txt_titulo.getText(),txt_autor.getText()));
			panel.add(boton_agregar,gbc);
			
			//Aniadir panel
			panel.setBackground(Color.pink);
			panel.setPreferredSize(new Dimension(400, 300));
			add(panel);
	}
	public void agregarArrayList(String txt_titulo,String txt_autor) {
		String tituloTrim=txt_titulo.trim();
		String autorTrim= txt_autor.trim();
<<<<<<< HEAD
		ArrayList <String> errores = new ArrayList<>();
=======
		ArrayList <String> errores= new ArrayList<>();
>>>>>>> c3aa97ac307968fd667bf3a3eff24f80b3e85e0b
		String [] listaErrores;
		
		
		if (!tituloTrim.isEmpty() && !autorTrim.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Se agrego el Libro","Proceso Completado",JOptionPane.INFORMATION_MESSAGE);
<<<<<<< HEAD
			Principal.listaLibros.add(new Libros(tituloTrim,autorTrim));
			principal.actualizarTablaLibros();
=======
			UILibros.listaLibros.add(new Libros(tituloTrim,autorTrim));
>>>>>>> c3aa97ac307968fd667bf3a3eff24f80b3e85e0b
			this.dispose();
		}
		if (tituloTrim.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se deben dejar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (autorTrim.isEmpty()) {
			
		}
		
	}
}
