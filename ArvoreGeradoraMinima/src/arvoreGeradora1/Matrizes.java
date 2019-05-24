package arvoreGeradora1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Matrizes {
	private static double[][] matrizAdjacencia;
	private static double[][] matrizEntrada;

	private Matrizes() {

	}

	private static void lerArquivo() throws FileNotFoundException {
		String arquivoCSV = "C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\ArvoreGeradoraMinima\\src\\arvoreGeradora1\\pontos.agm1";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			int tamanho = Integer.parseInt(linha);
			matrizEntrada = new double[tamanho][2];
			int i = 0;
			while ((linha = br.readLine()) != null) {

				String[] texto;
				texto = linha.split(csvDivisor);
				for (int j = 0; j < texto.length; j++) {
					matrizEntrada[i][j] = Double.parseDouble(texto[j]);
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
		matrizAdjacencia = new double[matrizEntrada.length][matrizEntrada.length];
		for (int i = 0; i < matrizEntrada.length; i++) {
			for (int j = 1 + i; j < matrizEntrada.length; j++) {
				coordX = matrizEntrada[j][0] - matrizEntrada[i][0];
				coordY = matrizEntrada[j][1] - matrizEntrada[i][1];
				distanc = Math.sqrt((coordX * coordX) + (coordY * coordY));
				matrizAdjacencia[i][j] = distanc;
				matrizAdjacencia[j][i] = distanc;
			}
		}
	}

	public static double[][] getMatrizAdjacencia() throws FileNotFoundException {
		if (matrizAdjacencia == null) {
			if (matrizEntrada == null)
				lerArquivo();
			constroiMatrizAdjacencia();
		}
		return matrizAdjacencia;
	}

	public static double[][] getMatrizEntrada() throws FileNotFoundException {
		if (matrizEntrada == null)
			lerArquivo();
		return matrizEntrada;
	}
}
