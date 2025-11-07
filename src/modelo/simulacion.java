package modelo;

import java.util.ArrayList;

import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado;

public class simulacion extends Thread {
    private ArrayList<Asociado> asociados;
    private Ambulancia ambulancia;

    public simulacion(ArrayList<Asociado> asociados, Ambulancia a) {
        this.asociados = asociados;
        this.ambulancia = a;
    }

    public void detener() {
        int i;
        for (i = 0; i < asociados.size(); i++) {
            asociados.get(i).detener();
        }
    }

    @Override
    public void run() {
        int i;
        for (i = 0; i < asociados.size(); i++) {
            asociados.get(i).run();
        }
    }

}
