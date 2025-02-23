package atividades.moodle01.exercicio01;

import javax.swing.JOptionPane;

public class Fatorial {

	public static void main(String[] args) {
		long num = Long.parseLong(JOptionPane.showInputDialog("Informe um número"));
		JOptionPane.showMessageDialog(null, "Fatorial: " + calcularFatorial(num));
	}

	public static long calcularFatorial(long numero){
		if(numero == 0) return 0;
		else if(numero == 1) return 1;
		else{
			return numero*calcularFatorial(numero-1);
		}
	}

}
