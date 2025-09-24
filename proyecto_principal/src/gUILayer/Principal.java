package gUILayer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;


public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuprincipal;
	private JMenu jmarchivo, jmherramientas, jmsubmenu;
	private JMenuItem jmiabrir, jmiguardar, jmisalir, jmicolor, jmiop1, jmiop2;
	private JToolBar jtbsecciones;
	private JButton btnlibros, btnusuarios, btnsalir;
	private JButton jblibros, jbusuarios;

	public Principal(String title) {
		super(title);
        setSize(800, 600);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		initcomponents();
		
	}

	private void initcomponents(){
        menuprincipal = new JMenuBar();
		contenidoToolBar();
        
        jmarchivo = new JMenu("Archivo");
        jmarchivo.setMnemonic('A');
        jmiabrir = new JMenuItem("Abrir", 'A');
        jmiabrir.addActionListener(this);
        jmarchivo.add(jmiabrir);
        jmiguardar = new JMenuItem("Guardar", 'G');
        jmiguardar.addActionListener(this);
        jmarchivo.add(jmiguardar);
        jmarchivo.addSeparator();
        jmisalir = new JMenuItem("Salir", 'S');
        jmisalir.addActionListener(this);
        jmarchivo.add(jmisalir);
		
        menuprincipal.add(jmarchivo);
        setJMenuBar(menuprincipal);
    }

	private void contenidoToolBar() {
        jtbsecciones = new JToolBar();
        btnlibros = new JButton();
        btnlibros.addActionListener(this);
        btnlibros.setToolTipText("Abri22r");
        btnlibros.setBorderPainted(false);
        btnlibros.setBackground(Color.LIGHT_GRAY);
        btnlibros.setIcon(new ImageIcon("imagenes/icons8-open-24.png"));
        jtbsecciones.add(btnlibros);
        jtbsecciones.addSeparator();

        btnusuarios = new JButton();
        btnusuarios.addActionListener(this);
        btnusuarios.setToolTipText("Guardar");
        btnusuarios.setBorderPainted(false);
        btnusuarios.setBackground(Color.LIGHT_GRAY);
        btnusuarios.setIcon(new ImageIcon("imagenes/icons8-save-50.png"));
        jtbsecciones.add(btnusuarios);

        jtbsecciones.addSeparator();
        btnusuarios = new JButton();
        btnusuarios.addActionListener(this);
        btnusuarios.setToolTipText("Color");
        btnusuarios.setBorderPainted(false);
        btnusuarios.setBackground(Color.LIGHT_GRAY);
        btnusuarios.setIcon(new ImageIcon("imagenes/icons8-color-30.png"));
        jtbsecciones.add(btnusuarios);

        jtbsecciones.add(new JSeparator(JSeparator.VERTICAL));
        jtbsecciones.add(Box.createHorizontalGlue());
        btnsalir = new JButton();
        btnsalir.addActionListener(this);
        btnsalir.setToolTipText("Salir de la aplicacion");
        btnsalir.setBorderPainted(false);
        btnsalir.setBackground(Color.LIGHT_GRAY);
        btnsalir.setIcon(new ImageIcon("imagenes/icons8-exit-50.png"));
        jtbsecciones.add(btnsalir);

		this.add(jtbsecciones, "North");
		JButton btn1 = new JButton("Vista 1212");
        JButton btn2 = new JButton("Vista 2");

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmiabrir) {
			JFileChooser jfcabrir = new JFileChooser();
            jfcabrir.setDialogTitle("Abrir un archivo");
            FileNameExtensionFilter fndat = new FileNameExtensionFilter("Archivos de tipo dat","dat");
            jfcabrir.addChoosableFileFilter(fndat);
            jfcabrir.setFileFilter(fndat);
            if(jfcabrir.showOpenDialog(this) != JFileChooser.CANCEL_OPTION){
                File archivo = jfcabrir.getSelectedFile();
                System.out.println(archivo.getName());
			}
		}
		//else if (e.getSource() == )
	}

}


