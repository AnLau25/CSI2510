/*
 * doubly linked list of integers 
 * (with dummy element)
 *
 * CSI2510 Algortihmes et Structures de Donnees
 * www.uottawa.ca
 *
 * Robert Laganiere, 2017
 *
*/
public class DoublyLinkedListGen <T>{

    private class Node<T>{

        public T element;
        public Node<T> prev;
        public Node<T> next;

        public Node(T v, Node p, Node n) {
            element = v;
            prev = p;
            next = n;
        }
    }

    private Node<T> head;
    private int size;

    // construct empty list
    public DoublyLinkedList() {
        head = new Node<T>(null, null, null);
        head.prev = head.next = head;
        size = 0;
    }

    // get the list size
    public int getSize() {
        return size;
    }

    // add a new integer (most efficient)
    public boolean add(T value) {
        Node<T> newNode = new Node<T>(value, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;

        size++;

        return true;
    }

    // add a new integer (least efficient)
    public boolean addOppositeSide(T value) {
        Node<T> newNode = new Node<T>(value, head.prev, head);
        head.prev.next = newNode;
        head.prev = newNode;

        size++;

        return true;
    }

    // search a node in the list, given an element.
    // Returns -1 if it's not in the list
    public T search(T value) {

        Node<T> n = head.next;

        while (n != head && !n.element.equals(value)) {
            n = n.next;
        }

        if (n == head) {
            return null;
        }

        return n.element;
    }

    // remove a given integer (first occurrence of)
    public boolean searchAndRemove(T value) {

        Node<T> n = head.next;

        while (n != head && !n.element.equals(value)) {
            n = n.next;
        }

        if (n != head) {

            n.prev.next = n.next;
            n.next.prev = n.prev;
            size--;
            return true;

        } else {

            return false;
        }
    }

    // remove element at a given index
    public boolean removeAt(int index) {

        if (index < 0 || index >= size) {
            return false;
        }

        size--;

        Node<T> n = head.next;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }

        n.prev.next = n.next;
        n.next.prev = n.prev;

        return true;
    }

    // return the first element of the list
    public T getFirst() {
        return head.next.element;
    }

    // string representation
    public String toString() {

        StringBuffer s = new StringBuffer("");

        for (Node<T> node = head.next; node != head; node = node.next) {
            s.append("[" + node.element + "]");
        }

        return s.toString();
    }
}
