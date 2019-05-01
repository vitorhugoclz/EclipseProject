package caixeiro;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class CaixeiroViajante {
	static int[][] matriz = new int[58][58];
	static  ArrayList<Integer> disponiveis = new ArrayList<Integer>();
	static int[] output = new int[matriz.length];
	public static void main(String[] args) throws FileNotFoundException {
		lerArquivo();
		criaDisponiveis();
		vizinho();
		calculaScore();
	}
	public static void vizinho() {
		int linha = 0, coluna = 1, indice = 1, remover, size;
		disponiveis.remove(disponiveis.indexOf(0));
		while(disponiveis.size() != 0) {
			coluna = buscaMenor(matriz[linha]); //busca menor disponivel na linha
			output[indice] = coluna; //coloca o valor na saida
			
			//remove valor colocado dos disponiveis
			size = disponiveis.size();
			remover = disponiveis.indexOf(coluna);
			disponiveis.remove(remover);
			
			//faz as trocas de valores
			linha = coluna;
			indice++;			
		}
	}
	public static int buscaMenor(int[] lista) {
		int indice = 0, menor = 99999999;
		for(int i = 0; i < disponiveis.size(); i++) {
			if(lista[disponiveis.get(i)] < menor) {
				menor = lista[disponiveis.get(i)];
				indice = disponiveis.get(i);
			}
		}
		return indice;
	}
	public static void calculaScore() {
		int score = 0, i;
		for(i = 0;i < output.length - 1;i++) {
			System.out.print(output[i] + " ");
			score += matriz[output[i]][output[i + 1]];
		}
		System.out.print(output[i]);
		score += matriz[output[i]][output[0]];
		System.out.println("\n" + score);
	}
	public static void criaDisponiveis() {
		for(int i = 0;i < matriz.length; i++)
			disponiveis.add(i);
	}
	
	public static void lerArquivo()  throws FileNotFoundException{
		 File data = new File("C:\\Users\\Vitor\\eclipse-workspace\\EclipseProject\\CaixeiroViajante6x6\\src\\caixeiro\\cidades.txt");
	     int valor = 0, i, j;   
		 try (Scanner leitor = new Scanner(data)) {
	            for (i = 0; i < matriz.length; i++) {
	                for (j = 1 + i; j < matriz[i].length; j++) {
	                	valor = leitor.nextInt();
	                    matriz[i][j] = valor;
	                    matriz[j][i] = valor;
	                }
	            }
	            leitor.close();
	        }
	}
	public static void exibeMatriz() {
		for(int i = 0; i < matriz.length;i++) {
			for(int j = 0; j < matriz[i].length;j++)
				System.out.print(matriz[i][j] + " ");
			System.out.println();
		}
	}
}	
