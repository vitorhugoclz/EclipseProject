package arvoreGeradora1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

public class DesenhaGrafo extends JPanel {
	private double[][] matriz;
	private Aresta[] rotaAGM;
	private Aresta[] rotaAGMAnt;

	public DesenhaGrafo() throws FileNotFoundException {
		this.setLayout(null);
		this.setBounds(0, 0, 1000, 500);
		this.matriz = Matrizes.getMatrizEntrada();
		this.rotaAGM = RotaAGM.getRotaAGM();
		this.rotaAGMAnt = RotaAGM.getRotaAGMAnt();
	}

	@Override
	public void paintComponent(Graphics graphs) {
		super.paintComponent(graphs);
		this.setBackground(Color.WHITE);
		String numero = new String();
		// desenha uma forma oval na posição passada
		// graphs.fillOval(inteiro posicao no eixo x, inteiro posicao no eixo y, largura
		// da forma, altura da forma
		// passsei segundo a ordem da rota para ser desenhado
		for (int i = 0; i < matriz.length; i++) {
			graphs.setColor(Color.RED); // coloca como cor vermelha
			graphs.fillOval((int) (this.matriz[i][0] * 4 - 5), (int) (this.matriz[i][1] * 4 - 5), 10, 10);
			numero = Integer.toString(i);
			graphs.drawString(numero, (int) (this.matriz[i][0] * 4 + 5), (int) (this.matriz[i][1] * 4 + 5));
		}
		graphs.setColor(Color.blue); // coloca como cor azul
		for (int i = 0; i < rotaAGM.length && rotaAGM[i] != null; i++) {
			graphs.drawLine((int) (this.matriz[rotaAGM[i].saida][0] * 4), (int) (this.matriz[rotaAGM[i].saida][1] * 4),
					(int) (this.matriz[rotaAGM[i].chegada][0] * 4), (int) (this.matriz[rotaAGM[i].chegada][1] * 4));
			// graphs.drawLine(inteiro posicao eixo x, inteiro posicao no eixo y
			// as posicoes tirei da matriz de entrada segundo a rota
		}
		if (this.rotaAGMAnt != null) {
			for (int i = 0; i < matriz.length; i++) {
				graphs.setColor(Color.RED); // coloca como cor vermelha
				graphs.fillOval((int) (this.matriz[i][0] * 4 - 5 + 450), (int) (this.matriz[i][1] * 4 - 5), 10, 10);
				numero = Integer.toString(i);
				graphs.drawString(numero, (int) (this.matriz[i][0] * 4 + 5 + 450), (int) (this.matriz[i][1] * 4 + 5));
			}
			graphs.setColor(Color.blue); // coloca como cor azul
			for (int i = 0; i < rotaAGM.length && rotaAGMAnt[i] != null; i++) {
				graphs.drawLine((int) (this.matriz[rotaAGMAnt[i].saida][0] * 4 + 450),
						(int) (this.matriz[rotaAGMAnt[i].saida][1] * 4),
						(int) (this.matriz[rotaAGMAnt[i].chegada][0] * 4 + 450),
						(int) (this.matriz[rotaAGMAnt[i].chegada][1] * 4));
				// graphs.drawLine(inteiro posicao eixo x, inteiro posicao no eixo y
				// as posicoes tirei da matriz de entrada segundo a rota
			}
		}
		graphs.setColor(Color.BLACK);
		graphs.drawString("Grafo Atual", 200, 450);
		graphs.drawString("Grafo Antigo", 600, 450);
		
	}
}
