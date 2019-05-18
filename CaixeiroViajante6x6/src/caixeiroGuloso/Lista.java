package caixeiroGuloso;

public class Lista {
	No inicio;
	int tamanho;

	public void insere(No no) {
		if (this.inicio == null) {
			inicio = no;
			tamanho++;
		} else {
			No aux = this.inicio;
			while (aux.prox != null)
				aux = aux.prox;
			aux.prox = no;
			no.ant = aux;
		}
		this.tamanho++;
		if(this.tamanho > 57)
			this.tamanho = 57;
	}

	public void inserePosicao(int indice, No no) {
		if (indice < this.tamanho) {
			if (indice == 0) {
				this.inicio.ant = no;
				no.prox = this.inicio;
				this.inicio = no;
			} else if (indice == this.tamanho - 1) {
				this.insere(no);
			} else {
				No aux = this.inicio;
				int i = 0;
				while (i < indice) {
					aux = aux.prox;
					i++;
				}
				no.ant = aux;
				no.prox = aux.prox;
				aux.prox = no;
				no.prox.ant = no;
			}
			this.tamanho++;
			if(this.tamanho > 57)
				this.tamanho = 57;
		}
	}

	public No remove(int indice) {
		No removido = this.inicio;
		if (indice < this.tamanho) {
			if (indice == 0) {
				this.inicio = this.inicio.prox;
				this.inicio.ant = null;
			} 
			else if(indice == this.tamanho - 1) {
				int i = 0;
				while(i < indice) {
					removido = removido.prox;
					i++;
				}
				removido.ant.prox = null;
			}else {
				int i = 0;
				while(i < indice) {
					removido = removido.prox;
					i++;
				}	
				removido.ant.prox = removido.prox;
				removido.prox.ant = removido.ant;
			}
			removido.ant = null;
			removido.prox = null;
			this.tamanho--;
			return removido;
		}
		return null;
	}
	public int[] converterListaVetor(int tamanho) {
		int[] vetor = new int[tamanho];
		No aux = this.inicio;
		for(int i = 0;i < tamanho && aux != null;i++) {
			vetor[i] = aux.valor;
			aux = aux.prox;
		}
		return vetor;
	}
}
