package caixeiroOberserver;

import java.util.ArrayList;
import java.util.Random;

public class BuscaGulosa {
	private double[][] matrizAdj;
	
	public BuscaGulosa(double[][] matrizAdj) {
		this.matrizAdj = matrizAdj;
	}
	public int[] buscaGulosa(int[] rota) {
		ArrayList<Integer> lista = converterVetorLista(rota);
		double bestScore = scoreLista(lista);
		int[] melhorRota = new int[rota.length];
		Random random = new Random();
		int indice = random.nextInt(lista.size()), melhorPosic = 0;
		int cidade = lista.remove(indice);
		for (int i = 0; i < lista.size(); i++) {
			lista.add(i, cidade);
			double score = scoreLista(lista);
			if(score <= bestScore) {
				bestScore = score;
				melhorPosic = i;
			}
			lista.remove(i);
		}
		lista.add(melhorPosic, cidade);
		copiaListaVetor(lista, melhorRota);
		return melhorRota;
	}
	public static void copiaListaVetor(ArrayList<Integer> lista, int[] rota) {
		for(int i = 0;i < lista.size() && i < rota.length; i++)
			rota[i] = lista.get(i);
	}
	private ArrayList<Integer> converterVetorLista(int[] vetor) {
		ArrayList<Integer> lista = new ArrayList<Integer>(vetor.length);
		for (int i = 0; i < vetor.length; i++)
			lista.add(vetor[i]);
		return lista;
	}
	
	private  double scoreLista(ArrayList<Integer> lista) {
		double score = 0;
		for(int i = 0;i < lista.size() - 1; i++)
			score += matrizAdj[lista.get(i)][lista.get(i + 1)];
		score += matrizAdj[lista.get(lista.size() - 1)][lista.get(0)];
		return score;
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
