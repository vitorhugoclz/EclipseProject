package projetoSO;

import java.util.ArrayList;
import java.util.List;

public class WorstFit implements AlgoritmoInsercao{

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
		Bloco maiorBloco = null;
		for(int i = 0;i < listaBlocos.size(); i++) {
			Bloco bloco = listaBlocos.get(i);
			if(maiorBloco == null)
				maiorBloco = bloco;
			else if(bloco.getTamanho() > maiorBloco.getTamanho())
				maiorBloco = bloco;
		}
		return maiorBloco;
	}
}


