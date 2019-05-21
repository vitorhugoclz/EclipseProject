package caixeiroGuloso;

import java.util.*;
import java.awt.Graphics;
import java.io.*;
import javax.swing.*;

public class caixeiro {
	static double[][] matriz;
	static double[][] entrada;
	static int[] bestOutput;
	static double bestScore = 9999999.99;
	static boolean flag = false;
	static JFrame janela = new JFrame();

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		lerArquivo();
		criaGrafo();
		int i = 0;
		bestOutput = new int[matriz.length];
		int[] rota = vizinhoMaisProximo(i);
		double scoreAtual = calculaScore(rota);
		copiarRota(rota, bestOutput);
		desenhaJanela(bestOutput, entrada);
		try {
			Thread.sleep(350);
		} catch (InterruptedException ex) {
		}
		for (i = 1; i < matriz.length; i++) {
			rota = vizinhoMaisProximo(i);
			scoreAtual = calculaScore(rota);
			if (scoreAtual < bestScore) {
				copiarRota(rota, bestOutput);
				bestScore = scoreAtual;
				janela.repaint();
				try {
					Thread.sleep(350);
				} catch (InterruptedException ex) {
				}
			}
		}
		for(i = 0;i < 1500; i++) {
			ArrayList lista = converterVetorLista(bestOutput);
			buscaGulosa(lista);
		}
		printarRota(bestOutput);
		System.out.println(bestScore);

	}

	public static void buscaGulosa(ArrayList<Integer> lista) {
		Random random = new Random();
		int indice = random.nextInt(lista.size()), melhorPosic = 0;
		int cidade = lista.remove(indice);
		for (int i = 0; i < lista.size(); i++) {
			lista.add(i, cidade);
			double score = scoreLista(lista);
			if(score < bestScore) {
				bestScore = score;
				copiaListaVetor(lista, bestOutput);
				melhorPosic = i;
				janela.repaint();
				try {
					Thread.sleep(350);
				} catch (InterruptedException ex) {
				}
			}
			lista.remove(i);
		}
		lista.add(melhorPosic, cidade);
	}
	public static void copiaListaVetor(ArrayList<Integer> lista, int[] rota) {
		for(int i = 0;i < lista.size() && i < rota.length; i++)
			rota[i] = lista.get(i);
	}
	public static double scoreLista(ArrayList<Integer> lista) {
		double score = 0;
		for(int i = 0;i < lista.size() - 1; i++)
			score += matriz[lista.get(i)][lista.get(i + 1)];
		score += matriz[lista.get(lista.size() - 1)][lista.get(0)];
		return score;
	}
	public static void desenhaJanela(int[] rota, double[][] matrizPontos) throws InterruptedException {
		janela.setSize(650, 650);
		janela.setTitle("Rotas");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		DesenharLinhas grafo = new DesenharLinhas(rota, matrizPontos);
		janela.add(grafo);
	}

	public static ArrayList<Integer> converterVetorLista(int[] vetor) {
		ArrayList<Integer> lista = new ArrayList<Integer>(vetor.length);
		for (int i = 0; i < vetor.length; i++)
			lista.add(vetor[i]);
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

	public static int[] copiarRota(int[] vetorA, int vetorB[]) {
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
			System.out.println(vetor[i] + " ");
		System.out.println("\n");
	}

}