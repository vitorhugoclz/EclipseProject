package arvoreGeradora1;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		AGMKruskal krukal = new AGMKruskal(); 
		krukal.run();
		InterfaceGrafica janela = new InterfaceGrafica();
		DesenhaGrafo grafo = new DesenhaGrafo();
		janela.add(grafo);
		System.out.println();
	}
}
