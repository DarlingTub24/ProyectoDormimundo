package gUILayer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UILibros_eliminar extends JFrame {
	private JPanel panel;
	private GridBagLayout layout = new GridBagLayout();
	private JButton boton_Si,boton_No;
	private int indiceLibro;
	public UILibros_eliminar(int indice) {
		indiceLibro=indice;
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
		panel.add(new JLabel("Esta seguro que desea eliminarlo?"),gbc);
		
		//PARTE DEL Boton Si
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		gbc.weightx = 0.5; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		boton_Si = new JButton("SI");
		boton_Si.addActionListener(e -> eliminarLibro(indiceLibro));
		panel.add(boton_Si,gbc);
		
		//PARTE DEL Boton No
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		gbc.weightx = 0.5; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		boton_No = new JButton("NO");
		boton_No.addActionListener(e -> this.dispose());
		panel.add(boton_No,gbc);
		
	}
	public void eliminarLibro(int indice) {
		UILibros.listaLibros.remove(indice);
		this.dispose();
	}
}
