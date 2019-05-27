package caixeiroOberserver;

import java.io.FileNotFoundException;

public class Rota {
	private static int[] rota;
	
	private Rota() {
		
	}
	public static int[] getRota() throws FileNotFoundException {
		if(rota == null)
			rota = new int[Matrizes.getMatrizAdj().length];
		return rota;
	}
	public static void copiaRota(int[] novaRota) {
		for(int i = 0;i < novaRota.length && i < rota.length; i++)
			rota[i] = novaRota[i];
	}
}
