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
		bestScore = 99999999.99;
	}
	private int[] twoOPTSwap(int[] output, int i, int j) {
        int nOutput[] = new int[output.length];
        for (int a = 0; a <= i - 1; a++) {
            nOutput[a] = output[a];
        }
        int d = 0;
        for (int t = i; t <= j; t++) {
            nOutput[t] = output[j - d];
            d++;
        }
        for (int h = j + 1; h < output.length; h++) {
            nOutput[h] = output[h];
        }
        return nOutput;
    }
	private void twoOPT() {
		boolean flag = true;
		boolean melhora = false;
		int[] rota = new int[melhorRota.length];
		for(int i = 0;i < melhorRota.length; i++)
			rota[i] = melhorRota[i];
		int[] novaRota;
		while(flag) {
			for(int i = 1; i < melhorRota.length; i++) {
				melhora = false;
				for(int k = i + 1;k < melhorRota.length; k++) {
					novaRota = this.twoOPTSwap(rota, i, k);
					double novaDist = this.calculaScore(novaRota);
					if(novaDist < bestScore) {
						Rota.copiaRota(novaRota);
						rota = novaRota;
						bestScore = novaDist;
						this.estadoAlterado();
						melhora = true;
						break;
					}
					else
						flag = false;
				}
				if(melhora)
					break;
			}
		}
	}
	private int[] vizinhoMaisProximo(int atual) {
		int[] rotaAtual = new int[matrizAdj.length];
		ArrayList<Integer> disponiveis = iniciaDisp();
		int indice = disponiveis.indexOf(atual);
		disponiveis.remove(indice);
		rotaAtual[0] = atual;
		for (int j = 1; j < rotaAtual.length && !disponiveis.isEmpty(); j++) {
			atual = buscaMaisProximo(matrizAdj[atual], disponiveis);
			rotaAtual[j] = atual;
			disponiveis.remove(disponiveis.indexOf(atual));
		}
		return rotaAtual;
	}

	private int buscaMaisProximo(double[] vetor, ArrayList<Integer> disponiveis) {
		int melhorPosic = 0;
		double menorDist = 999999999.99;
		for (int i = 0; i < disponiveis.size(); i++) {
			int atual = disponiveis.get(i);
			if (vetor[atual] < menorDist) {
				menorDist = vetor[atual];
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0;i < matrizAdj.length; i++) {
			int[] rotaAtual = vizinhoMaisProximo(i);
			double scoreAtual = this.calculaScore(rotaAtual);
			if(scoreAtual < bestScore) {
				bestScore = scoreAtual;
				Rota.copiaRota(rotaAtual);
				this.estadoAlterado();
			}
		}
		this.twoOPT();
		System.out.println(bestScore);
	}
}
