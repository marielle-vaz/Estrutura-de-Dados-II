package atividades.moodle01.exercicio03;

public class SomaRecursiva {

	public static void main(String[] args) {
		int vetor[] = {1,2,3,4,5,6,7,8,9,10};
		System.out.println("Soma: " + somarElementos(vetor, vetor.length));
	}

	public static int somarElementos(int[] vetor, int tamanho){
		if (tamanho == 0) return 0;

		return vetor[tamanho-1] + somarElementos(vetor, tamanho-1);
	}

}
