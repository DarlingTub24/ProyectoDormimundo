package gUILayer;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Libros;

public class UILibros_modificar extends JFrame{
		private JPanel panel;
		private GridBagLayout layout = new GridBagLayout();
		private JButton boton_modificar;
		private int indiceLibro;
	
		public UILibros_modificar(int indice) {
			indiceLibro=indice;
			setTitle("Modificar Libro");
			setSize(700,450);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(new GridBagLayout());
			InitComponents();
			getContentPane().add(panel);
		}
		private void InitComponents() {
			panel = new JPanel(layout);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			
			//PARTE DEL Titulo:
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			panel.add(new JLabel("Titulo:"),gbc);
			//Insertar JTextField
			JTextField txt_titulo = new JTextField(10);
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			panel.add(txt_titulo,gbc);
			
			//PARTE DEL Autor:
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			panel.add(new JLabel("Autor:"),gbc);
			//Insertar JTextField
			JTextField txt_autor = new JTextField(10);
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			panel.add(txt_autor,gbc);
			
			//PARTE DEL Boton Agregar
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth=2;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			boton_modificar = new JButton("AGREGAR");
			boton_modificar.addActionListener(e -> modificarArrayList(txt_titulo.getText(),txt_autor.getText()));
			panel.add(boton_modificar,gbc);
			
			//Aniadir panel
			panel.setBackground(Color.pink);
			add(panel);
			
		}
		public void modificarArrayList(String txt_titulo,String txt_autor) {
			UILibros.listaLibros.get(indiceLibro).setTitulo(txt_titulo);
			UILibros.listaLibros.get(indiceLibro).setAutor(txt_autor);
			this.dispose();
		}
}
