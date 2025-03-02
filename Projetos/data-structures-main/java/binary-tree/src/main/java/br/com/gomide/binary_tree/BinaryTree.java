package br.com.gomide.binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

  private Node<T> root;

  @Override
  public Node<T> createTree(T element) {
    root = new Node<>();
    root.setValue(element);
    return root;
  }

  @Override
  public Node<T> createTree(T[] elements) {
    if (elements.length == 0) return null;
        root = new Node<>();
        root.setValue(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            insert(root, elements[i]);
        }
        return root;
  }

  @Override
  public Integer degree(Node<T> rootNode, T nodeElement) {
    Node<T> node = getByElement(rootNode, nodeElement);
    if (node == null) return -1;
    int degree = 0;
    if (node.getLeft() != null) degree++;
    if (node.getRight() != null) degree++;
    return degree;
  }

  @Override
  public void insert(Node<T> rootNode, T element) {
    if (rootNode == null) {
      rootNode = new Node<>();
      rootNode.setValue(element);
      return;
    }
    if (element.compareTo(rootNode.getValue()) < 0) {
        if (rootNode.getLeft() == null) {
            Node<T> newNode = new Node<>();
            newNode.setValue(element);
            rootNode.setLeft(newNode);
        } else insert(rootNode.getLeft(), element);
    } else {
        if (rootNode.getRight() == null) {
            Node<T> newNode = new Node<>();
            newNode.setValue(element);
            rootNode.setRight(newNode);
        } else insert(rootNode.getRight(), element);
    }
  }

  @Override
  public boolean remove(Node<T> rootNode, T nodeElement) {
    if (rootNode == null) return false;
    Node<T> parent = null;
    Node<T> current = rootNode;

    while (current != null && !current.getValue().equals(nodeElement)) {
      parent = current;
      if (nodeElement.compareTo(current.getValue()) < 0) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }

    if (current == null) return false;

    if (current.getLeft() == null && current.getRight() == null) {
      if (parent == null) root = null;
      else if (parent.getLeft() == current) parent.setLeft(null);
      else parent.setRight(null);
    } else if (current.getLeft() == null) {
      if (parent == null) root = current.getRight();
      else if (parent.getLeft() == current) parent.setLeft(current.getRight());
      else parent.setRight(current.getRight());
    } else if (current.getRight() == null) {
      if (parent == null) root = current.getLeft();
      else if (parent.getLeft() == current) parent.setLeft(current.getLeft());
      else parent.setRight(current.getLeft());
    } else {
      Node<T> successor = current.getRight();
      Node<T> successorParent = current;
      while (successor.getLeft() != null) {
        successorParent = successor;
        successor = successor.getLeft();
      }
      current.setValue(successor.getValue());
      if (successorParent.getLeft() == successor) successorParent.setLeft(successor.getRight());
    else successorParent.setRight(successor.getRight());
    }
    return true;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || rootNode.getValue().equals(nodeElement)) return null;
    
    if ((rootNode.getLeft() != null && rootNode.getLeft().getValue().equals(nodeElement)) ||
      (rootNode.getRight() != null && rootNode.getRight().getValue().equals(nodeElement))) {
        return rootNode;
    }
    
    Node<T> leftSearch = getFather(rootNode.getLeft(), nodeElement);
    
    return (leftSearch != null) ? leftSearch : getFather(rootNode.getRight(), nodeElement);
  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    Node<T> father = getFather(rootNode, nodeElement);
    if (father == null) return null;
    if (father.getLeft() != null && father.getLeft().getValue().equals(nodeElement)) return father.getRight();
    return father.getLeft();
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    if (rootNode == null || rootNode.getValue().equals(element)) return rootNode;
    return element.compareTo(rootNode.getValue()) < 0 ? getByElement(rootNode.getLeft(), element) : getByElement(rootNode.getRight(), element);
  }

  @Override
  public Integer calculateTreeDepth(Node<T> rootNode) {
    if (rootNode == null) return 0;
    return 1 + Math.max(calculateTreeDepth(rootNode.getLeft()), calculateTreeDepth(rootNode.getRight()));
  }

  @Override
  public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
    int level = 0;
    Node<T> current = rootNode;
    while (current != null) {
      if (current.getValue().equals(nodeElement)) return level;
      level++;
      if (nodeElement.compareTo(current.getValue()) < 0) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }
    return -1;
  }

  @Override
  public String toString(Node<T> rootNode) {
    if (rootNode == null) return "";
    return toString(rootNode.getLeft()) + rootNode.getValue() + " " + toString(rootNode.getRight());
  }

}
