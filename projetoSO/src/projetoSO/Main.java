package projetoSO;
import java.io.IOException;
import java.util.*;
public class Main {
	/*Luiz Gustavo Akazawa 2018.1.08.025
	 *Vtir Hugo da Costa Luz 2018.1.08.023*/
	public static void main(String[] args) throws InterruptedException, IOException {
		while(true) {
			System.out.println("Cada execucao sera separada \n nao havendo relacao entre elas");
			System.out.println("---------------");
			System.out.println("|[1] FirstFit |");
			System.out.println("|[2] NextFit  |");
			System.out.println("|[3] BestFit  |");
			System.out.println("|[4] WorstFit |");
			System.out.println("|[5] Sair     |");
			System.out.println("---------------");
			Scanner ler = new Scanner(System.in);
			int opcao = ler.nextInt();
			if(opcao == 1) {
				Memoria memoria = new Memoria(1024);
				System.out.println("Execucao do Algoritmo FirstFit");
				AlgoritmoInsercao algoritmo = new FirstFit();
				memoria.executar(algoritmo);
				System.out.println("Fim execucao\n\n");
			} else if(opcao == 2) {
				Memoria memoria = new Memoria(1024);
				System.out.println("Execucao do Algoritmo NextFit");
				AlgoritmoInsercao algoritmo = new NextFit();
				memoria.executar(algoritmo);
				System.out.println("Fim execucao\n\n");
			} else if(opcao == 3) {
				Memoria memoria = new Memoria(1024);
				System.out.println("Execucao do Algoritmo BestFit");
				AlgoritmoInsercao algoritmo = new BestFit();
				memoria.executar(algoritmo);
				System.out.println("Fim execucao\n\n");
			} else if(opcao == 4) {
				Memoria memoria = new Memoria(1024);
				System.out.println("Execucao do Algoritmo WorstFit");
				AlgoritmoInsercao algoritmo = new WorstFit();
				memoria.executar(algoritmo);
				System.out.println("Fim execucao\n\n");
			} else if(opcao == 5) {
				ler.close();
				System.out.println("Fechando");
				break;
			} else
				System.out.println("Opcao Invalida");
		}
	}
}
