package gUILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.Libros;
import objetos.Usuarios;
import objetos.Prestamos;
import objetos.Usuarios;

public class UIPrestamos_devolver extends JFrame{
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_Si,boton_No;
	private int indiceLibro;
	private JLabel aviso;
	private JComboBox<String> comboBoxPrestamos;
	private Principal principal;
	
	public UIPrestamos_devolver(Principal principal) {
		this.principal = principal;
		setTitle("Devolver Libro");
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
		
		//PARTE DEL aviso:
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		aviso=new JLabel("Seleccion el Libro a devolver:");
		aviso.setFont(new Font("Arial", Font.PLAIN, 24));
		aviso.setForeground(Color.RED);
		panel.add(aviso,gbc);
		//ComboBox
		gbc.gridx = 0;
		gbc.gridy = 1;
		comboBoxPrestamos = new JComboBox<>();
		StringBuilder prestamoCadena = new StringBuilder();
		for (Prestamos prestamo : Principal.listaPrestamos) {
			prestamoCadena.setLength(0);
			if (prestamo.getLibro().isPrestado()) {
				prestamoCadena.append(prestamo.getInformacionLibro());
				prestamoCadena.append(" - ");
				prestamoCadena.append(prestamo.getNombreUsuario());
				comboBoxPrestamos.addItem(prestamoCadena.toString());
			}
		}
		comboBoxPrestamos.setSelectedIndex(-1);
		panel.add(comboBoxPrestamos,gbc);
		//PARTE DEL Boton Devolver
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.weightx = 0.5; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		boton_Si = new JButton("DEVOLVER");
		boton_Si.setFont(new Font("Arial", Font.BOLD, 24));
		boton_Si.addActionListener(e -> devolverLibro((String) comboBoxPrestamos.getSelectedItem()));
		panel.add(boton_Si,gbc);
		
		//PARTE DEL Boton No
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.weightx = 0.5; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		boton_No = new JButton("CANCELAR");
		boton_No.setFont(new Font("Arial", Font.PLAIN, 24));
		boton_No.addActionListener(e -> this.dispose());
		panel.add(boton_No,gbc);
	}

	public void devolverLibro(String libroDevolver) {
		//Aqui nomas se cambia por el nombre correcto de los Arraylist y de que clase vienen
		String [] partes = libroDevolver.split(" - ");
		//parte 1 = libro
		//parte 2 = autor
		//parte 3 = usuario
		
		for (int i = 0; i < Principal.listaPrestamos.size(); i++) {
			if (Principal.listaPrestamos.get(i).getLibro().getTitulo().equals(partes[0])&&
				Principal.listaPrestamos.get(i).getLibro().getAutor().equals(partes[1])&&
				Principal.listaPrestamos.get(i).getUsuario().getNombre().equals(partes[2])) {
				Principal.listaPrestamos.get(i).devolver();
				Principal.listaPrestamos.get(i).getUsuario();
				Principal.listaPrestamos.remove(i);
			}
		}
		principal.actualizarTablaPrestamos();
		this.dispose();
	}
}
