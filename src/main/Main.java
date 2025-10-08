package main;

import clinica.Clinica;
import medicos.Clinico;
import individuos.Persona;

public class Main {
	public static void main(String[] args) {
		//Clase para probar boludeces.
		
		//String nom, String dni, String ciudad, String dom, String tel, String mat
		Clinico m = new Clinico("AAAAA","20202020","bsas", "calle nazi", "101", "0001");
		Clinico m2 = new Clinico("BBBB","161616","Jujenio", "calle nazi2", "1012", "0022");
		Clinica c = Clinica.getClinica("Clinica1", "direccion1", "mardel", "911");
		c.agregaMedico(m);c.agregaMedico(m2);
		System.out.println(c.toString());
	}

}
