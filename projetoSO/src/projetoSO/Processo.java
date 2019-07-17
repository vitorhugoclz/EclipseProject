package projetoSO;

public class Processo {
	private int tamanho;
	private int numero;
	public Processo(int tamanho, int numero) {
		this.tamanho = tamanho;
		this.numero = numero;
	}
	public int getTamanho() {
		return tamanho;
	}
	public int getNumero() {
		return numero;
	}
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if(obj instanceof Processo) {
			Processo processo1 = (Processo) obj;
			if(processo1.numero == this.numero && processo1.tamanho == this.tamanho)
				flag = true;
		}
		return flag;
	}
	@Override
	public String toString() {
		return "Processo Numero: "+this.numero + ", tamanho: " + this.tamanho;
	}
}
