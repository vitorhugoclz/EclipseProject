package caixeiroOPT;
import java.io.FileNotFoundException;
import java.util.*;

public class Rota {
	private static int[] rota;
	
	private Rota(){};
	
	public static int[] getRota() throws FileNotFoundException {
		if(rota == null)
			rota = new int[Matrizes.getMatrizAdjacencia().length];
		return rota;
	}
	public static void copiaRota(int[] novaRota) {
		for(int i = 0;i < novaRota.length && i < rota.length; i++)
			rota[i] = novaRota[i];
	}
}
