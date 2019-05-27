package arvoreGeradora1;

import java.io.FileNotFoundException;
import java.util.*;

public class RotaAGM {
	private static Aresta[] rotaAGM;
	private static Aresta[] rotaAGMAnt;
	private static ArrayList<String[]> arestasCortadas;

	public static Aresta[] getRotaAGM() throws FileNotFoundException {
		if (rotaAGM == null)
			rotaAGM = new Aresta[Matrizes.getMatrizAdjacencia().length - 1];
		return rotaAGM;

	}

	public static Aresta[] getRotaAGMAnt() throws FileNotFoundException {
		if (rotaAGM == null)
			return rotaAGM;
		if (rotaAGMAnt == null)
			rotaAGMAnt = new Aresta[Matrizes.getMatrizAdjacencia().length - 1];
		for (int i = 0; i < rotaAGMAnt.length; i++)
			rotaAGMAnt[i] = rotaAGM[i];
		return rotaAGMAnt;
	}

	public static ArrayList<String[]> getArestasCortadas() {
		if (arestasCortadas == null)
			arestasCortadas = new ArrayList<String[]>();
		return arestasCortadas;
	}

	public static double getScoreAtual() throws FileNotFoundException {
		double score = 0.0;

		for (int i = 0; i < rotaAGM.length; i++)
			if (rotaAGM[i] != null)
				score += rotaAGM[i].peso;
		return score;
	}
	
	public static double getScoreAnt() throws FileNotFoundException {
		double score = 0.0;
		double score2 = getScoreAtual();
		double[][] matrizAdj = Matrizes.getMatrizAdjacencia();

		for (int i = 0; i < rotaAGMAnt.length; i++)
			if (rotaAGMAnt[i] != null)
				score += rotaAGMAnt[i].peso;
		return score;
	}

}
