package arvoreGeradora1;

import java.io.FileNotFoundException;

public class UnionFind {
	private NoPC[] conjuntos;
	
	public UnionFind() throws FileNotFoundException {
		this.conjuntos = new NoPC[Matrizes.getMatrizAdjacencia().length];
		this.construir();
	}
	public NoPC[] getConjuntos() {
		return this.conjuntos;
	}
	private void construir() {
		for (int i = 0; i < conjuntos.length; i++) {
			this.conjuntos[i] = new NoPC();
			this.conjuntos[i].valor = i;
			this.conjuntos[i].pai = this.conjuntos[i];
		}
	}

	private NoPC buscaRaiz(int indice) {
		NoPC aux = this.conjuntos[indice];
		while (aux.valor != aux.pai.valor) {
			aux = aux.pai;
		}
		return aux;
	}

	public void juntar(int a, int b) {
		NoPC raizA = this.buscaRaiz(a);
		NoPC raizB = this.buscaRaiz(b);

		raizA.pai = raizB;
		this.conjuntos[a].pai = raizB;
		this.conjuntos[b].pai = raizB;
	}

	public boolean buscar(int a, int b) {
		NoPC raizA = buscaRaiz(a);
		NoPC raizB = buscaRaiz(b);
		return raizB.valor == raizA.valor;
	}
}
