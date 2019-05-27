package arvoreGeradora1;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Collections;

public class AGMKruskal extends Observable implements Runnable {
	private Aresta[] rotaAGM;
	private Aresta[] rotaAGMAnt;
	private double[][] matrizAdj;
	private boolean incial = true;
	private int tempSleep = 12;
	public AGMKruskal() throws FileNotFoundException {
		this.rotaAGM = RotaAGM.getRotaAGM();
		this.rotaAGMAnt = RotaAGM.getRotaAGMAnt();
		this.matrizAdj = Matrizes.getMatrizAdjacencia();
	}

	public void algoritmo() throws FileNotFoundException {
		ArrayList<Aresta> listaArestas = new ArrayList<Aresta>();
		UnionFind unionFind = new UnionFind();
		for (int i = 0; i < matrizAdj.length; i++) {
			for (int j = 1 + i; j < matrizAdj[i].length; j++) {
				Aresta aresta = new Aresta(i, j, matrizAdj[i][j]);
				if (aresta.peso > 0.0)
					listaArestas.add(aresta);
			}
		}
		Collections.sort(listaArestas);
		Aresta aresta;
		for (int i = 0, j = 0; i < listaArestas.size() && j < rotaAGM.length; i++) {
			aresta = listaArestas.get(i);
			if (!unionFind.buscar(aresta.chegada, aresta.saida)) {
				rotaAGM[j] = aresta;
				unionFind.juntar(aresta.chegada, aresta.saida);
				j++;
			}
		}
	}

	private void abreFechaRota() {
		this.tempSleep = 20;
		for (int i = 0; i < rotaAGM.length; i++) {
			if (rotaAGM[i] != null && rotaAGMAnt[i] != null && rotaAGM[i].compareTo(rotaAGMAnt[i]) != 0) {
				String[] operacao = new String[3];
				operacao[0] = "dsl";
				operacao[1] = Integer.toString(rotaAGMAnt[i].saida);
				operacao[2] = Integer.toString(rotaAGMAnt[i].chegada);
				ArrayList<String[]> listaOP = RotaAGM.getArestasCortadas();
				listaOP.add(operacao);
				this.tempSleep = 12;
			}

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			CortaGrafo corta = new CortaGrafo();
			this.algoritmo();
			if (this.incial) {
				RotaAGM.getRotaAGMAnt();
				this.incial = false;
			}
			for (int i = 0; i < 3192; i++) {
				corta.alteraGrafo(i);
				RotaAGM.getRotaAGMAnt();
				this.algoritmo();
				this.abreFechaRota();
				this.mudouEstado();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mudouEstado() {
		this.setChanged();
		this.notifyObservers();
		try {
			Thread.sleep(tempSleep);
		} catch (InterruptedException ex) {
		}
	}
}
