package caixeiroOPT;

import java.awt.Color;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class caixeiro {
	public static void main(String[] args) throws FileNotFoundException {
		int[] rota = Rota.getRota();
		InterfaceGrafica janela = new InterfaceGrafica();
		DesenhaGrafo grafo = new DesenhaGrafo();
		janela.add(grafo);
		OtimizacaoRota otimizacao = new OtimizacaoRota();
		otimizacao.addObserver(janela);
		otimizacao.run();
		System.out.println();
	}
}
