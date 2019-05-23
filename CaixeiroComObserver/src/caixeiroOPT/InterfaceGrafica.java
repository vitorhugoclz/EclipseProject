package caixeiroOPT;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
public class InterfaceGrafica extends JFrame implements Observer{
	public InterfaceGrafica() {
		this.setSize(650, 650);
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
