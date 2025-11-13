package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import excepciones.AsociadoInvalidoException;

import Controlador.AsociadosController;
import modelo.ambulancia.Asociado;
public class VentanaAsociados extends JDialog {

    // --- CAMBIO ---
    // Necesitamos que sean miembros de la clase para acceder a ellos
    // desde los ActionListeners
	private AsociadosController controlador;
    private CardLayout cardLayout;
    private JPanel panelDerechoContenedor;
    private String nombreAsociado, apellidoAsociado, dniAsociado, telefonoAsociado, domicilioAsociado, ciudadAsociado;
    private JTextArea areaListado;

    public VentanaAsociados(JFrame parent, String nombre, AsociadosController controlador) {
    	
    	super(parent, "Asociados", true); // Ventana modal
    	this.controlador = controlador;
    	this.controlador.setVista(this);

        this.nombreAsociado = null;
        this.apellidoAsociado = null;
        this.dniAsociado = null;
        setSize(700, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // === HEADER ===
        JPanel header = new JPanel();
        header.setBackground(new Color(102, 255, 153)); // verde
        JLabel lblTitulo = new JLabel(nombre);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        header.add(lblTitulo);
        getContentPane().add(header, BorderLayout.NORTH);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        // === CONTENIDO PRINCIPAL ===
        JPanel contenido = new JPanel(new GridLayout(1, 2));
        getContentPane().add(contenido, BorderLayout.CENTER);

        // --- IZQUIERDA: Botones ---
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(153, 255, 204));
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel lblAsociados = new JLabel("<html><u>ASOCIADOS</u></html>");
        lblAsociados.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsociados.setFont(new Font("Arial", Font.BOLD, 18));
        lblAsociados.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.add(lblAsociados);

        JButton btnAgregar = new JButton("<html><b><u>AGREGAR</u></b></html>");
        JButton btnEliminar = new JButton("<html><b><u>ELIMINAR</u></b></html>");
        JButton btnListar = new JButton("<html><b><u>LISTAR</u></b></html>");
        btnListar.addActionListener(this.controlador);
        btnListar.setActionCommand("ListarAsociados");

        for (JButton b : new JButton[]{btnAgregar, btnEliminar, btnListar}) {
            b.setBackground(new Color(128, 204, 153));
            b.setFocusPainted(false);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(200, 40));
            b.setFont(new Font("Arial", Font.BOLD, 14));
            panelBotones.add(Box.createVerticalStrut(25));
            panelBotones.add(b);
        }

        panelBotones.add(Box.createVerticalGlue());
        panelBotones.add(Box.createVerticalStrut(25));

        contenido.add(panelBotones);

        // --- DERECHA: Contenedor con CardLayout ---
        // --- CAMBIO ---
        // 1. Creamos el CardLayout y el panel que lo usará
        cardLayout = new CardLayout();
        panelDerechoContenedor = new JPanel(cardLayout);

        // 2. Creamos los paneles individuales ("cartas") llamando a métodos
        JPanel panelAgregar = crearPanelAgregar();
        JPanel panelEliminar = crearPanelEliminar();
        JPanel panelListar = crearPanelListar();
        // (Aquí podrías crear también un panelListar)

        // 3. Añadimos las "cartas" al contenedor con un nombre único
        panelDerechoContenedor.add(panelAgregar, "AGREGAR");
        panelDerechoContenedor.add(panelEliminar, "ELIMINAR");
        panelDerechoContenedor.add(panelListar, "LISTAR");
        // panelDerechoContenedor.add(panelListar, "LISTAR");
        
        // 4. Añadimos el contenedor principal de la derecha al contenido
        contenido.add(panelDerechoContenedor);

        // 5. Configuramos los ActionListeners de los botones de la izquierda
        btnAgregar.addActionListener(e -> {
            cardLayout.show(panelDerechoContenedor, "AGREGAR");
        });

        btnEliminar.addActionListener(e -> {
            cardLayout.show(panelDerechoContenedor, "ELIMINAR");
        });
        
