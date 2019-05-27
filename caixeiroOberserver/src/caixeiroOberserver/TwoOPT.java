package caixeiroOberserver;

import java.io.FileNotFoundException;

public class TwoOPT {
	private double[][] matrizAdj;
	private int[] rota;

	public TwoOPT(double[][] matrizAdj, int[] rota) throws FileNotFoundException {
		this.matrizAdj = matrizAdj;
		this.rota = rota;
	}

	public void twoOpt() {
		double scoreAtual = this.calculaScore(rota);
		double score = 9999999.99;
		for (int i = 1; i < this.rota.length; i++) {
			for (int j = i + 1; j < this.rota.length; j++) {
				int[] novaRota = twoOptSwap(this.rota, i, j);
				score = this.calculaScore(novaRota);
				if (score < scoreAtual) {
					Rota.copiaRota(novaRota);
					scoreAtual = score;
					twoOpt();
				}
			}
		}
	}

	private int[] twoOptSwap(int[] rota, int i, int j) {
		int novaRota[] = new int[58];
		for (int a = 0; a <= i - 1; a++) {
			novaRota[a] = rota[a];
		}
		int d = 0;
		for (int t = i; t <= j; t++) {
			novaRota[t] = rota[j - d];
			d++;
		}
		for (int h = j + 1; h < rota.length; h++) {
			novaRota[h] = rota[h];
		}
		return novaRota;
	}

	private double calculaScore(int[] vetor) {
		double score = 0.0;
		int i;
		for (i = 0; i < vetor.length - 1; i++)
			score += matrizAdj[vetor[i]][vetor[i + 1]];
		score += matrizAdj[vetor[i]][vetor[0]];
		return score;
	}
}
