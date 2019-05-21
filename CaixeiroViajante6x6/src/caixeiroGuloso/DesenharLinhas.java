package caixeiroGuloso;

import javax.swing.*;
import java.awt.*;

public class DesenharLinhas extends JPanel {
	public int[] rota; //rota completa para imprimir
	public double[][] matriz; //matriz das posicoes, aquele arquivo de entrada
	public DesenharLinhas(int[] rota, double[][] matrizPontos) {
		this.rota = rota; //inicializacao recebendo a rota
		this.matriz = matrizPontos; //inicializacao rebendo o matrizEntrada
	}

	@Override
	public void paintComponent(Graphics graphs) {
		super.paintComponent(graphs);
		this.setBackground(Color.WHITE);
        String numero = new String();
		int  j = 1, i = 0;
		graphs.setColor(Color.GREEN); //coloca como cor VERDE
		graphs.fillOval((int) (this.matriz[this.rota[i]][0] * 5 - 5), 
				(int) (this.matriz[this.rota[i]][1] * 5 - 5), 10, 10);
		numero = Integer.toString(rota[i]);
		graphs.drawString(numero, (int) (this.matriz[this.rota[i]][0] * 5 + 5), 
				(int) (this.matriz[this.rota[i]][1] * 5 + 5));
		
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
			graphs.drawLine((int) (this.matriz[this.rota[i]][0] * 5), (int) (this.matriz[this.rota[i]]                                                                                                   [1] * 5), (int) (this.matriz[rota[j]][0] * 5),
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