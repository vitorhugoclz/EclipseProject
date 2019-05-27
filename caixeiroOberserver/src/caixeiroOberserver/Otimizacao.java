package caixeiroOberserver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

public class Otimizacao extends Observable implements Runnable {
	private int[] melhorRota;
	private double maiorScore;
	private double[][] matrizAdj;

	public Otimizacao() throws FileNotFoundException {
		this.melhorRota = Rota.getRota();
		this.matrizAdj = Matrizes.getMatrizAdj();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.maiorScore = 9999999999.99;
		VizinhoMaisProximo vizinho = new VizinhoMaisProximo(matrizAdj);
		for (int i = 0; i < matrizAdj.length; i++) {
			int[] rotaAtual = vizinho.vizinhoMaisProximo(i);
			double scoreAtual = this.calculaScore(rotaAtual);
			if (scoreAtual < maiorScore) {
				maiorScore = scoreAtual;
				Rota.copiaRota(rotaAtual);
				this.estadoAlterado();
			}
		}
		BuscaGulosa buscaGulosa = new BuscaGulosa(this.matrizAdj);
		for (int i = 0; i < 100; i++) {
			int[] rotaAtual = buscaGulosa.buscaGulosa(this.melhorRota);
			double scoreAtual = this.calculaScore(rotaAtual);
			if (scoreAtual < maiorScore) {
				maiorScore = scoreAtual;
				Rota.copiaRota(rotaAtual);
				this.estadoAlterado();
			}
		}
		
		TwoOPT twoOpt;
		try {
			
			twoOpt = new TwoOPT(matrizAdj, this.melhorRota);
			for(int i = 0;i < 100; i++)
				twoOpt.twoOpt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.estadoAlterado();
	}

	private double calculaScore(int[] vetor) {
		double score = 0.0;
		int i;
		for (i = 0; i < vetor.length - 1; i++)
			score += matrizAdj[vetor[i]][vetor[i + 1]];
		score += matrizAdj[vetor[i]][vetor[0]];
		return score;
	}

	private void estadoAlterado() {
		this.setChanged();
		this.notifyObservers();
		try {
			Thread.sleep(350);
		} catch (InterruptedException ex) {
		}
	}
}
