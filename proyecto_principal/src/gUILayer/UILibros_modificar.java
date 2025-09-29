package gUILayer;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Libros;

public class UILibros_modificar extends JFrame{
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_modificar;
	private JLabel titulo,autor;
	private JComboBox<Libros> comboBoxLibros;
	private JTextField txt_titulo,txt_autor;
	private Principal principal;

			public UILibros_modificar(Principal principal) {
			this.principal = principal;
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
		
		//ComboBox Libro
		gbc.gridx = 0;
		gbc.gridy = 0;
		comboBoxLibros = new JComboBox<>();
		txt_titulo = new JTextField(10);
		txt_autor = new JTextField(10);
		for (Libros libro : Principal.listaLibros) {
			if (libro.isPrestado()==false) {
				comboBoxLibros.addItem(libro);
			}
		}
		comboBoxLibros.setSelectedIndex(-1);
		comboBoxLibros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxLibros.getSelectedItem()==null) {
					txt_titulo.setText("");
					txt_autor.setText("");
				} else {
					txt_titulo.setText(((Libros)comboBoxLibros.getSelectedItem()).getTitulo());
					txt_autor.setText(((Libros)comboBoxLibros.getSelectedItem()).getAutor());
				}
			}
		});
		panel.add(comboBoxLibros,gbc);
		//PARTE DEL Titulo:
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.fill= GridBagConstraints.NONE;
		titulo = new JLabel("Titulo Modificado:");
		titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(titulo,gbc);
		
		//Insertar JTextField Titulo
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		txt_titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(txt_titulo,gbc);
		
		//PARTE DEL Autor:
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill= GridBagConstraints.NONE;
		autor = new JLabel("Autor Modificado:");
		autor.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(autor,gbc);
		//Insertar JTextField Autor
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		txt_autor.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(txt_autor,gbc);
		
		//PARTE DEL Boton Modificar
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		boton_modificar = new JButton("MODIFICAR");
		boton_modificar.setFont(new Font("Arial", Font.BOLD, 24));
		boton_modificar.addActionListener(e -> modificarArrayList(txt_titulo.getText(),txt_autor.getText(),(Libros)comboBoxLibros.getSelectedItem()));
		panel.add(boton_modificar,gbc);
		
		//Aniadir panel
		panel.setBackground(Color.pink);
		panel.setPreferredSize(new Dimension(400, 300));
		add(panel);
		
	}
	public void modificarArrayList(String txt_titulo,String txt_autor, Libros libroModificar) {
		String tituloTrim=txt_titulo.trim();
		String autorTrim= txt_autor.trim();
		
		for (int i = 0; i < Principal.listaLibros.size(); i++) {
			if (Principal.listaLibros.get(i).equals(libroModificar)) {
				
				if (!tituloTrim.isEmpty() && !autorTrim.isEmpty()) {
					if (Principal.listaLibros.get(i).getTitulo().equals(tituloTrim) &&
						Principal.listaLibros.get(i).getAutor().equals(autorTrim)) {
						JOptionPane.showMessageDialog(null, "No se ha modificado nada", null, JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,"Se modifico el Libro","Proceso Completado",JOptionPane.INFORMATION_MESSAGE);
						Principal.listaLibros.get(i).setTitulo(tituloTrim);
						Principal.listaLibros.get(i).setAutor(autorTrim);
						principal.actualizarTablaLibros();
						this.dispose();
					}
				} else if (tituloTrim.isEmpty() || autorTrim.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se deben dejar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		}
		
	}
}

