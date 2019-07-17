package projetoSO;

import java.util.*;

public class TabelaProcesso {
	private int tamanhoAtual;
	private int tamanhoMaximo;
	private List<Processo> listaProcessos = new ArrayList<>();
	private List<Processo> processosEspera = new ArrayList<>();
	
	public TabelaProcesso(int tamanhoMaximo) {
		this.tamanhoMaximo = tamanhoMaximo;
	}

	public boolean addProcesso(Processo processo) {
		boolean flag = false;
		if (processo.getTamanho() + this.tamanhoAtual < this.tamanhoMaximo - 1) {
			flag = true;
			this.tamanhoAtual += processo.getTamanho();		
			this.listaProcessos.add(processo);
		}
		return flag;
	}
	public Processo removerProcesso() {
		Processo removido = null;
		Random random = new Random();
		if(listaProcessos.size() > 0) {
			int indice = random.nextInt(listaProcessos.size());
			removido = listaProcessos.get(indice);
			listaProcessos.remove(indice);
			this.tamanhoAtual -= removido.getTamanho();
		}
		return removido;
	}
	public int getTamanhoAtual() {
		return tamanhoAtual;
	}

	public int getTamanhoMaximo() {
		return tamanhoMaximo;
	}

	public List<Processo> getListaProcessos() {
		return listaProcessos;
	}
	public void addEspera(Processo processo) {
		this.processosEspera.add(processo);
	}
	public Processo removerEspera() {
		Processo processo = null;
		if(this.processosEspera.size() > 0)
			processo = this.processosEspera.remove(0);
		return processo;
	}
}
