package InterfazGrafica;

import javax.swing.*;
import java.awt.*;

public class VentanaEvolucionAsociado extends JFrame {

    public VentanaEvolucionAsociado(String name) {
        setTitle("Evolución del Asociado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(null); // usaremos posiciones absolutas para asemejar el diseño del Figma

        // Panel verde (izquierda)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(144, 238, 144)); // verde claro
        panelIzquierdo.setBounds(0, 0, 350, 600);
        panelIzquierdo.setLayout(null);
        add(panelIzquierdo);

        // Panel violeta (derecha)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground( new Color(204, 204, 255)); // violeta claro
        panelDerecho.setBounds(350, 0, 650, 600);
        panelDerecho.setLayout(null);
        add(panelDerecho);

        // Título (nombre clínica)
        JLabel lblTitulo = new JLabel(name, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 22));
        lblTitulo.setBounds(0, 20, 350, 40);
        panelIzquierdo.add(lblTitulo);

        // Botones de asociados
        String[] asociados = {"ASOCIADO 1", "ASOCIADO 2", "ASOCIADO 3", "ASOCIADO 4", "ASOCIADO 5", "ASOCIADO 6", "ASOCIADO 7"};
        int y = 80;
        for (String nombre : asociados) {
            JButton boton = new JButton(nombre);
            boton.setBackground(Color.DARK_GRAY);
            boton.setForeground(Color.WHITE);
            boton.setFont(new Font("Arial", Font.BOLD, 14));
            boton.setBounds(50, y, 250, 35);
            panelIzquierdo.add(boton);
            y += 55;
        }

        // Cuadro gris (simulación o evolución)
        JTextArea panelEvolucion = new JTextArea();
        panelEvolucion.setEditable(false);
        panelEvolucion.setBackground(Color.LIGHT_GRAY);
        panelEvolucion.setBounds(100, 100, 450, 350);
        panelDerecho.add(panelEvolucion);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(430, 480, 150, 40);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        panelDerecho.add(btnVolver);

        // Acción del botón volver
        btnVolver.addActionListener(e -> {
            new VentanaSimulacion(null, name).setVisible(true); // volver a simulación
            dispose();
        });
    }
}