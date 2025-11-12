package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import Controlador.SimulacionController;
import modelo.ambulancia.*;

public class VentanaSimulacion extends JDialog {

	private JFrame ventanaPrincipal;
	private SimulacionController controlador;
    private JLabel lblEstado;
    private JTextField txtCantAsociados;
    private JTextArea areaMovimientos;
    private JTextField txtCantSolicitudes;
    private JButton btnComenzar,btnFinalizar, btnMant;

    public VentanaSimulacion(JFrame ventanaPrincipal, String nombre, SimulacionController controlador) {
    	
    	super(ventanaPrincipal, "Simulación - Clínica", true);
    	this.ventanaPrincipal = ventanaPrincipal;
    	this.controlador = controlador;
    	this.controlador.setVista(this);
    	
    	
        setTitle("Simulación - Clínica");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // === PANEL SUPERIOR ===
        JPanel header = new JPanel();
        header.setBackground(new Color(144, 255, 144)); // verde claro
        JLabel lblTitulo = new JLabel(nombre);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(lblTitulo);
        getContentPane().add(header, BorderLayout.NORTH);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        // === PANEL CENTRAL ===
        JPanel centro = new JPanel(new GridLayout(1, 3));
        getContentPane().add(centro, BorderLayout.CENTER);

        Color violeta = new Color(204, 204, 255);
        Color verde = new Color(144, 255, 180);
        Color verdeBoton = new Color(120, 220, 160);

        // ==== COLUMNA IZQUIERDA ====
        JPanel colIzq = new JPanel();
        colIzq.setBackground(violeta);
        colIzq.setLayout(new BoxLayout(colIzq, BoxLayout.Y_AXIS));
        colIzq.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel lblAmb = new JLabel("<html><u>AMBULANCIA</u></html>");
        lblAmb.setFont(new Font("Arial", Font.BOLD, 16));
        lblAmb.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEstadoAct = new JLabel("<html><u>ESTADO ACTUAL</u></html>");
        lblEstadoAct.setFont(new Font("Arial", Font.BOLD, 14));
        lblEstadoAct.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblEstado = new JLabel("...");
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnVerEvol = new JButton("VER EVOLUCION ASOC.");
        btnVerEvol.setBackground(verdeBoton);
        btnVerEvol.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerEvol.setFocusPainted(false);

        colIzq.add(lblAmb);
        colIzq.add(Box.createVerticalStrut(15));
        colIzq.add(lblEstadoAct);
        colIzq.add(Box.createVerticalStrut(5));
        colIzq.add(lblEstado);
        colIzq.add(Box.createVerticalGlue());
        colIzq.add(btnVerEvol);

        // ==== COLUMNA CENTRAL ====
        JPanel colCentro = new JPanel();
        colCentro.setBackground(verde);
        colCentro.setLayout(new BoxLayout(colCentro, BoxLayout.Y_AXIS));
        colCentro.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        btnMant = new JButton(" SOLICITAR MANTENIMIENTO ");
        btnMant.setBackground(verdeBoton);
        btnMant.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCant = new JLabel("CANTIDAD ASOCIADOS");
        lblCant.setFont(new Font("Arial", Font.BOLD, 14));
        lblCant.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtCantAsociados = new JTextField(10);
        txtCantAsociados.setMaximumSize(new Dimension(70, 30));
        txtCantAsociados.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCantAsociados.setOpaque(false);
        txtCantAsociados.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        txtCantAsociados.setHorizontalAlignment(JTextField.CENTER);
        txtCantAsociados.setFont(new Font("Arial", Font.BOLD, 17));

        
        JLabel lblCantSolicitudes = new JLabel("CANTIDAD DE SOLICITUDES");
        lblCantSolicitudes.setFont(new Font("Arial", Font.BOLD, 14));
        lblCantSolicitudes.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtCantSolicitudes = new JTextField(10);
        txtCantSolicitudes.setMaximumSize(new Dimension(70, 30));
        txtCantSolicitudes.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCantSolicitudes.setOpaque(false);
        txtCantSolicitudes.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        txtCantSolicitudes.setHorizontalAlignment(JTextField.CENTER);
        txtCantSolicitudes.setFont(new Font("Arial", Font.BOLD, 17));

        btnComenzar = new JButton(" COMENZAR ");
        btnFinalizar = new JButton(" FINALIZAR ");
        for (JButton b : new JButton[]{btnComenzar, btnFinalizar, btnMant}) {            
            b.setPreferredSize(new Dimension(210, 29));
            b.setMaximumSize(new Dimension(210, 29));
            b.setBackground(Color.decode("#4C845E"));
            b.setForeground(verde);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.setOpaque(true);
            b.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#4C845E")));
        }

        colCentro.add(Box.createVerticalStrut(30));
        colCentro.add(lblCant);
        colCentro.add(Box.createVerticalStrut(10));
        colCentro.add(txtCantAsociados);
        
        colCentro.add(Box.createVerticalStrut(30));
        colCentro.add(lblCantSolicitudes);
        colCentro.add(Box.createVerticalStrut(10));
        colCentro.add(txtCantSolicitudes);
        
        colCentro.add(Box.createVerticalStrut(40));
        colCentro.add(btnComenzar);
        
        colCentro.add(Box.createVerticalStrut(20));
        colCentro.add(btnMant);
        
        colCentro.add(Box.createVerticalStrut(20));
        colCentro.add(btnFinalizar);
        colCentro.add(Box.createVerticalGlue());

        // ==== COLUMNA DERECHA ====
        JPanel colDer = new JPanel();
        colDer.setBackground(violeta);
        colDer.setLayout(new BoxLayout(colDer, BoxLayout.Y_AXIS));
        colDer.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JLabel lblMov = new JLabel("<html><u>MOVIMIENTOS</u></html>");
        lblMov.setFont(new Font("Arial", Font.BOLD, 16));
        lblMov.setAlignmentX(Component.CENTER_ALIGNMENT);

        areaMovimientos = new JTextArea();
        areaMovimientos.setEditable(false);
        
        areaMovimientos.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(Color.decode("#8484A7"), 3, true), // borde redondeado
        	    BorderFactory.createEmptyBorder(5, 5, 5, 5) // margen interno
        	));
        
        areaMovimientos.setFont(new Font("Poppins", Font.BOLD, 13));
        
        JScrollPane scroll = new JScrollPane(areaMovimientos);
        scroll.setPreferredSize(new Dimension(250, 300));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        scroll.getViewport().setBackground(Color.LIGHT_GRAY);
        // Saque la barra fea del scroll
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null); // Sin borde del scroll
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        
        

        colDer.add(lblMov);
        colDer.add(Box.createVerticalStrut(20));
        colDer.add(scroll);

        // Agregar columnas al panel central
        centro.add(colIzq);
        centro.add(colCentro);
        centro.add(colDer);
        
        btnComenzar.setActionCommand("comenzarsimulacion");
        btnComenzar.addActionListener(controlador);

        btnFinalizar.setActionCommand("TerminaSimulacion");
        btnFinalizar.addActionListener(controlador);

        btnMant.setActionCommand("SolicitarMantenimiento");
        btnMant.addActionListener(controlador);

        btnComenzar.setEnabled(true);
		btnFinalizar.setEnabled(false);
		btnMant.setEnabled(false);
        
        btnVerEvol.setActionCommand("EvolucionPacientes");
        btnVerEvol.addActionListener(controlador);
    }

	public SimulacionController getControlador() {
		return controlador;
	}

	public void setControlador(SimulacionController controlador) {
		this.controlador = controlador;
	}

	public JFrame getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JTextField getTxtCantAsociados() {
		return txtCantAsociados;
	}

	public JTextArea getAreaMovimientos() {
		return areaMovimientos;
	}
	
	public JTextField getTxtCantSolicitudes() {
	    return txtCantSolicitudes;
	}


	public JButton getBtnComenzar() {
		return btnComenzar;
	}

	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}

	public JButton getBtnMant() {
		return btnMant;
	}

	

}