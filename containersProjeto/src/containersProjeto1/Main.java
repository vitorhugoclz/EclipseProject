package containersProjeto1;

import java.util.*;


public class Main {
	static List<Produto> listaOrdPeso;
	static List<Produto> listaUsados;
	static Container[] containers;

	public static void main(String[] args) {
		String ambiente = "src\\containersProjeto1\\enviroment_01.csv";
		String produtos = "src\\containersProjeto1\\products_01.csv";

		containers = LeituraArquivo.lerMeioAmbiente(ambiente);
		listaOrdPeso = LeituraArquivo.lerProdutos(ambiente, produtos);
		listaUsados = new ArrayList<Produto>();
		Collections.sort(listaOrdPeso);
		buscarMaisLeves();
		preencherContainers();
		double valorTotal = 0.0;
		for(int i = 0;i < containers.length; i++) {
			valorTotal += containers[i].getValorTotal();
			System.out.println(containers[i].getValorTotal());
		}
		System.out.println(valorTotal);
	}
	public static void preencherContainers() {
		for (int i = 0; i < listaOrdPeso.size(); i++) {
			Produto produtoAtual = listaOrdPeso.get(i);
			if (!listaUsados.contains(produtoAtual)) {
				for (int j = 0; j < containers.length; j++) {
					if(containers[j].inserirProduto(produtoAtual)) {
						listaUsados.add(produtoAtual);
						break;
					}
				}
			}
		}
	}
	public static void buscarMaisLeves() {
		int tamListaOrdPeso = listaOrdPeso.size();
		double maiorValor;
		Produto melhorProd = null;
		int k = 0;
		int i = 0;
		while (k < containers.length) {
			for (i = 0; i < tamListaOrdPeso / 2; i++) {
				maiorValor = 0.0;
				for (int j = 0; j < tamListaOrdPeso / 2; j++) {
					Produto produtoAtul = listaOrdPeso.get(j);
					if (produtoAtul.getValor() > maiorValor) {
						if (!listaUsados.contains(produtoAtul)) {
							melhorProd = produtoAtul;
							maiorValor = produtoAtul.getValor();
						}
					}
				}
				if (k < containers.length - 1) {
					if (!containers[k].inserirProduto(melhorProd)) {
						k++;
						containers[k].inserirProduto(melhorProd);
					}
					listaUsados.add(melhorProd);
				}
			}
			if (i == tamListaOrdPeso / 2)
				break;
		}
	}
}
