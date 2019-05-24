package arvoreGeradora1;

import java.io.FileNotFoundException;
import java.util.*;
public class RotaAGM {
	private static Aresta[] rotaAGM;
	private static Aresta[] rotaAGMAnt;
	private static ArrayList<Aresta> arestasCortadas;
	public static Aresta[] getRotaAGM() throws FileNotFoundException {
		if(rotaAGM == null)
			rotaAGM = new Aresta[Matrizes.getMatrizAdjacencia().length - 1];
		return rotaAGM;
			
	}
	public static Aresta[] getRotaAGMAnt() throws FileNotFoundException {
		if(rotaAGM == null)
			return rotaAGM;
		if(rotaAGMAnt == null)
			rotaAGMAnt = new Aresta[Matrizes.getMatrizAdjacencia().length - 1];
		for(int i = 0;i < rotaAGMAnt.length; i++)
			rotaAGMAnt[i] = rotaAGM[i];
		return rotaAGMAnt;
	}
	
	public static ArrayList<Aresta> getArestasCortadas() {
		if(arestasCortadas == null)
			arestasCortadas = new ArrayList<Aresta>();
		return arestasCortadas;
	}
}
