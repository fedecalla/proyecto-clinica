package modelo;

import java.sql.SQLException;
import java.util.List;

import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado;
import modelo.persistencia.AsociadoDAO;

public class simulacion extends Thread {
	private AsociadoDAO dao;
	private List<Asociado> asociados;
	private Ambulancia ambulancia;
	private int cant_asociados;

	public simulacion(Ambulancia amb, AsociadoDAO dao) {
		this.ambulancia = amb;
		this.dao = dao;
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

	public void detener() {
		for (Asociado a : this.asociados) {
            a.detener();
        }
	}

	@Override
	public void run() {
		try {
			List<Asociado> todos = this.dao.listarAsociados(this.ambulancia);
			int limite = Math.min(this.cant_asociados, todos.size());
			//System.out.println("limite: "+ limite);
			this.asociados = todos.subList(0, limite);
			for (Asociado a : this.asociados) {
				//System.out.println(a.toString1()  + "\n");
	            a.run();
	        }
		}
		catch (SQLException e) {
		}

	}
}
