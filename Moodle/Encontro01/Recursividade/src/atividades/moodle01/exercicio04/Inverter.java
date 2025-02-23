package atividades.moodle01.exercicio04;

import javax.swing.JOptionPane;

public class Inverter {
    public static void main(String[] args) {
        long numero = Long.parseLong(JOptionPane.showInputDialog("Informe um número para ser invertido"));
        JOptionPane.showMessageDialog(null,"Número " + numero + " invertido: " + inverterNumero(numero));
    }
 
    public static long inverterNumero(long num) {
        return inverterNumeroRecursivo(num, 0);
    }

    private static long inverterNumeroRecursivo(long n, long resultado) {
        if (n == 0) return resultado;        
        return inverterNumeroRecursivo(n/10, resultado*10 + n%10);
    }
}
