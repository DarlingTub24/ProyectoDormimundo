package gUILayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Usuarios;

public class UIUsuario_Agregar extends JFrame {
    private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_agregar;
	private JLabel titulo,autor;
	private Principal principal;

	public UIUsuario_Agregar(Principal principal) {
		this.principal = principal;
		setTitle("Agregar Usuario");
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
			gbc.weightx=1.0;
			gbc.weighty=1.0;
			gbc.fill= GridBagConstraints.NONE;
			titulo = new JLabel("Nombre de usuario:");
			titulo.setFont(new Font("Arial", Font.PLAIN, 24));
			panel.add(titulo,gbc);
			//Insertar JTextField
			JTextField txt_titulo = new JTextField(10);
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			txt_titulo.setFont(new Font("Arial", Font.PLAIN, 24));
			panel.add(txt_titulo,gbc);
			
			//PARTE DEL Boton Agregar
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth=2;
			gbc.fill= GridBagConstraints.HORIZONTAL;
			boton_agregar = new JButton("AGREGAR");
			boton_agregar.setFont(new Font("Arial", Font.BOLD, 24));
            String numUsuario = principal.listaUsuarios.size() + 1 + "";
			boton_agregar.addActionListener(e -> agregarArrayList(txt_titulo.getText()));
			panel.add(boton_agregar,gbc);
			
			//Aniadir panel
			panel.setBackground(Color.pink);
			panel.setPreferredSize(new Dimension(400, 300));
			add(panel);
	}
	public void agregarArrayList(String txt_autor) {
        String numUsuario = principal.listaUsuarios.size() + 1 + "";
		Principal.listaUsuarios.add(new Usuarios(txt_autor, numUsuario));
		principal.actualizarTablaUsuarios();
		this.dispose();
	}
}
