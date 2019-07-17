package projetoSO;

public class Bloco {
	private int inicio;
	private int fim;
	private int tamanho;
	public Bloco(int inicio, int fim) {
		this.inicio = inicio;
		this.fim = fim;
		this.tamanho = this.fim - this.inicio;
	}
	public int getInicio() {
		return inicio;
	}
	public int getFim() {
		return fim;
	}
	public int getTamanho() {
		return tamanho;
	}
}
