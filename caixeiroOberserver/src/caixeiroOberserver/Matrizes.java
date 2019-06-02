package caixeiroOberserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Matrizes {
	private static double[][] matrizAdj;
	private static double[][] matrizEnt;

	private Matrizes() {

	}

	private static void lerArquivo() throws FileNotFoundException {
		String arquivoCSV = "C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\caixeiroOberserver\\src\\caixeiroOberserver\\cidades.tsp";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			int tamanho = Integer.parseInt(linha);
			matrizEnt = new double[tamanho][2];
			int i = 0;
			while ((linha = br.readLine()) != null) {

				String[] texto;
				texto = linha.split(csvDivisor);
				for (int j = 0; j < texto.length; j++) {
					matrizEnt[i][j] = Double.parseDouble(texto[j]);
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void constroiMatrizAdjacencia() {
		double coordX, coordY;
		double distanc = 0;
		matrizAdj = new double[matrizEnt.length][matrizEnt.length];
		for (int i = 0; i < matrizEnt.length; i++) {
			for (int j = 1 + i; j < matrizEnt.length; j++) {
				coordX = matrizEnt[j][0] - matrizEnt[i][0];
				coordY = matrizEnt[j][1] - matrizEnt[i][1];
				distanc = Math.sqrt((coordX * coordX) + (coordY * coordY));
				matrizAdj[i][j] = distanc;
				matrizAdj[j][i] = distanc;
			}
		}
	}
	public static double[][] getMatrizAdj() throws FileNotFoundException {
		if (matrizAdj == null) {
			if(matrizAdj == null)
				lerArquivo();
			constroiMatrizAdjacencia();
		}
		return matrizAdj;
	}
	public static double[][] getMatrizEnt() throws FileNotFoundException {
		if(matrizEnt == null)
			lerArquivo();
		return matrizEnt;
	}
}
