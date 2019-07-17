package projetoSO;

import java.util.Random;

public class Memoria {
	private TabelaProcesso tabelaProcessos;
	private Processo[] memoria;
	private int numeroProcessos;
	public Memoria(int tamanhoMaximo) {
		this.tabelaProcessos = new TabelaProcesso(tamanhoMaximo);
		this.memoria = new Processo[tamanhoMaximo];
	}

	public void executar(AlgoritmoInsercao algoritmo) {
		for (int i = 0; i < 10; i++) {
			Processo processo = gerarProcesso();
			Bloco bloco = algoritmo.execucao(processo, memoria);
			if (bloco != null) {
				inserirMemoria(bloco, processo);
				tabelaProcessos.addProcesso(processo);
				saidaTexto("Processo Inserido", processo);
			} else {
				//remove um processo da memoria
				tabelaProcessos.addEspera(processo);
				System.out.println("P[" + processo.getNumero() +"]: " + processo.getTamanho() + " adicionado a espera");
				Processo removido = tabelaProcessos.removerProcesso();
				limparProcesso(removido);
				saidaTexto("Processo Removido", removido);
				
				//tenta colocar um processo em Espera na memoria
				Processo emEspera = tabelaProcessos.removerEspera();
				Bloco bloco2 = algoritmo.execucao(emEspera, memoria);
				if (bloco2 != null) {
					inserirMemoria(bloco2, processo);
					tabelaProcessos.addProcesso(emEspera);
					saidaTexto("Processo Inserido", emEspera);
				} else {
					System.out.println("Processo em espera nao coube");
					System.out.println("P[" + emEspera.getNumero() + "]:" + emEspera.getTamanho());
					tabelaProcessos.addEspera(emEspera);
				}
			}
		}
	}

	private void limparProcesso(Processo processo) {
		for (int i = 0; i < this.memoria.length; i++) {
			if (this.memoria[i] != null && this.memoria[i].equals(processo))
				memoria[i] = null;
		}
	}

	private void inserirMemoria(Bloco bloco, Processo processo) {
		for (int i = bloco.getInicio(), cont = 0; i < bloco.getFim() && cont < processo.getTamanho(); i++, cont++) {
			memoria[i] = processo;
		}
	}

	private Processo gerarProcesso() {
		Random random = new Random();
		int[] vetorTamanhos = {(memoria.length / 4), (memoria.length / 5), (memoria.length / 8),
				(memoria.length / 10), };
		int posicao = random.nextInt(vetorTamanhos.length);
		Processo processo = new Processo(vetorTamanhos[posicao], numeroProcessos);
		numeroProcessos++;
		return processo;
	}

	private void saidaTexto(String texto, Processo processo) {
		System.out.println(texto + " ----> " + processo);
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] != null) {
				Processo processo2 = memoria[i];
				System.out.print("(P[" + processo2.getNumero() + "]: " + processo2.getTamanho() + ") ");
				i += processo2.getTamanho() -1;
			} else {
				int cont = 1;
				i++;
				while (i < memoria.length && memoria[i] == null) {
					cont++;
					i++;
				}
				System.out.print("(Livre: " + cont + ") ");
				i--;
			}
		}
		System.out.println();
	}
}
