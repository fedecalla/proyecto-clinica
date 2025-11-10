package InterfazGrafica;

import javax.swing.*;
import java.awt.*;

public class VentanaSimulacion extends JFrame {

    private JLabel lblEstado;
    private JTextField txtCantAsociados;
    private JTextArea areaMovimientos;

    public VentanaSimulacion(JFrame ventanaPrincipal, String nombre) {
        setTitle("Simulación - Clínica");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        lblEstado = new JLabel("ESTADO");
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

        JButton btnMant = new JButton("SOLICITAR MANTENIMIENTO");
        btnMant.setBackground(verdeBoton);
        btnMant.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCant = new JLabel("CANT. ASOCIADOS");
        lblCant.setFont(new Font("Arial", Font.BOLD, 14));
        lblCant.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtCantAsociados = new JTextField(10);
        txtCantAsociados.setMaximumSize(new Dimension(200, 30));
        txtCantAsociados.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnComenzar = new JButton("COMENZAR");
        JButton btnFinalizar = new JButton("FINALIZAR");
        for (JButton b : new JButton[]{btnComenzar, btnFinalizar}) {
            b.setBackground(verdeBoton);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFocusPainted(false);
        }

        colCentro.add(Box.createVerticalStrut(20));
        colCentro.add(btnMant);
        colCentro.add(Box.createVerticalStrut(30));
        colCentro.add(lblCant);
        colCentro.add(Box.createVerticalStrut(10));
        colCentro.add(txtCantAsociados);
        colCentro.add(Box.createVerticalStrut(40));
        colCentro.add(btnComenzar);
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
        JScrollPane scroll = new JScrollPane(areaMovimientos);
        scroll.setPreferredSize(new Dimension(250, 300));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        scroll.getViewport().setBackground(Color.LIGHT_GRAY);

        colDer.add(lblMov);
        colDer.add(Box.createVerticalStrut(20));
        colDer.add(scroll);

        // Agregar columnas al panel central
        centro.add(colIzq);
        centro.add(colCentro);
        centro.add(colDer);
        
        
        btnVerEvol.addActionListener(e -> {
            new VentanaEvolucionAsociado(nombre).setVisible(true);
            dispose();
        });
    }


}