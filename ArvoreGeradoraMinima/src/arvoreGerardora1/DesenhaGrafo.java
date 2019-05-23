package arvoreGerardora1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

public class DesenhaGrafo extends JPanel{
	public double[][] matriz;
	public Aresta[] rotaAGM;
	
	public DesenhaGrafo() throws FileNotFoundException {
		matriz = Matrizes.getMatrizEntrada();
		rotaAGM = RotaAGM.getRotaAGM();
	}
	@Override
	public void paintComponent(Graphics graphs) {
		super.paintComponent(graphs);
		this.setBackground(Color.WHITE);
        String numero = new String();
		int  j = 1, i = 0;
		
		//desenha uma forma oval na posição passada
		//graphs.fillOval(inteiro posicao no eixo x, inteiro posicao no eixo y, largura da forma, altura da forma
		//passsei segundo a ordem da rota para ser desenhado
		for (i = 0; i < matriz.length; i++) {
			graphs.setColor(Color.RED); //coloca como cor vermelha
			graphs.fillOval((int) (this.matriz[i][0] * 5 - 5), (int) (this.matriz[i][1] * 5 - 5), 10, 10);
			numero = Integer.toString(i);
			graphs.drawString(numero, (int) (this.matriz[i][0] * 5 + 5), 
					(int) (this.matriz[i][1] * 5 + 5));
		}

		/*for(i = 0;i < rota.length && j < rota.length; i++) {
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
				(int) (this.matriz[this.rota[0]][1] * 5));*/
	}
}
