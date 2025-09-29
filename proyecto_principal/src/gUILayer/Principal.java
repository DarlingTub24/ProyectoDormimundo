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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import objetos.Libros;
import objetos.Prestamos;
import objetos.Usuarios;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    private String quePanel;
	private JMenuBar menuprincipal;
	private JMenu jmarchivo;
	private JMenuItem jmiabrir, jmisalir;
	private JToolBar jtbsecciones;
	private JButton btnlibros, btnusuarios, btnprestamos;
    private JButton btnagregarRegistro, btnEliminarRegistro, btnmodificarRegistro;
    public static ArrayList<Libros> listaLibros;
    public static ArrayList<Prestamos> listaPrestamos;
    public static ArrayList<Usuarios> listaUsuarios;
    private JPanel jplibros, jpprestamos, jpprincipal, jpusuarios, jpAgregarQuitar;
    private CardLayout cardlayout;

	public Principal(String title) {
		super(title);
        setSize(800, 600);  
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
                guardarDatos();
				salir();
			}

		});
		initcomponents();
		
	}
    
	private void initcomponents(){
        menuprincipal = new JMenuBar();
        cardlayout = new CardLayout();
        jpprincipal = new JPanel(cardlayout);
        contenidoToolBar();
		
        jmarchivo = new JMenu("Archivo");
        jmarchivo.setMnemonic('A');
        jmiabrir = new JMenuItem("Abrir", 'A');
        jmiabrir.addActionListener(this);
        jmarchivo.add(jmiabrir);
        jmarchivo.addSeparator();
        jmisalir = new JMenuItem("Salir", 'S');
        jmisalir.addActionListener(this);
        jmarchivo.add(jmisalir);
		
        menuprincipal.add(jmarchivo);
        setJMenuBar(menuprincipal);

        cargarDatos();
        jpprestamos = new JPanel(new BorderLayout());
        jplibros = new JPanel(new BorderLayout());
        jpusuarios = new JPanel(new BorderLayout());
        contenidoPrestamos();
        contenidoLibros();
        contenidousuarios();
        jpprincipal.add(jpprestamos, "Prestamos");
        jpprincipal.add(jplibros, "Libros");
        jpprincipal.add(jpusuarios, "Usuarios");
        this.add(jpprincipal, BorderLayout.CENTER);

        contenidoBarraNavegacion();
    }

	private void contenidoToolBar() {
        jtbsecciones = new JToolBar();
        btnprestamos = new JButton("Prestamos");
        btnprestamos.addActionListener(this);
        btnprestamos.setToolTipText("Prestamos");
        btnprestamos.setBorderPainted(false);
        btnprestamos.setBackground(Color.LIGHT_GRAY);
        jtbsecciones.add(btnprestamos);
        jtbsecciones.addSeparator();

        btnlibros = new JButton("Libros");
        btnlibros.addActionListener(this);
        btnlibros.setToolTipText("Libros");
        btnlibros.setBorderPainted(false);
        btnlibros.setBackground(Color.LIGHT_GRAY);
        jtbsecciones.add(btnlibros);

        jtbsecciones.addSeparator();
        btnusuarios = new JButton("Usuarios");
        btnusuarios.addActionListener(this);
        btnusuarios.setToolTipText("Usuarios");
        btnusuarios.setBorderPainted(false);
        btnusuarios.setBackground(Color.LIGHT_GRAY);
        jtbsecciones.add(btnusuarios);

        // jtbsecciones.add(new JSeparator(JSeparator.VERTICAL));
        // jtbsecciones.add(Box.createHorizontalGlue());
        // btnsalir = new JButton();
        // btnsalir.addActionListener(this);
        // btnsalir.setToolTipText("Salir de la aplicacion");
        // btnsalir.setBorderPainted(false);
        // btnsalir.setBackground(Color.LIGHT_GRAY);
        // jtbsecciones.add(btnsalir);

		this.add(jtbsecciones, "North");
    }

    private void contenidoBarraNavegacion(){
        jpAgregarQuitar = new JPanel();
        btnagregarRegistro = new JButton("Agregar");
        btnagregarRegistro.addActionListener(this);
        btnEliminarRegistro = new JButton("Devolver");
        btnEliminarRegistro.addActionListener(this);
        btnmodificarRegistro = new JButton("Modificar");
        btnmodificarRegistro.addActionListener(this);
        jpAgregarQuitar.add(btnagregarRegistro);
        jpAgregarQuitar.add(btnEliminarRegistro);
        jpAgregarQuitar.add(btnmodificarRegistro);
        btnmodificarRegistro.setEnabled(false);
        this.add(jpAgregarQuitar, BorderLayout.SOUTH);
    }

    public void contenidoLibros(){
        // Columnas
        String[] columnas = {"Nombre del Libro", "Autor"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };
        
        // Llenar el modelo
        for (Libros libro : listaLibros) {
            modelo.addRow(new Object[]{libro.getTitulo(), libro.getAutor()});
            libro.setPrestado(false);
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel titulo = new JLabel("Lista de Libros");
        jplibros.add(titulo, BorderLayout.NORTH);
        jplibros.add(scrollPane, BorderLayout.CENTER);
        System.out.println("Libros cargados: " + listaLibros.size());
    }

    public void actualizarTablaLibros() {
        jplibros.removeAll(); // Limpia el panel
        String[] columnas = {"Nombre del Libro", "Autor"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };

        for (Libros libro : listaLibros) {
            modelo.addRow(new Object[]{libro.getTitulo(), libro.getAutor()});
            
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel titulo = new JLabel("Lista de Libros");
        jplibros.add(titulo, BorderLayout.NORTH);
        jplibros.add(scrollPane, BorderLayout.CENTER);

        jplibros.revalidate();
        jplibros.repaint();
    }

    public void contenidousuarios(){
        // Columnas
        String[] columnas = {"No. Identificador", "Usuario"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
            @Override
                public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };

        // Llenar el modelo
        for (Usuarios usuario : listaUsuarios) {
            modelo.addRow(new Object[]{usuario.getId(), usuario.getNombre()});
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel titulo = new JLabel("Lista de Usuarios");
        jpusuarios.add(titulo, BorderLayout.NORTH);
        jpusuarios.add(scrollPane, BorderLayout.CENTER);
        System.out.println("Usuarios cargados: " + listaUsuarios.size());
    }

        public void actualizarTablaUsuarios() {
        jpusuarios.removeAll(); // Limpia el panel
        String[] columnas = {"No. Identificador", "Usuario"};
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // ninguna celda editable
            }
        };

        for (Usuarios usuario : listaUsuarios) {
            modelo.addRow(new Object[]{usuario.getId(), usuario.getNombre()});
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel titulo = new JLabel("Lista de Usuarios");
        jpusuarios.add(titulo, BorderLayout.NORTH);
        jpusuarios.add(scrollPane, BorderLayout.CENTER);

        jpusuarios.revalidate();
        jpusuarios.repaint();
    }

    public void contenidoPrestamos(){
        quePanel = "Prestamos";
        // Columnas
        String[] columnas = {"Nombre del Libro", "Nombre del autor", "Fecha de prestamo", "Fecha de regreso límite", "Solicitado por", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
            @Override
                public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };
        // Llenar el modelo
        for (Prestamos prestamo : listaPrestamos) {
            modelo.addRow(new Object[]{prestamo.getLibro().getTitulo(),
            prestamo.getLibro().getAutor(),
            prestamo.getFecha_Prestamo(),
            prestamo.getFecha_Vencimiento(), 
            prestamo.getUsuario().getNombre(),
            prestamo.isVencido() ? "Vencido" : "A tiempo" } );
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel titulo = new JLabel("Lista de Prestamos");
        //jpprestamos.add(titulo, BorderLayout.NORTH);
        jpprestamos.add(scrollPane, BorderLayout.CENTER);
    }

    public void actualizarTablaPrestamos() {
        jpprestamos.removeAll(); // Limpia el panel
        // Columnas
        String[] columnas = {"Nombre del Libro", "Estado", "Fecha de prestamo", "Fecha de regreso límite", "Solicitado por", "columna" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
            @Override
                public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };
        
        // Llenar el modelo
        for (Prestamos prestamo : listaPrestamos) {
            modelo.addRow(new Object[]{prestamo.getLibro().getTitulo(),
            prestamo.getLibro().getAutor(),
            prestamo.getFecha_Prestamo(),
            prestamo.getFecha_Vencimiento(), 
            prestamo.getUsuario().getNombre(),
            prestamo.isVencido() ? "Vencido" : "A tiempo" } );
        }
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel titulo = new JLabel("Lista de Libros");
        jpprestamos.add(titulo, BorderLayout.NORTH);
        jpprestamos.add(scrollPane, BorderLayout.CENTER);

        jpprestamos.revalidate();
        jpprestamos.repaint();
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
		} else if (e.getSource() == btnlibros){
            cardlayout.show(jpprincipal, "Libros");
            btnEliminarRegistro.setText("Eliminar");
            btnmodificarRegistro.setEnabled(true);
            quePanel = "Libros";

        } else if(e.getSource() == btnprestamos) {
            cardlayout.show(jpprincipal, "Prestamos");
            btnEliminarRegistro.setText("Devolver");
            btnmodificarRegistro.setEnabled(false);
            quePanel = "Prestamos";

        } else if (e.getSource() == btnusuarios) {
            cardlayout.show(jpprincipal, "Usuarios");
            btnEliminarRegistro.setText("Eliminar");
            btnmodificarRegistro.setEnabled(true);
            quePanel = "Usuarios";

        } else if (e.getSource() == btnagregarRegistro) {
            if (quePanel == "Prestamos") {
                //Abrir ventana agregar prestamos
                UIPrestamos_agregar agregarPrestamo = new UIPrestamos_agregar(this);
                agregarPrestamo.setVisible(true);
            }

            else if (quePanel == "Libros") {
                //Abrir ventana agregar libros
                System.out.println("Agregar Libro");
                UILibros_agregar agregarLibro = new UILibros_agregar(this);
                agregarLibro.setVisible(true);
            }

            else if (quePanel == "Usuarios") {
                //Abrir ventana agregar usuarios
                System.out.println("Agregar Usuario");
                UIUsuario_Agregar agregarUsuario = new UIUsuario_Agregar(this);
                agregarUsuario.setVisible(true);
            }

        } else if (e.getSource() == btnEliminarRegistro){

            if (quePanel == "Prestamos"){
                UIPrestamos_devolver devolverPrestamo = new UIPrestamos_devolver(this);
                devolverPrestamo.setVisible(true);
            }
            else if (quePanel == "Libros"){
                UILibros_eliminar eliminarLibro = new UILibros_eliminar(this);
                eliminarLibro.setVisible(true);
            }
            else if (quePanel == "Usuarios"){
                UIUsuario_eliminar eliminarUsuario = new UIUsuario_eliminar(this);
                eliminarUsuario.setVisible(true);
            }
        } else if (e.getSource() == btnmodificarRegistro){
            if (quePanel == "Libros"){
                UILibros_modificar modificarLibro = new UILibros_modificar(this);
                modificarLibro.setVisible(true);
            } else if (quePanel == "Usuarios"){
                UIUsuario_modificar moidficarUsuario = new UIUsuario_modificar(this);
                moidficarUsuario.setVisible(true);
            }
        }
        else if (e.getSource() == jmisalir) {
                salir();
            }
        
		//else if (e.getSource() == )
	}

    private void salir() {
        int resp = JOptionPane.showConfirmDialog(this, "Desea salir de la aplicacion?", "Salir de mi aplicacion", JOptionPane.YES_NO_OPTION);
        if( resp == JOptionPane.NO_OPTION)
            return;
        
        if( resp == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

private void cargarDatos() {
    listaLibros = new ArrayList<>();
    listaPrestamos = new ArrayList<>();
    listaUsuarios = new ArrayList<>();

    // === LIBROS ===
    File archivo = new File("proyecto_principal/src/Recursos/libros.dat");
    if (!archivo.exists()) {
        try {
            archivo.createNewFile();
            System.out.println("ARCHIVO DE LIBROS CREADO CON EXITO");
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR EL ARCHIVO DE LIBROS: " + e.getMessage());
        }
    }

    try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
        while (true) {
            Libros libro = (Libros) entrada.readObject();
            listaLibros.add(libro);
        }
    } catch (EOFException e) {
        System.out.println("Fin de archivo LIBROS alcanzado");
    } catch (FileNotFoundException e) {
        System.out.println("ARCHIVO DE LIBROS NO ENCONTRADO: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("CLASE DE LIBROS NO COMPATIBLE: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("ERROR DE ENTRADA/SALIDA LIBROS: " + e.getMessage());
    }

    // === PRESTAMOS ===
    File archivoPrestamos = new File("proyecto_principal/src/Recursos/prestamos.dat");
    if (!archivoPrestamos.exists()) {
        try {
            archivoPrestamos.createNewFile();
            System.out.println("ARCHIVO DE PRESTAMOS CREADO CON EXITO");
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR EL ARCHIVO DE PRESTAMOS: " + e.getMessage());
        }
    }

    try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoPrestamos))) {
        while (true) {
            Prestamos prestamo = (Prestamos) entrada.readObject();
            listaPrestamos.add(prestamo);
        }
    } catch (EOFException e) {
        System.out.println("Fin de archivo PRESTAMOS alcanzado");
    } catch (FileNotFoundException e) {
        System.out.println("ARCHIVO DE PRESTAMOS NO ENCONTRADO: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("CLASE DE PRESTAMOS NO COMPATIBLE: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("ERROR DE ENTRADA/SALIDA PRESTAMOS: " + e.getMessage());
    }

    // === USUARIOS ===
    File archivoUsuarios = new File("proyecto_principal/src/Recursos/usuarios.dat");
    if (!archivoUsuarios.exists()) {
        try {
            archivoUsuarios.createNewFile();
            System.out.println("ARCHIVO DE USUARIOS CREADO CON EXITO");
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR EL ARCHIVO DE USUARIOS: " + e.getMessage());
        }
    }

    try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoUsuarios))) {
        while (true) {
            Usuarios usuario = (Usuarios) entrada.readObject();
            listaUsuarios.add(usuario);
        }
    } catch (EOFException e) {
        System.out.println("Fin de archivo USUARIOS alcanzado");
    } catch (FileNotFoundException e) {
        System.out.println("ARCHIVO DE USUARIOS NO ENCONTRADO: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("CLASE DE USUARIOS NO COMPATIBLE: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("ERROR DE ENTRADA/SALIDA USUARIOS: " + e.getMessage());
    }
}

