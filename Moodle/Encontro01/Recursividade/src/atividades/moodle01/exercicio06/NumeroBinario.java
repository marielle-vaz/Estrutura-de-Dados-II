package atividades.moodle01.exercicio06;

import javax.swing.JOptionPane;

public class NumeroBinario {
    public static void main(String[] args) {
        long numero = Long.parseLong(JOptionPane.showInputDialog("Informe um número"));
        System.out.println("Número " + numero + " em binário: " + converterParaBinario(numero));
    }

    public static String converterParaBinario(long n) {
        if (n == 0) return "0";
        else if (n == 1) return "1";
        return converterParaBinario(n/2) + (n%2);
    }
}