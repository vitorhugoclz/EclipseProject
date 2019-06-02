package arvoreGeradora1;
import java.io.FileNotFoundException;
import java.util.*;
public class CortaGrafo {
	private String[][] matrizOperacoes;
	private double[][] matrizAdj;
	private ArrayList<String[]> arestasCortadas;
	private int contOP;
	public CortaGrafo() throws FileNotFoundException {
		matrizOperacoes = Matrizes.getMatrizOperacoes();
		this.arestasCortadas = RotaAGM.getArestasCortadas();
		this.matrizAdj = Matrizes.getMatrizAdjacencia();
		this.contOP = 0;
	}
	
	public boolean alteraGrafo(int i) throws FileNotFoundException {
		String[] operacao = matrizOperacoes[i];
		boolean flag = false;
		if(operacao[0].intern() == "add") {
			int linha = Integer.parseInt(operacao[1]);
			int coluna = Integer.parseInt(operacao[2]);
			
			if(matrizAdj[linha][coluna] < 0.0) {
				this.arestasCortadas.clear();
				matrizAdj[linha][coluna] = matrizAdj[linha][coluna] * -1;
				matrizAdj[coluna][linha] = matrizAdj[coluna][linha] * -1;
				
				//modificando matriz Lista de operacoes
				this.arestasCortadas.add(operacao);
				double pesoAtual = matrizAdj[coluna][linha];
				Aresta[] rota = RotaAGM.getRotaAGM();
				for(int j = 0;j < rota.length; j++) {
					if(rota[j].peso > pesoAtual) {
						flag = true;
						break;
					}
				}
			}
		}
		else {
			int linha = Integer.parseInt(operacao[1]);
			int coluna = Integer.parseInt(operacao[2]);
			if(matrizAdj[linha][coluna] > 0.0) {
				this.arestasCortadas.clear();
				Aresta aresta = new Aresta(linha, coluna, matrizAdj[coluna][linha]);
				matrizAdj[linha][coluna] = matrizAdj[linha][coluna] * -1;
				matrizAdj[coluna][linha] = matrizAdj[coluna][linha] * -1;
				
				//modificando matriz Lista de operacoes
				this.arestasCortadas.add(operacao);
				
				Aresta[] rota = RotaAGM.getRotaAGM();
				for(int j = 0;j < rota.length; j++) {
					if(rota[j].equals(aresta)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}
	
	public int getContOP() {
		return this.contOP;
	}
}
