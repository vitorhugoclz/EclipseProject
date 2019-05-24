package arvoreGeradora1;

import java.io.FileNotFoundException;
import java.util.*;
import  java.util.Collections;
public class AGMKruskal extends Observable implements Runnable{
	private Aresta[] rotaAGM;
	private Aresta[] rotaAGMAnt;
	private double[][] matrizAdj;
	public AGMKruskal() throws FileNotFoundException {
		this.rotaAGM = RotaAGM.getRotaAGM();
		this.rotaAGMAnt = RotaAGM.getRotaAGMAnt();
		this.matrizAdj = Matrizes.getMatrizAdjacencia();
	}
	
	public void algoritmo() throws FileNotFoundException {
		ArrayList<Aresta> listaArestas= new ArrayList<Aresta>();
		UnionFind unionFind = new UnionFind();
		for(int i = 0;i < matrizAdj.length; i++) {
			for(int j = 0;j < matrizAdj[i].length; j++) {
				Aresta aresta = new Aresta(i, j, matrizAdj[i][j]);
				if(aresta.peso != 0.0)
					listaArestas.add(aresta);
			}
		}
		Collections.sort(listaArestas);
		Aresta aresta;
		for(int i = 0, j = 0;i < listaArestas.size() && j < rotaAGM.length; i++) {
			aresta = listaArestas.get(i);
			if(!unionFind.buscar(aresta.chegada, aresta.saida)) {
				rotaAGM[j] = aresta;
				unionFind.juntar(aresta.chegada, aresta.saida);
				j++;
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.algoritmo();
			this.mudouEstado();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void mudouEstado() {
		this.setChanged();
		this.notifyObservers();
		try {
			Thread.sleep(350);
		} catch (InterruptedException ex) {
		}
	}
}
