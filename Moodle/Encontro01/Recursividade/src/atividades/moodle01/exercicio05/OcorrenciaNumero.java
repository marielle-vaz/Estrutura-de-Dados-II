package atividades.moodle01.exercicio05;

import javax.swing.JOptionPane;

public class OcorrenciaNumero {
    public static void main(String[] args) {
        long numero = Long.parseLong(JOptionPane.showInputDialog("Informe uma sequência de números"));
        long digito = Long.parseLong(JOptionPane.showInputDialog("Qual dígito deseja buscar?"));

        System.out.println("O dígito " + digito + " ocorre " + procurarDigito(numero, digito) + " vezes em " + numero);
    }

    public static long procurarDigito(long n, long k){
        if(n == 0) return 0;
        return (n%10 == k?1:0) + procurarDigito(n/10, k);
    }
}
