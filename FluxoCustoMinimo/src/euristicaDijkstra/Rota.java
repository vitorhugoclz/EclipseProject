package euristicaDijkstra;

public class Rota {
	private double[] distancia;
	private int[] pais;
	public Rota(double[] distancia,int[] pais) {
		this.distancia = distancia;
		this.pais = pais;
	}
	public double[] getDistancia() {
		return this.distancia;
	}
	public int[] getPais() {
		return this.pais;
	}
}
