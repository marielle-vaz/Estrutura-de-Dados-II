package atividades.moodle01.exercicio07;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\marie\\Desktop\\Instituto Federal Goiano - Campus Trindade"); // current directory

        File[] listOfFiles = folder.listFiles(); // list all files in the current directory

        for (File file : listOfFiles) {
            System.out.println("Absolute path: " + file.getAbsolutePath()); // absolute path of the file
            System.out.println("Name: " + file.getName()); // name of the file
            System.out.println("Is directory: " + file.isDirectory()); // true if the file is a directory
            System.out.println("Is file: " + file.isFile()); // true if the file is a regular file
            System.out.println("");
        }
    }
}
