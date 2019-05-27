package caixeiroOberserver;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class InterfaceGrafica extends JFrame implements Observer{
	private DesenhaGrafo grafo;
	public InterfaceGrafica() throws FileNotFoundException {
		this.setSize(650, 650);
		this.setTitle("Rotas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		
		this.grafo = new DesenhaGrafo();
		this.add(grafo);
		this.setVisible(true);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
	}
}
