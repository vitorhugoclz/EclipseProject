package containersProjeto1;

public class Produto implements Comparable<Produto>{
	private String numProduto;
	private double volume;
	private double peso;
	private double valor;
	private int quantidade;
	public Produto(String numProduto, double volume, double peso, 
			double valor, int quantidade) {
		this.numProduto = numProduto;
		this.volume = volume;
		this.peso = peso;
		this.valor = valor;	
		this.quantidade = quantidade;
	}
	
	public String getNumProduto() {
		return numProduto;
	}
	
	public double getVolume() {
		return volume;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public double getValor() {
		return valor;
	}
	
	public boolean equals(Object obj) {
		boolean flag = false;
		if(obj instanceof Produto) {
			obj = (Produto) obj;
			flag = this.numProduto.equals(((Produto) obj).numProduto);
		}
		return flag;
	}

	@Override
	public int compareTo(Produto obj) {
		if(this.peso > obj.peso)
			return 1;
		else if(this.peso < obj.peso)
			return -1;  
		return 0;
	}
}
