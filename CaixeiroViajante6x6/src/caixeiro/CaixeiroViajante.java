package caixeiro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CaixeiroViajante {
	static int[][] matriz = new int[58][58];
	static ArrayList<Integer> disponiveis = new ArrayList<Integer>();
	static int[] output = new int[matriz.length];
	static int[] bestOutput = new int[matriz.length];
	static int bestScore = 999999;
	public static void main(String[] args) throws FileNotFoundException {
		lerArquivo();
		for (int i = 0; i < matriz.length; i++) {
			criaDisponiveis();
			vizinho(i);
			calculaScore();
		}
		exibeResultado();
	}

	public static void vizinho(int i) {
		int linha = i, coluna = 0, indice = 1, remover;
		output[0] = i;
		disponiveis.remove(disponiveis.indexOf(i));
		while (disponiveis.size() != 0) {
			coluna = buscaMenor(matriz[linha]); // busca menor disponivel na linha
			output[indice] = coluna; // coloca o valor na saida

			// remove valor colocado dos disponiveis
			remover = disponiveis.indexOf(coluna);
			disponiveis.remove(remover);

			// faz as trocas de valores
			linha = coluna;
			indice++;
		}
	}

	public static int buscaMenor(int[] lista) {
		// recebe como parametro uma linha do grafo
		int indice = 0, menor = 99999999;
		for (int i = 0; i < disponiveis.size(); i++) {
			if (lista[disponiveis.get(i)] < menor) {
				menor = lista[disponiveis.get(i)];
				indice = disponiveis.get(i);
			}
		}
		return indice;
	}

	public static void calculaScore() {
		int score = 0, i;
		for (i = 0; i < output.length - 1; i++) {
			score += matriz[output[i]][output[i + 1]];
		}
		score += matriz[output[i]][output[0]];
		if(bestScore > score) {
			bestScore = score;
			for(i = 0;i < output.length; i++)
				bestOutput[i] = output[i];
		}
	}

	public static void criaDisponiveis() {
		for (int i = 0; i < matriz.length; i++)
			disponiveis.add(i);
	}

	public static void lerArquivo() throws FileNotFoundException {
		File data = new File(
				"C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\CaixeiroViajante6x6\\src\\caixeiro\\cidades.txt");
		int valor = 0, i, j;
		try (Scanner leitor = new Scanner(data)) {
			for (i = 0; i < matriz.length; i++) {
				for (j = 1 + i; j < matriz[i].length; j++) {
					valor = leitor.nextInt();
					matriz[i][j] = valor;
					matriz[j][i] = valor;
				}
			}
			leitor.close();
		}
	}

	public static void exibeResultado() {
		for (int i = 0; i < bestOutput.length; i++) {
			System.out.print(bestOutput[i] + " ");
		}
		System.out.println("\n" + bestScore);
	}
}
