package containersProjeto1;

import java.util.*;

public class Container {
	private double pesoMaximo;
	private double volumeMaximo;
	
	private double pesoAtual;
	private double volumeAtual;
	private double valorTotal;
	private List<Produto> listaProduto;
	
	public Container(double pesoTotal, double volumeTotal) {
		this.pesoMaximo = pesoTotal;
		this.volumeMaximo = volumeTotal;
		this.listaProduto = new ArrayList<>();
	}
	public boolean inserirProduto(Produto novoProduto) {
		if(this.pesoAtual + novoProduto.getPeso() > this.pesoMaximo ||
				this.volumeAtual + novoProduto.getVolume() > this.volumeMaximo)
			return false;
		this.volumeAtual += novoProduto.getVolume();
		this.pesoAtual += novoProduto.getPeso();
		
		this.listaProduto.add(novoProduto);
		this.valorTotal += novoProduto.getValor();
		return true;
	}
	public double getPesoMaximo() {
		return pesoMaximo;
	}
	public double getVolumeMaximo() {
		return volumeMaximo;
	}
	public double getPesoAtual() {
		return pesoAtual;
	}
	public double getVolumeAtual() {
		return volumeAtual;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public List<Produto> getListaProduto() {
		return listaProduto;
	}
}
