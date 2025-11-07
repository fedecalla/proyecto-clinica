package InterfazGrafica;

import javax.swing.*;
import java.awt.*;

public class VentanaAsociados extends JDialog {

    // --- CAMBIO ---
    // Necesitamos que sean miembros de la clase para acceder a ellos
    // desde los ActionListeners
    private CardLayout cardLayout;
    private JPanel panelDerechoContenedor;

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

        // --- DERECHA: Contenedor con CardLayout ---
        // --- CAMBIO ---
        // 1. Creamos el CardLayout y el panel que lo usará
        cardLayout = new CardLayout();
        panelDerechoContenedor = new JPanel(cardLayout);

        // 2. Creamos los paneles individuales ("cartas") llamando a métodos
        JPanel panelAgregar = crearPanelAgregar();
        JPanel panelEliminar = crearPanelEliminar();
        // (Aquí podrías crear también un panelListar)

        // 3. Añadimos las "cartas" al contenedor con un nombre único
        panelDerechoContenedor.add(panelAgregar, "AGREGAR");
        panelDerechoContenedor.add(panelEliminar, "ELIMINAR");
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
            // cardLayout.show(panelDerechoContenedor, "LISTAR");
            JOptionPane.showMessageDialog(this, "Aún no implementado");
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

        // Etiqueta "DNI"
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alineado a la derecha
        panel.add(lblDni, gbc);

        // Campo de texto para DNI
        JTextField txtDni = new JTextField(15);
        txtDni.setMaximumSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDni, gbc);

        // Botón "ELIMINAR"
        JButton btnEliminarForm = new JButton("<html><b><u>ELIMINAR</u></b></html>");
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
            String dni = txtDni.getText();
            
        });

        return panel;
    }


    // Ejemplo para probar de forma independiente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Creamos un JFrame "dummy" (vacío) solo para que JDialog tenga un "padre"
            JFrame dummy = new JFrame();
            dummy.setUndecorated(true); // Que no se vea
            dummy.setLocation(-2000, -2000); // Moverlo fuera de la pantalla
            dummy.setVisible(true);
            
            VentanaAsociados dialog = new VentanaAsociados(dummy);
            dialog.setVisible(true);
            
            // Cuando el diálogo se cierra, cerramos el dummy frame también
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    dummy.dispose();
                }
            });
        });
    }
}
