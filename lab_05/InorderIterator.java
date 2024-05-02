import java.util.Iterator;
import java.util.NoSuchElementException;

public class InorderIterator<E> implements Iterator<E> {

    private class Node<E> {
        E elem;
        Node<E> left;
        Node<E> right;
    
        public Node(E elem) {
            this.elem = elem;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> current;
    private Node<E> predecessor;

    public InorderIterator(Node<E> root) {
        current = root;
        predecessor = null;
    }

    public boolean hasNext() {
        return current != null;
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        E result = null;

        while (current != null) {
            if (current.left == null) {
                result = current.elem;
                current = current.right;
                break;
            } else {
                predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    result = current.elem;
                    predecessor.right = null;
                    current = current.right;
                    break;
                }
            }
        }

        return result;
    }

    public void remove() {
        // Not implemented
    }
}
