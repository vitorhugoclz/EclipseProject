package custoMinimoDijkstra;

public class Aresta {
	private double capacFluxo;
	private double custoFluxo;
	private double capacAtual;
	public Aresta(double capacFluxo, double custoFluxo, double capacAtual) {
		this.capacAtual = capacAtual;
		this.custoFluxo = custoFluxo;
		this.capacFluxo = capacFluxo;
	}
	public double getCapacFluxo() {
		return capacFluxo;
	}
	public double getCustoFluxo() {
		return custoFluxo;
	}
	public double getCapacAtual() {
		return capacAtual;
	}
	public void setCapacAtual(double capacAtual) {
		this.capacAtual = capacAtual;
	}
}
