package br.com.gomide.binary_tree;

public class Main {
    public static void main(String[] args) {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[] { 37, 20, 10, 30, 80, 100, 5, 180, 90 };
    
        Node<Integer> rootNode = binaryTreeOps.createTree(elements);
        
        System.out.println(binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 180);
        System.out.println(binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 80);
        System.out.println(binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 10);
        System.out.println(binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 20);
        System.out.println(binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 37);
        System.out.println(binaryTreeOps.toString(rootNode));

    }
}
