package src.teste;

public class QuadradoMagico {
	public static void main(String[] args) {
		int[][] matriz1 = {{4,9,2},
				   {3,5,7},
				   {8,1,6}};
		int[][] matriz2 = {{3,9,2},
				   {3,5,7},
				   {8,1,6}};
		quadradoMagico(matriz1);
		quadradoMagico(matriz2);
	}
	public static void quadradoMagico(int[][] matriz) {
		boolean teste = true;
		int linhas = matriz.length, colunas = matriz.length;
		int[] valores = new int[linhas + colunas];
		int k = 0;
		for(int i = 0;i < linhas; i++) {
			int soma = 0;
			for(int j = 0;j < colunas;j++) {
				soma = soma + matriz[i][j];
			}
			valores[k] = soma;
			k++;
		}
		for(int i = 0;i < colunas; i++) {
			int soma = 0;
			for(int j = 0;j < linhas; j++) {
				soma = soma + matriz[j][i];
			}
			valores[k] = soma;
			k++;
		}
		for(int i = 0;i < (linhas + colunas) - 1; i++) {
			if(valores[i] != valores[i + 1]) {
				teste = false;
				break; //comando para para loop
			}
		}
		if(teste)
			System.out.println("É um quadrado Mágico");
		else
			System.out.println("Não é um quadrado Mágico");
	}
}
