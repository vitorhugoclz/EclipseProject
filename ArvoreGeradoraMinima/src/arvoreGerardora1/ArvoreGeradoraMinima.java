package arvoreGerardora1;

import java.io.FileNotFoundException;
import java.util.*;
import  java.util.Collections;
public class ArvoreGeradoraMinima {
	public static void main(String[] args) throws FileNotFoundException {
		UnionFind unionFind = new UnionFind();
		NoPC[] conjunto = unionFind.getConjuntos();
		int TAM = unionFind.getConjuntos().length;
		for (int i = 0; i < unionFind.getConjuntos().length - 1; i++)
			unionFind.juntar(i, i + 1);
		System.out.println(unionFind.buscar(1, 9));
		System.out.println(unionFind.buscar(56, 54));
		System.out.println(unionFind.buscar(12, 18));
		System.out.println(unionFind.buscar(1, unionFind.getConjuntos().length - 1));
		System.out.println("FimFim");

		ArrayList<Aresta> arestas = new ArrayList<Aresta>();
		Random random = new Random();
		for (int i = 0; i < 58; i++) {
			double valor = random.nextDouble();
			Aresta aresta = new Aresta(i, i + 1, valor);
			arestas.add(aresta);
		}
		Collections.sort(arestas);
		for(int i = 0;i < arestas.size(); i++)
			System.out.print(arestas.get(i).peso + " ");
	}
}
