package gUILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import objetos.Libros;

public class UILibros_eliminar extends JFrame {
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_Si,boton_No;
	private JLabel aviso;
	private JComboBox<Libros> comboBoxLibros;
	Principal principal;

	public UILibros_eliminar(Principal principal) {
		this.principal = principal;
		setTitle("Eliminar Libro");
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
		aviso=new JLabel("Seleccione el libro a eliminar:");
		aviso.setFont(new Font("Arial", Font.PLAIN, 24));
		aviso.setForeground(Color.RED);
		panel.add(aviso,gbc);
		//Parte del ComboBox
		gbc.gridx = 0;
		gbc.gridy = 1;
		comboBoxLibros = new JComboBox<>();

		for (Libros libro : Principal.listaLibros) {
			if (libro.isPrestado()==false) {
				comboBoxLibros.addItem(libro);
			}
		}
		comboBoxLibros.setSelectedIndex(-1);
		panel.add(comboBoxLibros,gbc);
		
		//PARTE DEL Boton ELIMINAR
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.weightx = 0.5; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		boton_Si = new JButton("ELIMINAR");
		boton_Si.setFont(new Font("Arial", Font.BOLD, 24));
		boton_Si.addActionListener(e -> eliminarLibro((Libros)comboBoxLibros.getSelectedItem()));
		panel.add(boton_Si,gbc);
		
		//PARTE DEL Boton CANCELAR
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
	public void eliminarLibro(Libros libro) {
		if (libro!=null) {
			for (int i = 0; i < Principal.listaLibros.size(); i++) {
				if (Principal.listaLibros.get(i).equals(libro)) {
					Principal.listaLibros.remove(i);
				}
			}
			this.dispose();
			JOptionPane.showMessageDialog(null, "Se ha ELIMINADO el libro",null,JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione un Libro",null,JOptionPane.WARNING_MESSAGE);

		}
		
	}
}
