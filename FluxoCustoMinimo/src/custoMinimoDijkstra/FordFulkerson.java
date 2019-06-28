package custoMinimoDijkstra;

public class FordFulkerson {
	double fordFulkerson(Grafo grafo, double meta) {
		// criando vetor de pais(rota)
		int[] pais = new int[grafo.getNumVertices()];
		double fluxoMaximo = 0.0;
		Dijkstra dijkstra = new Dijkstra();
		while (dijkstra.dijkstraAlg(grafo, grafo.getProdutor(), pais) && fluxoMaximo < meta) {
			double fluxoCaminho = fluxoMin(grafo, pais);
			System.out.println(fluxoCaminho);
			if (fluxoCaminho + fluxoMaximo > meta)
				fluxoCaminho = meta - fluxoMaximo;
			atualizarGrafo(grafo, pais, fluxoCaminho);
			fluxoMaximo += fluxoCaminho;
		}
		return fluxoMaximo;
	}

	private double fluxoMin(Grafo grafo, int[] pais) {
		// este metodo busca o menor valor de capacidade atual
		double menor = Double.MAX_VALUE;
		int i = grafo.getConsumidor();
		while (pais[i] != -1) {
			double valor = grafo.getCapacidadeAtual(pais[i], i);
			if (valor < menor)
				menor = valor;
			i = pais[i];
		}
		return menor;
	}

	private void atualizarGrafo(Grafo grafo, int[] pais, double fluxoCaminho) {
		int i = grafo.getConsumidor();
		while (pais[i] != -1) {
			grafo.setCapacidadeAtual(pais[i], i, fluxoCaminho);
			i = pais[i];
		}
	}
}
