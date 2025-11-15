package InterfazGrafica;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.util.*;

import Controlador.SimulacionController;
import modelo.ambulancia.Asociado;

public class VentanaEvolucionAsociado extends JDialog {
	private JTextArea panelEvolucion;
	private SimulacionController controlador;
	private int cant_asociados;

	public VentanaEvolucionAsociado(String name, SimulacionController controlador,int cant_asociados) {
		super((JFrame)null, "Simulación - Clínica", true);
		this.controlador = controlador;

		setTitle("Evolución del Asociado");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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


		if (this.controlador.getAsociados()!=null) {
			int y = 80;
			int i = 0;
			for (Asociado a : this.controlador.getAsociados()) {
				JButton boton = new JButton(a.getNombre());
				boton.putClientProperty("id", i);
				boton.setBackground(Color.DARK_GRAY);
				boton.setForeground(Color.WHITE);
				boton.setFont(new Font("Arial", Font.BOLD, 14));
				boton.setBounds(50, y, 250, 35);
				panelIzquierdo.add(boton);

				boton.setActionCommand("mostrarSolicitudes");
				boton.addActionListener(this.controlador);

				y += 55;
				i++;
			}
		}

		// Cuadro gris (simulación o evolución)
		panelEvolucion = new JTextArea();
		panelEvolucion.setEditable(false);
		panelEvolucion.setBackground(Color.white);
		panelEvolucion.setBounds(100, 100, 450, 350);
		panelDerecho.add(panelEvolucion);
		panelEvolucion.setFont(new Font("Arial", Font.BOLD, 13));
		panelEvolucion.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(Color.decode("#8484A7"), 3, true), // borde redondeado
        	    BorderFactory.createEmptyBorder(5, 5, 5, 5) // margen interno
        	));
		
		

		// Botón de volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(430, 480, 150, 40);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
		panelDerecho.add(btnVolver);

		btnVolver.addActionListener(e -> 
		{
			dispose();
		});

	}
	/**
	 * getter de panel de evolucion<br>
	 * @return el panel en el que se va a mostrar la evolucion de los asociados<br>
	 * pre: panel tiene que existir y ser != null<br>
	 * post: devuelve el panel donde se coloca la informacion de la evoluvion de los asociados<br>
	 */
	public JTextArea getPanelEvolucion() {
		return panelEvolucion;
	}

	/**
	 * 
	 * @param solicitudes arrayList de las solicitudes que van pidiendo los asociados
	 * @param a asociado que va pidiendo las solicutides
	 * pre: asociado existente != null<br>
	 * post: muestra las solicitudes del asociado<br>
	 */
	public void mostrarSolicitudes(ArrayList<String> solicitudes,Asociado a) {
		this.getPanelEvolucion().setFont(new Font("Arial", Font.ITALIC, 13));
		this.getPanelEvolucion().setText(a.getNombre() + " - " + a.getDNI() + "\n ... \n");
		int i = 0;
		for (String s : solicitudes) {
			i++;
			this.getPanelEvolucion().append(i + " - " + s);
		}
	}
}
