package arvoreGeradora2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeituraArq {
	public static double[][] lerArquivo() {
		String arquivoCSV = "C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\ArvoreGeradoraMinima\\src\\arvoreGeradora2\\pontos.agm1";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			int tamanho = Integer.parseInt(linha);
			double[][] matrizEntrada = new double[tamanho][2];
			int i = 0;
			while ((linha = br.readLine()) != null) {

				String[] texto;
				texto = linha.split(csvDivisor);
				for (int j = 0; j < texto.length; j++) {
					matrizEntrada[i][j] = Double.parseDouble(texto[j]);
				}
				i++;
			}
			return matrizEntrada;
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
		return null;
	}
	
	public static String[][] leituraOperacoes() {
		String arquivoCSV = "C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\ArvoreGeradoraMinima\\src\\arvoreGeradora2\\operacoes.agm1";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			int tamanho = Integer.parseInt(linha);
			int i = 0;
			String[][] matrizOperacoes = new String[tamanho][3];
			while ((linha = br.readLine()) != null && i < matrizOperacoes.length) {

				String[] texto;
				texto = linha.split(csvDivisor);
				for (int j = 0; j < texto.length && j < matrizOperacoes[i].length; j++) {
					matrizOperacoes[i][j] = texto[j];
				}
				i++;
			}
			return matrizOperacoes;
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
		return null;
	}
	
}
