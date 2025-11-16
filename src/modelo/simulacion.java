package modelo;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado;
import modelo.ambulancia.Operario;
import persistencia.AsociadoDAO;

public class simulacion extends Thread {
	private AsociadoDAO dao;
	private List<Asociado> asociados;
	private Ambulancia ambulancia;
	private Operario operario;
	private int cant_asociados;
	private int solicitudesxasociado;
	
	public simulacion(Ambulancia amb, Operario op) {
		this.ambulancia = amb;
		this.dao = new AsociadoDAO();
		this.operario = op;
	}
	
	public int getSolicitudesxasociado() {
		return solicitudesxasociado;
	}

	public void setSolicitudesxasociado(int solicitudesxasociado) {
		this.solicitudesxasociado = solicitudesxasociado;
	}
	
	public Operario getOperario() {
		return this.operario;
	}
	
	
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

	public int getCant_asociados() {
		return cant_asociados;
	}

	public void setCant_asociados(int cant_asociados) {
		this.cant_asociados = cant_asociados;
	}

	public void setCant(int cant) {
		this.cant_asociados = cant;
	}
	
	public List<Asociado> getAsociados() {
		return asociados;
	}
	
	
	// ------ ------ ------ ------ ------ ------ ------ ------ ------ ------
	
	
	/**
	 * <b>Detener Simulacion </b> <br>
	 * <p>Metodo encargado de recorrer todos los hilos activos y 'desactivar' la flag que hace que sigan ejecutandose continuamente.</p>
	 */
	public void detener() {
		for (Asociado a : this.asociados) {
            a.detener();
        }
	}

	/**
	 * <b>Comienzo de la simulacion</b> <br>
	 * <p>Metodo encargado de recibir la lista de Asociados desde la base de datos, a partir de esta crea una sublista con la cantidad de asociados
	 * requeridos para simular, y luego recorrera esta sublista para darle a cada hilo, la cantidad de ejecuciones, y llamar a su metodo start().</p>
	 */
	public void comenzar() {
		try {
			List<Asociado> todos = this.dao.listarAsociados(this.ambulancia);
			int limite = Math.min(this.cant_asociados, todos.size());
			Collections.shuffle(todos);
			this.asociados = todos.subList(0, limite);
			for (Asociado a : this.asociados) {
				a.setCantSolicitudes(this.solicitudesxasociado);
				a.start();
			}
	            
	        
		}
		catch (SQLException e) {
		}
	}
	
	public boolean enTaller() {
		return (this.ambulancia.getEnTaller()==1);
	}
	
	
	
}
