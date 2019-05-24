package arvoreGeradora1;
import java.io.FileNotFoundException;
import java.util.*;
public class CortaGrafo {
	private String[][] matrizOperacoes;
	private double[][] matrizAdj;
	private ArrayList<Aresta> arestasCortadas;
	public CortaGrafo() throws FileNotFoundException {
		matrizOperacoes = Matrizes.getMatrizOperacoes();
		arestasCortadas = RotaAGM.getArestasCortadas();
		matrizAdj = Matrizes.getMatrizAdjacencia();
	}
	
	public void alteraGrafo(int i) {
		String[] operacao = matrizOperacoes[i];
		
	}
}
