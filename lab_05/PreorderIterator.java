import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class PreorderIterator<E> implements Iterator<E> {

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

    private Stack<Node<E>> stack;

    public PreorderIterator(Node<E> root) {
        stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Node<E> current = stack.pop();
        if (current.right != null) {
            stack.push(current.right);
        }
        if (current.left != null) {
            stack.push(current.left);
        }

        return current.elem;
    }
}