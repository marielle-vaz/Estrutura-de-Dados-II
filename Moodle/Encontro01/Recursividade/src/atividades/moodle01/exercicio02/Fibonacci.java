package atividades.moodle01.exercicio02;

import javax.swing.JOptionPane;

public class Fibonacci {

	public static void main(String[] args) {
		long p = Long.parseLong(JOptionPane.showInputDialog("Informe a posição da sequência de Fibonacci"));
		System.out.println("Fibonacci: " + calcularFibonacci(p));
	}

	public static long calcularFibonacci(long posicao){
		if((posicao == 0) || (posicao == 1)) return 0;
		else if(posicao == 2) return 1;
		else{
			return calcularFibonacci(posicao-1) + calcularFibonacci(posicao-2);
		}
	}

}
