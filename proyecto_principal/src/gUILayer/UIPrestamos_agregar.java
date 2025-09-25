package gUILayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Libros;
import objetos.Prestamos;
import objetos.Usuarios;

public class UIPrestamos_agregar extends JFrame{
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_agregar;
	private JLabel usuario,libro,vencimiento;
	private JComboBox<Libros> comboBoxLibros;
	private JComboBox<Usuarios> comboBoxUsuarios;
	
	public UIPrestamos_agregar() {
		setTitle("Agregar Prestamos");
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
		
		//JLabel Usuario
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill= GridBagConstraints.NONE;
		usuario= new JLabel("Seleccione Usuario:");
		usuario.setFont(new Font("Arial",Font.PLAIN,24));
		panel.add(usuario,gbc);
		
		//Insertar ComboBoxUsuarios
		gbc.gridx=1;
		gbc.gridy=0;
		comboBoxUsuarios = new JComboBox<>(UIPrestamos.listaUsuarios.toArray(new Usuarios[0]));
		comboBoxUsuarios.setSelectedIndex(-1);
		panel.add(comboBoxUsuarios,gbc);
		
		//JLabel Libros
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.fill= GridBagConstraints.NONE;
		libro= new JLabel("Seleccione Libro:");
		libro.setFont(new Font("Arial",Font.PLAIN,24));
		panel.add(libro,gbc);
		
		//Insertar ComboBoxLibros
		gbc.gridx=1;
		gbc.gridy=1;
		comboBoxLibros = new JComboBox<>();
		for (Libros libro : UIPrestamos.listaLibros) {
			if (libro.isPrestado()==false) {
				comboBoxLibros.addItem(libro);
			}
		}
		comboBoxLibros.setSelectedIndex(-1);
		panel.add(comboBoxLibros,gbc);
		//JLabel Fecha Vencimiento
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.fill= GridBagConstraints.NONE;
		vencimiento= new JLabel("Fecha Vencimiento:");
		vencimiento.setFont(new Font("Arial",Font.PLAIN,24));
		panel.add(vencimiento,gbc);
		
		//JTextField Vencimiento
		JTextField txt_vencimiento = new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		txt_vencimiento.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(txt_vencimiento,gbc);
		
		//PARTE DEL Boton Agregar
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth=2;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		boton_agregar = new JButton("AGREGAR PRESTAMO");
		boton_agregar.setFont(new Font("Arial", Font.BOLD, 24));
		boton_agregar.addActionListener(e -> agregarArrayList((Usuarios)comboBoxUsuarios.getSelectedItem(),
				(Libros)comboBoxLibros.getSelectedItem(),txt_vencimiento.getText()));
		panel.add(boton_agregar,gbc);
		
		//Aniadir panel
		add(panel);
		
	}

	public void agregarArrayList(Usuarios usuario, Libros libro, String fVencimiento) {
		 UIPrestamos.listaPrestamos.add(new Prestamos(usuario, libro, fVencimiento));
		 this.dispose();
	}
}
