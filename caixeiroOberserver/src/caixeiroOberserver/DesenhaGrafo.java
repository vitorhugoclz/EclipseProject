package caixeiroOberserver;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

public class DesenhaGrafo extends JPanel {
	private double[][] matriz;
	private int[] rota;
	private double[][] matrizAdj;
	public DesenhaGrafo() throws FileNotFoundException {
		matriz = Matrizes.getMatrizEnt();
		matrizAdj = Matrizes.getMatrizAdj();
		rota = Rota.getRota();
	}

	@Override
	public void paintComponent(Graphics graphs) {
		super.paintComponent(graphs);
		this.setBackground(Color.WHITE);
		String numero = new String();
		int j = 1, i = 0;
		graphs.setColor(Color.GREEN); // coloca como cor VERDE
		graphs.fillOval((int) (matriz[rota[i]][0] * 5 - 5), (int) (matriz[rota[i]][1] * 5 - 5), 10, 10);
		numero = Integer.toString(rota[i]);
		graphs.drawString(numero, (int) (matriz[rota[i]][0] * 5 + 5), (int) (matriz[this.rota[i]][1] * 5 + 5));

		graphs.setColor(Color.RED);
		for (i = 1; i < rota.length; i++) {
			graphs.fillOval((int) (this.matriz[this.rota[i]][0] * 5 - 5), (int) (this.matriz[this.rota[i]][1] * 5 - 5),
					10, 10);
			numero = Integer.toString(rota[i]);
			graphs.drawString(numero, (int) (this.matriz[this.rota[i]][0] * 5 + 5),
					(int) (this.matriz[this.rota[i]][1] * 5 + 5));
		}
		graphs.setColor(Color.BLACK);
		graphs.fillOval((int) (this.matriz[this.rota[i - 1]][0] * 5 - 5),
				(int) (this.matriz[this.rota[i - 1]][1] * 5 - 5), 10, 10);
		numero = Integer.toString(rota[i - 1]);
		graphs.drawString(numero, (int) (this.matriz[this.rota[i - 1]][0] * 5 + 5),
				(int) (this.matriz[this.rota[i - 1]][1] * 5 + 5));

		for (i = 0; i < rota.length && j < rota.length; i++) {
			graphs.setColor(Color.blue); // coloca como cor azul
			graphs.drawLine((int) (this.matriz[this.rota[i]][0] * 5), (int) (this.matriz[this.rota[i]][1] * 5),
					(int) (this.matriz[this.rota[j]][0] * 5), (int) (this.matriz[this.rota[j]][1] * 5));
			j++;
		}
		graphs.setColor(Color.blue);
		graphs.drawLine((int) (this.matriz[this.rota[j - 1]][0] * 5), (int) (this.matriz[this.rota[j - 1]][1] * 5),
				(int) (this.matriz[this.rota[0]][0] * 5), (int) (this.matriz[this.rota[0]][1] * 5));

		// imprime o melhor score atual
		double score = this.calculaScore(this.rota);
		String SCORE = Double.toString(score);
		graphs.drawString(SCORE, 500, 600);
	}
	private double calculaScore(int[] vetor) {
		double score = 0.0;
		int i;
		for (i = 0; i < vetor.length - 1; i++)
			score += matrizAdj[vetor[i]][vetor[i + 1]];
		score += matrizAdj[vetor[i]][vetor[0]];
		return score;
	}
}
