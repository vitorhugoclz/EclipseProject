package caixeiroOPT;

import java.io.FileNotFoundException;
import java.util.*;

public class OtimizacaoRota extends Observable implements Runnable {
	public int[] melhorRota;
	private double[][] matrizAdj;
	private double bestScore;
	public OtimizacaoRota() throws FileNotFoundException {
		melhorRota = Rota.getRota();
		matrizAdj = Matrizes.getMatrizAdjacencia();
		bestScore = 0.0;
	}

	private int[] vizinhoMaisDistante(int atual) {
		int[] rotaAtual = new int[matrizAdj.length];
		ArrayList<Integer> disponiveis = iniciaDisp();
		int indice = disponiveis.indexOf(atual);
		disponiveis.remove(indice);
		rotaAtual[0] = atual;
		for (int j = 1; j < rotaAtual.length && !disponiveis.isEmpty(); j++) {
			atual = buscaMaisDistante(matrizAdj[atual], disponiveis);
			rotaAtual[j] = atual;
			disponiveis.remove(disponiveis.indexOf(atual));
		}
		return rotaAtual;
	}

	private int buscaMaisDistante(double[] vetor, ArrayList<Integer> disponiveis) {
		int melhorPosic = 0;
		double maiorDist = 0.0;
		for (int i = 0; i < disponiveis.size(); i++) {
			int atual = disponiveis.get(i);
			if (vetor[atual] > maiorDist) {
				maiorDist = vetor[atual];
				melhorPosic = atual;
			}
		}
		return melhorPosic;
	}

	private ArrayList<Integer> iniciaDisp() {
		ArrayList<Integer> disponiveis = new ArrayList<Integer>();
		for (int i = 0; i < matrizAdj.length; i++)
			disponiveis.add(i);
		return disponiveis;
	}

	public double calculaScore(int[] vetor) {
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0;i < matrizAdj.length; i++) {
			int[] rotaAtual = vizinhoMaisDistante(i);
			double scoreAtual = this.calculaScore(rotaAtual);
			if(scoreAtual > bestScore) {
				bestScore = scoreAtual;
				Rota.copiaRota(rotaAtual);
				this.estadoAlterado();
			}
		}
	}

}
