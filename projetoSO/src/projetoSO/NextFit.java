package projetoSO;

import java.util.*;

public class NextFit implements AlgoritmoInsercao {
	private int posAtual;

	@Override
	public Bloco execucao(Processo processo, Processo[] memoria) {
		Bloco bloco = null;
		int i = posAtual + 1;
		int inicio = -1, contador = 0;
		if (i == memoria.length)
			i = 0;
		if (posAtual == 0) {
			FirstFit firstFit = new FirstFit();
			bloco = firstFit.execucao(processo, memoria);
		} else {
			while (i != posAtual) {
				if (i == memoria.length) {
					inicio = -1;
					contador = 0;
					i = 0;
				}
				if (memoria[i] == null && contador < processo.getTamanho()) {
					if (inicio == -1)
						inicio = i;
					contador++;
				} else if (memoria[i] == null && contador == processo.getTamanho() && inicio != -1) {
					bloco = new Bloco(inicio, inicio + processo.getTamanho());
					break;
				}
				if (memoria[i] != null && contador < processo.getTamanho()) {
					inicio = -1;
					contador = 0;
				}
				i++;
			}
		}
		if(bloco != null) {
			System.out.println("Posição Atual: " + posAtual);
			this.posAtual = bloco.getFim() - 1;
		}
		return bloco;
	}
}
