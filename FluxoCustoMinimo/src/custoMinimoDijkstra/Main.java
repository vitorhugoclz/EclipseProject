package custoMinimoDijkstra;

public class Main {
	public static void main(String[] args) {
		Grafo grafo = LerArquivo.lerArquivo();
		FordFulkerson fordFulkerson = new FordFulkerson();
		double fluxoMaximo = fordFulkerson.fordFulkerson(grafo, Double.MAX_VALUE);
		System.out.println("Fluxo Maximo de Custo Mínimo:" + 
				fluxoMaximo);
		System.out.println("Rota:Fluxo:");
		for(int i = 0;i < grafo.getNumVertices(); i++) {
			for(int j = 0;j < grafo.getNumVertices(); j++) {
				if(grafo.getCapacidadeAtual(i, j) != 
						grafo.getCapacidadeTotal(i, j)) {
					double fluxo = grafo.getCapacidadeTotal(i, j) - 
							grafo.getCapacidadeAtual(i, j);
					System.out.println(i + "->" + j+ ":" + fluxo);
				}
			}
		}
	}
}
