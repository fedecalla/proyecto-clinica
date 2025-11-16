package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.PrincipalController;

public class VentanaPrincipal extends JFrame {
	private PrincipalController controlador;

	public VentanaPrincipal(PrincipalController controlador, String nombre) {

		this.controlador = controlador;
		this.controlador.setVista(this);

		setTitle("Panel Clínica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
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

		// === PANEL CENTRAL (3 columnas) ===
		JPanel centro = new JPanel(new GridLayout(1, 3));
		getContentPane().add(centro, BorderLayout.CENTER);

		// LATERALES (violetas)
		Color violeta = new Color(204, 204, 255);
		JPanel izquierda = new JPanel();
		izquierda.setBackground(violeta);
		JPanel derecha = new JPanel();
		derecha.setBackground(violeta);

		// COLUMNA CENTRAL (verde con botones)
		JPanel central = new JPanel();
		central.setBackground(new Color(144, 255, 180));
		central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
		central.setBorder(BorderFactory.createEmptyBorder(80, 30, 80, 30));

		JButton btnAsociados = new JButton("ASOCIADOS");
		JButton btnSimulacion = new JButton("SIMULACION");
		JButton btnInicializacion = new JButton("INICIALIZACION");

		for (JButton b : new JButton[] { btnAsociados, btnSimulacion, btnInicializacion}) {
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			b.setBackground(new Color(120, 220, 160));
			b.setFont(new Font("Arial", Font.BOLD, 14));
			b.setFocusPainted(false);
		}
		btnAsociados.setActionCommand("VentanaAsociados");
		btnAsociados.addActionListener(this.controlador);
		btnSimulacion.setActionCommand("VentanaSimulacion");
		btnSimulacion.addActionListener(this.controlador);
		btnInicializacion.setActionCommand("INICIALIZAR");
		btnInicializacion.addActionListener(this.controlador);
		central.add(Box.createVerticalGlue());
		central.add(btnAsociados);
		central.add(Box.createVerticalStrut(40));
		central.add(btnSimulacion);
		central.add(Box.createVerticalGlue());

		centro.add(izquierda);
		centro.add(central);
		centro.add(derecha);
	}

}