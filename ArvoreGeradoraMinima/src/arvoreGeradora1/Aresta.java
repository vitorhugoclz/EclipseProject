package arvoreGeradora1;

public class Aresta implements Comparable<Object>{
	public int saida;
	public int chegada;
	public double peso;
	
	public Aresta(int saida, int chegada, double peso) {
		this.saida = saida;
		this.chegada = chegada;
		this.peso = peso;
	}
	@Override
	public int compareTo(Object v) {
		Aresta vizinha = (Aresta) v;
		if(this.peso < vizinha.peso)
			return -1;
		if(this.peso > vizinha.peso)
			return 1;
		return 0;
	}
}
