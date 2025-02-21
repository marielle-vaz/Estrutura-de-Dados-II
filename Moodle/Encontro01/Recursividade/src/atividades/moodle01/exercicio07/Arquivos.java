package atividades.moodle01.exercicio07;

import java.io.File;

import javax.swing.JOptionPane;

public class Arquivos {
    public static void main(String[] args) {
       /* 
        File folder = new File("C:\\Users\\marie\\Desktop\\Instituto Federal Goiano - Campus Trindade"); // current directory
        File[] listOfFiles = folder.listFiles(); // list all files in the current directory

        for (File file : listOfFiles) {
            System.out.println("Absolute path: " + file.getAbsolutePath()); // absolute path of the file
            System.out.println("Name: " + file.getName()); // name of the file
            System.out.println("Is directory: " + file.isDirectory()); // true if the file is a directory
            System.out.println("Is file: " + file.isFile()); // true if the file is a regular file
            System.out.println("");
        }*/ 

        String pasta = JOptionPane.showInputDialog("Informe a raíz da sua estrutura de pastas (Exemplo: C:\\Users\\marie\\Desktop)"); // Diretório atual
        String nomeArq = JOptionPane.showInputDialog("Informe o nome e a extensão do arquivo (Exemplo: AtividadeEstrutura.pdf)");
        
        boolean encontrado = buscarArquivo(pasta, nomeArq);
        System.out.println("Arquivo encontrado: " + encontrado);

        if(encontrado){
            JOptionPane.showMessageDialog(null, "Encontrei " + qtde + " arquivo(s)!", "ENCONTRADO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Não encontrei nenhum arquivo!", "NÃO ENCONTRADO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    static int qtde = 0;

    private static boolean buscarArquivo(String caminhoOrigem, String nomeArquivo) {
        File arq = new File(caminhoOrigem);
        
        if (!arq.exists() || !arq.isDirectory()) {
            JOptionPane.showMessageDialog(null, "Arquivo ou pasta informada inexistente!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        File[] arquivos = arq.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().equals(nomeArquivo)) {
                    qtde++;
                    return true;
                } else if (arquivo.isDirectory() && buscarArquivo(arquivo.getAbsolutePath(), nomeArquivo)) {
                    qtde++;
                    return true;
                }
            }
        }
        return false;
    }
    
}
