package projetoSO;

import java.util.*;

public class BestFit implements AlgoritmoInsercao {

	@Override
	public Bloco execucao(Processo processo, Processo[] memoria) {
		List<Bloco> listaBlocos = new ArrayList<>();
		for(int i = 0;i < memoria.length; i++) {
			if(memoria[i] == null) {
				int cont = 0;
				int inicio = i;
				while(i < memoria.length && memoria[i] == null) {
					i++;
					cont++;
				}
				if(cont >= processo.getTamanho())
					listaBlocos.add(new Bloco(inicio, inicio + cont));
			}
		}
		Bloco menorBloco = null;
		for(int i = 0;i < listaBlocos.size(); i++) {
			Bloco bloco = listaBlocos.get(i);
			if(menorBloco == null)
				menorBloco = bloco;
			else if(bloco.getTamanho() < menorBloco.getTamanho())
				menorBloco = bloco;
		}
		return menorBloco;
	}

}
