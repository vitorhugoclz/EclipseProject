package arvoreGeradora1;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class InterfaceGrafica extends JFrame implements Observer{
	public InterfaceGrafica() {
		this.setSize(1350, 730);
		this.setTitle("Rotas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
	}

}
