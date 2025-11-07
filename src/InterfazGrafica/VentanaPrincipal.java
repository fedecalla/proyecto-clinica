package InterfazGrafica;

import javax.swing.*;

import Controlador.AsociadosController;

import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
	private AsociadosController asociadosController;
    public VentanaPrincipal(AsociadosController asociadosController) {
    	this.asociadosController = asociadosController;
        setTitle("Panel Clínica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // --- Panel superior con nombre ---
        JPanel header = new JPanel();
        header.setBackground(new Color(102, 255, 153)); // verde claro
        JLabel titulo = new JLabel("<nombre_clinica>");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        header.add(titulo);
        getContentPane().add(header, BorderLayout.NORTH);

        // --- Panel central (3 columnas) ---
        JPanel centro = new JPanel(new GridLayout(1, 3));
        getContentPane().add(centro, BorderLayout.CENTER);

        // ==== COLUMNA IZQUIERDA ====
        JPanel colIzq = new JPanel();
        colIzq.setBackground(new Color(204, 204, 255)); // violeta claro
        colIzq.setLayout(new BoxLayout(colIzq, BoxLayout.Y_AXIS));
        colIzq.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblAmb = new JLabel("<html><u>AMBULANCIA</u></html>");
        lblAmb.setForeground(new Color(0, 32, 1));
        lblAmb.setFont(new Font("Arial", Font.BOLD, 16));
        lblAmb.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEstadoAct = new JLabel("<html><u>ESTADO ACTUAL</u></html>");
        lblEstadoAct.setForeground(new Color(0, 32, 1));
        lblEstadoAct.setFont(new Font("Arial", Font.BOLD, 12));
        lblEstadoAct.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEstado = new JLabel("ESTADO");
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEstado.setForeground(new Color(0, 32, 1));
        lblEstado.setFont(new Font("Arial", Font.BOLD, 12));

        JButton btnFinalizar = new JButton("<html><b><u>FINALIZAR</u></b></html>");
        btnFinalizar.setBackground(new Color(102, 204, 153));
        btnFinalizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFinalizar.setFocusPainted(false);

        colIzq.add(lblAmb);
        colIzq.add(lblEstadoAct);
        colIzq.add(Box.createVerticalStrut(10));
        colIzq.add(lblEstado);
        colIzq.add(Box.createVerticalGlue());
        colIzq.add(btnFinalizar);

        // ==== COLUMNA CENTRAL ====
        JPanel colCentro = new JPanel();
        colCentro.setBackground(new Color(153, 255, 204)); // verde más claro
        colCentro.setLayout(new BoxLayout(colCentro, BoxLayout.Y_AXIS));
        colCentro.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnAsociados = new JButton("<html><b><u>ASOCIADOS</u></b></html>");
        
        btnAsociados.addActionListener(e -> {
            VentanaAsociados va = new VentanaAsociados(this,this.asociadosController);
            va.setVisible(true);
            this.asociadosController.setVista(va);
        });
        
        
        JButton btnMant = new JButton("<html><b><u>SOLICITAR<br>MANTENIMIENTO</u></b></html>");
        btnAsociados.setBackground(new Color(102, 204, 153));
        btnMant.setBackground(new Color(102, 204, 153));
        btnAsociados.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMant.setAlignmentX(Component.CENTER_ALIGNMENT);

        colCentro.add(Box.createVerticalGlue());
        colCentro.add(btnAsociados);
        colCentro.add(Box.createVerticalStrut(40));
        colCentro.add(btnMant);
        colCentro.add(Box.createVerticalGlue());

        // ==== COLUMNA DERECHA ====
        JPanel colDer = new JPanel();
        colDer.setBackground(new Color(204, 204, 255));
        colDer.setLayout(new BoxLayout(colDer, BoxLayout.Y_AXIS));
        colDer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnEvol = new JButton("<html><b><u>EVOLUCION<br>ASOC.</u></b></html>");
        btnEvol.setBackground(new Color(102, 204, 153));
        btnEvol.setAlignmentX(Component.CENTER_ALIGNMENT);

        colDer.add(Box.createVerticalGlue());
        colDer.add(btnEvol);
        colDer.add(Box.createVerticalGlue());

        // Agregar las tres columnas
        centro.add(colIzq);
        centro.add(colCentro);
        centro.add(colDer);
        this.setVisible(true);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }*/
}
