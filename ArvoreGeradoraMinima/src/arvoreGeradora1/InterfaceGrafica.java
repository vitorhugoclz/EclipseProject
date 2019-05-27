package arvoreGeradora1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceGrafica extends JFrame implements Observer, ActionListener {
	private JButton botao;
	private JPanel grafo;
	private JPanel painelOP;
	private AGMKruskal kruskal;
	private boolean flag = true;

	public InterfaceGrafica() throws FileNotFoundException {
		this.setSize(1350, 730);
		this.setTitle("Rotas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);

		grafo = new DesenhaGrafo();

		this.botao = new JButton();
		this.botao.setBounds(650, 600, 100, 50);
		this.botao.setText("Next");
		this.botao.addActionListener(this);

		/*painelOP = new JPanel();
		painelOP.setBounds(1000, 0, 350, 730);
		painelOP.setBorder(BorderFactory.createTitledBorder("Operações"));

		this.add(painelOP);*/
		this.add(botao);
		this.add(grafo);

		this.kruskal = new AGMKruskal();
		this.kruskal.addObserver(this);

		this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
	}

	public void actionPerformed(ActionEvent evento) {
		Thread thread = new Thread(this.kruskal);
		thread.start();
	}

}
