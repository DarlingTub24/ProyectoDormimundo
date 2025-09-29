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

import objetos.Usuarios;

public class UIUsuario_modificar extends JFrame{
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_modificar;
	private JLabel titulo,autor;
	private JComboBox<Usuarios> comboBoxUsuarios;
	private JTextField idUsuario,nombreUsuario;
	private Principal principal;

			public UIUsuario_modificar(Principal principal) {
			this.principal = principal;
			setTitle("Modificar Usuario");
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
		comboBoxUsuarios = new JComboBox<>();
		idUsuario = new JTextField(10);
        idUsuario.setEditable(false);
		nombreUsuario = new JTextField(10);
		for (Usuarios usuario : Principal.listaUsuarios) {
            comboBoxUsuarios.addItem(usuario);
		}
		comboBoxUsuarios.setSelectedIndex(-1);
		comboBoxUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxUsuarios.getSelectedItem()==null) {
					idUsuario.setText("");
					nombreUsuario.setText("");
				} else {
					idUsuario.setText(((Usuarios)comboBoxUsuarios.getSelectedItem()).getId());
					nombreUsuario.setText(((Usuarios)comboBoxUsuarios.getSelectedItem()).getNombre());
				}
			}
		});
		panel.add(comboBoxUsuarios,gbc);
		//PARTE DEL Titulo:
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.fill= GridBagConstraints.NONE;
		titulo = new JLabel("Usuario Modificado:");
		titulo.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(titulo,gbc);
		
		//Insertar JTextField id
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		idUsuario.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(idUsuario,gbc);
		
		//PARTE DEL Autor:
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill= GridBagConstraints.NONE;
		autor = new JLabel("Usuario Modificado:");
		autor.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(autor,gbc);
		//Insertar JTextField Autor
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		nombreUsuario.setFont(new Font("Arial", Font.PLAIN, 24));
		panel.add(nombreUsuario,gbc);
		
		//PARTE DEL Boton Modificar
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill= GridBagConstraints.HORIZONTAL;
		boton_modificar = new JButton("MODIFICAR");
		boton_modificar.setFont(new Font("Arial", Font.BOLD, 24));
		boton_modificar.addActionListener(e -> modificarArrayList(idUsuario.getText(),nombreUsuario.getText(),(Usuarios)comboBoxUsuarios.getSelectedItem()));
		panel.add(boton_modificar,gbc);
		
		//Aniadir panel
		panel.setBackground(Color.pink);
		panel.setPreferredSize(new Dimension(400, 300));
		add(panel);
		
	}
	public void modificarArrayList(String idUsuario,String nombreUsuario, Usuarios usuarioModificar) {
		String idTtrim=idUsuario.trim();
		String nombreTrim= nombreUsuario.trim();
		
		for (int i = 0; i < Principal.listaUsuarios.size(); i++) {
			if (Principal.listaUsuarios.get(i).equals(usuarioModificar)) {
				
				if (!idTtrim.isEmpty() && !nombreTrim.isEmpty()) {
					if (Principal.listaUsuarios.get(i).getId().equals(idTtrim) &&
						Principal.listaUsuarios.get(i).getNombre().equals(nombreTrim)) {
						JOptionPane.showMessageDialog(null, "No se ha modificado nada", null, JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,"Se modifico el Usuario","Proceso Completado",JOptionPane.INFORMATION_MESSAGE);
						Principal.listaUsuarios.get(i).setNombre(nombreTrim);
						principal.actualizarTablaUsuarios();
						this.dispose();
					}
				} else if (idTtrim.isEmpty() || nombreTrim.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se deben dejar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
}

