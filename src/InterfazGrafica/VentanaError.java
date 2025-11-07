package InterfazGrafica;

import javax.swing.*;
import java.awt.*;

public class VentanaError extends JDialog {

    public VentanaError(JFrame parent, String nombreExcepcion) {
        super(parent, "Error", true); // true = bloquea la ventana principal
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel superior (rojo) ---
        JPanel header = new JPanel();
        header.setBackground(Color.RED);
        JLabel lblError = new JLabel("ERROR");
        lblError.setFont(new Font("Arial", Font.BOLD, 22));
        lblError.setForeground(Color.WHITE);
        header.add(lblError);
        add(header, BorderLayout.NORTH);

        // --- Panel central (verde claro) ---
        JPanel cuerpo = new JPanel();
        cuerpo.setBackground(new Color(204, 255, 204));
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));

        JLabel lblExcepcion = new JLabel("<html><b>" + nombreExcepcion + "</b></html>");
        lblExcepcion.setFont(new Font("Arial", Font.PLAIN, 18));
        lblExcepcion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAceptar = new JButton("ACEPTAR");
        btnAceptar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAceptar.setBackground(Color.LIGHT_GRAY);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAceptar.addActionListener(e -> dispose());

        cuerpo.add(Box.createVerticalGlue());
        cuerpo.add(lblExcepcion);
        cuerpo.add(Box.createVerticalStrut(40));
        cuerpo.add(btnAceptar);
        cuerpo.add(Box.createVerticalGlue());

        add(cuerpo, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Ejemplo de uso independiente
        SwingUtilities.invokeLater(() -> {
            JFrame dummy = new JFrame(); // Ventana "padre" ficticia
            dummy.setVisible(true);
            new VentanaError(dummy, "NullPointerException").setVisible(true);
        });
    }
}