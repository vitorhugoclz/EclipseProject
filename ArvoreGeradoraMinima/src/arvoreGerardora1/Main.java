package arvoreGerardora1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
			/*AGMKruskal krukal = new AGMKruskal();
			krukal.run();*/
		InterfaceGrafica janela = new InterfaceGrafica();
		DesenhaGrafo grafo = new DesenhaGrafo();
		janela.add(grafo);
	}
}
