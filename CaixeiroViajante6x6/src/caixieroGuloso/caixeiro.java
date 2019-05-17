package caixeiroGuloso;

import java.util.*;
import java.io.*;
import java.math.*;
import java.awt.*;
import javax.swing.*;

public class caixeiro {
	static double[][] matriz;
	static double[][] entrada;
	static int[] bestOutput;
	static double bestScore = 9999999.99;

	public static void main(String[] args) throws FileNotFoundException {
		lerArquivo();
		criaGrafo();
		for (int i = 0; i < matriz.length; i++) {

			int[] rota = vizinhoMaisProximo(i);
			double scoreAtual = calculaScore(rota);
			if (scoreAtual < bestScore) {
				bestOutput = copiarRota(rota);
				bestScore = scoreAtual;
			}
		}
		printarRota(bestOutput);
		System.out.println(bestScore);
		System.out.println("Final");
		JFrame janela = new JFrame();
		janela.setSize(400, 400);
		janela.setTitle("Rotas");
		janela.setVisible(true);
		Lista lista = converterVetorLista(bestOutput);
		for (int i = 0; i < 800; i++) {
			buscaGulosa(lista);
			bestOutput = lista.converterListaVetor(lista.tamanho);
			bestScore = calculaScore(bestOutput);
		}
		System.out.println("FimFim");
	}

	public static Lista buscaGulosa(Lista listaAntiga) {
		Random random = new Random();
		int indice = random.nextInt(matriz.length);
		No removido = listaAntiga.remove(indice);
		int i = 0, melhorPosicao = 0;
		double menorDist = 99999.99;
		for (No aux = listaAntiga.inicio; aux.prox != null; aux = aux.prox, i++) {
			if (menorDist > matriz[removido.valor][aux.valor] && matriz[removido.valor][aux.valor] != 0.0) {
				menorDist = matriz[removido.valor][aux.valor];
				melhorPosicao = i;
			}
		}
		listaAntiga.inserePosicao(melhorPosicao, removido);
		return listaAntiga;
	}

	public static Lista converterVetorLista(int[] vetor) {
		Lista lista = new Lista();
		No no = new No();
		no.valor = vetor[0];
		lista.insere(no);
		No aux = lista.inicio;
		for (int i = 1; i < vetor.length; i++) {
			no = new No();
			no.valor = vetor[i];
			lista.insere(no);
		}
		lista.tamanho = vetor.length;
		return lista;
	}

	public static int[] vizinhoMaisProximo(int atual) {
		int[] vetor = new int[matriz.length];
		ArrayList<Integer> disponiveis = criarDisponiveis();
		vetor[0] = atual;
		disponiveis.remove(disponiveis.indexOf(atual));
		for (int i = 1; i < vetor.length && !disponiveis.isEmpty(); i++) {
			int prox = buscarMaisProximo(atual, disponiveis);
			vetor[i] = prox;
			atual = prox;
			int remov = disponiveis.indexOf(vetor[i]);
			disponiveis.remove(remov);
		}
		return vetor;
	}

	public static int buscarMaisProximo(int linha, ArrayList<Integer> disponiveis) {
		double menorDist = 999999.99;
		int retorno = 0;
		for (int i = 0; i < disponiveis.size(); i++) {
			if (matriz[linha][disponiveis.get(i)] < menorDist) {
				menorDist = matriz[linha][disponiveis.get(i)];
				retorno = disponiveis.get(i);
			}
		}
		return retorno;
	}

	public static double calculaScore(int[] rota) {
		double score = 0;
		int i;
		for (i = 0; i < rota.length - 1; i++) {
			score += matriz[rota[i]][rota[i + 1]];
		}
		score += matriz[rota[i]][rota[0]];
		return score;
	}

	public static ArrayList<Integer> criarDisponiveis() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < matriz.length; i++)
			temp.add(i);
		return temp;

	}

	public static int[] copiarRota(int[] vetorA) {
		int[] vetorB = new int[vetorA.length];
		for (int i = 0; i < vetorA.length && i < vetorB.length; i++)
			vetorB[i] = vetorA[i];
		return vetorB;
	}

	public static void criaGrafo() {
		double coordX, coordY;
		double distanc = 0;
		matriz = new double[entrada.length][entrada.length];
		for (int i = 0; i < entrada.length; i++) {
			for (int j = 1 + i; j < entrada.length; j++) {
				coordX = entrada[j][0] - entrada[i][0];
				coordY = entrada[j][1] - entrada[i][1];
				distanc = Math.sqrt((coordX * coordX) + (coordY * coordY));
				matriz[i][j] = distanc;
				matriz[j][i] = distanc;
			}
		}
	}

	public static void lerArquivo() throws FileNotFoundException {

		String arquivoCSV = "/home/2018.1.08.023/eclipse-workspace/CaixeiroViajante/src/caixeiroGuloso/cidades.tsp";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			int tamanho = Integer.parseInt(linha);
			entrada = new double[tamanho][2];
			int i = 0;
			while ((linha = br.readLine()) != null) {

				String[] texto;
				texto = linha.split(csvDivisor);
				for (int j = 0; j < texto.length; j++) {
					entrada[i][j] = Double.parseDouble(texto[j]);
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void printarRota(int[] vetor) {
		for (int i = 0; i < vetor.length; i++)
			System.out.print(vetor[i] + " ");
		System.out.println("\n");
	}

}