package custoMinimoDijkstra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivo {
	public static Grafo lerArquivo() {
		String arquivoCSV = "C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\FluxoCustoMinimo\\src\\custoMinimoDijkstra\\entrada.txt";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		String csvDivisor2 = ";";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			int tamanho = Integer.parseInt(linha);
			Aresta[][] matrizEntrada = new Aresta[tamanho][tamanho];
			int produtor = 0, consumidor = 0;
			linha = br.readLine();
			String[] texto;
			texto = linha.split(csvDivisor);
			produtor = Integer.parseInt(texto[0]);
			consumidor = Integer.parseInt(texto[1]);
			int i = 0;
			while ((linha = br.readLine()) != null && i < matrizEntrada.length) {
				texto = linha.split(csvDivisor);
				for (int j = 0; j < texto.length; j++) {
					int capFluxo, custFluxo;
					String[] valores = texto[j].split(csvDivisor2);
					capFluxo = Integer.parseInt(valores[0]);
					custFluxo = Integer.parseInt(valores[1]);
					matrizEntrada[i][j] = new Aresta(capFluxo, custFluxo, capFluxo);
				}
				i++;
			}
			Grafo grafo = new Grafo(matrizEntrada, produtor, consumidor);
			return grafo;
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
