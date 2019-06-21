package euristicaDijkstra;

import java.util.*;
import java.lang.*;
import java.io.*;

public class EuristicaDijkstra {
	private void inicializaEstruturas(double[] distancia, int[] pais,boolean[] abertos, int inicial) {
		if(distancia.length == pais.length && abertos.length == distancia.length) {
			for(int i = 0;i < distancia.length; i++) {
				distancia[i] = Double.MAX_VALUE;
				pais[i] = -1;
				abertos[i] = true;
			}
		}
		else {
			for(int i = 0;i < distancia.length; i++)
				distancia[i] = Integer.MAX_VALUE;
			for(int i = 0;i < pais.length; i++)
				pais[i] = -1;
			for(int i = 0;i < abertos.length; i++)
				abertos[i] = true;
		}
		distancia[inicial] = 0;
	}
	public void relaxar(Grafo grafo, double[] distancia, int[] pais, int u, int v) {
		int[] adjacentes = grafo.adjacentes(u);
		for(int i = 0;i < adjacentes.length && i != v; i++) {
			double peso =  distancia[u] + grafo.getPeso(u, v);
			if(distancia[v] > peso) {
				distancia[v] = peso;
				pais[v] = u;
			}
		}
			
	}
	private int menorDist(Grafo grafo, boolean[] abertos, double[] distancia) {
		int i = 0, numVertices = grafo.getNumVertices();
		//verifica se exisite vértice aberto
		for(i = 0;i < numVertices; i++)
			if(abertos[i]) break;
		if(i == numVertices) return -1;
		//busca o vertice com a menor distancia
		int menor = i;
		for(i = menor + 1;i < numVertices; i++)
			if(abertos[i] && distancia[menor]>distancia[i])
				menor = i;
		return menor;
	}
	private boolean existeAberto(boolean[] abertos) {
		for(int i = 0;i < abertos.length; i++)
			if(abertos[i])
				return true;
		return false;
	}
	public Rota dijkstraAlg(Grafo grafo, int inicial) {
		//criacao e inicializacao de estruturas
		int[] pais = new int[grafo.getNumVertices()];
		double[] distancia = new double[grafo.getNumVertices()];
		boolean[] abertos = new boolean[grafo.getNumVertices()];
		this.inicializaEstruturas(distancia, pais, abertos, inicial);
		
		while(existeAberto(abertos)) {
			int u = menorDist(grafo, abertos, distancia);
			abertos[u] = false;
			
			int[] adjacentes = grafo.adjacentes(u);
			for(int i = 0;i < adjacentes.length; i++)
				this.relaxar(grafo, distancia, pais, u, adjacentes[i]);
		}
		Rota rota = new Rota(distancia, pais);
		return rota;
	}
}
