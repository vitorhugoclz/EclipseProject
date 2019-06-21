package euristicaDijkstra;

import java.io.ObjectInputStream.GetField;

public class Aresta {
	private int capacidadeFluxo;
	private int custoFluxo;
	
	public Aresta(int capFluxo,int custFluxo) {
		this.capacidadeFluxo = capFluxo;
		this.custoFluxo = custFluxo;
	}
	public int getCapFluxo() {
		return this.capacidadeFluxo;
	}
	public int getCustFluxo() {
		return this.custoFluxo;
	}
}
