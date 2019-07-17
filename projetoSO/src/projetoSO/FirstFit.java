package projetoSO;
import java.util.*;
public class FirstFit implements AlgoritmoInsercao{
	@Override
	public Bloco execucao(Processo processo, Processo[] memoria) {
		Bloco bloco = null;
		int i = 0, contador = 0, inicio = -1;
		for(i = 0;i < memoria.length; i++) {
			if(memoria[i] == null && contador < processo.getTamanho()) {
				if(inicio == -1)
					inicio = i;
				contador++;
			} else if(memoria[i] == null && contador >= processo.getTamanho())
				break;
			else {
				inicio = -1;
				contador = 0;
			}
		}
		if(contador >= processo.getNumero() && i < memoria.length) {
			bloco = new Bloco(inicio, inicio + processo.getTamanho());
		}
		return bloco;
	}
}
