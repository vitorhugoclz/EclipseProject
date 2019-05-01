package caixeiro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class caixeiroViajante2 {
	static int[][] matriz = new int[58][58];
	static  ArrayList<Integer> disponiveis = new ArrayList<Integer>();
	static ArrayList<Integer> output = new ArrayList<Integer>();
	public static void main(String[] args) throws FileNotFoundException {
		lerArquivo();
		criaDisponiveis();
		vizinho();
		calculaScore();
	}
	public static void vizinho() {
		int linha = 0, coluna = 0, remover;
		disponiveis.remove(disponiveis.indexOf(0));
		while(disponiveis.size() != 0) {
			coluna = buscaMenor(matriz[linha]); //busca menor disponivel na linha
			output.add(coluna);//coloca o valor na saida
			
			//remove valor colocado dos disponiveis
			remover = disponiveis.indexOf(coluna);
			disponiveis.remove(remover);
			
			//faz as trocas de valores
			linha = coluna;		
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
		for(i = 0;i < output.size() - 1;i++) {
			System.out.print(output.get(i) + " ");
			score += matriz[output.get(i)][output.get(i + 1)];
		}
		System.out.print(output.get(i));
		score += matriz[output.get(i)][output.get(0)];
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
}
