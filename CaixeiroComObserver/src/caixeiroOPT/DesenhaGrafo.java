package caixeiroOPT;

import javax.swing.JPanel;
import java.awt.*;
import java.io.FileNotFoundException;

public class DesenhaGrafo extends JPanel {
	private double[][] matriz;
	private int[] rota;
	public DesenhaGrafo() throws FileNotFoundException {
		matriz = Matrizes.getMatrizEntrada();
		rota = Rota.getRota();
	}
	@Override
	public void paintComponent(Graphics graphs) {
		super.paintComponent(graphs);
		this.setBackground(Color.WHITE);
        String numero = new String();
		int  j = 1, i = 0;
		graphs.setColor(Color.GREEN); //coloca como cor VERDE
		graphs.fillOval((int) (matriz[rota[i]][0] * 5 - 5), 
				(int) (matriz[rota[i]][1] * 5 - 5), 10, 10);
		numero = Integer.toString(rota[i]);
		graphs.drawString(numero, (int) (matriz[rota[i]][0] * 5 + 5), 
				(int) (matriz[this.rota[i]][1] * 5 + 5));
		
		//desenha uma forma oval na posição passada
		//graphs.fillOval(inteiro posicao no eixo x, inteiro posicao no eixo y, largura da forma, altura da forma
		//passsei segundo a ordem da rota para ser desenhado
		for (i = 1; i < rota.length; i++) { //itereção para desenhar as cidades
			graphs.setColor(Color.RED); //coloca como cor vermelha
			graphs.fillOval((int) (this.matriz[this.rota[i]][0] * 5 - 5), (int) (this.matriz[this.rota[i]][1] * 5 - 5), 10, 10);
			numero = Integer.toString(rota[i]);
			graphs.drawString(numero, (int) (this.matriz[this.rota[i]][0] * 5 + 5), 
					(int) (this.matriz[this.rota[i]][1] * 5 + 5));
		}
		graphs.setColor(Color.BLACK);
		graphs.fillOval((int) (this.matriz[this.rota[i - 1]][0] * 5 - 5), (int) (this.matriz[this.rota[i - 1]][1] * 5 - 5), 10, 10);
		numero = Integer.toString(rota[i - 1]);
		graphs.drawString(numero, (int) (this.matriz[this.rota[i - 1]][0] * 5 + 5), 
				(int) (this.matriz[this.rota[i - 1]][1] * 5 + 5));

		for(i = 0;i < rota.length && j < rota.length; i++) {
			graphs.setColor(Color.blue); //coloca como cor azul
			graphs.drawLine((int) (this.matriz[this.rota[i]][0] * 5),
					(int) (this.matriz[this.rota[i]][1] * 5),
					(int) (this.matriz[this.rota[j]][0] * 5),
					(int) (this.matriz[this.rota[j]][1] * 5));
			//deseha uma linha entre as coordenadas passadas
			//graphs.drawLine(inteiro posicao eixo x, inteiro posicao no eixo y
			//as posicoes tirei da matriz de entrada segundo a rota			
			j++;
		}
		graphs.setColor(Color.blue);
		graphs.drawLine((int) (this.matriz[this.rota[j - 1]][0] * 5),
				(int) (this.matriz[this.rota[j - 1]][1] * 5), (int) (this.matriz[this.rota[0]][0] * 5),
				(int) (this.matriz[this.rota[0]][1] * 5));
	}
}
