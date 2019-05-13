package caixeiro;

/*anotacoes de resultado
 * melhor resultado possivel: 25.395
 * vizinho mais 27384
 * teste 1 26840 4x
 * teste 2 26612 2x
 * teste 3 26761
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class CaixeiroViajanteHillClimbing {
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
			int score = calculaScore(output);
			copiaMelhor(score, output);
		}
		copiaVetores(bestOutput, output);
		hillClimbing(output);
		exibeResultado();
	}

	public static void hillClimbing(int[] vetor) {
		Random random = new Random();
		int aux = 0, resultadoAnterior = bestScore, resultadoAtual, cont = 0;
		int posicao1, posicao2;
		for (int i = 0; i < 2000000; i++) {
			posicao1 = random.nextInt(58);
			posicao2 = random.nextInt(58);
			aux = vetor[posicao1];
			vetor[posicao1] = vetor[posicao2];
			vetor[posicao2] = aux;
			resultadoAtual = calculaScore(vetor);
			if (resultadoAnterior > resultadoAtual) {
				cont = 0;
				if (bestScore > resultadoAtual) {
					resultadoAnterior = resultadoAtual;
					bestScore = resultadoAtual;
					copiaVetores(vetor, bestOutput);
				} else
					resultadoAnterior = resultadoAtual;
			} else if (resultadoAnterior == resultadoAtual)
				cont++;
			else {
				cont = 0;
				aux = vetor[posicao1];
				vetor[posicao1] = vetor[posicao2];
				vetor[posicao2] = aux;
			}
			if (cont == 4) {
				cont = 0;
				shufle(vetor);
				resultadoAnterior = calculaScore(vetor);
			}
		}
	}

	public static void shufle(int[] vetor) {
		Random random = new Random();
		int aux, posicao1, posicao2;
		for (int i = 0; i < 3; i++) {
			posicao1 = random.nextInt(58);
			posicao2 = random.nextInt(58);
			aux = vetor[posicao1];
			vetor[posicao1] = vetor[posicao2];
			vetor[posicao2] = aux;
		}
	}

	public static void copiaVetores(int[] vetorA, int[] vetorB) {
		/* copia o valores do vetorA para vetorB */
		for (int i = 0; i < vetorA.length; i++)
			vetorB[i] = vetorA[i];
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

	public static int calculaScore(int[] vetor) {
		int score = 0, i;
		for (i = 0; i < vetor.length - 1; i++) {
			score += matriz[vetor[i]][vetor[i + 1]];
		}
		score += matriz[vetor[i]][vetor[0]];
		return score;
	}

	public static void copiaMelhor(int score, int[] vetor) {
		if (bestScore > score) {
			bestScore = score;
			for (int i = 0; i < vetor.length; i++)
				bestOutput[i] = vetor[i];
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
