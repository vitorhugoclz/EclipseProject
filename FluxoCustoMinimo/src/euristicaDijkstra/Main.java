package euristicaDijkstra;

public class Main {
	public static void main(String[] args) {
		Aresta[][] matrizFluxo = LerArquivo.lerArquivo();
		System.out.println();
		Grafo grafo = Grafo.getGrafo();
		grafo.convertMatrizFlux(matrizFluxo);
		EuristicaDijkstra euristica = new EuristicaDijkstra();
		Rota rota = euristica.dijkstraAlg(grafo, grafo.getProdutor());
		int[] pais = rota.getPais();
		int i = grafo.getConsumidor();
		double soma = 0;
		System.out.println(grafo.getConsumidor());
		int menorFlux = Integer.MAX_VALUE;
		while(pais[i] != -1) {
			System.out.println(pais[i] + ": " + grafo.getPeso(pais[i], i));
			soma += grafo.getPeso(pais[i], i);
			if(matrizFluxo[pais[i]][i].getCapFluxo() < menorFlux)
				menorFlux = matrizFluxo[pais[i]][i].getCapFluxo();
			i = pais[i];
		}
		System.out.println(soma);
		System.out.println(menorFlux);
	}
}
