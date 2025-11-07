package InterfazGrafica;

import javax.swing.*;
import java.awt.*;

public class VentanaAsociados extends JDialog {

    public VentanaAsociados(JFrame parent) {
        super(parent, "Asociados", true); // Ventana modal
        setSize(700, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // === HEADER ===
        JPanel header = new JPanel();
        header.setBackground(new Color(102, 255, 153)); // verde
        JLabel lblTitulo = new JLabel("<nombre_clinica>");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        header.add(lblTitulo);
        getContentPane().add(header, BorderLayout.NORTH);

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

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(204, 255, 204));
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));


        JButton btnAgregarForm = new JButton("<html><b><u>AGREGAR</u></b></html>");
        btnAgregarForm.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregarForm.setBackground(new Color(153, 255, 204)); // verde claro
        btnAgregarForm.setFocusPainted(false);
        btnAgregarForm.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregarForm.setMaximumSize(new Dimension(200, 40));

        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();
        JTextField campo3 = new JTextField();
        JTextField campo4 = new JTextField();

        JTextField[] campos = {campo1, campo2, campo3, campo4};
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
            String dato1 = campo1.getText();
            String dato2 = campo2.getText();
            String dato3 = campo3.getText();
            String dato4 = campo4.getText();
            JOptionPane.showMessageDialog(this, 
                "Datos ingresados:\n" + dato1 + "\n" + dato2 + "\n" + dato3 + "\n" + dato4,
                "Asociado agregado",
                JOptionPane.INFORMATION_MESSAGE);
        });
        contenido.add(panelForm);
    }

    // Ejemplo para probar de forma independiente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame dummy = new JFrame();
            dummy.setVisible(true);
            new VentanaAsociados(dummy).setVisible(true);
        });
    }
}
