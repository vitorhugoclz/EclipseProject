package caixeiroOPT;

import java.awt.Color;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class caixeiro {
	public static void main(String[] args) throws FileNotFoundException {
		InterfaceGrafica janela = new InterfaceGrafica();
		DesenhaGrafo grafo = new DesenhaGrafo();
		janela.add(grafo);
		OtimizacaoRota otimizacao = new OtimizacaoRota();
		otimizacao.addObserver(janela);
		Thread thread = new Thread(otimizacao);
		thread.start();
		System.out.println();
	}
}
