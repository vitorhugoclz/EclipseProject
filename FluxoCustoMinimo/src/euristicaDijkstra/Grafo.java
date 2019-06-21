package euristicaDijkstra;

import java.util.*;

public class Grafo {
	double[][] matrizAdj;
	static Grafo grafo;
	private int produtor;
	private int consumidor;

	private Grafo() {
	}

	public static Grafo getGrafo() {
		if (grafo == null)
			grafo = new Grafo();
		return grafo;
	}

	public int[] adjacentes(int indice) {
		List<Integer> adjac = new ArrayList<Integer>();
		for (int i = 0; i < matrizAdj[indice].length; i++)
			if (matrizAdj[indice][i] > 0.0)
				adjac.add(i);
		int[] retorno = new int[adjac.size()];
		for (int i = 0; i < adjac.size(); i++)
			retorno[i] = adjac.get(i);
		return retorno;
	}

	public double getPeso(int linha, int coluna) {
		return matrizAdj[linha][coluna];
	}

	public int getNumVertices() {
		return matrizAdj.length;
	}

	public void setProdutor(int produtor) {
		this.produtor = produtor;
	}

	public void setConsumidor(int consumidor) {
		this.consumidor = consumidor;
	}

	public int getProdutor() {
		return this.produtor;
	}

	public int getConsumidor() {
		return this.consumidor;
	}

	public void convertMatrizFlux(Aresta[][] matrizFluxo) {
		double[][] matrizAdj = new double[matrizFluxo.length][matrizFluxo.length];
		for (int i = 0; i < matrizFluxo.length - 1; i++) {
			for (int j = 0; j < matrizFluxo[i].length; j++) {
				if(matrizFluxo[i][j] != null && matrizFluxo[i][j].getCapFluxo() > 0) {
					double custFluxo = matrizFluxo[i][j].getCustFluxo();
					double capFluxo = matrizFluxo[i][j].getCapFluxo();
					matrizAdj[i][j] = custFluxo / capFluxo;
				}
				else
					matrizAdj[i][j] = 0.0;
			}
		}
		this.setMatriz(matrizAdj);
	}

	public void setMatriz(double[][] matriz) {
		this.matrizAdj = matriz;
	}
}
