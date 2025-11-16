package modelo.ambulancia;

public class Operario extends Thread{
	private Ambulancia ambulancia;
	private Llamador llamador;


	public Operario(Ambulancia a) {
		assert a != null : "La ambulancia no puede ser null";
		this.ambulancia = a;
	}
	
	
	/**
	 * Inicia un proceso de llamada para activar la ambulancia mediante un {@link Llamador}.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>ambulancia</code> no debe ser {@code null}.</li>
	 *   <li>La ambulancia no debe estar en taller (<code>getEnTaller() != 1</code>).</li>
	 *   <li>Debe existir la clase {@link Llamador} correctamente implementada para manejar la activación.</li>
	 * </ul>
	 *
	 * <p><b>Postcondiciones:</b></p>
	 * <ul>
	 *   <li>Se crea una nueva instancia de {@link Llamador} asociada a la ambulancia.</li>
	 *   <li>El llamador se activa mediante <code>activar()</code>.</li>
	 *   <li>El llamador comienza su ejecución en un hilo independiente mediante <code>start()</code>.</li>
	 * </ul>
	 */

	public void Llamar() {
			if (this.ambulancia.getEnTaller()!=1)
				this.llamador = new Llamador(this.ambulancia);
				this.llamador.activar();
				this.llamador.start();			
	}

}
