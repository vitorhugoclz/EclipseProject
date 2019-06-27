package custoMinimoDijkstra;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private Aresta[][] matrizAdj;
	private int produtor;
	private int consumidor;
	
	public Grafo(Aresta[][] matrizAdj, int produtor, int consumidor) {
		this.matrizAdj = matrizAdj;
		this.produtor = produtor;
		this.consumidor = consumidor;
	}
	public int[] adjacentes(int indice) {
		List<Integer> adjac = new ArrayList<Integer>();
		for (int i = 0; i < matrizAdj[indice].length; i++)
			if (matrizAdj[indice][i] != null && matrizAdj[indice][i].getCapacAtual() > 0.0)
				adjac.add(i);
		int[] retorno = new int[adjac.size()];
		for (int i = 0; i < adjac.size(); i++)
			retorno[i] = adjac.get(i);
		return retorno;
	}
	public double getCusto(int linha, int coluna) {
		return this.funcaoCusto(this.matrizAdj[linha][coluna]);
	}
	public int getNumVertices() {
		int tam = 0;
		if(this.matrizAdj != null)
			tam = this.matrizAdj.length;
		return tam;			
	}
	public int getProdutor() {
		return this.produtor;
	}
	public int getConsumidor() {
		return this.consumidor;
	}
	public double getCapacidadeAtual(int linha, int coluna) {
		double valor = 0.0;
		if(this.matrizAdj != null)
			valor = this.matrizAdj[linha][coluna].getCapacAtual();
		return valor;
	}
	public double getCapacidadeTotal(int linha, int coluna) {
		double valor = 0.0;
		if(this.matrizAdj != null)
			valor = this.matrizAdj[linha][coluna].getCapacFluxo();
		return valor;
	}
	public void setCapacidadeAtual(int linha, int coluna, double valor) {
		double valorAtual = matrizAdj[linha][coluna].getCapacAtual();
		double novoValor = valorAtual - valor;
		matrizAdj[linha][coluna].setCapacAtual(novoValor);
	}
	private double funcaoCusto(Aresta aresta) {
		//return aresta.getCapacAtual() * aresta.getCustoFluxo();
		return 0.0;
	}
}
