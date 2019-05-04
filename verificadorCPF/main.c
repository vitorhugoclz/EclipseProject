/*
 * main.c
 *
 *  Created on: 3 de mai de 2019
 *      Author: Vitor
 */

#include <stdio.h>
#include <stdlib.h>
#define MARCA 9999999999
#define TAM 11
#define TRUE 1
#define FALSE 0
int vetorCPF[TAM];

void criarVetor(char cpfEntrada[TAM + 1]) {
	int i, numASCII;
	for(i = 0;i < TAM; i++) {
		numASCII = (int) cpfEntrada[i]; //recebe o valor referente a tabela ASCII
		vetorCPF[i] = numASCII - 48;
	}
}
int testarCPF() {
	int vetorV[TAM - 2] = {10, 9, 8, 7, 6, 5, 4, 3, 2};
	int vetorW[TAM - 1] ={11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	int soma = 0, i, resultado, digitosVerificadores[2], flag = FALSE;

	for(i = 0;i < TAM -2;i++)
		//calcula a soma para o primeiro caso gerando o primeiro digito verificador
		soma += vetorCPF[i]*vetorV[i];

	resultado = soma % 11;
	if(resultado < 2)
		digitosVerificadores[0] = 0;
	else
		digitosVerificadores[0] = 11 - resultado;
	soma = 0;
	for(i = 0;i < TAM - 1;i++)
		//calcula a soma para o primeiro caso gerando o segundo digito verificador
		soma += vetorW[i] * vetorCPF[i];

	resultado = soma % 11;
	if(resultado < 2)
		digitosVerificadores[1] = 0;
	else
		digitosVerificadores[1] = 11 - resultado;
	if(digitosVerificadores[0] == vetorCPF[9] && digitosVerificadores[1] == vetorCPF[10])
		flag = TRUE;
	printf("Os digitos verificadores encontrado: ");
	printf("%d %d\n", digitosVerificadores[0], digitosVerificadores[1]);
	return flag;
}
void buscaEstado() {
	printf("Estado de origem do documento passado: ");
	switch(vetorCPF[8]) {
		case 1: printf("RS\n");
				break;
		case 2: printf("DF, GO, MT, MS, TO\n");
						break;
		case 3: printf("AM, PA, RR, AP, AC, RO\n");
						break;
		case 4: printf("PB, PE, AL, RN\n");
						break;
		case 5: printf("BA, SE\n");
						break;
		case 6: printf("MG\n");
						break;
		case 7: printf("RJ, ES\n");
						break;
		case 8: printf("SP\n");
						break;
		case 9: printf("PR, SC\n");
						break;

	}
}
int main(void) {
	int opcao, i, flag;
	char cpfEntrada[TAM + 1];

	do {
		printf("Menu\n");
		printf("[1] Testar CPF\n");
		printf("[2] Sair\n");
		scanf(" %d", &opcao);

		switch(opcao) {
			case 1: printf("Digite o CPF da forma XXXXXXXXXXX\n");
					scanf(" %s", &cpfEntrada);
					criarVetor(cpfEntrada);
					flag = testarCPF();
					for(i = 0;i < TAM; i++) {
						//imprime o vetor que foi passado no inicio
						if(i % 3 == 0 && i != 0 && i != 9)
							printf(".");
						if(i == 9)
							printf("-");
						printf("%d", vetorCPF[i]);
					}
					if(flag) printf("\nO CPF passado e valido\n");
					else printf("\nO CPF passado e invalido\n");
					buscaEstado();
					break;
			case 2: printf("Saindo...");
				break;
			default: break;
		}
	}while(opcao != 2);

	return 0;
}
