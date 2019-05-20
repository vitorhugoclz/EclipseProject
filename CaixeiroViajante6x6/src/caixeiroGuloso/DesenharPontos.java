package caixeiroGuloso;

import java.awt.*;
import javax.swing.*;

public class DesenharPontos extends JFrame {
	int[] rota;
	double[][] entrada;

	public DesenharPontos(int[] rota, double[][] entrada) {
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.rota = rota;
		this.entrada = entrada;
	}

	@Override
	public void paint(Graphics graphs) {
		graphs.setColor(Color.GREEN); // coloca como cor VERDE
		int i = 0;
		graphs.fillOval((int) (this.entrada[this.rota[i]][0] * 5 - 5),
				(int) (this.entrada[this.rota[i]][1] * 5 - 5), 10, 10);
		for (i = 1; i < rota.length - 1; i++)
			graphs.fillOval((int) (this.entrada[this.rota[i]][0] * 5 - 5),
					(int) (this.entrada[this.rota[i]][1] * 5 - 5), 10, 10);
		graphs.fillOval((int) (this.entrada[this.rota[i]][0] * 5 - 5),
				(int) (this.entrada[this.rota[i]][1] * 5 - 5), 10, 10);
	}
}
