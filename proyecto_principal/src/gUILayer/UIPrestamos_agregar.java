package gUILayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Principal principal;
	
	public UIPrestamos_agregar(Principal principal) {
		this.principal = principal;
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
		comboBoxUsuarios = new JComboBox<>(Principal.listaUsuarios.toArray(new Usuarios[0]));
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
		for (Libros libro : Principal.listaLibros) {
			if (libro.isPrestado()==false) {
				System.out.println("hola manolo");
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
		String fVencimientoTrim = fVencimiento.trim();
		ArrayList<String> errores = new ArrayList<>();
		boolean fechaValida = validaFecha(fVencimientoTrim);
		String[] listaErrores;
		if (usuario==null ) {
			errores.add("•Seleccione un Usuario de la lista");
		}
		if (libro==null) {
			errores.add("•Seleccione un Libro de la lista");
		}
		if (fVencimientoTrim.isEmpty()) {
			errores.add("•Ingrese una fecha de Vencimiento");
		} else if (!fechaValida) {
			errores.add("•La fecha esta erronea");
		}
		if (errores.isEmpty()) {
			Principal.listaPrestamos.add(new Prestamos(usuario, libro, fVencimiento));
			principal.actualizarTablaPrestamos();
			this.dispose();
		} else {
			listaErrores = errores.toArray(new String[0]);
			JOptionPane.showMessageDialog(null, listaErrores,"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean validaFecha(String fVencimientoTrim) {
		Boolean flag=false; 
		if (fVencimientoTrim.matches("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}$")) {
			flag=true;
		}
		return flag;
	}
}