private void guardarDatos() {
    // === LIBROS ===
    try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("proyecto_principal/src/Recursos/libros.dat"))) {
        for (Libros libro : listaLibros) {
            salida.writeObject(libro);
        }
        System.out.println("ARCHIVO DE LIBROS GUARDADO CON EXITO");
    } catch (IOException e) {
        System.out.println("ERROR GUARDANDO LIBROS: " + e.getMessage());
        e.printStackTrace();
    }

    // === PRESTAMOS ===
    try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("proyecto_principal/src/Recursos/prestamos.dat"))) {
        for (Prestamos prestamo : listaPrestamos) {
            salida.writeObject(prestamo);
        }
        System.out.println("ARCHIVO DE PRESTAMOS GUARDADO CON EXITO");
    } catch (IOException e) {
        System.out.println("ERROR GUARDANDO PRESTAMOS: " + e.getMessage());
        e.printStackTrace();
    }

    // === USUARIOS ===
    try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("proyecto_principal/src/Recursos/usuarios.dat"))) {
        for (Usuarios usuario : listaUsuarios) {
            salida.writeObject(usuario);
        }
        System.out.println("ARCHIVO DE USUARIOS GUARDADO CON EXITO");
    } catch (IOException e) {
        System.out.println("ERROR GUARDANDO USUARIOS: " + e.getMessage());
        e.printStackTrace();
    }
}

}


