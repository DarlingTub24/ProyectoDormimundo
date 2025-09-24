package gUILayer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        //archivo
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmiabrir) {
			JFileChooser jfcabrir = new JFileChooser();
            jfcabrir.setDialogTitle("Abrir un archivo");
            jfcabrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Archivos de texto","txt");
            FileNameExtensionFilter filtropdf = new FileNameExtensionFilter("Archivos PDF","pdf");
            jfcabrir.setFileFilter(filtropdf);
            jfcabrir.addChoosableFileFilter(fnef);
            jfcabrir.addChoosableFileFilter(filtropdf);
            if(jfcabrir.showOpenDialog(this) != JFileChooser.CANCEL_OPTION){
                File archivo = jfcabrir.getSelectedFile();
                System.out.println(archivo.getName());
			}
		}
	}
}


