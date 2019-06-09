package containersProjeto1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class LeituraArquivo {
	public static Container[] lerMeioAmbiente(String ambiente) {
		String arquivoCSV = ambiente;
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			linha = br.readLine();
			linha = br.readLine();
			String[] valores = linha.split(csvDivisor);
			int quantCont = Integer.parseInt(valores[1]);
			int cargaMaxima = Integer.parseInt(valores[2]);
			int volumeMaximo = Integer.parseInt(valores[3]);
			
			Container[] vetorContainer = new Container[quantCont];
			for(int i = 0;i < vetorContainer.length; i++)
				vetorContainer[i] = new Container(cargaMaxima, volumeMaximo);
			return vetorContainer;
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
	public static ArrayList<Produto> lerProdutos(String ambiente, String produtos) {
		String arquivoCSV1 = ambiente;
		String arquivoCSV2 = produtos;
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new FileReader(arquivoCSV1));
			linha = br.readLine();
			linha = br.readLine();
			String[] valores = linha.split(csvDivisor);
			int quantProd = Integer.parseInt(valores[0]);
			ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
			br.close();
			br = new BufferedReader(new FileReader(arquivoCSV2));
			linha = br.readLine();
			linha = br.readLine();
			int i = 0;
			while(i < quantProd && linha != null) {
				valores = linha.split(csvDivisor);
				String numProduto = valores[0];
				double volume = Double.parseDouble(valores[1]);
				double peso = Double.parseDouble(valores[2]);
				double valor = Double.parseDouble(valores[3]);
				Produto produto = new Produto(numProduto, volume, peso, valor, 1);
				listaProdutos.add(produto);
				
				linha = br.readLine();
				i++;
			}
			return listaProdutos;
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
