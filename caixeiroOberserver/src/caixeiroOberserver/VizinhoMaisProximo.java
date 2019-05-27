package caixeiroOberserver;

import java.util.ArrayList;

public class VizinhoMaisProximo {
	private double[][] matrizAdj;
	
	public VizinhoMaisProximo(double[][] matrizAdj) {
		this.matrizAdj = matrizAdj;
	}
	public int[] vizinhoMaisProximo(int atual) {
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
}
