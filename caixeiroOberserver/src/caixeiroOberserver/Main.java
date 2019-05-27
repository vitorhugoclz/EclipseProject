package caixeiroOberserver;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		InterfaceGrafica janela = new InterfaceGrafica();
		Otimizacao otimizacao = new Otimizacao();
		otimizacao.addObserver(janela);
		Thread thread = new Thread(otimizacao);
		int[] melhorRota = new int[Matrizes.getMatrizAdj().length];
		double melhorScore = 99999999.99;
		thread.start();
		for(int i = 0;i < 800; i++) {
			int[] rota = Rota.getRota();
			double scoreAtual = calculaScore(rota);
			if(scoreAtual < melhorScore && scoreAtual > 0.0) {
				for(int j = 0;j < rota.length; j++)
					melhorRota[j] = rota[j];
				melhorScore = scoreAtual;
			}
			for(int j = 0;j < rota.length; j++)
				rota[j] = 0;
			thread.run();
		}
		int[] rota1 = {13, 3, 21, 32, 50, 27, 25, 12, 36, 10, 1, 11, 8, 30, 5, 15, 18, 31, 6, 57, 56, 7, 33, 38, 20, 22, 16, 0, 40, 35, 52, 23, 46, 26, 29, 41, 53, 4, 24, 17, 49, 45, 14, 19, 2, 54, 34, 48, 44, 55, 39, 43, 47, 37, 42, 51, 28, 9};
		Rota.copiaRota(melhorRota);
		janela.repaint();
		System.out.println(calculaScore(melhorRota));
		
		/*int[] testeO = {13,9,28,42,51,37,47,43,39,55,44,48,34,54,2,19,14,45,49,17,24,4,53,41,29,26,16,22,20,0,23,46,52,35,40,38,33,7,56,57,6,31,18,15,5,30,8,1,11,10,36,12,25,27,50,21,32,3};
		double testeOScore = calculaScore(testeO);
		System.out.println(testeOScore);
		System.out.println();*/
	}
	private static double calculaScore(int[] vetor) throws FileNotFoundException {
		double[][] matrizAdj = Matrizes.getMatrizAdj();
		double score = 0.0;
		int i;
		for (i = 0; i < vetor.length - 1; i++)
			score += matrizAdj[vetor[i]][vetor[i + 1]];
		score += matrizAdj[vetor[i]][vetor[0]];
		return score;
	}
}