        btnListar.addActionListener(e -> {
            // Aquí deberías cargar los datos reales.
            // Por ahora, simulamos la carga por si los datos cambian.
            cargarDatosDeEjemplo(); 
            
            cardLayout.show(panelDerechoContenedor, "LISTAR");
        });
    }

    /**
     * Crea el panel del formulario para "Agregar Asociado".
     * (Este es tu código original para panelForm)
     */
    private JPanel crearPanelAgregar() {
        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(204, 255, 204));
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JButton btnAgregarForm = new JButton("<html><b><u>AGREGAR</u></b></html>");
        btnAgregarForm.addActionListener(this.controlador);
        btnAgregarForm.setActionCommand("AgregarAsociado");
        //btnAgregarForm.addActionListener(this.controlador);
        btnAgregarForm.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregarForm.setBackground(new Color(153, 255, 204)); // verde claro
        btnAgregarForm.setFocusPainted(false);
        btnAgregarForm.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregarForm.setMaximumSize(new Dimension(200, 40));

        JLabel lblagregar = new JLabel("<html><u>AGREGAR</u></html>");
        lblagregar.setHorizontalAlignment(SwingConstants.CENTER);
        lblagregar.setFont(new Font("Arial", Font.BOLD, 18));
        lblagregar.setAlignmentX(0.5f);
        panelForm.add(lblagregar);

        JTextField campo1 = new JTextField("Nombre del Asociado...");
        campo1.setForeground(Color.gray);
        campo1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo1.getText().equals("Nombre del Asociado...")) {
                    campo1.setText("");
                    campo1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo1.getText().isEmpty()) {
                    campo1.setText("Nombre del Asociado...");
                    campo1.setForeground(Color.GRAY);
                }
            }
        });
        JTextField campo2 = new JTextField("Apellido del asociado...");
        campo2.setForeground(Color.gray);
        campo2.addFocusListener(new java.awt.event.FocusAdapter() {
        	@Override
        	public void focusGained(java.awt.event.FocusEvent e) {
        		if (campo2.getText().equals("Apellido del asociado...")) {
        			campo2.setText("");
        			campo2.setForeground(Color.BLACK);
        		}
        	}
        	
        	@Override
        	public void focusLost(java.awt.event.FocusEvent e) {
        		if (campo2.getText().isEmpty()) {
        			campo2.setText("Apellido del asociado...");
        			campo2.setForeground(Color.GRAY);
        		}
        	}
        });
        JTextField campo3 = new JTextField("DNI...");
        campo3.setForeground(Color.gray);
        campo3.addFocusListener(new java.awt.event.FocusAdapter() {
        	@Override
        	public void focusGained(java.awt.event.FocusEvent e) {
        		if (campo3.getText().equals("DNI...")) {
        			campo3.setText("");
        			campo3.setForeground(Color.BLACK);
        		}
        	}
        	
        	@Override
        	public void focusLost(java.awt.event.FocusEvent e) {
        		if (campo3.getText().isEmpty()) {
        			campo3.setText("DNI...");
        			campo3.setForeground(Color.GRAY);
        		}
        	}
        });
        JTextField campo4 = new JTextField("TELEFONO...");
        campo4.setForeground(Color.gray);
        campo4.addFocusListener(new java.awt.event.FocusAdapter() {
        	@Override
        	public void focusGained(java.awt.event.FocusEvent e) {
        		if (campo4.getText().equals("TELEFONO...")) {
        			campo4.setText("");
        			campo4.setForeground(Color.BLACK);
        		}
        	}
        	
        	@Override
        	public void focusLost(java.awt.event.FocusEvent e) {
        		if (campo4.getText().isEmpty()) {
        			campo4.setText("TELEFONO...");
        			campo4.setForeground(Color.GRAY);
        		}
        	}
        });
        JTextField campo5 = new JTextField("DOMICILIO...");
        campo5.setForeground(Color.gray);
        campo5.addFocusListener(new java.awt.event.FocusAdapter() {
        	@Override
        	public void focusGained(java.awt.event.FocusEvent e) {
        		if (campo5.getText().equals("DOMICILIO...")) {
        			campo5.setText("");
        			campo5.setForeground(Color.BLACK);
        		}
        	}
        	
        	@Override
        	public void focusLost(java.awt.event.FocusEvent e) {
        		if (campo5.getText().isEmpty()) {
        			campo5.setText("DOMICILIO...");
        			campo5.setForeground(Color.GRAY);
        		}
        	}
        });
        JTextField campo6 = new JTextField("CIUDAD...");
        campo6.setForeground(Color.gray);
        campo6.addFocusListener(new java.awt.event.FocusAdapter() {
        	@Override
        	public void focusGained(java.awt.event.FocusEvent e) {
        		if (campo6.getText().equals("CIUDAD...")) {
        			campo6.setText("");
        			campo6.setForeground(Color.BLACK);
        		}
        	}
        	
        	@Override
        	public void focusLost(java.awt.event.FocusEvent e) {
        		if (campo6.getText().isEmpty()) {
        			campo6.setText("CIUDAD...");
        			campo6.setForeground(Color.GRAY);
        		}
        	}
        });

        JTextField[] campos = {campo1, campo2, campo3, campo4, campo5, campo6};
        for (JTextField c : campos) {
            c.setMaximumSize(new Dimension(300, 30));
            c.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelForm.add(Box.createVerticalStrut(15));
            panelForm.add(c);
        }

        panelForm.add(Box.createVerticalStrut(30));
        panelForm.add(btnAgregarForm);
        panelForm.add(Box.createVerticalGlue());

        btnAgregarForm.addActionListener(e -> {
            this.nombreAsociado= campo1.getText();
            this.apellidoAsociado= campo2.getText();
            this.dniAsociado= campo3.getText();
            this.telefonoAsociado = campo4.getText();
            this.domicilioAsociado = campo5.getText();
            this.ciudadAsociado = campo6.getText();
        });

        return panelForm;
    }

    /**
     * --- CAMBIO ---
     * Crea el panel del formulario para "Eliminar Asociado".
     * (Basado en la imagen original)
     */
    private JPanel crearPanelEliminar() {
        JPanel panel = new JPanel();
        // Usamos el mismo color de fondo que el panel de agregar
        panel.setBackground(new Color(204, 255, 204));
        panel.setLayout(new GridBagLayout()); // GridBagLayout da más control
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes

        // Título "ELIMINAR"
        JLabel title = new JLabel("<html><u>ELIMINAR</u></html>");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);



        // Campo de texto para DNI
        JTextField txtDni = new JTextField("DNI...");
        txtDni.setForeground(Color.gray);
        txtDni.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtDni.getText().equals("DNI...")) {
                    txtDni.setText("");
                    txtDni.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtDni.getText().isEmpty()) {
                    txtDni.setText("DNI...");
                    txtDni.setForeground(Color.GRAY);
                }
            }
        });
        txtDni.setMaximumSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDni, gbc);
        
        

        // Botón "ELIMINAR"
        JButton btnEliminarForm = new JButton("<html><b><u>ELIMINAR</u></b></html>");
        btnEliminarForm.addActionListener(this.controlador);
        btnEliminarForm.setActionCommand("EliminarAsociado");
        //btnEliminarForm.addActionListener(this.controlador);
        // Mismos estilos que el botón de agregar
        btnEliminarForm.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminarForm.setBackground(new Color(153, 255, 204));
        btnEliminarForm.setFocusPainted(false);
        btnEliminarForm.setMaximumSize(new Dimension(200, 40));
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(30, 5, 5, 5); // Más espacio arriba
        panel.add(btnEliminarForm, gbc);
        
        // Espaciador para empujar todo hacia arriba
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1.0; // Ocupa todo el espacio vertical
        panel.add(new JLabel(""), gbc); 

        // ActionListener para el botón de eliminar
        btnEliminarForm.addActionListener(e -> {
            this.dniAsociado = txtDni.getText();           
        });

        return panel;
    }
        
    
    public boolean SonNumeros(String texto)
    {
    	boolean Esnumero = true;
    	for(char c: texto.toCharArray())
    	{
    		if(!Character.isDigit(c))
    		{
    			Esnumero = false;
    			break;
    		}
    	}
    	return Esnumero;
    }
    
    public String getNombreAsociado() throws AsociadoInvalidoException
    {
    	if(this.nombreAsociado == null || this.nombreAsociado.isBlank())
    		throw new AsociadoInvalidoException("Tiene que completar el campo: Nombre");
    	else
    		return this.nombreAsociado;
    }
    public String getApellidoAsociado() throws AsociadoInvalidoException
    {
    	if(this.apellidoAsociado== null || this.apellidoAsociado.isBlank())
    		throw new AsociadoInvalidoException("Tiene que completar el campo: Apellido");
    	return this.apellidoAsociado;
    }
    public String getTelefonoAsociado() throws AsociadoInvalidoException
    {
    	if(this.telefonoAsociado == null || this.telefonoAsociado.isBlank() || !this.SonNumeros(this.telefonoAsociado))
    		throw new AsociadoInvalidoException("Tiene que completar el campo con el formato correcto: Telefono");
    	return this.telefonoAsociado;
    }
    public String getDomicilioAsociado() throws AsociadoInvalidoException
    {
    	if(this.domicilioAsociado == null || this.domicilioAsociado.isBlank())
    		throw new AsociadoInvalidoException("Tiene que completar el campo: Domicilio");
    	return this.domicilioAsociado;
    }
    
    public String getCiudadAsociado() throws AsociadoInvalidoException
    {
    	if(this.ciudadAsociado == null || this.ciudadAsociado.isBlank())
    		throw new AsociadoInvalidoException("Tiene que completar el campo: Ciudad");
    	return this.ciudadAsociado;
    }
    public String getDniAsociado() throws AsociadoInvalidoException
    {
    	if(this.dniAsociado == null || this.dniAsociado.isBlank() || !this.SonNumeros(this.dniAsociado))
    		throw new AsociadoInvalidoException("Tiene que completar el campo con el formato correcto: Dni");
    	else
    		return this.dniAsociado;
    }
    
    public void popUp(String mensaje)
    {
    	JOptionPane.showMessageDialog(this, mensaje);
    }

    private JPanel crearPanelListar() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 255, 204));
        // BorderLayout es ideal: Título al NORTE, Lista (con scroll) al CENTRO.
        panel.setLayout(new BorderLayout()); 
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Título
        JLabel title = new JLabel("<html><u>LISTADO DE ASOCIADOS</u></html>");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        // Añadimos un borde vacío para darle espacio antes del área de texto
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); 
        panel.add(title, BorderLayout.NORTH);

        // Área de Texto (donde irá la lista)
        areaListado = new JTextArea();
        areaListado.setEditable(false); // Para que el usuario no pueda escribir
        areaListado.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Letra monoespaciada
        
        // Panel con Scroll (¡la clave!)
        // Añadimos el areaListado DENTRO del JScrollPane
        JScrollPane scrollPane = new JScrollPane(areaListado);
        
        // Añadimos el scrollPane (que contiene el área de texto) al centro
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    
    public void Mostrar_Asociados(ArrayList<Asociado> asociados) {
    	
    	for(Asociado a : asociados){
    		areaListado.setText(a.getPersona().toString());
    	}
    	
    }
    
    private void cargarDatosDeEjemplo() {
        // Usamos StringBuilder para construir el texto eficientemente
        StringBuilder sb = new StringBuilder();
        
        sb.append("--- LISTADO DE ASOCIADOS ACTIVOS ---\n\n");
        
        // Generamos 50 líneas para forzar el scroll
        for (int i = 1; i <= 50; i++) {
            sb.append(String.format("Asociado N°%03d - DNI: %d - Nombre: Juan Perez %d\n", i, (20000000 + i*10), i));
        }

        // Ponemos el texto en el área
        areaListado.setText(sb.toString());
        
        // --- IMPORTANTE ---
        // Mueve el cursor (y el scroll) al inicio del texto.
        areaListado.setCaretPosition(0); 
    }

}
