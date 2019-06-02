package arvoreGeradora2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Matriz {
	private static Matriz matriz;
	private double[][] matrizAdj;
	private double[][] matrizEnt;
	private String[][] matrizOpr;
	private Matriz() {
	}
	public double[][] getMatrizAdj() {
		return this.matrizAdj;
	}
	public double[][] getMatrizEnt() {
		return this.matrizEnt;
	}
	public String[][] getMatrizOpr() {
		return this.matrizOpr;
	}
	
	public void removeAresta(int linha, int coluna) {
		if(this.matrizAdj[linha][coluna] > 0.0) {
			
		}
	}
	public static Matriz getMatriz() {
		if (matriz == null) {
			matriz = new Matriz();
			matriz.matrizEnt = LeituraArq.lerArquivo();
			matriz.matrizAdj = constroiMatrizAdjacencia(matriz.matrizEnt);
			matriz.matrizOpr = LeituraArq.leituraOperacoes();
		}
		return matriz;
	}

	private static double[][] constroiMatrizAdjacencia(double[][] matrizEntrada) {
		double coordX, coordY;
		double distanc = 0;
		double[][] matrizAdjacencia = new double[matrizEntrada.length][matrizEntrada.length];
		for (int i = 0; i < matrizEntrada.length; i++) {
			for (int j = 1 + i; j < matrizEntrada.length; j++) {
				coordX = matrizEntrada[j][0] - matrizEntrada[i][0];
				coordY = matrizEntrada[j][1] - matrizEntrada[i][1];
				distanc = Math.sqrt((coordX * coordX) + (coordY * coordY));
				matrizAdjacencia[i][j] = distanc;
				matrizAdjacencia[j][i] = distanc;
			}
		}
		return matrizAdjacencia;
	}
}
