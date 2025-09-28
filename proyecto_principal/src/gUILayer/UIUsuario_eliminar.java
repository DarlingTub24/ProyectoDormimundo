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

import objetos.Usuarios;

public class UIUsuario_eliminar extends JFrame {
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_Si,boton_No;
	private JLabel aviso;	
	private JComboBox<Usuarios> comboBoxUsuarios;
	Principal principal;

	public UIUsuario_eliminar(Principal principal) {
		this.principal = principal;
		setTitle("Eliminar Usuario");
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
		aviso=new JLabel("Seleccione el Usuario a eliminar:");
		aviso.setFont(new Font("Arial", Font.PLAIN, 24));
		aviso.setForeground(Color.RED);
		panel.add(aviso,gbc);
		//Parte del ComboBox
		gbc.gridx = 0;
		gbc.gridy = 1;
		comboBoxUsuarios = new JComboBox<>();
		for (Usuarios usuario : Principal.listaUsuarios) {
			comboBoxUsuarios.addItem(usuario);
		}
		comboBoxUsuarios.setSelectedIndex(-1);
		panel.add(comboBoxUsuarios,gbc);
		
		//PARTE DEL Boton ELIMINAR
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.weightx = 0.5; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		boton_Si = new JButton("ELIMINAR");
		boton_Si.setFont(new Font("Arial", Font.BOLD, 24));
		boton_Si.addActionListener(e -> eliminarUsuario((Usuarios)comboBoxUsuarios.getSelectedItem()));
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
public void eliminarUsuario(Usuarios usuario) {
		if (usuario != null) {
			for (int i = 0; i < Principal.listaUsuarios.size(); i++) {
				if (Principal.listaUsuarios.get(i).equals(usuario)) {
					Principal.listaUsuarios.remove(i);
				}
			}
			principal.actualizarTablaUsuarios();
			this.dispose();
			JOptionPane.showMessageDialog(null, "Se ha ELIMINADO el Usuario",null,JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione un Usuario",null,JOptionPane.WARNING_MESSAGE);
		}
	}
}
